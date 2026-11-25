package model;

/**
 * Representa un registro de inventario almacenado
 * en la tabla Inventario de PostgreSQL.
 *
 * Contiene el identificador del inventario,
 * el stock disponible y el identificador del producto asociado.
 *
 * Sesión 3 - Modelo POJO de la UP2.
 */
public class Inventario {

    private int id;
    private int stock;
    private int idProducto;

    /**
     * Constructor vacío de la clase Inventario.
     */
    public Inventario() {
    }

    /**
     * Crea un inventario asociado a un producto
     * con una cantidad de stock determinada.
     *
     * El identificador lo genera automáticamente PostgreSQL.
     *
     * @param stock Cantidad de unidades disponibles.
     * @param idProducto Identificador del producto.
     */
    public Inventario(int stock, int idProducto) {
        this.stock = stock;
        this.idProducto = idProducto;
    }

    /**
     * Crea un inventario con todos sus datos.
     *
     * @param id Identificador del inventario.
     * @param stock Cantidad de unidades disponibles.
     * @param idProducto Identificador del producto.
     */
    public Inventario(int id, int stock, int idProducto) {
        this.id = id;
        this.stock = stock;
        this.idProducto = idProducto;
    }

    /**
     * Devuelve el identificador del inventario.
     *
     * @return Identificador del inventario.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador del inventario.
     *
     * @param id Nuevo identificador del inventario.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Devuelve la cantidad de stock disponible.
     *
     * @return Cantidad de stock.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Establece la cantidad de stock disponible.
     *
     * @param stock Nueva cantidad de stock.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Devuelve el identificador del producto asociado.
     *
     * @return Identificador del producto.
     */
    public int getIdProducto() {
        return idProducto;
    }

    /**
     * Establece el identificador del producto asociado.
     *
     * @param idProducto Nuevo identificador del producto.
     */
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * Devuelve una representación textual del inventario,
     * incluyendo su identificador, stock y producto asociado.
     *
     * @return Cadena con los datos del inventario.
     */
    @Override
    public String toString() {
        return "Inventario{" +
                "id=" + id +
                ", stock=" + stock +
                ", idProducto=" + idProducto +
                '}';
    }
}