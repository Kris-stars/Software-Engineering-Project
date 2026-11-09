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
    
 // TC20: READ finds an existing publication by its ID.
    @Test
    public void read_existingPublication_returnsMatchingObject() {
        Publications first = new Publications("1111111111111111", "Daily", 2.50);
        Publications second = new Publications("2222222222222222", "Weekly", 4.00);
        java.util.List<Publications> list = java.util.Arrays.asList(first, second);
        assertSame(second, Publications.read("2222222222222222", list));
    }

    // TC21: READ returns null for a valid but unknown ID.
    @Test
    public void read_unknownPublication_returnsNull() {
        java.util.List<Publications> list = java.util.Arrays.asList(
            new Publications("1111111111111111", "Daily", 2.50));
        assertNull(Publications.read("2222222222222222", list));
    }

    // TC22: READ rejects an incorrectly sized ID.
    @Test
    public void read_shortID_returnsNull() {
        assertNull(Publications.read("123", new java.util.ArrayList<Publications>()));
    }

    // TC23: READ rejects a null ID.
    @Test
    public void read_nullID_returnsNull() {
        assertNull(Publications.read(null, new java.util.ArrayList<Publications>()));
    }

    // TC24: READ handles an empty list or null list safely.
    @Test
    public void read_emptyOrNullList_returnsNull() {
        assertNull(Publications.read("1111111111111111", new java.util.ArrayList<Publications>()));
        assertNull(Publications.read("1111111111111111", null));
    }

    // TC25: UPDATE changes the price and keeps the same object.
    @Test
    public void update_validPrice_changesPrice() {
        Publications pub = new Publications("1111111111111111", "Daily", 2.50);
        java.util.List<Publications> list = java.util.Arrays.asList(pub);
        assertSame(pub, Publications.update(pub.getPublicationsID(), "Daily", 3.00, list));
        assertEquals(3.00, pub.getPrice(), 0.001);
    }

    // TC26: UPDATE changes the frequency.
    @Test
    public void update_validFrequency_changesFrequency() {
        Publications pub = new Publications("1111111111111111", "Daily", 2.50);
        Publications.update(pub.getPublicationsID(), "Weekly", 2.50, java.util.Arrays.asList(pub));
        assertEquals("Weekly", pub.getFrequency());
    }

    // TC27: UPDATE can change both editable fields together.
    @Test
    public void update_bothFields_changesBoth() {
        Publications pub = new Publications("1111111111111111", "Daily", 2.50);
        Publications.update(pub.getPublicationsID(), "Monthly", 5.00, java.util.Arrays.asList(pub));
        assertEquals("Monthly", pub.getFrequency());
        assertEquals(5.00, pub.getPrice(), 0.001);
        assertEquals("1111111111111111", pub.getPublicationsID()); // ID stays fixed.
    }

    // TC28: UPDATE must not create a record if the ID does not exist.
    @Test
    public void update_unknownID_returnsNull() {
        Publications pub = new Publications("1111111111111111", "Daily", 2.50);
        assertNull(Publications.update("2222222222222222", "Weekly", 3.00,
            java.util.Arrays.asList(pub)));
    }

    // TC29: UPDATE rejects a negative price.
    @Test
    public void update_negativePrice_returnsNull() {
        Publications pub = new Publications("1111111111111111", "Daily", 2.50);
        assertNull(Publications.update(pub.getPublicationsID(), "Weekly", -1,
            java.util.Arrays.asList(pub)));
    }

    // TC30: UPDATE rejects an unsupported frequency.
    @Test
    public void update_invalidFrequency_returnsNull() {
        Publications pub = new Publications("1111111111111111", "Daily", 2.50);
        assertNull(Publications.update(pub.getPublicationsID(), "Yearly", 3.00,
            java.util.Arrays.asList(pub)));
    }

    // TC31: UPDATE rejects a null ID.
    @Test
    public void update_nullID_returnsNull() {
        Publications pub = new Publications("1111111111111111", "Daily", 2.50);
        assertNull(Publications.update(null, "Weekly", 3.00, java.util.Arrays.asList(pub)));
    }

    // TC32: An invalid update must leave ALL original fields unchanged.
    @Test
    public void update_invalidInput_doesNotPartiallyChangeObject() {
        Publications pub = new Publications("1111111111111111", "Daily", 2.50);
        assertNull(Publications.update(pub.getPublicationsID(), "Weekly", -1,
            java.util.Arrays.asList(pub)));
        assertEquals("Daily", pub.getFrequency());
        assertEquals(2.50, pub.getPrice(), 0.001);
    }
    
    // TC33
    // Test Objective: Delete an existing publication
    // Input(s): Valid publication ID in list
    // Expected Output(s): Publication removed; true returned
    @Test
    public void delete_existingPublication_returnsTrue() {

        Publications pub = new Publications("1111111111111111", "Daily", 2.50);

        java.util.List<Publications> list = new java.util.ArrayList<>();
        list.add(pub);

        assertTrue(Publications.delete("1111111111111111", list));
        assertEquals(0, list.size());
    }

    // TC34
    // Test Objective: Reject deletion of an unknown publication
    // Input(s): Valid ID not present in list
    // Expected Output(s): False returned; original publication retained
    @Test
    public void delete_unknownPublication_returnsFalse() {

        Publications pub = new Publications("1111111111111111", "Daily", 2.50);

        java.util.List<Publications> list = new java.util.ArrayList<>();
        list.add(pub);

        assertFalse(Publications.delete("2222222222222222", list));
        assertEquals(1, list.size());
    }

    // TC35
    // Test Objective: Reject deletion using an invalid ID
    // Input(s): ID shorter than 16 characters
    // Expected Output(s): False returned
    @Test
    public void delete_invalidID_returnsFalse() {

        java.util.List<Publications> list = new java.util.ArrayList<>();

        assertFalse(Publications.delete("123", list));
    }

    // TC36
    // Test Objective: Reject deletion using a null ID
    // Input(s): Null publication ID
    // Expected Output(s): False returned
    @Test
    public void delete_nullID_returnsFalse() {

        java.util.List<Publications> list = new java.util.ArrayList<>();

        assertFalse(Publications.delete(null, list));
    }

    // TC37
    // Test Objective: Handle an empty or null publication list
    // Input(s): Empty list or null list
    // Expected Output(s): False returned
    @Test
    public void delete_emptyOrNullList_returnsFalse() {

        java.util.List<Publications> list = new java.util.ArrayList<>();

        assertFalse(Publications.delete("1111111111111111", list));
        assertFalse(Publications.delete("1111111111111111", null));
    }

    // TC38
    // Test Objective: Ensure only the matching publication is removed
    // Input(s): List containing multiple publications
    // Expected Output(s): Correct publication removed; others retained
    @Test
    public void delete_onlyMatchingPublication_isRemoved() {

        Publications first = new Publications("1111111111111111", "Daily", 2.50);
        Publications second = new Publications("2222222222222222", "Weekly", 4.00);

        java.util.List<Publications> list = new java.util.ArrayList<>();
        list.add(first);
        list.add(second);

        assertTrue(Publications.delete("1111111111111111", list));

        assertEquals(1, list.size());
        assertSame(second, list.get(0));
        assertNull(Publications.read("1111111111111111", list));
    }

    // TC39
    // Test Objective: Prevent deleting the same publication twice
    // Input(s): Delete the same publication ID twice
    // Expected Output(s): First deletion succeeds; second fails
    @Test
    public void delete_twice_secondAttemptReturnsFalse() {

        Publications pub = new Publications("1111111111111111", "Daily", 2.50);

        java.util.List<Publications> list = new java.util.ArrayList<>();
        list.add(pub);

        assertTrue(Publications.delete("1111111111111111", list));
        assertFalse(Publications.delete("1111111111111111", list));
    }

    // TC40
    // Test Objective: Safely handle null entries in the publication list
    // Input(s): List containing null and a valid publication
    // Expected Output(s): Matching publication removed without errors
    @Test
    public void delete_listContainsNull_removesMatchingPublication() {

        Publications pub = new Publications("1111111111111111", "Daily", 2.50);

        java.util.List<Publications> list = new java.util.ArrayList<>();
        list.add(null);
        list.add(pub);

        assertTrue(Publications.delete("1111111111111111", list));
        assertEquals(1, list.size());
        assertNull(list.get(0));
    }
}
