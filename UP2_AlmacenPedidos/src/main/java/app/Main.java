package app;

import dao.inventarioDAO;
import dao.pedidoDAO;
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

        pedidoDAO.procesarPedido(conn, pedido);

        int nuevoStock = stockActualProducto - cantidad;

        inventarioDAO.actualizarStock(conn, pedido.getIdProducto(), nuevoStock);

        if(nuevoStock < 10){

        }


    }

}
