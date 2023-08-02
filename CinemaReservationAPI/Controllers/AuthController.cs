using Azure.Core;
using CinemaReservationAPI.Dto;
using CinemaReservationAPI.Models;
using Dapper;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.IdentityModel.Tokens;
using System;
using System.CodeDom.Compiler;
using System.Data.SqlClient;
using System.IdentityModel.Tokens.Jwt;
using System.Reflection.Metadata;
using System.Security.Claims;
using System.Security.Cryptography;

namespace CinemaReservationAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class AuthController : ControllerBase
    {
        private readonly IConfiguration _configuration;
        private readonly CinemaReservationDbContext _dbContext;
        private readonly IUserService _userService;

        public AuthController(IConfiguration configuration, IUserService userService, CinemaReservationDbContext dbContext)
        {
            _configuration = configuration;
            _userService = userService;
            _dbContext = dbContext;
        }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<User>>> GetAllUsers()
        {
            using var connection = new SqlConnection(_configuration.GetConnectionString("DefaultConnection"));
            IEnumerable<User> users = await SelectAllUsers(connection);

            return Ok(users);
        }

        [HttpPost("register")]
        public async Task<ActionResult<User>> Register(UserDto request)
        {
            using var connection = new SqlConnection(_configuration.GetConnectionString("DefaultConnection"));

            IEnumerable<User> existingUsers = await SelectAllUsers(connection);
            if (existingUsers.Any(u => u.Email.Equals(request.Email) || u.Username.Equals(request.Username)))
            {
                return BadRequest("User already exists.");
            }

            CreatePasswordHash(request.Password, out byte[] passwordHash, out byte[] passwordSalt);
            User newUser = new User
            {
                Username = request.Username,
                Email = request.Email,
                Role = "normal",
                PasswordHash = passwordHash,
                PasswordSalt = passwordSalt
            };

            await connection.ExecuteAsync(@"INSERT INTO users (Username, Email, Role, PasswordHash, PasswordSalt, CinemaId, RefreshToken, TokenCreated, TokenExpires) 
                                    VALUES (@Username, @Email, @Role, @PasswordHash, @PasswordSalt, @CinemaId, @RefreshToken, @TokenCreated, @TokenExpires)", newUser);

            return Ok(newUser);
        }

        [HttpPost("login")]
        public async Task<ActionResult<string>> Login(string username, string password)
        {
            using var connection = new SqlConnection(_configuration.GetConnectionString("DefaultConnection"));

            User user = await SelectUserByUsername(connection, username);
            if (user != null && VerifyPasswordHash(password, user.PasswordHash, user.PasswordSalt))
            {
                string token = CreateToken(user);
                var refreshToken = GeneratRefreshToken();
                SetRefreshToken(refreshToken, user); 
                return Ok(token);
            }
            return Unauthorized();
        }
        [HttpPost("refresh-token")]
        public async Task<ActionResult<string>> RefreshToken()
        {
            var refreshToken = Request.Cookies["refreshToken"];

            User user = await GetUserByRefreshToken(refreshToken);

            if (user != null)
            {
                if (user.TokenExpires < DateTime.Now)
                {
                    return Unauthorized("Token expired");
                }

                string token = CreateToken(user);
                var newRefreshToken = GeneratRefreshToken();
                SetRefreshToken(newRefreshToken, user); 

                return Ok(token);
            }

            return Unauthorized("Invalid Refresh Token.");
        }

        private RefreshToken GeneratRefreshToken()
        {
            var refreshToken = new RefreshToken
            {
                Token = Convert.ToBase64String(RandomNumberGenerator.GetBytes(64)),
                Expires = DateTime.Now.AddDays(7),
                Created = DateTime.Now
            };
            return refreshToken;
        }
        private void SetRefreshToken(RefreshToken newRefreshToken, User user)
        {
            var cookieOptions = new CookieOptions
            {
                HttpOnly = true,
                Expires = newRefreshToken.Expires
            };
            Response.Cookies.Append("refreshToken", newRefreshToken.Token, cookieOptions);

            user.RefreshToken = newRefreshToken.Token;
            user.TokenCreated = newRefreshToken.Created;
            user.TokenExpires = newRefreshToken.Expires;

            using var connection = new SqlConnection(_configuration.GetConnectionString("DefaultConnection"));
            connection.Execute(@"UPDATE users SET RefreshToken = @RefreshToken, TokenCreated = @TokenCreated, TokenExpires = @TokenExpires WHERE Id = @UserId",
                new { RefreshToken = user.RefreshToken, TokenCreated = user.TokenCreated, TokenExpires = user.TokenExpires, UserId = user.Id });
        }

        private string CreateToken(User user)
        {
            List<Claim> claims = new List<Claim>
            {
                new Claim(ClaimTypes.Name, user.Username),
                new Claim(ClaimTypes.Role, user.Role)
            };
            var key = new SymmetricSecurityKey(System.Text.Encoding.UTF8.GetBytes(
                _configuration.GetSection("AppSettings:Token").Value));

            var creds = new SigningCredentials(key, SecurityAlgorithms.HmacSha512Signature);

            var token = new JwtSecurityToken(
                claims: claims,
                expires: DateTime.Now.AddDays(1),
                signingCredentials: creds);

            var jwt = new JwtSecurityTokenHandler().WriteToken(token);

            return jwt;
        }
        private void CreatePasswordHash(string password, out byte[] passwordHash, out byte[] passwordSalt)
        {
            using (var hmac = new HMACSHA512())
            {
                passwordSalt = hmac.Key;
                passwordHash = hmac.ComputeHash(System.Text.Encoding.UTF8.GetBytes(password));
            }
        }

        private bool VerifyPasswordHash(string password, byte[] passwordHash, byte[] passwordSalt)
        {
            using (var hmac = new HMACSHA512(passwordSalt))
            {
                var computedHash = hmac.ComputeHash(System.Text.Encoding.UTF8.GetBytes(password));
                return computedHash.SequenceEqual(passwordHash);
            }
        }
        private static async Task<IEnumerable<User>> SelectAllUsers(SqlConnection connection)
        {
            return await connection.QueryAsync<User>("SELECT * FROM users");
        }
        private async Task<User> SelectUserByUsername(SqlConnection connection, string username)
        {
            string query = "SELECT * FROM Users WHERE Username = @Username";
            var parameters = new { Username = username };
            return await connection.QueryFirstOrDefaultAsync<User>(query, parameters);
        }
        private async Task<User> GetUserByRefreshToken(string refreshToken)
        {
            using var connection = new SqlConnection(_configuration.GetConnectionString("DefaultConnection"));
            string query = "SELECT * FROM Users WHERE RefreshToken = @RefreshToken";
            var parameters = new { RefreshToken = refreshToken };
            return await connection.QueryFirstOrDefaultAsync<User>(query, parameters);
        }

    }
}
