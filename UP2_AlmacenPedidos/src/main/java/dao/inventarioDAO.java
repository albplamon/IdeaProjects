package dao;

import model.Pedido;
import util.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class inventarioDAO {

    public int consultarStock(int idProducto) throws SQLException, IOException {

        String sql = "SELECT stock FROM Inventario WHERE idProducto = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idProducto);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return rs.getInt(1);
                }

                return -1;
            }
        }
    }
}
