package app;

import dao.inventarioDAO;
import model.Pedido;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

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
        } else {
            System.out.println("Stock actual: " + stockActualProducto);
        }
    }

}
