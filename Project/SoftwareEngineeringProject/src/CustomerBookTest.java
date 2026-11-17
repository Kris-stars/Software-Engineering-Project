
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CustomerBookTest {

    // TC01
    // Test Objective: Verify customer ID is accepted when exactly 16 characters long
    // Input: Customer ID = "1234567890123456"
    // Expected Output: Customer ID accepted
    @Test
    void validCustomerID_TC01() {
        CustomerBook customer = new CustomerBook();
        assertTrue(customer.verifyCustomerID("1234567890123456"));
    }

    // TC02
    // Test Objective: Verify customer ID is rejected when shorter than 16 characters
    // Input: Customer ID = "123"
    // Expected Output: Invalid customer ID
    @Test
    void invalidCustomerIDTooShort_TC02() {
        CustomerBook customer = new CustomerBook();
        assertFalse(customer.verifyCustomerID("123"));
    }

    // TC03
    // Test Objective: Verify customer ID is rejected when longer than 16 characters
    // Input: Customer ID = "12345678901234567"
    // Expected Output: Invalid customer ID
    @Test
    void invalidCustomerIDTooLong_TC03() {
        CustomerBook customer = new CustomerBook();
        assertFalse(customer.verifyCustomerID("12345678901234567"));
    }

    // TC04
    // Test Objective: Verify first name is accepted when exactly 1 character long
    // Input: First Name = "J"
    // Expected Output: First name accepted
    @Test
    void validFirstNameMinBoundary_TC04() {
        CustomerBook customer = new CustomerBook();
        assertTrue(customer.verifyFirstName("J"));
    }

    // TC05
    // Test Objective: Verify first name is accepted when more than 1 character
    // Input: First Name = "John"
    // Expected Output: First name accepted
    @Test
    void validFirstName_TC05() {
        CustomerBook customer = new CustomerBook();
        assertTrue(customer.verifyFirstName("John"));
    }

    // TC06
    // Test Objective: Verify first name is rejected when blank
    // Input: First Name = ""
    // Expected Output: Invalid first name
    @Test
    void invalidFirstNameBlank_TC06() {
        CustomerBook customer = new CustomerBook();
        assertFalse(customer.verifyFirstName(""));
    }

    // TC07
    // Test Objective: Verify surname is accepted when exactly 1 character long
    // Input: Surname = "S"
    // Expected Output: Surname accepted
    @Test
    void validSurnameMinBoundary_TC07() {
        CustomerBook customer = new CustomerBook();
        assertTrue(customer.verifySurname("S"));
    }

    // TC08
    // Test Objective: Verify surname is accepted when more than 1 character
    // Input: Surname = "Smith"
    // Expected Output: Surname accepted
    @Test
    void validSurname_TC08() {
        CustomerBook customer = new CustomerBook();
        assertTrue(customer.verifySurname("Smith"));
    }

    // TC09
    // Test Objective: Verify surname is rejected when blank
    // Input: Surname = ""
    // Expected Output: Invalid surname
    @Test
    void invalidSurnameBlank_TC09() {
        CustomerBook customer = new CustomerBook();
        assertFalse(customer.verifySurname(""));
    }

    // TC10
    // Test Objective: Verify address is accepted when exactly 1 character long
    // Input: Address = "A"
    // Expected Output: Address accepted
    @Test
    void validAddressMinBoundary_TC10() {
        CustomerBook customer = new CustomerBook();
        assertTrue(customer.verifyAddress("A"));
    }

    // TC11
    // Test Objective: Verify address is accepted when more than 1 character
    // Input: Address = "Main Street"
    // Expected Output: Address accepted
    @Test
    void validAddress_TC11() {
        CustomerBook customer = new CustomerBook();
        assertTrue(customer.verifyAddress("Main Street"));
    }

    // TC12
    // Test Objective: Verify address is rejected when blank
    // Input: Address = ""
    // Expected Output: Invalid address
    @Test
    void invalidAddressBlank_TC12() {
        CustomerBook customer = new CustomerBook();
        assertFalse(customer.verifyAddress(""));
    }

    // TC13
    // Test Objective: Verify phone number is accepted when exactly 10 digits
    // Input: Phone Number = "0871234567"
    // Expected Output: Phone number accepted
    @Test
    void validPhoneNumber_TC13() {
        CustomerBook customer = new CustomerBook();
        assertTrue(customer.verifyPhoneNumber("0871234567"));
    }

    // TC14
    // Test Objective: Verify phone number is rejected when fewer than 10 digits
    // Input: Phone Number = "087123456"
    // Expected Output: Invalid phone number
    @Test
    void invalidPhoneNumberTooShort_TC14() {
        CustomerBook customer = new CustomerBook();
        assertFalse(customer.verifyPhoneNumber("087123456"));
    }

    // TC15
    // Test Objective: Verify phone number is rejected when more than 10 digits
    // Input: Phone Number = "08712345678"
    // Expected Output: Invalid phone number
    @Test
    void invalidPhoneNumberTooLong_TC15() {
        CustomerBook customer = new CustomerBook();
        assertFalse(customer.verifyPhoneNumber("08712345678"));
    }

    // TC16
    // Test Objective: Verify phone number is rejected when containing letters
    // Input: Phone Number = "08712AB567"
    // Expected Output: Invalid phone number
    @Test
    void invalidPhoneNumberAlphaCharacters_TC16() {
        CustomerBook customer = new CustomerBook();
        assertFalse(customer.verifyPhoneNumber("08712AB567"));
    }

    // TC17
    // Test Objective: Verify customer account is created when all details are valid
    // Input: ID, John, Smith, Main Street, 0871234567
    // Expected Output: Customer account created successfully
    @Test
    void createValidCustomerAccount_TC17() {
        CustomerBook customer = new CustomerBook();
        assertTrue(customer.createCustomerAccount(
                "1234567890123456",
                "John",
                "Smith",
                "Main Street",
                "0871234567"));
    }

    // TC18
    // Test Objective: Verify account creation fails with invalid customer ID
    // Input: Invalid ID, John, Smith, Main Street, 0871234567
    // Expected Output: Customer account not created
    @Test
    void createAccountInvalidCustomerID_TC18() {
        CustomerBook customer = new CustomerBook();
        assertFalse(customer.createCustomerAccount(
                "123",
                "John",
                "Smith",
                "Main Street",
                "0871234567"));
    }

    // TC19
    // Test Objective: Verify account creation fails with invalid first name
    // Input: Valid ID, "", Smith, Main Street, 0871234567
    // Expected Output: Customer account not created
    @Test
    void createAccountInvalidFirstName_TC19() {
        CustomerBook customer = new CustomerBook();
        assertFalse(customer.createCustomerAccount(
                "1234567890123456",
                "",
                "Smith",
                "Main Street",
                "0871234567"));
    }

    // TC20
    // Test Objective: Verify account creation fails with invalid surname
    // Input: Valid ID, John, "", Main Street, 0871234567
    // Expected Output: Customer account not created
    @Test
    void createAccountInvalidSurname_TC20() {
        CustomerBook customer = new CustomerBook();
        assertFalse(customer.createCustomerAccount(
                "1234567890123456",
                "John",
                "",
                "Main Street",
                "0871234567"));
    }

    // TC21
    // Test Objective: Verify account creation fails with invalid address
    // Input: Valid ID, John, Smith, "", 0871234567
    // Expected Output: Customer account not created
    @Test
    void createAccountInvalidAddress_TC21() {
        CustomerBook customer = new CustomerBook();
        assertFalse(customer.createCustomerAccount(
                "1234567890123456",
                "John",
                "Smith",
                "",
                "0871234567"));
    }

    // TC22
    // Test Objective: Verify account creation fails with invalid phone number
    // Input: Valid ID, John, Smith, Main Street, 087123456
    // Expected Output: Customer account not created
    @Test
    void createAccountInvalidPhoneNumber_TC22() {
        CustomerBook customer = new CustomerBook();
        assertFalse(customer.createCustomerAccount(
                "1234567890123456",
                "John",
                "Smith",
                "Main Street",
                "087123456"));
    }

    // TC23
    // Test Objective: Verify customer can be added, found and deleted
    // Input: Valid customer details
    // Expected Output: Customer is added, found and deleted successfully
 // TC23
    @Test
    void addFindDeleteCustomer_TC23() {

        CustomerBook customer = new CustomerBook();

        assertTrue(customer.createCustomerAccount(
            "1234567890123456",
            "John",
            "Smith",
            "Main Street",
            "0871234567"
        ));

        customer.addCustomer(customer);

        assertNotNull(customer.findCustomerByID("1234567890123456"));

        assertTrue(customer.deleteCustomer("1234567890123456"));

        assertNull(customer.findCustomerByID("1234567890123456"));
    }
}
    
