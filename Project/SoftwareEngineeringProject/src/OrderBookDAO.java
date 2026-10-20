import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderBookDAO {

    private static final String INSERT_ORDER =
        "INSERT INTO OrderBookTable " +
        "(orderNum, customerNum, publicationsID) " +
        "VALUES (?, ?, ?)";

    public boolean addOrder(OrderBook.Order order) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt =
                 conn.prepareStatement(INSERT_ORDER)) {

            stmt.setString(1, order.getOrderNum());
            stmt.setString(2, order.getCustomerNum());
            stmt.setString(3,
                order.getPublication().getPublicationsID());

            int rowsInserted = stmt.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}