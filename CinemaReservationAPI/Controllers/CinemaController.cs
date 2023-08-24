using CinemaReservationAPI.Dto;
using CinemaReservationAPI.Models;
using Dapper;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Cors;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System.Data;
using System.Data.SqlClient;

namespace CinemaReservationAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class CinemaController : ControllerBase
    {
        private readonly IConfiguration _config;

        public CinemaController(IConfiguration config)
        {
            _config = config;
        }
        
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Cinema>>> GetAllCinemas()
        {
            using var connection = new SqlConnection(_config.GetConnectionString("DefaultConnection"));
            IEnumerable<Cinema> cinemas = await SelectAllCinemas(connection);

            return Ok(cinemas);
        }

        [HttpGet("{cinemaId}")]
        public async Task<ActionResult<Cinema>> GetCinema(int cinemaId)
        {
            using var connection = new SqlConnection(_config.GetConnectionString("DefaultConnection"));
            var cinema = await connection.QueryFirstOrDefaultAsync<Cinema>("SELECT * FROM cinemas WHERE Id = @Id", new { Id = cinemaId });

            if (cinema == null)
            {
                return NotFound();
            }

            return Ok(cinema);
        }

        [HttpPost]
        public async Task<ActionResult<IEnumerable<Cinema>>> CreateCinema(CinemaDto request)
        {
            using var connection = new SqlConnection(_config.GetConnectionString("DefaultConnection"));

            var parameters = new
            {
                Name = request.Name,
                Address = request.Address,
                ZipCode = request.ZipCode,  
                Town = request.Town
            };

            await connection.ExecuteAsync(@"INSERT INTO cinemas (Name, Address, ZipCode, Town) 
                                    VALUES (@name, @Address, @ZipCode, @Town)", parameters);

            return Ok(await SelectAllCinemas(connection));
        }

        [HttpPut("{cinemaId}")]
        public async Task<ActionResult<IEnumerable<Cinema>>> UpdateCinema(int cinemaId, CinemaDto request)
        {
            using var connection = new SqlConnection(_config.GetConnectionString("DefaultConnection"));

            var parameters = new
            {
                Id = cinemaId,
                Name = request.Name,
                Address = request.Address,
                ZipCode = request.ZipCode,
                Town = request.Town
            };

            await connection.ExecuteAsync(@"UPDATE cinemas SET 
                                    Name = @Name,
                                    Address = @Address,
                                    ZipCode = @ZipCode,
                                    Town = @Town
                                    WHERE Id = @Id", parameters);

            return Ok(await SelectAllCinemas(connection));
        }

        [HttpDelete("{cinemaId}")]
        public async Task<ActionResult<Cinema>> DeleteCinema(int cinemaId)
        {
            using var connection = new SqlConnection(_config.GetConnectionString("DefaultConnection"));
            var cinema = await connection.QueryFirstOrDefaultAsync<Cinema>("DELETE FROM cinemas WHERE Id = @Id", new { Id = cinemaId });

            if (cinema == null)
            {
                return NotFound();
            }

            return Ok(cinema);
        }

        private static async Task<IEnumerable<Cinema>> SelectAllCinemas(SqlConnection connection)
        {
            return await connection.QueryAsync<Cinema>("SELECT * FROM cinemas");
        }
    }
}
