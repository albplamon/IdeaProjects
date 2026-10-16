package Serialization;
import java.io.Serializable;

public class Mascota implements Serializable{
    private static final long serialVersionUID = 1L;

    private int id;
    private String nombre;
    private String especie;
    private int edad;

    public Mascota(int id, String nombre, String especie, int edad){
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.edad= edad;
    }
    private int getEdad(){
        return edad;
    }
    private String getNombre(){
        return nombre;
    }
    private String getEspecie(){
        return especie;
    }
    private int getId(){
        return id;
    }

    @Override
    public String toString(){
        return "-ID: " + id +"\n" +
                "-Nombre: " +nombre+"\n"+
                "-Especie "+especie+"\n"+
                "-Edad: " +edad+ " año/s \n" +
                "------------------------";
    }
}
