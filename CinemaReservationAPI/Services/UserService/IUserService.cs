using CinemaReservationAPI.Models;

namespace CinemaReservationAPI.Services.UserService
{
    public interface IUserService
    {
        IEnumerable<User> GetAllUsers();
    }
}
