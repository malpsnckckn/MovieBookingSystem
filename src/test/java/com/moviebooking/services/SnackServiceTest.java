package test.java.com.moviebooking.services;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.moviebooking.services.SnackService;
import com.moviebooking.models.Snack;
/**
 * Unit tests for SnackService.
 * Verifies snack selection logic.
 */
public class SnackServiceTest {

    private SnackService snackService;
/**
* Creates a fresh SnackService before each test.
*/
    @BeforeEach
    void setUp() {
        snackService = new SnackService();
    }
/**
* Tests retrieving a snack with a valid index.
*/
    @Test
    void testGetSnackByValidIndex() {
        Snack snack = snackService.getSnackByIndex(1);

        assertNotNull(snack, "Snack should not be null for a valid index.");
        assertEquals("Popcorn (Small)", snack.getName(), "Snack name should match.");
        assertEquals(4, snack.getPrice(), "Snack price should match.");
    }
/**
* Tests behavior when index is zero.
*/
    @Test
    void testGetSnackByInvalidIndexZero() {
        Snack snack = snackService.getSnackByIndex(0);
        assertNull(snack, "Snack should be null when index is 0.");
    }
/**
* Tests behavior when index is negative.
*/
    @Test
    void testGetSnackByInvalidIndexNegative() {
        Snack snack = snackService.getSnackByIndex(-1);
        assertNull(snack, "If the index is negative, the snack should be empty.");
    }
/**
* Tests behavior when index exceeds list size.
*/
    @Test
    void testGetSnackByInvalidIndexOutOfBounds() {
        Snack snack = snackService.getSnackByIndex(100);
        assertNull(snack, "Snack should be null when index exceeds snack list size.");
    }
}
