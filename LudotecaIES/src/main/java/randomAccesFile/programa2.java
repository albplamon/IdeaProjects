package randomAccesFile;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class programa2 {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        try (RandomAccessFile raf = new RandomAccessFile("texto.txt", "rw")) {
            raf.setLength(0);
            raf.writeBytes("hola que tal\n");
            raf.writeBytes("la casa es grande\n");
            raf.writeBytes("hola otra vez, hola de nuevo\n");
            raf.writeBytes("aqui no hay nada\n");
            System.out.println("Texto guardado");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Introduce la palabra que quieres buscar: ");
        String palabra = sc.nextLine();
        String mayusculas = palabra.toUpperCase();

        try (RandomAccessFile raf = new RandomAccessFile("texto.txt", "rw")) {
            int modificadas = 0;

            while (true) {
                long inicioLinea = raf.getFilePointer();
                String linea = raf.readLine();

                if (linea == null) {
                    break;
                }

                long inicioSiguiente = raf.getFilePointer();

                if (linea.contains(palabra)) {
                    String nueva = linea.replace(palabra, mayusculas);

                    raf.seek(inicioLinea);
                    raf.writeBytes(nueva);
                    raf.seek(inicioSiguiente);

                    modificadas++;
                }
            }

            if (modificadas == 0) {
                System.out.println("Esa palabra no sale ni de casualidad");
            } else {
                System.out.println("Cambiadas " + modificadas + " líneas");
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}