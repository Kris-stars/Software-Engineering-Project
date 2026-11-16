import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {

    public void insert(Bill bill) throws SQLException {
    	 String sql = "INSERT INTO bill "
                 + "(area, customer_num, docket_num) "
                 + "VALUES (?, ?, ?)";

      try (Connection con = DBConnection.getConnection();
           PreparedStatement ps = con.prepareStatement(sql)) {

          ps.setDouble(1, bill.getArea());
          ps.setString(2, bill.getCustomerNum());
          ps.setString(3, bill.getDocketnum());

          ps.executeUpdate();
      }
  }

  // Used by the existing BillCLI
  public boolean addBill(Bill bill) {

      try {
          insert(bill);
          return true;
      } catch (SQLException e) {
          e.printStackTrace();
          return false;
      }
  }

  // READ - Get all bills
  public List<Bill> getAll() throws SQLException {

      List<Bill> bills = new ArrayList<>();

      String sql = "SELECT area, customer_num, docket_num "
                 + "FROM bill";

      try (Connection con = DBConnection.getConnection();
           PreparedStatement ps = con.prepareStatement(sql);
           ResultSet rs = ps.executeQuery()) {

          while (rs.next()) {

              bills.add(new Bill(
                  rs.getDouble("area"),
                  rs.getString("customer_num"),
                  rs.getString("docket_num")
              ));
          }
      }

      return bills;
  }

  // READ - Find bill by docket number
  public Bill getByDocketNum(String docketNum) throws SQLException {

      String sql = "SELECT area, customer_num, docket_num "
                 + "FROM bill WHERE docket_num = ?";

      try (Connection con = DBConnection.getConnection();
           PreparedStatement ps = con.prepareStatement(sql)) {

          ps.setString(1, docketNum);

          try (ResultSet rs = ps.executeQuery()) {

              if (rs.next()) {
                  return new Bill(
                      rs.getDouble("area"),
                      rs.getString("customer_num"),
                      rs.getString("docket_num")
                  );
              }
          }
      }

      return null;
  }

  // UPDATE - Change area and customer number
  public boolean update(Bill bill) throws SQLException {

      String sql = "UPDATE bill SET area = ?, "
                 + "customer_num = ? WHERE docket_num = ?";

      try (Connection con = DBConnection.getConnection();
           PreparedStatement ps = con.prepareStatement(sql)) {

          ps.setDouble(1, bill.getArea());
          ps.setString(2, bill.getCustomerNum());
          ps.setString(3, bill.getDocketnum());

          return ps.executeUpdate() > 0;
      }
  }

  // DELETE - Remove bill
  public boolean delete(String docketNum) throws SQLException {

      String sql = "DELETE FROM bill "
                 + "WHERE docket_num = ?";

      try (Connection con = DBConnection.getConnection();
           PreparedStatement ps = con.prepareStatement(sql)) {

          ps.setString(1, docketNum);

          return ps.executeUpdate() > 0;
      }
  }
}