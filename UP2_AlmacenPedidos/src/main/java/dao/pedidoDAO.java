package dao;

import model.Pedido;
import util.DatabaseConnection;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;

public class pedidoDAO {

    public Pedido pidiendo(String usuario, String clave) throws IOException, SQLException {
        String sql = "SELECT id, usuario, clave FROM usuarios WHERE usuario = ? AND clave = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ps.setString(2, clave);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Pedido(rs.getInt("ID"), rs.getInt("usuario"), rs.getString("clave"));
                }
                return null;
            }
        }
    }

    public void insertarTabla(Pedido pedido) throws IOException, SQLException {
        String sql = "INSERT INTO Pedido (IdCliente, idProducto, cantidad) VALUES (?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, pedido.getIdCliente());
            ps.setInt(4, pedido.getIdProducto());
            ps.setInt(5, pedido.getCantidad());

            ps.executeUpdate();
        }
    }
}
