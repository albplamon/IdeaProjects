package dao;

import model.Usuario;
import util.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    // Devuelve el Usuario si las credenciales son correctas, o null si no lo son.
    // Lanza IOException (config) o SQLException (conexión/SQL) para que MainApp las distinga.
    public Usuario login(String usuario, String clave) throws IOException, SQLException {
        String sql = "SELECT id, usuario, clave FROM usuarios WHERE usuario = ? AND clave = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ps.setString(2, clave);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(rs.getInt("id"), rs.getString("usuario"), rs.getString("clave"));
                }
                return null;
            }
        }
    }
}