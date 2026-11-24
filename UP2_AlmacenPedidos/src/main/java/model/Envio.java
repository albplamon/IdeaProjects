package model;   // ajusta al paquete que uses

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Envio {
    private int idEnvio;
    private LocalDateTime fechaEnvio;
    private int idProducto;

    // Constructor para crear un envío nuevo (el id lo genera la BD)
    public Envio(int idProducto) {
        this.fechaEnvio = LocalDateTime.now();
        this.idProducto = idProducto;
    }
}