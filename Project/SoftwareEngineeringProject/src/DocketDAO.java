import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocketDAO {

    // CREATE - Save docket
    public void insert(Docket docket) throws SQLException {

        String sql = "INSERT INTO docket "
                   + "(docket_num, customer_num, area, stock) "
                   + "VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, docket.getDocketnum());
            ps.setString(2, docket.getCustomerNum());
            ps.setDouble(3, docket.getArea());
            ps.setBoolean(4, docket.isStock());

            ps.executeUpdate();
        }
    }

    // READ - Get all dockets
    public List<Docket> getAll() throws SQLException {

        List<Docket> dockets = new ArrayList<>();

        String sql = "SELECT docket_num, customer_num, "
                   + "area, stock FROM docket";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Docket docket = new Docket(
                    rs.getDouble("area"),
                    rs.getString("customer_num"),
                    rs.getString("docket_num")
                );

                docket.setStock(rs.getBoolean("stock"));

                dockets.add(docket);
            }
        }

        return dockets;
    }

    // READ - Find docket by number
    public Docket getByDocketNum(String docketNum) throws SQLException {

        String sql = "SELECT docket_num, customer_num, area, stock "
                   + "FROM docket WHERE docket_num = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, docketNum);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Docket docket = new Docket(
                        rs.getDouble("area"),
                        rs.getString("customer_num"),
                        rs.getString("docket_num")
                    );

                    docket.setStock(rs.getBoolean("stock"));

                    return docket;
                }
            }
        }

        return null;
    }

    // UPDATE - Change docket details
    public boolean update(Docket docket) throws SQLException {

        String sql = "UPDATE docket SET customer_num = ?, "
                   + "area = ?, stock = ? WHERE docket_num = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, docket.getCustomerNum());
            ps.setDouble(2, docket.getArea());
            ps.setBoolean(3, docket.isStock());
            ps.setString(4, docket.getDocketnum());

            return ps.executeUpdate() > 0;
        }
    }

    // DELETE - Remove docket
    public boolean delete(String docketNum) throws SQLException {

        String sql = "DELETE FROM docket WHERE docket_num = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, docketNum);

            return ps.executeUpdate() > 0;
        }
    }
}