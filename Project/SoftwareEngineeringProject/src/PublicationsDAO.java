import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PublicationsDAO {

    private static final String INSERT_PUBLICATION =
        "INSERT INTO PublicationsTable " +
        "(publicationsID, frequency, price) " +
        "VALUES (?, ?, ?)";

    public boolean addPublication(Publications publication) {

        try (Connection conn = DBConnection.getConnection();

             PreparedStatement stmt = conn.prepareStatement(INSERT_PUBLICATION)) {

            stmt.setString(1, publication.getPublicationsID());
            stmt.setString(2, publication.getFrequency());
            stmt.setDouble(3, publication.getPrice());

            int rowsInserted = stmt.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
