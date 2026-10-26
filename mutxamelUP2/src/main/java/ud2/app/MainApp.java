package ud2.app;
import ud2.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;


public class MainApp {
    public static void main(String[] args) {
        try (Connection con = DatabaseConnection.getConnection()) {
            if (con != null && !con.isClosed()) {
                System.out.println("BD conectada correctamente.");
            } else {
                System.out.println("No se ha podido establecer la conexión.");
            }
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
    }
}