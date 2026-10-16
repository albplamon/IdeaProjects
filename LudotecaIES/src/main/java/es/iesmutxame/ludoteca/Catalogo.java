package es.iesmutxame.ludoteca;

import java.util.ArrayList;

public class Catalogo {
    private ArrayList<Videojuego> listaJuegos;

    public Catalogo(){
        this.listaJuegos = new ArrayList<>();
    }

    public boolean comprobarID(int id){
        for(Videojuego juego : listaJuegos){
            if(juego.getID() == id){
                return true;
            }
        }
        return false;
    }
    public void añadirJuego(Videojuego videojuego) {
        if (comprobarID(videojuego.getID())) {
            throw new IllegalArgumentException("Ya existe un videojuego con el ID " + videojuego.getID());
        }
        listaJuegos.add(videojuego);
        System.out.println("Juego añadido con éxito!");
    }
    public void buscarPorID(int id){
        for(Videojuego juego : listaJuegos){
            if(juego.getID()== id){
                System.out.println("El juego con ID: " + id + " es: "+ juego.getTitulo());
            }
        }
    }
    public void eliminarPorID(int id){
        for(Videojuego juego : listaJuegos){
            if(juego.getID()== id) {
                listaJuegos.remove(juego);
                break;
            }
        }
    }
    public int obtenerCantidad(){
        return listaJuegos.size();
    }

    public void mostrarJuegos(){
        if(listaJuegos.isEmpty()){
            System.out.println("No hay ningún juego en el catálogo.");
        }else {
            System.out.println(listaJuegos);
        }
    }
    public void mostrarCatalogo() {
        for(Videojuego j : listaJuegos){
            int total = (int) (j.getStock() * j.getPrecio());
            System.out.println("ID: " + j.getID() +
                    " - Titulo: " + j.getTitulo() +
                    " - Stock: " + j.getStock() +
                    " -Precio Total Stock: " + total);

        }
    }
    public void compararPorUD(int cantidad) {
        int encontrados = 0;
        for (Videojuego j : listaJuegos) {
            if (j.getStock() <= cantidad) {
                System.out.println("-El juego " + j.getTitulo() + ", " + j.getStock() + " unidades");
                encontrados++;
            }
        }
        if (encontrados == 0) {
            System.out.println("No hay juegos con menos que eso.");
        }
    }
}
