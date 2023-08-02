using CinemaReservationAPI.Models;
using System;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;


namespace CinemaReservationAPI
{
    public class Startup
    {
        private readonly IConfiguration _configuration;

        public Startup(IConfiguration configuration)
        {
            _configuration = configuration;
        }

        public void ConfigureServices(IServiceCollection services)
        {
            services.AddDbContext<CinemaReservationDbContext>(options =>
                options.UseSqlServer(_configuration.GetConnectionString("DefaultConnection")));

        }
    }
}
