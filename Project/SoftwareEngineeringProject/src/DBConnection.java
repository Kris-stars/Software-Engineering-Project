import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
        "jdbc:mysql://localhost:3306/billing_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";          // our Workbench username
    private static final String PASSWORD = "yourPassword"; // our Workbench password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
