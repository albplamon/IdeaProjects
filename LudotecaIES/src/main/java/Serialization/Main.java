package Serialization;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Mascota> listaMascotas = new ArrayList<>();
        listaMascotas.add(new Mascota(420, "Loco","Caniche",5));
        listaMascotas.add(new Mascota(421, "Poco","Bulldog",10));
        listaMascotas.add(new Mascota(422, "Moco","Labrador",1));



        try (ObjectOutputStream salida = new ObjectOutputStream(
                new FileOutputStream("mascotas.dat"))) {
            salida.writeObject(listaMascotas);
            System.out.println("mascotas añadidas");
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }

        try(ObjectInputStream entrada = new ObjectInputStream(
                new FileInputStream("mascotas.dat")
        )){
            ArrayList<Mascota> recuperada = (ArrayList<Mascota>)entrada.readObject();
            for(Mascota mascotuca: recuperada) {
                System.out.println(mascotuca);
            }
        }catch (IOException | ClassNotFoundException e){
            System.out.println("Error al leer: " + e.getMessage());
        }
    }
}
