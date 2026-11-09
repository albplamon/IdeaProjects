package app;
import dao.UsuarioDAO;
import model.Usuario;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        System.out.print("Usuario: ");
        String usuario = sc.nextLine();
        System.out.print("Contraseña: ");
        String clave = sc.nextLine();

        try {
            Usuario u = usuarioDAO.login(usuario, clave);

            if (u != null) {
                System.out.println("Acceso permitido. Bienvenido, " + u.getUsuario());
            } else {
                System.out.println("Acceso denegado: usuario o contraseña incorrectos.");
            }

        } catch (IOException e) {
            System.out.println("Error al leer config.properties: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error de base de datos: " + e.getMessage());
        }
    }
}