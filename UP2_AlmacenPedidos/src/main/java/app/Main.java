package app;

import dao.envioDAO;
import dao.inventarioDAO;
import dao.pedidoDAO;
import model.Envio;
import model.Pedido;
import util.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {



        try(Connection conn = DatabaseConnection.getConnection()) {

            conn.setAutoCommit(false);


            try {
                System.out.println("Introduce el ID del cliente:");
                int idCliente = sc.nextInt();

                System.out.println("Introduce el ID del producto:");
                int idProducto = sc.nextInt();

                System.out.println("Introduce la cantidad:");
                int cantidad = sc.nextInt();

                Pedido pedido = new Pedido(idCliente, idProducto, cantidad);

                inventarioDAO inventarioDAO = new inventarioDAO();

                int stockActualProducto = inventarioDAO.consultarStock(conn, pedido.getIdProducto());

                if (stockActualProducto == -1) {
                    System.out.println("El producto no existe en el inventario.");
                    exit(0);
                } else {
                    System.out.println("Stock: " + stockActualProducto);
                }
                if (stockActualProducto < cantidad) {
                    System.out.println("No hay stock suficiente.");
                    conn.rollback();
                    return;
                }
                pedidoDAO pedidoDAO = new pedidoDAO();

                if (!pedidoDAO.procesarPedido(conn, pedido)) {
                    conn.rollback();
                    System.out.println("No se ha podido insertar el pedido.");
                    return;
                }

                System.out.println("Pedido insertado. ID: " + pedido.getIdPedido());

                int nuevoStock = stockActualProducto - cantidad;

                if (nuevoStock < 10) {
                    conn.rollback();
                    System.out.println("Stock insuficiente. Se deshace la transacción.");
                    return;
                }

                if (!inventarioDAO.actualizarStock(conn, pedido.getIdProducto(), nuevoStock)) {
                    conn.rollback();
                    return;
                }
                Envio nuevoEnvio = new Envio(pedido.getIdProducto());

                envioDAO envioDAO = new envioDAO();

                if (!envioDAO.insertar(conn, nuevoEnvio)) {
                    conn.rollback();
                    return;
                }

                if (!pedidoDAO.actualizarEstado(conn, pedido.getIdPedido(), "enviado")) {
                    conn.rollback();
                    return;
                }

                conn.commit();

                System.out.println("Pedido y envío realizados correctamente.");
            } catch (SQLException | IOException e) {
                conn.rollback();
                System.out.println("Error se deshizo la transaccion.");

            }

            conn.close();
            sc.close();
            
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }




    }

}
