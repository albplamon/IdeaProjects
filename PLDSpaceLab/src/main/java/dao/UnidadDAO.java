package dao;

import model.Unidad;
import util.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnidadDAO {

    // Opción 1: listar todas las unidades
    public List<Unidad> listar() throws IOException, SQLException {
        String sql = "SELECT id, codigo, modelo, estado, responsable_id FROM unidades ORDER BY id";
        List<Unidad> lista = new ArrayList<>();

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    // Opción 2: buscar por id (devuelve null si no existe)
    public Unidad buscarPorId(int id) throws IOException, SQLException {
        String sql = "SELECT id, codigo, modelo, estado, responsable_id FROM unidades WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
                return null;
            }
        }
    }

    // Opción 3: insertar (devuelve true si se insertó 1 fila)
    public boolean insertar(Unidad u) throws IOException, SQLException {
        String sql = "INSERT INTO unidades (codigo, modelo, estado, responsable_id) VALUES (?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getCodigo());
            ps.setString(2, u.getModelo());
            ps.setString(3, u.getEstado());
            ps.setInt(4, u.getResponsableId());

            return ps.executeUpdate() == 1;
        }
    }

    // Opción 4: modificar estado (false si el id no existe)
    public boolean actualizarEstado(int id, String estado) throws IOException, SQLException {
        String sql = "UPDATE unidades SET estado = ? WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, estado);
            ps.setInt(2, id);

            return ps.executeUpdate() == 1;
        }
    }

    // Opción 5: eliminar (false si el id no existe)
    public boolean eliminar(int id) throws IOException, SQLException {
        String sql = "DELETE FROM unidades WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() == 1;
        }
    }

    // Convierte una fila del ResultSet en un objeto Unidad
    private Unidad mapear(ResultSet rs) throws SQLException {
        return new Unidad(
                rs.getInt("id"),
                rs.getString("codigo"),
                rs.getString("modelo"),
                rs.getString("estado"),
                rs.getInt("responsable_id"));
    }
}