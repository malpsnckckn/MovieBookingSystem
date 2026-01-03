package test.java.com.moviebooking.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.moviebooking.services.ReservationService;
import com.moviebooking.models.User;

import java.util.Scanner;

public class ReservationServiceTest {

    private ReservationService reservationService;
    private User testUser;

    @BeforeEach
    void setUp() {
        reservationService = new ReservationService();
        testUser = new User("testuser", "1234");
    }

    @Test
    void testMakeReservationWithInvalidDaySelection() {

        String fakeInput = "0\n";
        Scanner scanner = new Scanner(fakeInput);

        assertDoesNotThrow(() -> {
            reservationService.makeReservation(scanner, testUser);
        }, "Method should not throw exception for invalid day input.");
    }
}
