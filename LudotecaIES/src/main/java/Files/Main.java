package Files;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        File d = new File("C:\\Users\\albplamon\\IdeaProjects\\LudotecaIES\\src\\filesprueba");

        if (d.mkdir()) {
            System.out.println("Éxito");
        } else {
            System.out.println("El directorio ya existe");
        }

        File f = new File("C:\\Users\\albplamon\\IdeaProjects\\LudotecaIES\\src\\filesprueba\\archivo.txt");
        try {
            if (f.createNewFile()) {
                System.out.println("Éxito creaste el archivo");
            } else {
                System.out.println("Ya existe el archivo");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String[] contenido = d.list();

        if (contenido != null) {
            for (String elemento : contenido) {
                System.out.println(elemento);
            }
        }

        if(f.delete()){
            System.out.println("Borrado con éxito");
        }else{
            System.out.println("Vaya chasco");
        }
        File f2 = new File("C:\\Users\\albplamon\\IdeaProjects\\LudotecaIES\\src\\filesprueba\\archivo2.txt");
        File newf = new File("C:\\Users\\albplamon\\IdeaProjects\\LudotecaIES\\src\\filesprueba\\nuevoarchivonueevoNombre.txt");

        try{
            f2.createNewFile();

            if(f2.renameTo(newf)){
                System.out.println("Yes!lo has renombrado!");
            }else{
                System.out.println("Fallaste en el renombramiento");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(newf.exists()){
            System.out.println("Si el archivo existe");
        }else{
            System.out.println("Aqui no hay nada");
        }


    }
}
