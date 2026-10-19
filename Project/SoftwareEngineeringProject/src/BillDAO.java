import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {

    public void insert(Bill bill) throws SQLException {
        String sql = "INSERT INTO bill (area, customer_num, docket_num) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, bill.getArea());
            ps.setString(2, bill.getCustomerNum());
            ps.setString(3, bill.getDocketNum());
            ps.executeUpdate();
        }
    }

    public List<Bill> getAll() throws SQLException {
        List<Bill> bills = new ArrayList<>();
        String sql = "SELECT area, customer_num, docket_num FROM bill";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                bills.add(new Bill(
                    rs.getDouble("area"),
                    rs.getString("customer_num"),
                    rs.getString("docket_num")));
            }
        }
        return bills;
    }
}