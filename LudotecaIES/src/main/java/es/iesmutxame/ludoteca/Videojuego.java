package es.iesmutxame.ludoteca;

public class Videojuego {

    private int id;
    private String titulo;
    private String plataforma;
    private double precio;
    private int stock;

    public Videojuego(int id, String titulo,String plataforma, double precio, int stock){
        this.id = id;
        this.titulo = titulo;
        this.plataforma = plataforma;
        this.precio = precio;
        this.stock = stock;
    }
    public String getTitulo(){
        return titulo;
    }
    public double getPrecio(){
        return precio;
    }
    public int getStock(){
        return stock;
    }
    public String getPlataforma(){
        return plataforma;
    }
    public int getID(){
        return id;
    }

    @Override
    public String toString(){
        return "-El juego con id " + this.id +
                " se llama " + this.titulo +
                ", la plataforma es " + this.plataforma +
                ", cuesta "+ this.precio + " €" +
                " y quedan " + this.stock + " unidades" + "\n";
    }


}