package model;

import java.time.LocalDateTime;

/**
 * Representa un pedido registrado en la tabla Pedido
 * de PostgreSQL.
 *
 * Contiene el identificador del pedido, el cliente,
 * la fecha, el estado, el producto y la cantidad solicitada.
 *
 * Sesión 3 - Modelo POJO de la UP2.
 */
public class Pedido {

    private int idPedido;
    private int idCliente;
    private LocalDateTime fecha;
    private String status;
    private int idProducto;
    private int cantidad;

    /**
     * Constructor vacío de la clase Pedido.
     */
    public Pedido() {
    }

    /**
     * Crea un pedido nuevo con los datos del cliente,
     * producto y cantidad.
     *
     * La fecha se establece en el momento de su creación
     * y el estado inicial del pedido es Pendiente.
     * El identificador lo genera automáticamente PostgreSQL.
     *
     * @param idCliente Identificador del cliente.
     * @param idProducto Identificador del producto.
     * @param cantidad Cantidad de producto solicitada.
     */
    public Pedido(int idCliente, int idProducto, int cantidad) {
        this.idCliente = idCliente;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.fecha = LocalDateTime.now();
        this.status = "Pendiente";
    }

    /**
     * Crea un pedido con todos sus datos.
     *
     * @param idPedido Identificador del pedido.
     * @param idCliente Identificador del cliente.
     * @param fecha Fecha de creación del pedido.
     * @param status Estado actual del pedido.
     * @param idProducto Identificador del producto.
     * @param cantidad Cantidad de producto solicitada.
     */
    public Pedido(int idPedido, int idCliente, LocalDateTime fecha,
                  String status, int idProducto, int cantidad) {
        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.fecha = fecha;
        this.status = status;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    /**
     * Devuelve el identificador del pedido.
     *
     * @return Identificador del pedido.
     */
    public int getIdPedido() {
        return idPedido;
    }

    /**
     * Establece el identificador del pedido.
     *
     * @param idPedido Nuevo identificador del pedido.
     */
    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    /**
     * Devuelve el identificador del cliente.
     *
     * @return Identificador del cliente.
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * Establece el identificador del cliente.
     *
     * @param idCliente Nuevo identificador del cliente.
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * Devuelve la fecha de creación del pedido.
     *
     * @return Fecha del pedido.
     */
    public LocalDateTime getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha del pedido.
     *
     * @param fecha Nueva fecha del pedido.
     */
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    /**
     * Devuelve el estado actual del pedido.
     *
     * @return Estado del pedido.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Establece el estado del pedido.
     *
     * @param status Nuevo estado del pedido.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Devuelve el identificador del producto.
     *
     * @return Identificador del producto.
     */
    public int getIdProducto() {
        return idProducto;
    }

    /**
     * Establece el identificador del producto.
     *
     * @param idProducto Nuevo identificador del producto.
     */
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * Devuelve la cantidad de producto solicitada.
     *
     * @return Cantidad solicitada.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad de producto solicitada.
     *
     * @param cantidad Nueva cantidad solicitada.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Devuelve una representación textual del pedido,
     * incluyendo su identificador, cliente, fecha,
     * estado, producto y cantidad.
     *
     * @return Cadena con los datos del pedido.
     */
    @Override
    public String toString() {
        return "Pedido{" +
                "idPedido=" + idPedido +
                ", idCliente=" + idCliente +
                ", fecha=" + fecha +
                ", status='" + status + '\'' +
                ", idProducto=" + idProducto +
                ", cantidad=" + cantidad +
                '}';
    }
}