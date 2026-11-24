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
    static Connection conn;

    static {
        try {
            conn = DatabaseConnection.getConnection();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws SQLException, IOException {

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

            int stockActualProducto = inventarioDAO.consultarStock(pedido.getIdProducto());

            if (stockActualProducto == -1) {
                System.out.println("El producto no existe en el inventario.");
                exit(0);
            } else {
                System.out.println("Stock: " + stockActualProducto);
            }
            pedidoDAO pedidoDAO = new pedidoDAO();

            boolean insertado = pedidoDAO.procesarPedido(conn, pedido);

            if (!insertado) {
                conn.rollback();
                System.out.println("No se ha podido insertar el pedido.");
                return;
            }

            System.out.println("Pedido insertado. ID: " + pedido.getIdPedido());

            int nuevoStock = stockActualProducto - cantidad;

            inventarioDAO.actualizarStock(conn, pedido.getIdProducto(), nuevoStock);

            Envio nuevoEnvio = new Envio(pedido.getIdProducto());
            envioDAO envioDAO = new envioDAO();

            if(nuevoStock >= 10){
                envioDAO.insertar(conn, nuevoEnvio);
                pedidoDAO.actualizarEstado(conn, pedido.getIdPedido(),"enviado");
                System.out.println("Envio realizado con éxito. Quedaban mas de 10 unidades.");
            }else{
                conn.rollback();
                System.out.println("Stock insuficiente para completar el pedido. Quedarian menos de 10ud");
            }
        } catch (SQLException | IOException e) {
            conn.rollback();
            System.out.println("Error se deshizo la transaccion.");

        }

        conn.close();
        sc.close();


    }

}
