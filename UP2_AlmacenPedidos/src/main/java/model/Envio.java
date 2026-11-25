package model;

import java.time.LocalDateTime;

/**
 * Representa un envío registrado en la tabla Envio
 * de PostgreSQL.
 *
 * Contiene el identificador del envío, la fecha de envío
 * y el identificador del producto asociado.
 *
 *
 */
public class Envio {

    private int idEnvio;
    private LocalDateTime fechaEnvio;
    private int idProducto;

    /**
     * Constructor vacío de la clase Envio.
     */
    public Envio() {
    }

    /**
     * Crea un envío asociado a un producto.
     * El identificador lo genera automáticamente PostgreSQL
     * y la fecha se establece en el momento de su creación.
     *
     * @param idProducto Identificador del producto enviado.
     */
    public Envio(int idProducto) {
        this.fechaEnvio = LocalDateTime.now();
        this.idProducto = idProducto;
    }

    /**
     * Crea un envío con todos sus datos.
     *
     * @param idEnvio Identificador del envío.
     * @param fechaEnvio Fecha en la que se realiza el envío.
     * @param idProducto Identificador del producto asociado.
     */
    public Envio(int idEnvio, LocalDateTime fechaEnvio, int idProducto) {
        this.idEnvio = idEnvio;
        this.fechaEnvio = fechaEnvio;
        this.idProducto = idProducto;
    }

    /**
     * Devuelve el identificador del envío.
     *
     * @return Identificador del envío.
     */
    public int getIdEnvio() {
        return idEnvio;
    }

    /**
     * Establece el identificador del envío.
     *
     * @param idEnvio Nuevo identificador del envío.
     */
    public void setIdEnvio(int idEnvio) {
        this.idEnvio = idEnvio;
    }

    /**
     * Devuelve la fecha en la que se realiza el envío.
     *
     * @return Fecha del envío.
     */
    public LocalDateTime getFechaEnvio() {
        return fechaEnvio;
    }

    /**
     * Establece la fecha del envío.
     *
     * @param fechaEnvio Nueva fecha del envío.
     */
    public void setFechaEnvio(LocalDateTime fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    /**
     * Devuelve el identificador del producto asociado al envío.
     *
     * @return Identificador del producto.
     */
    public int getIdProducto() {
        return idProducto;
    }

    /**
     * Establece el identificador del producto asociado al envío.
     *
     * @param idProducto Nuevo identificador del producto.
     */
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * Devuelve una representación textual del envío,
     * incluyendo su identificador, fecha y producto asociado.
     *
     * @return Cadena con los datos del envío.
     */
    @Override
    public String toString() {
        return "Envio{" +
                "idEnvio=" + idEnvio +
                ", fechaEnvio=" + fechaEnvio +
                ", idProducto=" + idProducto +
                '}';
    }
}