package ud2.dao;
import ud2.model.SoftwareAuditado;
import ud2.util.DatabaseConnection;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class SoftwareAuditadoDAO {

    // 1. READ: Listar todo el inventario de software auditado
    public List<SoftwareAuditado> obtenerTodos() {
        List<SoftwareAuditado> lista = new ArrayList<>();
        String sql = "SELECT codigo, nombre, nivel_exposicion FROM software_auditado ORDER BY codigo";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                SoftwareAuditado app = new SoftwareAuditado(
                        rs.getInt("codigo"),
                        rs.getString("nombre"),
                        rs.getObject("nivel_exposicion", Integer.class)
                );
                lista.add(app);
            }
        } catch (IOException | SQLException e) {
            System.out.println("Error al listar software: " + e.getMessage());
        }
        return lista;
    }

    // 2. READ (Filtrado): Buscar aplicaciones cuyo riesgo sea mayor o igual al mínimo indicado
    public List<SoftwareAuditado> buscarPorRiesgoMinimo(int riesgoMinimo) {
        List<SoftwareAuditado> filtrada = new ArrayList<>();

        String sql = "SELECT codigo, nombre, nivel_exposicion "
                + "FROM software_auditado WHERE nivel_exposicion >= ?";


        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, riesgoMinimo);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                SoftwareAuditado app = new SoftwareAuditado(
                        rs.getInt("codigo"),
                        rs.getString("nombre"),
                        rs.getObject("nivel_exposicion", Integer.class)
                );

                filtrada.add(app);
            }

            rs.close();

        } catch (SQLException | IOException e) {
            System.err.println("Error al filtrar software: " + e.getMessage());
        }

        return filtrada;
    }
    public boolean insertarSoftware(SoftwareAuditado app){
        String sql = "INSERT INTO software_auditado "
                + "(codigo, nombre, nivel_exposicion) "
                + "VALUES (?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, app.getCodigo());
            ps.setString(2, app.getNombre());

            if (app.getNivelExposicion() == null) {
                ps.setObject(3, app.getNivelExposicion(), Types.INTEGER);
            } else {
                ps.setInt(3, app.getNivelExposicion());
            }

            return ps.executeUpdate() > 0;

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean actualizarNivelRiesgo(int codigo, int nuevoNivel) {

        String sql = "UPDATE software_auditado "
                + "SET nivel_exposicion = ? "
                + "WHERE codigo = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, nuevoNivel);
            ps.setInt(2, codigo);

            return ps.executeUpdate() > 0;

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean eliminarSoftware(int codigo) {

        String sql = "DELETE FROM software_auditado "
                + "WHERE codigo = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, codigo);

            return ps.executeUpdate() > 0;

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<SoftwareAuditado> buscarSoftwarePorNombre(String palabra) {

        List<SoftwareAuditado> lista = new ArrayList<>();

        String sql = "SELECT * FROM software_auditado "
                + "WHERE nombre ILIKE ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + palabra + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                SoftwareAuditado app = new SoftwareAuditado();

                app.setCodigo(rs.getInt("codigo"));
                app.setNombre(rs.getString("nombre"));

                int nivel = rs.getInt("nivel_exposicion");

                if (rs.wasNull()) {
                    app.setNivelExposicion(null);
                } else {
                    app.setNivelExposicion(nivel);
                }

                lista.add(app);
            }

            rs.close();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return lista;
    }
    public boolean actualizarDosNiveles(
            int codigo1, int nivel1, int codigo2, int nivel2) {
        String sql = "UPDATE software_auditado SET nivel_exposicion = ? WHERE codigo = ?";
        if (codigo1 == codigo2) {
            System.out.println("Debes indicar dos aplicaciones distintas.");
            return false;
        }
        try (Connection con = DatabaseConnection.getConnection()) {
            con.setAutoCommit(false);
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, nivel1);
                ps.setInt(2, codigo1);
                int filas1 = ps.executeUpdate();
                if (filas1 != 1) {
                    throw new SQLException("Primer registro inexistente.");
                }
                ps.setInt(1, nivel2);
                ps.setInt(2, codigo2);
                int filas2 = ps.executeUpdate();
                if (filas2 != 1) {
                    throw new SQLException("Segundo registro inexistente.");
                }
                con.commit();
                return true;
            } catch (SQLException e) {
                try {
                    con.rollback();
                } catch (SQLException errorRollback) {
                    System.out.println("Error al deshacer: " + errorRollback.getMessage());
                }
                System.out.println("Transacción cancelada: "+ e.getMessage());
                return false;
            }
        } catch (IOException | SQLException e) {
            System.out.println("Error de conexión o configuración: " + e.getMessage());
            return false;
        }
    }
}