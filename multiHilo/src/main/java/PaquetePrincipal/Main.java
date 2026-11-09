package PaquetePrincipal;



import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        HiloAuxiliar hilo1 = new HiloAuxiliar();
        System.out.println("Hilo Auxiliar Nuevo: Estado=" + hilo1.getState());

        hilo1.start();
        System.out.println("Hilo Auxiliar Iniciado: Estado=" + hilo1.getState());

        System.out.println("Esperamos a que termine el hilo");
        try {
            hilo1.join();   // el main espera aquí hasta que hilo1 termine
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println("\n Hilo Auxiliar Muerto: Estado=" + hilo1.getState());
        // Ahora siempre imprimirá TERMINATED
    }
}