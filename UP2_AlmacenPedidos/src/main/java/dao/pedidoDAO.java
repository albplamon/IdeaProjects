package dao;

import model.Pedido;
import util.DatabaseConnection;

import java.io.IOException;
import java.sql.*;

public class pedidoDAO {

    public boolean procesarPedido(Connection conn, Pedido pedido) throws SQLException {
        String sql = "INSERT INTO Pedido (idCliente, fecha, status, idProducto, cantidad) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql, new String[]{"idpedido"})) {
            ps.setInt(1, pedido.getIdCliente());
            ps.setTimestamp(2, Timestamp.valueOf(pedido.getFecha()));
            ps.setString(3, pedido.getStatus());
            ps.setInt(4, pedido.getIdProducto());
            ps.setInt(5, pedido.getCantidad());

            int filas = ps.executeUpdate();
            if (filas != 1) {
                return false;
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    pedido.setIdPedido(rs.getInt(1));
                }
            }
            return true;
        }
    }
    public boolean actualizarEstado(Connection conn, int idPedido, String estado) throws SQLException{
        String sql = "UPDATE Pedido SET status = ? WHERE idPedido = ?";

        try(PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, estado);
            ps.setInt(2, idPedido);

            int filas = ps.executeUpdate();

            return filas > 0;
        }

    }
}
