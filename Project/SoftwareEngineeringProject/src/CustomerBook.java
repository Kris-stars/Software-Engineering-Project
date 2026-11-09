import java.util.ArrayList;

public class CustomerBook {

    private String customerID;
    private String customerFirstName;
    private String customerSurname;
    private String customerAddress;
    private String customerPhoneNumber;

    // List to store all customers
    private static ArrayList<CustomerBook> customers = new ArrayList<>();

    // Create customer
    public CustomerBook(String customerID, String customerFirstName, String customerSurname, String customerAddress, String customerPhoneNumber) {

        setCustomerID(customerID);
        setCustomerFirstName(customerFirstName);
        setCustomerSurname(customerSurname);
        setCustomerAddress(customerAddress);
        setCustomerPhoneNumber(customerPhoneNumber);
    }

    // Valid customer ID must be 16 characters
    public void setCustomerID(String customerID) {

        if (customerID == null || customerID.length() != 16) {
            throw new IllegalArgumentException("Customer ID must be 16 characters");
        }

        this.customerID = customerID;
    }

    // First name must be 1 to 16 letters
    public void setCustomerFirstName(String customerFirstName) {

        if (customerFirstName == null || customerFirstName.trim().isEmpty()
                || customerFirstName.trim().length() > 16) {
            throw new IllegalArgumentException("First name must be 1 to 16 characters");
        }

        this.customerFirstName = customerFirstName;
    }

    // Surname must be 1 to 16 letters
    public void setCustomerSurname(String customerSurname) {

        if (customerSurname == null || customerSurname.trim().isEmpty()
                || customerSurname.trim().length() > 16) {
            throw new IllegalArgumentException("Surname must be 1 to 16 characters");
        }

        this.customerSurname = customerSurname;
    }

    // Address must be 1 to 16 letters
    public void setCustomerAddress(String customerAddress) {

        if (customerAddress == null || customerAddress.trim().isEmpty()
                || customerAddress.trim().length() > 16) {
            throw new IllegalArgumentException("Address must be 1 to 16 characters");
        }

        this.customerAddress = customerAddress;
    }

    // Phone number must contain exactly 10 digits
    public void setCustomerPhoneNumber(String customerPhoneNumber) {

        if (customerPhoneNumber == null || !customerPhoneNumber.matches("[0-9]{10}")) {
            throw new IllegalArgumentException("Phone number must contain 10 digits");
        }

        this.customerPhoneNumber = customerPhoneNumber;
    }

    //Adds new a customer to the list
    public void addCustomer(CustomerBook customer) {

        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }

        if (findCustomerByID(customer.getCustomerID()) != null) {
            throw new IllegalArgumentException("Customer ID already exists");
        }

        customers.add(customer);
    }

    //Finds a customer using their ID
    public CustomerBook findCustomerByID(String customerID) {

        for (CustomerBook customer : customers) {

            if (customer.getCustomerID().equals(customerID)) {
                return customer;
            }
        }

        return null;
    }

    //Updates a customers details
    public boolean updateCustomer(String customerID, String firstName, String surname, String address, String phoneNumber) {

        CustomerBook customer = findCustomerByID(customerID);

        if (customer == null) {
            return false;
        }

        customer.setCustomerFirstName(firstName);
        customer.setCustomerSurname(surname);
        customer.setCustomerAddress(address);
        customer.setCustomerPhoneNumber(phoneNumber);

        return true;
    }

    // Delete a customer using their ID
    public boolean deleteCustomer(String customerID) {

        CustomerBook customer = findCustomerByID(customerID);

        if (customer == null) {
            return false;
        }

        customers.remove(customer);

        return true;
    }

    // Read customer details using getters
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
