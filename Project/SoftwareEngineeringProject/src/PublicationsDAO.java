import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PublicationsDAO {

    // CREATE - Save publication
    public void insert(Publications publication) throws SQLException {

        String sql = "INSERT INTO publication "
                   + "(publication_id, frequency, price) "
                   + "VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, publication.getPublicationsID());
            ps.setString(2, publication.getFrequency());
            ps.setDouble(3, publication.getPrice());

            ps.executeUpdate();
        }
    }

    // Used by the existing PublicationsCLI
    public boolean addPublication(Publications publication) {

        try {
            insert(publication);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // READ - Get all publications
    public List<Publications> getAll() throws SQLException {

        List<Publications> publications = new ArrayList<>();

        String sql = "SELECT publication_id, frequency, price "
                   + "FROM publication";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                publications.add(new Publications(
                    rs.getString("publication_id"),
                    rs.getString("frequency"),
                    rs.getDouble("price")
                ));
            }
        }

        return publications;
    }

    // READ - Find publication by ID
    public Publications getById(String publicationId) throws SQLException {

        String sql = "SELECT publication_id, frequency, price "
                   + "FROM publication WHERE publication_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, publicationId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return new Publications(
                        rs.getString("publication_id"),
                        rs.getString("frequency"),
                        rs.getDouble("price")
                    );
                }
            }
        }

        return null;
    }

    // UPDATE - Change frequency and price
    public boolean update(Publications publication) throws SQLException {

        String sql = "UPDATE publication SET frequency = ?, "
                   + "price = ? WHERE publication_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, publication.getFrequency());
            ps.setDouble(2, publication.getPrice());
            ps.setString(3, publication.getPublicationsID());

            return ps.executeUpdate() > 0;
        }
    }

    // DELETE - Remove publication
    public boolean delete(String publicationId) throws SQLException {

        String sql = "DELETE FROM publication "
                   + "WHERE publication_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, publicationId);

            return ps.executeUpdate() > 0;
        }
    }
}