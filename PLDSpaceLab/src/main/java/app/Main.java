package app;
import dao.UnidadDAO;
import dao.UsuarioDAO;
import model.Unidad;
import model.Usuario;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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

        UnidadDAO dao = new UnidadDAO();
        dao.iniciarCampaniaPruebas(1,2);


        int opcion;

        do {
            System.out.println("\n=== GESTIÓN DE UNIDADES MIURA ===");
            System.out.println("1. Listar todas las unidades");
            System.out.println("2. Buscar una unidad por identificador");
            System.out.println("3. Insertar una nueva unidad");
            System.out.println("4. Modificar el estado de una unidad");
            System.out.println("5. Eliminar una unidad");
            System.out.println("6. Iniciar campaña conjunta de pruebas");
            System.out.println("7. Consultar unidades por estado");
            System.out.println("8. Consultar unidades por modelo");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            try {
                switch (opcion) {

                    case 1 -> {
                        List<Unidad> lista = dao.listar();
                        if (lista.isEmpty()) {
                            System.out.println("No hay unidades registradas.");
                        } else {
                            for (Unidad u : lista) {
                                System.out.println(u.getId() + " | " + u.getCodigo() + " | "
                                        + u.getModelo() + " | " + u.getEstado() + " | "
                                        + u.getResponsableId());
                            }
                        }
                    }

                    case 2 -> {
                        System.out.print("Id de la unidad: ");
                        int id = Integer.parseInt(sc.nextLine().trim());
                        Unidad u = dao.buscarPorId(id);
                        if (u == null) {
                            System.out.println("No existe ninguna unidad con id " + id + ".");
                        } else {
                            System.out.println(u.getId() + " | " + u.getCodigo() + " | "
                                    + u.getModelo() + " | " + u.getEstado() + " | "
                                    + u.getResponsableId());
                        }
                    }

                    case 3 -> {
                        System.out.print("Código: ");
                        String codigo = sc.nextLine().trim();
                        System.out.print("Modelo (MIURA 1 / MIURA 5): ");
                        String modelo = sc.nextLine().trim().toUpperCase();
                        System.out.print("Estado (PLANIFICADA / INTEGRACION / EN_ENSAYOS / REVISION): ");
                        String estado = sc.nextLine().trim().toUpperCase();
                        System.out.print("Id del responsable: ");
                        int responsableId = Integer.parseInt(sc.nextLine().trim());

                        Unidad nueva = new Unidad(0, codigo, modelo, estado, responsableId);
                        if (dao.insertar(nueva)) {
                            System.out.println("Unidad insertada correctamente.");
                        } else {
                            System.out.println("No se pudo insertar la unidad.");
                        }
                    }

                    case 4 -> {
                        System.out.print("Id de la unidad: ");
                        int id = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Nuevo estado (PLANIFICADA / INTEGRACION / EN_ENSAYOS / REVISION): ");
                        String estado = sc.nextLine().trim().toUpperCase();

                        if (dao.actualizarEstado(id, estado)) {
                            System.out.println("Estado actualizado.");
                        } else {
                            System.out.println("No existe ninguna unidad con id " + id + ".");
                        }
                    }

                    case 5 -> {
                        System.out.print("Id de la unidad a eliminar: ");
                        int id = Integer.parseInt(sc.nextLine().trim());

                        if (dao.eliminar(id)) {
                            System.out.println("Unidad eliminada.");
                        } else {
                            System.out.println("No existe ninguna unidad con id " + id + ".");
                        }
                    }

                    case 6 -> {
                        System.out.println("Campaña conjunta: pendiente de implementar.");
                    }
                    case 7 -> {
                        System.out.print("Estado (PLANIFICADA / INTEGRACION / EN_ENSAYOS / REVISION): ");
                        String estado = sc.nextLine().trim().toUpperCase();

                        List<Unidad> lista = dao.listarPorEstado(estado);
                        if (lista.isEmpty()) {
                            System.out.println("No hay unidades en estado " + estado + ".");
                        } else {
                            for (Unidad u : lista) {
                                System.out.println(u.getCodigo() + " | " + u.getModelo() + " | "
                                        + u.getEstado() + " | " + u.getResponsableId());
                            }
                        }
                    }

                    case 8 -> {
                        System.out.print("Modelo (MIURA 1 - MIURA 5): ");
                        String modelo = sc.nextLine().trim().toUpperCase();

                        List<Unidad> lista = dao.listarPorModelo(modelo);
                        if (lista.isEmpty()) {
                            System.out.println("No hay unidades del modelo " + modelo + ".");
                        } else {
                            for (Unidad u : lista) {
                                System.out.println(u.getCodigo() + " | " + u.getModelo() + " | "
                                        + u.getEstado() + " | " + u.getResponsableId());
                            }
                        }
                    }

                    case 0 -> System.out.println("Saliendo del programa.");

                    default -> System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debes introducir un número entero válido.");
            } catch (IOException | SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (opcion != 0);

    }


}