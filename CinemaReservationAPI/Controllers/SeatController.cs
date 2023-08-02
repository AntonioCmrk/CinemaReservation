using CinemaReservationAPI.Models;
using Dapper;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System.Data.SqlClient;

namespace CinemaReservationAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class SeatController : ControllerBase
    {
        private readonly IConfiguration _config;

        public SeatController(IConfiguration config)
        {
            _config = config;
        }

        [HttpGet]
        public async Task<ActionResult<IEnumerable<Seat>>> GetAllSeats()
        {
            using var connection = new SqlConnection(_config.GetConnectionString("DefaultConnection"));
            IEnumerable<Seat> seats = await SelectAllSeats(connection);

            return Ok(seats);
        }

        [HttpGet("{seatId}")]
        public async Task<ActionResult<Seat>> GetSeat(int seatId)
        {
            using var connection = new SqlConnection(_config.GetConnectionString("DefaultConnection"));
            var seat = await connection.QueryFirstOrDefaultAsync<Seat>("SELECT * FROM seats WHERE Id = @Id", new { Id = seatId });

            if (seat == null)
            {
                return NotFound();
            }

            return Ok(seat);
        }

        [HttpPost]
        public async Task<ActionResult<IEnumerable<Seat>>>CreateSeat(Seat seat)
        {
            using var connection = new SqlConnection(_config.GetConnectionString("DefaultConnection"));
            var parameters = new
            {
                AuditoriumId = seat.AuditoriumId,
                Row = seat.Row,
                Number = seat.Number,
                ReservationHolder = seat.ReservationHolder
            };

            await connection.ExecuteAsync(@"INSERT INTO seats (AuditoriumId, Row, Number, ReservationHolder) 
                                    VALUES (@AuditoriumId, @Row, @Number, @ReservationHolder)", parameters);

            return Ok(await SelectAllSeats(connection));
        }

        [HttpPut("{seatId}")]
        public async Task<ActionResult<IEnumerable<Seat>>> UpdateSeat(int seatId, Seat updatedSeat)
        {
            using var connection = new SqlConnection(_config.GetConnectionString("DefaultConnection"));
            var parameters = new
            {
                Id = seatId,
                AuditoriumId = updatedSeat.AuditoriumId,
                Row = updatedSeat.Row,
                Number = updatedSeat.Number,
                ReservationHolder = updatedSeat.ReservationHolder
            };

            await connection.ExecuteAsync(@"UPDATE seats SET 
                                    AuditoriumId = @AuditoriumId,
                                    Row = @Row,
                                    Number = @Number,
                                    ReservationHolder = @ReservationHolder
                                    WHERE Id = @Id", parameters);

            return Ok(await SelectAllSeats(connection));
        }

        [HttpDelete("{seatId}")]
        public async Task<ActionResult<Seat>> DeleteSeat(int seatId)
        {
            using var connection = new SqlConnection(_config.GetConnectionString("DefaultConnection"));
            var seat = await connection.QueryFirstOrDefaultAsync<Seat>("DELETE FROM seats WHERE Id = @Id", new { Id = seatId });

            if (seat == null)
            {
                return NotFound();
            }

            return Ok(seat);
        }

        private static async Task<IEnumerable<Seat>> SelectAllSeats(SqlConnection connection)
        {
            return await connection.QueryAsync<Seat>("SELECT * FROM seats");
        }
    }
}
