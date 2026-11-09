import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderBookDAO {

    // CREATE - Save order
    public void insert(OrderBook.Order order) throws SQLException {

        String sql = "INSERT INTO order_book "
                   + "(order_num, customer_num, publication_id) "
                   + "VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, order.getOrderNum());
            ps.setString(2, order.getCustomerNum());
            ps.setString(3, order.getPublication().getPublicationsID());

            ps.executeUpdate();
        }
    }

    // Used by the existing OrderBookCLI
    public boolean addOrder(OrderBook.Order order) {

        try {
            insert(order);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // READ - Get all orders
    public List<OrderBook.Order> getAll() throws SQLException {

        List<OrderBook.Order> orders = new ArrayList<>();

        String sql =
            "SELECT o.order_num, o.customer_num, "
          + "p.publication_id, p.frequency, p.price "
          + "FROM order_book o "
          + "JOIN publication p "
          + "ON o.publication_id = p.publication_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Publications pub = new Publications(
                    rs.getString("publication_id"),
                    rs.getString("frequency"),
                    rs.getDouble("price")
                );

                orders.add(new OrderBook.Order(
                    rs.getString("order_num"),
                    rs.getString("customer_num"),
                    pub
                ));
            }
        }

        return orders;
    }

    // READ - Find order by number
    public OrderBook.Order getByOrderNum(String orderNum)
            throws SQLException {

        String sql =
            "SELECT o.order_num, o.customer_num, "
          + "p.publication_id, p.frequency, p.price "
          + "FROM order_book o "
          + "JOIN publication p "
          + "ON o.publication_id = p.publication_id "
          + "WHERE o.order_num = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, orderNum);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Publications pub = new Publications(
                        rs.getString("publication_id"),
                        rs.getString("frequency"),
                        rs.getDouble("price")
                    );

                    return new OrderBook.Order(
                        rs.getString("order_num"),
                        rs.getString("customer_num"),
                        pub
                    );
                }
            }
        }

        return null;
    }

    // UPDATE - Change order details
    public boolean update(OrderBook.Order order) throws SQLException {

        String sql = "UPDATE order_book SET customer_num = ?, "
                   + "publication_id = ? WHERE order_num = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, order.getCustomerNum());
            ps.setString(2, order.getPublication().getPublicationsID());
            ps.setString(3, order.getOrderNum());

            return ps.executeUpdate() > 0;
        }
    }

    // DELETE - Remove order
    public boolean delete(String orderNum) throws SQLException {

        String sql = "DELETE FROM order_book "
                   + "WHERE order_num = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, orderNum);

            return ps.executeUpdate() > 0;
        }
    }
}