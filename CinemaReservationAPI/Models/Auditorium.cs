namespace CinemaReservationAPI.Models
{
    public class Auditorium
    {
        public int Id { get; set; }
        public int CinemaId { get; set; }
        public string Label { get; set; }
        public List<Projection> Projections { get; set; }
        public List<Seat> Seats { get; set; }
    }
}
