package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection = null;
    private DatabaseConnection() {}
    static {
        String url = "jdbc:postgresql://localhost:5432/plsdspace_lab";
        String user = "postgres";
        String password = "postgres"; // Contraseña de tu PostgreSQL
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() {
        return connection;
    }
}