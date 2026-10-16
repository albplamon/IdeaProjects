package es.iesmutxame.ludoteca;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CatalogoTest {

    private Catalogo catalogo;

    @BeforeEach
    void prepararCatalogoVacio() {
        catalogo = new Catalogo();
    }

    @Test
    void catalogoRecienCreadoNoContieneVideojuegos() {
        assertEquals(0, catalogo.obtenerCantidad());
    }

    @Test
    void anadirUnVideojuegoHaceQueLaCantidadAlmacenadaSeaUno() {
        catalogo.añadirJuego(new Videojuego(101, "Órbita", "PC", 19.95, 7));

        assertEquals(1, catalogo.obtenerCantidad());
    }

    @Test
    void noSePuedenAnadirDosVideojuegosConElMismoIdentificador() {
        catalogo.añadirJuego(new Videojuego(101, "Órbita", "PC", 19.95, 7));
        Videojuego repetido = new Videojuego(101, "Hades", "PC", 20.00, 10);

        assertThrows(IllegalArgumentException.class, () -> catalogo.añadirJuego(repetido));
        assertEquals(1, catalogo.obtenerCantidad());
    }
}