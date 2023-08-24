using CinemaReservationAPI.Models;
using System.Security.Claims;

namespace CinemaReservationAPI.Services.UserService
{
    public class UserService : IUserService
    {
        private readonly IHttpContextAccessor _httpContextAccessor;
        private readonly CinemaReservationDbContext _context;
        public UserService(IHttpContextAccessor httpContextAccessor, CinemaReservationDbContext context) 
        {
            _httpContextAccessor = httpContextAccessor;
            _context = context;
        }
        public IEnumerable<User> GetAllUsers()
        {
            return _context.Users;
        }
    }
}
