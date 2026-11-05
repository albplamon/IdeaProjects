package ud2.dao;
import ud2.model.SoftwareAuditado;
import ud2.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class SoftwareAuditadoDAO {
    static Connection con = DatabaseConnection.getConnection();
    // 1. READ: Listar todo el inventario de software auditado
    public List<SoftwareAuditado> obtenerTodos() {
        List<SoftwareAuditado> lista = new ArrayList<>();
        String sql = "SELECT codigo, nombre, nivel_exposicion FROM software_auditado ORDER BY codigo ASC";
        try {

            try (Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    SoftwareAuditado app = new SoftwareAuditado(
                            rs.getInt("codigo"),
                            rs.getString("nombre"),
                            rs.getObject("nivel_exposicion", Integer.class) // Lectura segura de posibles NULL
                    );
                    lista.add(app);
                }
            }
        } catch (SQLException e) {
            System.err.printf("Error SQL al listar software (%s): %s%n", e.getSQLState(),
                    e.getMessage());
        }
        return lista;}
    // 2. READ (Filtrado): Buscar aplicaciones cuyo riesgo sea mayor o igual al mínimo indicado
    public List<SoftwareAuditado> buscarPorRiesgoMinimo(int riesgoMinimo) {
        List<SoftwareAuditado> filtrada = new ArrayList<>();

        String sql = "SELECT codigo, nombre, nivel_exposicion "
                + "FROM software_auditado WHERE nivel_exposicion >= ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

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

        } catch (SQLException e) {
            System.err.println("Error al filtrar software: " + e.getMessage());
        }

        return filtrada;
    }
    public boolean insertarSoftware(SoftwareAuditado app){
        String sql = "INSERT INTO software_auditado "
                + "(codigo, nombre, nivel_exposicion) "
                + "VALUES (?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, app.getCodigo());
            ps.setString(2, app.getNombre());

            if (app.getNivelExposicion() == null) {
                ps.setObject(3, app.getNivelExposicion(), Types.INTEGER);
            } else {
                ps.setInt(3, app.getNivelExposicion());
            }

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean actualizarNivelRiesgo(int codigo, int nuevoNivel) {

        String sql = "UPDATE software_auditado "
                + "SET nivel_exposicion = ? "
                + "WHERE codigo = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, nuevoNivel);
            ps.setInt(2, codigo);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean eliminarSoftware(int codigo) {

        String sql = "DELETE FROM software_auditado "
                + "WHERE codigo = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, codigo);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<SoftwareAuditado> buscarSoftwarePorNombre(String palabra) {

        List<SoftwareAuditado> lista = new ArrayList<>();

        String sql = "SELECT * FROM software_auditado "
                + "WHERE nombre ILIKE ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

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

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}