using CinemaReservationAPI.Models;
using Dapper;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System.Data.SqlClient;

namespace CinemaReservationAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ProjectionController : ControllerBase
    {

        public IConfiguration _config { get; }
        public ProjectionController(IConfiguration config)
        {
            _config = config;
        }
        [HttpGet]
        public async  Task<ActionResult<List<Projection>>>GetAllProjections()
        {
            using var connection = new SqlConnection(_config.GetConnectionString("DefaultConnection"));
            IEnumerable<Projection> projections = await SelectAllProjections(connection);

            return Ok(projections);
        }
        
        [HttpGet("{projectionId}")]
        public async Task<ActionResult<Projection>> GetProjections(SignInResult projectionId)
        {
            using var connection = new SqlConnection(_config.GetConnectionString("DefaultConnection"));
            var projection = await connection.QueryFirstAsync<Projection>("SELECT * FROM projections where id = @Id", new { Id = projectionId});

            return Ok(projection);
        }

        [HttpPost]
        public async Task<ActionResult<List<Projection>>> CreateProjection(Projection projection)
        {
            using var connection = new SqlConnection(_config.GetConnectionString("DefaultConnection"));
            await connection.ExecuteAsync(@"INSERT INTO projections (CinemaId, AuditoriumId, Name, Genre, Date, Day, Image, Director, Year, Duration, Description, Price) 
          VALUES (@CinemaId, @AuditoriumId, @Name, @Genre, @Date, @Day, @Image, @Director, @Year, @Duration, @Description, @Price)",
        projection);

            return Ok(await SelectAllProjections(connection));
        }
        [HttpPut("{projectionId}")]
        public async Task<ActionResult<List<Projection>>> UpdateProjection(int projectionId, Projection updatedProjection)
        {
            using var connection = new SqlConnection(_config.GetConnectionString("DefaultConnection"));
            var parameters = new
            {
                Id = projectionId,
                updatedProjection.CinemaId,
                updatedProjection.AuditoriumId,
                updatedProjection.Name,
                updatedProjection.Genre,
                updatedProjection.Date,
                updatedProjection.Day,
                updatedProjection.Image,
                updatedProjection.Director,
                updatedProjection.Year,
                updatedProjection.Duration,
                updatedProjection.Description,
                updatedProjection.Price
            };

            await connection.ExecuteAsync(@"UPDATE projections SET 
                                CinemaId = @CinemaId,
                                AuditoriumId = @AuditoriumId,
                                Name = @Name,
                                Genre = @Genre,
                                Date = @Date,
                                Day = @Day,
                                Image = @Image,
                                Director = @Director,
                                Year = @Year,
                                Duration = @Duration,
                                Description = @Description,
                                Price = @Price
                                WHERE Id = @Id",
                                        parameters);

            return Ok(await SelectAllProjections(connection));
        }

        [HttpDelete("{projectionId}")]
        public async Task<ActionResult<Projection>> DeleteProjection(int projectionId)
        {
            using var connection = new SqlConnection(_config.GetConnectionString("DefaultConnection"));
            var projection = await connection.QuerySingleOrDefaultAsync<Projection>(
                "SELECT * FROM projections WHERE Id = @ProjectionId",
                new { ProjectionId = projectionId });

            if (projection == null)
            {
                return NotFound();
            }

            await connection.ExecuteAsync("DELETE FROM projections WHERE Id = @ProjectionId", new { ProjectionId = projectionId });

            return Ok(projection);
        }


        private static async Task<IEnumerable<Projection>> SelectAllProjections(SqlConnection connection)
        {
            return await connection.QueryAsync<Projection>("select * from projections");
        }
    }
}
