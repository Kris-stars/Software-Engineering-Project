import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerBookDAO {

    // CREATE - Save customer
    public void insert(CustomerBook customer) throws SQLException {

        String sql = "INSERT INTO customer_book "
                + "(customer_id, first_name, surname, address, phone_number) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, customer.getCustomerID());
            ps.setString(2, customer.getCustomerFirstName());
            ps.setString(3, customer.getCustomerSurname());
            ps.setString(4, customer.getCustomerAddress());
            ps.setString(5, customer.getCustomerPhoneNumber());

            ps.executeUpdate();
        }
    }

    // READ - Get all customers
    public List<CustomerBook> getAll() throws SQLException {

        List<CustomerBook> customers = new ArrayList<>();

        String sql = "SELECT customer_id, first_name, surname, "
                   + "address, phone_number FROM customer_book";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                customers.add(new CustomerBook(
                    rs.getString("customer_id"),
                    rs.getString("first_name"),
                    rs.getString("surname"),
                    rs.getString("address"),
                    rs.getString("phone_number")
                ));
            }
        }

        return customers;
    }

    // READ - Find customer by ID
    public CustomerBook getById(String customerId) throws SQLException {

        String sql = "SELECT customer_id, first_name, surname, "
                   + "address, phone_number FROM customer_book "
                   + "WHERE customer_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, customerId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return new CustomerBook(
                        rs.getString("customer_id"),
                        rs.getString("first_name"),
                        rs.getString("surname"),
                        rs.getString("address"),
                        rs.getString("phone_number")
                    );
                }
            }
        }

        return null;
    }

    // UPDATE - Change customer details
    public boolean update(CustomerBook customer) throws SQLException {

        String sql = "UPDATE customer_book SET "
                   + "first_name = ?, surname = ?, "
                   + "address = ?, phone_number = ? "
                   + "WHERE customer_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, customer.getCustomerFirstName());
            ps.setString(2, customer.getCustomerSurname());
            ps.setString(3, customer.getCustomerAddress());
            ps.setString(4, customer.getCustomerPhoneNumber());
            ps.setString(5, customer.getCustomerID());

            return ps.executeUpdate() > 0;
        }
    }

    // DELETE - Remove customer
    public boolean delete(String customerId) throws SQLException {

        String sql = "DELETE FROM customer_book WHERE customer_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, customerId);

            return ps.executeUpdate() > 0;
        }
    }
}