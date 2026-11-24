package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class Pedido {

    private int IdPedido;
    private int idCliente;
    private LocalDateTime fecha;
    private String status;
    private int idProducto;
    private int cantidad;


    public Pedido(int idCliente, int idProducto, int cantidad){
        this.idCliente = idCliente;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.fecha = LocalDateTime.now();
        this.status = "Pendiente";
    }


}
