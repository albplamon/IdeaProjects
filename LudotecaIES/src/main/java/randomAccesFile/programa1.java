package randomAccesFile;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class programa1 {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        try (RandomAccessFile raf = new RandomAccessFile("numeros.dat", "rw")) {

            for (int i = 1; i <= 10; i++) {
                raf.writeInt(i);
            }
            System.out.println("Números guardados");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try (RandomAccessFile raf = new RandomAccessFile("numeros.dat", "r")) {
            long cantidad = raf.length() / 4;
            System.out.println("Hay " + cantidad + " números");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Introduce la posicion del entero que deseas modificar: ");
        int modificacao = sc.nextInt();

        try (RandomAccessFile raf = new RandomAccessFile("numeros.dat", "rw")) {
            long cantidad = raf.length() / 4;
            if (modificacao >= 1 && modificacao <= cantidad) {
                System.out.println("Esta en posicion");
            } else {
                System.out.println("te has columpiado, se sale");
                return;
            }

            long posicion = (modificacao - 1) * 4L;
            System.out.println("El byte en que comienza es " + posicion);

            raf.seek(posicion);
            int valorActual = raf.readInt();
            System.out.println("Ahí hay un " + valorActual + ", ¿lo dejamos o lo cambiamos?");

            System.out.println("Introduce el nuevo valor: ");
            int nuevoValor = sc.nextInt();

            raf.seek(posicion);
            raf.writeInt(nuevoValor);
            System.out.println("Hecho, el " + valorActual + " ya es historia");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}