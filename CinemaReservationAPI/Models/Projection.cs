namespace CinemaReservationAPI.Models
{
    public class Projection
    {
        public int Id { get; set; }
        public int CinemaId { get; set; }
        public int AuditoriumId { get; set; }
        public string Name { get; set; }
        public string Genre { get; set; }
        public DateTime Date { get; set; }
        public string Day { get; set; }
        public string Image { get; set; }
        public string Director { get; set; }
        public int Year { get; set; }
        public int Duration { get; set; }
        public string Description { get; set; }
        public decimal Price { get; set; }
    }
}
