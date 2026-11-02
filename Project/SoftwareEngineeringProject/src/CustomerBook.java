
public class CustomerBook {

    private String customerID;
    private String customerFirstName;
    private String customerSurname;
    private String customerAddress;
    private String customerPhoneNumber;

    // Create customer
    public CustomerBook(String customerID, String customerFirstName, String customerSurname, String customerAddress, String customerPhoneNumber) {

        setCustomerID(customerID);
        setCustomerFirstName(customerFirstName);
        setCustomerSurname(customerSurname);
        setCustomerAddress(customerAddress);
        setCustomerPhoneNumber(customerPhoneNumber);
    }

    // valid customer id so making sure the id's are 16 characters long
    public void setCustomerID(String customerID) {
        if (customerID == null || customerID.length() != 16)
        {
            throw new IllegalArgumentException("Customer ID must be 16 characters");
        }
        this.customerID = customerID;
    }

    // setting the max letters for the first name
    public void setCustomerFirstName(String customerFirstName) {
        if (customerFirstName == null || customerFirstName.trim().isEmpty() || customerFirstName.trim().length() > 16)
        {
            throw new IllegalArgumentException("First name must be 1 to 16 characters");
        }
        this.customerFirstName = customerFirstName;
    }

    // setting the max letters the for surname
    public void setCustomerSurname(String customerSurname) {
        if (customerSurname == null || customerSurname.trim().isEmpty() || customerSurname.trim().length() > 16) 
        {
            throw new IllegalArgumentException("Surname must be 1 to 16 characters");
        }
        this.customerSurname = customerSurname;
    }

    // setting the max letters for the address
    public void setCustomerAddress(String customerAddress) {
        if (customerAddress == null || customerAddress.trim().isEmpty() || customerAddress.trim().length() > 16)
        {
            throw new IllegalArgumentException("Address must be 1 to 16 characters");
        }
        this.customerAddress = customerAddress;
    }

    // making sure the phone number is valid and only accepts numbers
    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        if (customerPhoneNumber == null || !customerPhoneNumber.matches("[0-9]{10}")) 
        {
            throw new IllegalArgumentException("Phone number must contain 10 digits");
        }
        this.customerPhoneNumber = customerPhoneNumber;
    }

    // Read customer details
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
