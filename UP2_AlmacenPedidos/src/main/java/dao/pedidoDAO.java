package dao;

import model.Pedido;
import util.DatabaseConnection;

import java.io.IOException;
import java.sql.*;


public class pedidoDAO {

    public boolean procesarPedido(Pedido pedido, int stockActualProducto){

        String sql = "INSERT INTO Pedido (idCliente, fecha, status, idProducto, cantidad) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {


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
        } catch (IOException | SQLException e) {
            System.err.println("Error al procesar pedido: " + e.getMessage());
            return false;
        }
    }
    public boolean actualizarEstado(int idPedido, String estado){
        String sql = "UPDATE Pedido SET status = ? WHERE idPedido = ?";

        try(Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setString(1, estado);
            ps.setInt(2, idPedido);

            int filas = ps.executeUpdate();

            return filas > 0;
        } catch (IOException | SQLException e) {
            System.err.println("Error al actualizar estado: " + e.getMessage());
            return false;
        }

    }
}
