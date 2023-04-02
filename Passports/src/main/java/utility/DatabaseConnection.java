package utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    public static Connection getConnection() {
        
        Connection connection = null;
        String url = "jdbc:derby://localhost:1527/PassportDB";
        String username = "app";
        String password = "app";

        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");

            connection = DriverManager.getConnection(url, username, password);

        } catch (Exception e) {

            e.printStackTrace();
        }
        return connection;
    }
}
