package test.java.com.moviebooking.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.moviebooking.services.UserService;
import com.moviebooking.models.User;
import com.moviebooking.models.Reservation;

import java.util.List;

public class UserServiceTest {

    private UserService userService;
    private User testUser;

    @BeforeEach
    void setUp() {
        userService = new UserService();
        testUser = new User("testuser", "1234", "USER");
        userService.addUser(testUser);
    }

    @Test
    void testGetUserByUsernameValid() {
        User user = userService.getUserByUsername("testuser");

        assertNotNull(user, "User should be found with valid username.");
        assertEquals("testuser", user.getUsername(), "Username should match.");
    }

    @Test
    void testGetUserByUsernameInvalid() {
        User user = userService.getUserByUsername("unknown");

        assertNull(user, "User should be null if username does not exist.");
    }

    @Test
    void testLoginWithCorrectCredentials() {
        User user = userService.login("testuser", "1234");

        assertNotNull(user, "Login should succeed with correct credentials.");
    }

    @Test
    void testLoginWithWrongPassword() {
        User user = userService.login("testuser", "wrong");

        assertNull(user, "Login should fail with wrong password.");
    }

    @Test
    void testGetUserReservationsWhenNoneExist() {
        List<Reservation> reservations =
                userService.getUserReservations("testuser");

        assertNotNull(reservations, "Reservation list should not be null.");
        assertTrue(reservations.isEmpty(),
                "Reservation list should be empty when user has no reservations.");
    }
}
