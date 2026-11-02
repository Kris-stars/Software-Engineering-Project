//import java.io.FileInputStream;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.Properties;
//
//public class DBConnection {
//
//    private static final Properties props = new Properties();
//
//    static {
//        try (FileInputStream in = new FileInputStream("db.properties")) {
//            props.load(in);
//        } catch (IOException e) {
//            throw new RuntimeException(
//                "db.properties not found. Copy db.properties.example to db.properties and add your password.", e);
//        }
//    }
//
//    public static Connection getConnection() throws SQLException {
//        	String url = props.getProperty("jdbc:mysql://localhost:3306/billing_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC");
//            String user = props.getProperty("root");
//            String password = props.getProperty("yourpassword");
//        
//        System.out.println("Connecting to: " + url);   // remove this line once it works
//
//        return DriverManager.getConnection(url, user, password);
//    }
//}

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static final Properties props = new Properties();

    static {
        try (FileInputStream in = new FileInputStream("db.properties")) {
            Properties raw = new Properties();
            raw.load(in);

            // Clean up the names and values: removes hidden characters
            // that can sneak in when copying and pasting
            for (String key : raw.stringPropertyNames()) {
                String cleanKey = key.replaceAll("[^a-zA-Z.]", "");
                props.setProperty(cleanKey, raw.getProperty(key).trim());
            }
        } catch (IOException e) {
            throw new RuntimeException(
                "db.properties not found. Copy db.properties.example to db.properties and add your password.", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");

        System.out.println("Connecting to: " + url);   // remove once it works

        return DriverManager.getConnection(url, user, password);
    }
}