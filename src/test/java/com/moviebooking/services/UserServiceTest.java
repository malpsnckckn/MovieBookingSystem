package test.java.com.moviebooking.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.moviebooking.services.UserService;
import com.moviebooking.models.User;
import com.moviebooking.models.Reservation;

import java.util.List;
/**
 * Unit tests for UserService.
 * Tests user lookup, login, and reservation access.
 */
public class UserServiceTest {

    private UserService userService;
    private User testUser;
/**
* Initializes UserService and adds a test user before each test.
*/
    @BeforeEach
    void setUp() {
        userService = new UserService();
        testUser = new User("testuser", "1234", "USER");
        userService.addUser(testUser);
    }
/**
* Tests retrieving a user with a valid username.
*/
    @Test
    void testGetUserByUsernameValid() {
        User user = userService.getUserByUsername("testuser");

        assertNotNull(user, "User should be found with valid username.");
        assertEquals("testuser", user.getUsername(), "Username should match.");
    }
/**
* Tests retrieving a user that does not exist.
*/
    @Test
    void testGetUserByUsernameInvalid() {
        User user = userService.getUserByUsername("unknown");

        assertNull(user, "User should be null if username does not exist.");
    }
/**
* Tests login with correct username and password.
*/
    @Test
    void testLoginWithCorrectCredentials() {
        User user = userService.login("testuser", "1234");

        assertNotNull(user, "Login should succeed with correct credentials.");
    }
/**
* Tests login failure with wrong password.
*/
    @Test
    void testLoginWithWrongPassword() {
        User user = userService.login("testuser", "wrong");

        assertNull(user, "Login should fail with wrong password.");
    }
/**
* Tests reservation history when user has no reservations.
*/
    @Test
    void testGetUserReservationsWhenNoneExist() {
        List<Reservation> reservations =
                userService.getUserReservations("testuser");

        assertNotNull(reservations, "Reservation list should not be null.");
        assertTrue(reservations.isEmpty(),
                "Reservation list should be empty when user has no reservations.");
    }
}
