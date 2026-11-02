//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class PublicationsDAO {
//
//    private static final String INSERT_PUBLICATION =
//        "INSERT INTO PublicationsTable " +
//        "(publicationsID, frequency, price) " +
//        "VALUES (?, ?, ?)";
//
//    public boolean addPublication(Publications publication) {
//
//        try (Connection conn = DBConnection.getConnection();
//
//             PreparedStatement stmt = conn.prepareStatement(INSERT_PUBLICATION)) {
//
//            stmt.setString(1, publication.getPublicationsID());
//            stmt.setString(2, publication.getFrequency());
//            stmt.setDouble(3, publication.getPrice());
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

public class PublicationsDAO {

    public void insert(Publications publication) throws SQLException {
        String sql = "INSERT INTO publication (publication_id, frequency, price) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, publication.getPublicationsID());
            ps.setString(2, publication.getFrequency());
            ps.setDouble(3, publication.getPrice());
            ps.executeUpdate();
        }
    }

    public List<Publications> getAll() throws SQLException {
        List<Publications> publications = new ArrayList<>();
        String sql = "SELECT publication_id, frequency, price FROM publication";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                publications.add(new Publications(
                    rs.getString("publication_id"),
                    rs.getString("frequency"),
                    rs.getDouble("price")));
            }
        }
        return publications;
    }
}
