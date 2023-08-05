using CinemaReservationAPI.Dto;
using CinemaReservationAPI.Models;
using Dapper;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System.Data.SqlClient;

namespace CinemaReservationAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class AuditoriumController : ControllerBase
    {
        private readonly IConfiguration _config;

        public AuditoriumController(IConfiguration config)
        {
            _config = config;
        }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<Auditorium>>> GetAllAuditoriums()
        {
            using var connection = new SqlConnection(_config.GetConnectionString("DefaultConnection"));
            IEnumerable<Auditorium> auditoriums = await SelectAllAuditoriums(connection);

            return Ok(auditoriums);
        }

        [HttpGet("{auditoriumId}")]
        public async Task<ActionResult<Auditorium>> GetAuditorium(int auditoriumId)
        {
            using var connection = new SqlConnection(_config.GetConnectionString("DefaultConnection"));
            var auditorium = await connection.QueryFirstOrDefaultAsync<Auditorium>("SELECT * FROM auditoriums WHERE Id = @Id", new { Id = auditoriumId });

            if (auditorium == null)
            {
                return NotFound();
            }

            return Ok(auditorium);
        }

        [HttpPost]
        public async Task<ActionResult<IEnumerable<Auditorium>>> CreateAuditorium(AuditoriumDto request)
        {
            using var connection = new SqlConnection(_config.GetConnectionString("DefaultConnection"));
            var parameters = new
            {
                CinemaId = request.CinemaId,
                Label = request.Label
            };

            await connection.ExecuteAsync(@"INSERT INTO auditoriums (CinemaId, Label) 
                                    VALUES (@CinemaId, @Label)", parameters);

            return Ok(await SelectAllAuditoriums(connection));
        }

        [HttpPut("{auditoriumId}")]
        public async Task<ActionResult<IEnumerable<Auditorium>>> UpdateAuditorium(int auditoriumId, AuditoriumDto request)
        {
            using var connection = new SqlConnection(_config.GetConnectionString("DefaultConnection"));
            var parameters = new
            {
                Id = auditoriumId,
                CinemaId = request.CinemaId,
                Label = request.Label
            };

            await connection.ExecuteAsync(@"UPDATE auditoriums SET 
                                    CinemaId = @CinemaId,
                                    Label = @Label
                                    WHERE Id = @Id", parameters);

            return Ok(await SelectAllAuditoriums(connection));
        }

        [HttpDelete("{auditoriumId}")]
        public async Task<ActionResult<Auditorium>> DeleteAuditorium(int auditoriumId)
        {
            using var connection = new SqlConnection(_config.GetConnectionString("DefaultConnection"));
            var auditorium = await connection.QueryFirstOrDefaultAsync<Auditorium>("DELETE FROM auditoriums WHERE Id = @Id", new { Id = auditoriumId });

            if (auditorium == null)
            {
                return NotFound();
            }

            return Ok(auditorium);
        }

        private static async Task<IEnumerable<Auditorium>> SelectAllAuditoriums(SqlConnection connection)
        {
            return await connection.QueryAsync<Auditorium>("SELECT * FROM auditoriums");
        }
    }
}
