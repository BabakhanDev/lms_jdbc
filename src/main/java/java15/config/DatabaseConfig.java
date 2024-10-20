package java15.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final String url = "jdbc:postgresql://localhost:5432/lms-db";
    private  static final String user = "postgres";
    private static final String password = "postgres";

    public static Connection getConnection() {
        Connection connection = null;

        try {
            connection= DriverManager.getConnection(url,user,password);
            System.out.println("Application is connected successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
}

