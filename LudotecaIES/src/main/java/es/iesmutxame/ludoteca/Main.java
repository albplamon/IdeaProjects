package es.iesmutxame.ludoteca;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Videojuego videojuego1 = new Videojuego(101,"Órbita", "PC",19.95, 7);
        Videojuego videojuego2 = new Videojuego(119,"Hades", "PC",20.00, 10);

        Catalogo miLista = new Catalogo();
        miLista.añadirJuego(videojuego1);
        miLista.añadirJuego(videojuego2);

        int opcion;
        do {
            System.out.println("  ");
            menu();
            System.out.println(">>Selecciona una opción:");
            try {
                opcion = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Debes introducir un número.");
                sc.nextLine();
                opcion = -1;
            }
            switch (opcion) {
                case 0:
                    System.out.println("Gracias y Adios!");
                    break;
                case 1:
                    miLista.mostrarJuegos();
                    break;
                case 2:
                    while(true) {
                        try {
                            System.out.println("Que videojuego quieres añadir? ");
                            System.out.println("ID: ");
                            int ID = sc.nextInt();
                            sc.nextLine();
                            if (miLista.comprobarID(ID)) {
                                System.out.println("Este ID ya esta en el catálogo. Saliendo...");
                                break;
                            }
                            System.out.println("Título: ");
                            String titulo = sc.nextLine();
                            System.out.println("Plataforma: ");
                            String plat = sc.nextLine();
                            System.out.println("Precio: ");
                            double precio = sc.nextDouble();
                            sc.nextLine();
                            System.out.println("Stock: ");
                            int stock = sc.nextInt();
                            sc.nextLine();
                            Videojuego j = new Videojuego(ID, titulo, plat, precio, stock);
                            miLista.añadirJuego(j);
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Debes introducir un número.");
                            sc.nextLine();
                        }catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    break;
                case 3:
                    miLista.mostrarCatalogo();
                    break;
                case 4:
                    try {
                        System.out.println("¿Cuantas unidades?");
                        int UD = sc.nextInt();
                        sc.nextLine();
                        if (UD < 0) {
                            System.out.println("El límite de stock no puede ser negativo.");
                        } else {
                            System.out.println("Estos son los juegos con menos unidades de las indicadas");
                            miLista.compararPorUD(UD);
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Debes introducir un número.");
                        sc.nextLine();
                    }
                    break;
                case 5:
                    GestionarVideojuegos gestionar = new GestionarVideojuegos();
                    gestionar.escribirVideojuegoAlFinal(videojuego1);
                    gestionar.escribirVideojuegoAlPrincipio(videojuego2);
                    gestionar.mostrarContenido();
                    break;
                default:
                    System.out.println("No entiendo, prueba otra vez.");
                    break;
            }
        }while (opcion != 0);

    }

    public static void menu(){
        System.out.println("LUDOTECA IES");
        System.out.println("1.Mostrar todos los videojuegos");
        System.out.println("2.Añadir un videojuego");
        System.out.println("3.Mostrar un resumen del catálogo");
        System.out.println("4.Consultar videojuegos con pocas unidades");
        System.out.println("5. Guardar y mostrar los videojuegos en un fichero");
        System.out.println("0.salir");

    }

}