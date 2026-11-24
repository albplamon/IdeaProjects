package dao;

import model.Envio;

import java.sql.*;

public class envioDAO {

    public boolean insertar(Connection conn, Envio envio) throws SQLException {
        String sql = "INSERT INTO Envio (fechaEnvio, idProducto) VALUES (?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, new String[]{"idenvio"})) {
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
        }
    }
}
