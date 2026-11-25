package dao;

import model.Envio;
import util.DatabaseConnection;

import java.io.IOException;
import java.sql.*;

public class envioDAO {

    public boolean insertar(Envio envio) {
        String sql = "INSERT INTO Envio (fechaEnvio, idProducto) VALUES (?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {


            ps.setTimestamp(1, Timestamp.valueOf(envio.getFechaEnvio()));
            ps.setInt(2, envio.getIdProducto());

            if (ps.executeUpdate() != 1) {
                return false;
            }
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    envio.setIdEnvio(rs.getInt(1));
                }
            }
            return true;
        } catch (IOException | SQLException e ) {
            System.err.println("Error al insertar: " + e.getMessage());
            return false;
        }
    }
}
