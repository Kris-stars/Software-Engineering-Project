
import static org.junit.Assert.*;
import org.junit.Test;

public class CustomerBookTest {

    // Test 1: Tests that a customer can be created with valid details
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

    // Test 2: Tests that an invalid phone number is rejected
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

    // Test 3: Tests that a customer ID with fewer than 16 characters is rejected
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

    // Test 4: Tests that an empty first name is rejected
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

    // Test 5: Tests that an empty surname is rejected
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

    // Test 6: Tests that an empty address is rejected
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

    // Test 7: Tests that a customer can be added and found using their ID
    @Test
    public void testAddAndFindCustomer() {

        CustomerBook customer = new CustomerBook(
            "1111111111111111",
            "Marlon",
            "Mog",
            "The Street",
            "0897654321"
        );

        customer.addCustomer(customer);

        CustomerBook foundCustomer =
            customer.findCustomerByID("1111111111111111");

        assertNotNull(foundCustomer);
        assertEquals("Marlon", foundCustomer.getCustomerFirstName());

        customer.deleteCustomer("1111111111111111");
    }

	//Test 8: Checks that a customer's details can be updated
	@Test
	public void testUpdateCustomer() {
	
	 CustomerBook customer = new CustomerBook(
	     "2222222222222222",
	     "Bill",
	     "Science guy",
	     "Big brain Street",
	     "0871234567"
	 );
	
	 customer.addCustomer(customer);
	
	 customer.updateCustomer(
	     "2222222222222222",
	     "Michael b",
	     "Jordan",
	     "High Street",
	     "0861234567"
	 );
	
	 assertEquals("Michael b", customer.getCustomerFirstName());
	 assertEquals("Jordan", customer.getCustomerSurname());
	 assertEquals("High Street", customer.getCustomerAddress());
	 assertEquals("0861234567", customer.getCustomerPhoneNumber());
	
	 customer.deleteCustomer("2222222222222222");
	}
	
	// Test 9: Checks that a customer can be deleted
	@Test
	public void testDeleteCustomer() {

	    CustomerBook customer = new CustomerBook(
	        "3333333333333333",
	        "Agnes",
	        "Brown",
	        "Something Street",
	        "0871234567"
	    );

	    customer.addCustomer(customer);

	    boolean deleted = customer.deleteCustomer("3333333333333333");

	    assertTrue(deleted);

	    assertNull(customer.findCustomerByID("3333333333333333"));
	}
	
	// Test 10: Checks that deleting one customer does not delete another
	@Test
	public void testDeleteOneCustomerKeepsOtherCustomers() {

	    CustomerBook customer1 = new CustomerBook(
	        "4444444444444444",
	        "Michael",
	        "Murphy",
	        "Bomb Street",
	        "0871234567"
	    );

	    CustomerBook customer2 = new CustomerBook(
	        "5555555555555555",
	        "Agnes",
	        "Brown",
	        "Something Street",
	        "0861234567"
	    );

	    customer1.addCustomer(customer1);
	    customer1.addCustomer(customer2);

	    customer1.deleteCustomer("4444444444444444");

	    assertNull(customer1.findCustomerByID("4444444444444444"));

	    assertNotNull(customer1.findCustomerByID("5555555555555555"));

	    customer1.deleteCustomer("5555555555555555");
	}
}
