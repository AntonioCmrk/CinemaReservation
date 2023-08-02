using System.Collections.Generic;
using Microsoft.EntityFrameworkCore;

namespace CinemaReservationAPI.Models
{
    public class CinemaReservationDbContext : DbContext
    {
        public CinemaReservationDbContext(DbContextOptions<CinemaReservationDbContext> options) : base(options) { }

        public DbSet<User> Users { get; set; }
    }
}

