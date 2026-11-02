//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class OrderBookDAO {
//
//    private static final String INSERT_ORDER =
//        "INSERT INTO OrderBookTable " +
//        "(orderNum, customerNum, publicationsID) " +
//        "VALUES (?, ?, ?)";
//
//    public boolean addOrder(OrderBook.Order order) {
//
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement stmt =
//                 conn.prepareStatement(INSERT_ORDER)) {
//
//            stmt.setString(1, order.getOrderNum());
//            stmt.setString(2, order.getCustomerNum());
//            stmt.setString(3,
//                order.getPublication().getPublicationsID());
//
//            int rowsInserted = stmt.executeUpdate();
//
//            return rowsInserted > 0;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//}

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderBookDAO {

    public void insert(OrderBook.Order order) throws SQLException {
        String sql = "INSERT INTO order_book (order_num, customer_num, publication_id) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, order.getOrderNum());
            ps.setString(2, order.getCustomerNum());
            ps.setString(3, order.getPublication().getPublicationsID());
            ps.executeUpdate();
        }
    }

    public List<OrderBook.Order> getAll() throws SQLException {
        List<OrderBook.Order> orders = new ArrayList<>();
        String sql = "SELECT o.order_num, o.customer_num, p.publication_id, p.frequency, p.price "
                   + "FROM order_book o JOIN publication p ON o.publication_id = p.publication_id";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Publications pub = new Publications(
                    rs.getString("publication_id"),
                    rs.getString("frequency"),
                    rs.getDouble("price"));
                orders.add(new OrderBook.Order(
                    rs.getString("order_num"),
                    rs.getString("customer_num"),
                    pub));
            }
        }
        return orders;
    }
}