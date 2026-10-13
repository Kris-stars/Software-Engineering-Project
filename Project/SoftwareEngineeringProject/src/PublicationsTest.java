import static org.junit.Assert.*;
import org.junit.Test;

public class PublicationsTest {

    // TC01
    // Test Objective: Verify publication ID accepted at 16 characters
    // Input(s): ID: 1234567890123456
    // Expected Output(s): ID accepted
    @Test
    public void publicationsID_16chars_valid() {
        assertTrue(Publications.isValidPublicationsID("1234567890123456"));
    }

    // TC02
    // Test Objective: Reject ID shorter than 16
    // Input(s): ID: 123456789012345
    // Expected Output(s): ID rejected
    @Test
    public void publicationsID_15chars_invalid() {
        assertFalse(Publications.isValidPublicationsID("123456789012345"));
    }

    // TC03
    // Test Objective: Reject ID longer than 16
    // Input(s): ID: 12345678901234567
    // Expected Output(s): ID rejected
    @Test
    public void publicationsID_17chars_invalid() {
        assertFalse(Publications.isValidPublicationsID("12345678901234567"));
    }

    // TC04
    // Test Objective: Reject empty ID
    // Input(s): ID: ""
    // Expected Output(s): ID rejected
    @Test
    public void publicationsID_empty_invalid() {
        assertFalse(Publications.isValidPublicationsID(""));
    }

    // TC05
    // Test Objective: Reject null ID
    // Input(s): ID: null
    // Expected Output(s): ID rejected
    @Test
    public void publicationsID_null_invalid() {
        assertFalse(Publications.isValidPublicationsID(null));
    }

    // TC06
    // Test Objective: Accept daily frequency
    // Input(s): Frequency: Daily
    // Expected Output(s): Frequency accepted
    @Test
    public void frequency_daily_valid() {
        assertTrue(Publications.isValidFrequency("Daily"));
    }

    // TC07
    // Test Objective: Accept weekly frequency
    // Input(s): Frequency: Weekly
    // Expected Output(s): Frequency accepted
    @Test
    public void frequency_weekly_valid() {
        assertTrue(Publications.isValidFrequency("Weekly"));
    }

    // TC08
    // Test Objective: Accept monthly frequency
    // Input(s): Frequency: Monthly
    // Expected Output(s): Frequency accepted
    @Test
    public void frequency_monthly_valid() {
        assertTrue(Publications.isValidFrequency("Monthly"));
    }

    // TC09
    // Test Objective: Reject unsupported frequency
    // Input(s): Frequency: Random
    // Expected Output(s): Frequency rejected
    @Test
    public void frequency_invalid() {
        assertFalse(Publications.isValidFrequency("Random"));
    }

    // TC10
    // Test Objective: Reject null frequency
    // Input(s): Frequency: null
    // Expected Output(s): Frequency rejected
    @Test
    public void frequency_null_invalid() {
        assertFalse(Publications.isValidFrequency(null));
    }

    // TC11
    // Test Objective: Accept minimum positive price
    // Input(s): Price: 0.01
    // Expected Output(s): Price accepted
    @Test
    public void price_min_valid() {
        assertTrue(Publications.isValidPrice(0.01));
    }

    // TC12
    // Test Objective: Accept normal price
    // Input(s): Price: 2.50
    // Expected Output(s): Price accepted
    @Test
    public void price_normal_valid() {
        assertTrue(Publications.isValidPrice(2.50));
    }

    // TC13
    // Test Objective: Reject zero price
    // Input(s): Price: 0
    // Expected Output(s): Price rejected
    @Test
    public void price_zero_invalid() {
        assertFalse(Publications.isValidPrice(0));
    }

    // TC14
    // Test Objective: Reject negative price
    // Input(s): Price: -1
    // Expected Output(s): Price rejected
    @Test
    public void price_negative_invalid() {
        assertFalse(Publications.isValidPrice(-1));
    }

    // TC15
    // Test Objective: Reject non-finite price
    // Input(s): Price: NaN
    // Expected Output(s): Price rejected
    @Test
    public void price_nan_invalid() {
        assertFalse(Publications.isValidPrice(Double.NaN));
    }

    // TC16
    // Test Objective: Create publication with valid fields
    // Input(s): 16-char ID, Daily, 2.50
    // Expected Output(s): Publication created
    @Test
    public void createPublication_valid() {
        Publications publication = new Publications("1234567890123456", "Daily", 2.50);
        assertEquals("1234567890123456", publication.getPublicationsID());
        assertEquals("Daily", publication.getFrequency());
        assertEquals(2.50, publication.getPrice(), 0.001);
    }

    // TC17
    // Test Objective: Reject creation with invalid ID
    // Input(s): 15-char ID, Daily, 2.50
    // Expected Output(s): IllegalArgumentException
    @Test
    public void createPublication_invalidID() {
        assertThrows(IllegalArgumentException.class, () -> new Publications("123456789012345", "Daily", 2.50));
    }

    // TC18
    // Test Objective: Reject creation with invalid frequency
    // Input(s): 16-char ID, Random, 2.50
    // Expected Output(s): IllegalArgumentException
    @Test
    public void createPublication_invalidFrequency() {
        assertThrows(IllegalArgumentException.class, () -> new Publications("1234567890123456", "Random", 2.50));
    }

    // TC19
    // Test Objective: Reject creation with invalid price
    // Input(s): 16-char ID, Daily, 0
    // Expected Output(s): IllegalArgumentException
    @Test
    public void createPublication_invalidPrice() {
        assertThrows(IllegalArgumentException.class, () -> new Publications("1234567890123456", "Daily", 0));
    }
}
