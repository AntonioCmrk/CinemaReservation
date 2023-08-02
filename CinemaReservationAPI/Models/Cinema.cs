namespace CinemaReservationAPI.Models
{
    public class Cinema
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public List<Auditorium> Auditoriums { get; set; }
        public string Address { get; set; }
        public string ZipCode { get; set; }
        public string Town { get; set; }
    }
}