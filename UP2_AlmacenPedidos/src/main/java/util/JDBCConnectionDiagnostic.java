package util;

import util.DatabaseConnection;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/** SESIÓN 2. Diagnóstico de errores de conexión y SQLException. */
public class JDBCConnectionDiagnostic {

    public static void muestraErrorSQL(SQLException e) {
        System.err.println("Error en la operación con la base de datos.");
        System.err.println("Descripción: " + e.getMessage());
        System.err.println("SQLState: " + e.getSQLState());
        System.err.println("Código del motor: " + e.getErrorCode());

        SQLException siguiente = e.getNextException();
        while (siguiente != null) {
            System.err.println("Error asociado: " + siguiente.getMessage());
            siguiente = siguiente.getNextException();
        }
    }

    // SESIÓN 2. Reto práctico: ejecutar esta clase de forma independiente.
    // Primero se comprueba la conexión correcta; después se fuerza un error
    // con una contraseña falsa SIN modificar el fichero config.properties.
    public static void main(String[] args) {
        System.out.println("Prueba 1. Conexión correcta:");
        try (Connection con = DatabaseConnection.getConnection()) {
            System.out.println("Conexión establecida con " +
                    con.getMetaData().getDatabaseProductName());
        } catch (IOException e) {
            System.err.println("Error de configuración: " + e.getMessage());
            return;
        } catch (SQLException e) {
            muestraErrorSQL(e);
            return;
        }

        System.out.println("Prueba 2. Contraseña incorrecta (error esperado):");
        Properties propiedades = new Properties();
        try (InputStream entrada = Files.newInputStream(Path.of("config.properties"))) {
            propiedades.load(entrada);
            String url = propiedades.getProperty("db.url");
            String usuario = propiedades.getProperty("db.user");
            try (Connection con = DriverManager.getConnection(
                    url, usuario, "contrasena_incorrecta_UP2")) {
                System.out.println("Conexión inesperada: revisa la configuración del servidor.");
            } catch (SQLException e) {
                muestraErrorSQL(e);
            }
        } catch (IOException e) {
            System.err.println("Error de configuración: " + e.getMessage());
        }
    }
}
