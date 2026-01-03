package test.java.com.moviebooking.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.moviebooking.services.ShowTimeService;
import com.moviebooking.models.ShowTime;

import java.util.List;

public class ShowTimeServiceTest{

    private ShowTimeService showTimeService;

    @BeforeEach
    void setUp() {
        showTimeService = new ShowTimeService();
    }

    @Test
    void testLoadShowTimesWithValidDay() {
        List<ShowTime> showTimes = showTimeService.loadShowTimes("Monday");

        assertNotNull(showTimes, "ShowTimes list should not be null.");
        assertFalse(showTimes.isEmpty(), "ShowTimes list should not be empty for a valid day.");
    }

    @Test
    void testLoadShowTimesWithInvalidDay() {
        List<ShowTime> showTimes = showTimeService.loadShowTimes("InvalidDay");

        assertNotNull(showTimes, "ShowTimes list should not be null even if file does not exist.");
        assertTrue(showTimes.isEmpty(), "ShowTimes list should be empty when file is not found.");
    }

    @Test
    void testSaveShowTimesDoesNotThrowException() {
        List<ShowTime> showTimes = showTimeService.loadShowTimes("Monday");

        assertDoesNotThrow(() -> {
            showTimeService.saveShowTimes("Monday", showTimes);
        }, "saveShowTimes should not throw any exception.");
    }
}
