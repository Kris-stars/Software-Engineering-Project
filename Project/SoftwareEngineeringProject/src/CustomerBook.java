
import java.util.ArrayList;

public class CustomerBook {

    // Customer details
    private String customerID;
    private String customerFirstName;
    private String customerSurname;
    private String customerAddress;
    private String customerPhoneNumber;

    // stores all the customers
    private static ArrayList<CustomerBook> customers = new ArrayList<>();
    
    // Default constructor
    public CustomerBook() {
    }
    
    // Constructor with customer details 
    public CustomerBook(String customerID, String customerFirstName, String customerSurname, String customerAddress, String customerPhoneNumber) 
    {

        if (!createCustomerAccount(customerID, customerFirstName, customerSurname, customerAddress, customerPhoneNumber)) {

            throw new IllegalArgumentException(
                    "Invalid customer details");
        }
    }

    // The customer ID must contain 16 characters
    public boolean verifyCustomerID(String customerID) {
        return customerID != null && customerID.length() == 16;
    }

    // The first name
    public boolean verifyFirstName(String firstName) {
        return firstName != null && !firstName.trim().isEmpty();
    }

    // The surname
    public boolean verifySurname(String surname) {
        return surname != null && !surname.trim().isEmpty();
    }

    // The address
    public boolean verifyAddress(String address) {
        return address != null && !address.trim().isEmpty();
    }

    // The phone number contains exactly 10 digits
    public boolean verifyPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("[0-9]{10}");
    }

 // Creates a customer
    public boolean createCustomerAccount(String customerID, String firstName,
            String surname, String address, String phoneNumber) {

        if (verifyCustomerID(customerID) && verifyFirstName(firstName) && verifySurname(surname) && verifyAddress(address) && verifyPhoneNumber(phoneNumber)) 
        {

            this.customerID = customerID;
            this.customerFirstName = firstName;
            this.customerSurname = surname;
            this.customerAddress = address;
            this.customerPhoneNumber = phoneNumber;

            return true;
        }

        return false;
    }
    
    // Add customer to the list
    public void addCustomer(CustomerBook customer) {

        if (customer == null || customer.customerID == null) {
            throw new IllegalArgumentException(
                    "Customer and customer ID cannot be null");
        }

        if (findCustomerByID(customer.getCustomerID()) != null) {
            throw new IllegalArgumentException(
                    "Customer ID already exists");
        }

        customers.add(customer);
    }

    // Find customer using their ID
    public CustomerBook findCustomerByID(String customerID) {

        if (customerID == null) {
            return null;
        }

        for (CustomerBook customer : customers) {
            if (customer.getCustomerID().equals(customerID)) {
                return customer;
            }
        }

        return null;
    }

    // Update customer details
    public boolean updateCustomer( String customerID, String firstName, String surname, String address, String phoneNumber)
    {

        CustomerBook customer = findCustomerByID(customerID);

        if (customer == null) {
            return false;
        }

        // Checks all details before updating
        if (!customer.verifyFirstName(firstName)|| !customer.verifySurname(surname) || !customer.verifyAddress(address) || !customer.verifyPhoneNumber(phoneNumber))
        {

            return false;
        }

        customer.customerFirstName = firstName;
        customer.customerSurname = surname;
        customer.customerAddress = address;
        customer.customerPhoneNumber = phoneNumber;

        return true;
    }

    // Delete customer using their ID
    public boolean deleteCustomer(String customerID) {

        CustomerBook customer = findCustomerByID(customerID);

        if (customer != null) {
            customers.remove(customer);
            return true;
        }

        return false;
    }

    // Getters
    public String getCustomerID() {
        return customerID;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }
}
