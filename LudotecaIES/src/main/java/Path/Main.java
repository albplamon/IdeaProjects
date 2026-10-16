package Path;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;


public class Main {

    public static void main(String[] args) {

        Path carpeta = Paths.get("C:\\Users\\albplamon\\IdeaProjects\\LudotecaIES\\src\\pathsPrueba");

        try {
            Files.createDirectory(carpeta);
            System.out.println("Carpeta creada");
        } catch (IOException e) {
            System.out.println("No se ha podido crear la carpeta o ya existe");
        }

        Path archivo = FileSystems.getDefault().getPath("C:\\Users\\albplamon\\IdeaProjects\\LudotecaIES\\src\\pathsPrueba\\camino.txt");
        try{
            BufferedWriter bw = Files.newBufferedWriter(archivo);
            bw.write("Uno dos, uno dos, probando probando");
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try{
            BufferedReader br = Files.newBufferedReader(archivo);
            System.out.println(br.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        try {
//            Files.delete(archivo);
//            System.out.println("Archivo eliminado con éxito");
//        } catch (IOException e) {
//            System.out.println("Fallo al eliminarlo");
//        }
        Path newArchivo = Paths.get("C:\\Users\\albplamon\\IdeaProjects\\LudotecaIES\\src\\main\\java\\Path\\pathsPrueba\\newCamino.txt");

        try {
            Files.move(archivo, newArchivo);
            System.out.println("Archivo renombrado correctamente");
        } catch (IOException e) {
            System.out.println("Error al renombrar el archivo");
        }

        if(Files.exists(newArchivo)){
            System.out.println("El archivo si existe!");
        }else{
            System.out.println("No existe nada");
        }


    }
}
