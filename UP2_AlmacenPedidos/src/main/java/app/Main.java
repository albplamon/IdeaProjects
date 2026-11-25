package app;

import dao.envioDAO;
import dao.inventarioDAO;
import dao.pedidoDAO;
import model.Envio;
import model.Pedido;
import util.DatabaseConnection;
import util.JDBCConnectionDiagnostic;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {


        try (Connection con = DatabaseConnection.getConnection()) {
            System.out.println(" -- Conexión con AlmacenPedidos establecida --");
        } catch (IOException e) {
            System.err.println(" - No se ha podido leer config.properties: " + e.getMessage());
            return;
        } catch (SQLException e) {
            JDBCConnectionDiagnostic.muestraErrorSQL(e);
            return;
        }


        try(Connection conn = DatabaseConnection.getConnection()) {

            conn.setAutoCommit(false);


            try {
                inventarioDAO inventarioDAO = new inventarioDAO();
                pedidoDAO pedidoDAO = new pedidoDAO();
                envioDAO envioDAO = new envioDAO();

                System.out.println("Introduce el ID del cliente:");
                int idCliente = sc.nextInt();

                System.out.println("Introduce el ID del producto:");
                int idProducto = sc.nextInt();

                System.out.println("Introduce la cantidad:");
                int cantidad = sc.nextInt();

                Pedido pedido = new Pedido(idCliente, idProducto, cantidad);



                int stockActualProducto = inventarioDAO.consultarStock(pedido.getIdProducto());

                if (stockActualProducto == -1) {
                    System.out.println("El producto no existe en el inventario.");
                    conn.rollback();
                    return;
                } else {
                    System.out.println("Stock del producto solicitado: " + stockActualProducto);
                }

                if (stockActualProducto < cantidad) {
                    System.out.println("No hay stock suficiente.");
                    conn.rollback();
                    return;
                }

                int nuevoStock = stockActualProducto - cantidad;

                if (nuevoStock < 10) {
                    conn.rollback();
                    System.out.println("Stock restante insuficiente. Se deshace la transacción.");
                    return;
                }



                if (!pedidoDAO.procesarPedido(pedido, nuevoStock)) {
                    conn.rollback();
                    System.out.println("No se ha podido procesar el pedido.");
                    return;
                }else{
                    System.out.println("Pedido insertado. ID: " + pedido.getIdPedido());
                }

                if (!inventarioDAO.actualizarStock(pedido.getIdProducto(), nuevoStock)) {
                    conn.rollback();
                    System.out.println("No se ha podido actualizar el stock.");
                    return;
                }
                Envio nuevoEnvio = new Envio(pedido.getIdProducto());



                if (!envioDAO.insertar(nuevoEnvio)) {
                    conn.rollback();
                    System.out.println("No se ha podido insertar el envio.");
                    return;
                }

                if (!pedidoDAO.actualizarEstado(pedido.getIdPedido(), "enviado")) {
                    conn.rollback();
                    System.out.println("No se ha podido actualizar el estado.");
                    return;
                }


                System.out.println("Pedido y envío realizados correctamente.");
                conn.commit();


            } catch (SQLException | IOException e) {
                conn.rollback();
                System.out.println("Error " + e + " se deshizo la transaccion.");

            }

            conn.close();
            sc.close();
            
        } catch (SQLException | IOException e) {
            System.err.println("Error" + e.getMessage());
        }




    }

}
