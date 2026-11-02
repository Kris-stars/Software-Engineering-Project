import static org.junit.Assert.*;
import org.junit.Test;

public class CustomerBookTest {

	//test 1: tests that a customer can be created with valid details
    @Test
    public void testCreateCustomer() {

        CustomerBook customer = new CustomerBook(
            "1234567890123456",
            "Isabella",
            "Perez",
            "Wall Street",
            "0871234567"
        );

        assertEquals("Isabella", customer.getCustomerFirstName());
        assertEquals("Perez", customer.getCustomerSurname());
        assertEquals("0871234567", customer.getCustomerPhoneNumber());
    }

    //test 2: tests that an invalid phone number is rejected
    @Test
    public void testInvalidPhoneNumber() {

    	try {
    	    new CustomerBook(
    	        "1234567890123456",
    	        "Isabella",
    	        "Perez",
    	        "Wall Street",
    	        "745678"
    	    );

    	    fail("Invalid phone number should not be accepted");

    	} catch (IllegalArgumentException e) {
    	    assertTrue(true);
    	}
    }
    	
    	//test 3: tests that a customer's id with fewer than 16 characters is rejected
    	@Test
    	public void testInvalidCustomerID() {

    	    try {
    	        new CustomerBook(
    	            "89845",
    	            "Isabella",
    	            "Perez",
    	            "Wall Street",
    	            "0871234567"
    	        );

    	        fail("Invalid customer ID should not be accepted");

    	    } catch (IllegalArgumentException e) {
    	        assertTrue(true);
    	    }
    		
    }
    	
    	//test 4: tests that an empty first name is rejected
    	@Test
    	public void testInvalidFirstName() {

    	    try {
    	        new CustomerBook(
    	            "1234567890123456",
    	            "",
    	            "Perez",
    	            "Wall Street",
    	            "0871234567"
    	        );

    	        fail("Invalid first name should not be accepted");

    	    } catch (IllegalArgumentException e) {
    	        assertTrue(true);
    	    }
    	}
    	
    	//test 5: tests that an empty surname is rejected
    	@Test
    	public void testInvalidSurname() {

    	    try {
    	        new CustomerBook(
    	            "1234567890123456",
    	            "Isabella",
    	            "",
    	            "Wall Street",
    	            "0871234567"
    	        );

    	        fail("Invalid surname should not be accepted");

    	    } catch (IllegalArgumentException e) {
    	        assertTrue(true);
    	    }
    	}
    	
    	//test 6: tests that an empty address is rejected
    	@Test
    	public void testInvalidAddress() {

    	    try {
    	        new CustomerBook(
    	            "1234567890123456",
    	            "Isabella",
    	            "Perez",
    	            "",
    	            "0871234567"
    	        );

    	        fail("Invalid address should not be accepted");

    	    } catch (IllegalArgumentException e) {
    	        assertTrue(true);
    	    }
    	}
}