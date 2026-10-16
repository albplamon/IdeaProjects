package es.iesmutxame.ludoteca;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestionarVideojuegos {

    public void escribirVideojuegoAlFinal(Videojuego videojuego) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("videojuegos.txt", true))) {
            bw.write(videojuego.toString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al escribir en el fichero: " + e.getMessage());
        }
    }

    public void escribirVideojuegoAlPrincipio(Videojuego videojuego) {
        List<String> lineas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("videojuegos.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("videojuegos.txt"))) {
            bw.write(videojuego.toString());
            bw.newLine();

            for (String linea : lineas) {
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al escribir en el fichero: " + e.getMessage());
        }
    }

    public void mostrarContenido() {
        boolean hayContenido = false;

        try (BufferedReader br = new BufferedReader(new FileReader("videojuegos.txt"))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
                hayContenido = true;
            }

        } catch (FileNotFoundException e) {
            System.out.println("El fichero no existe.");
            return;
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
            return;
        }

        if (!hayContenido) {
            System.out.println("El fichero está vacío.");
        }
    }
}
