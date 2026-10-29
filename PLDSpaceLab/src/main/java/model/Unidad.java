package model;

public class Unidad {

    private int id;
    private String codigo;
    private String modelo;
    private String estado;
    private int responsableId;


    public Unidad() {
    }

    public Unidad(String codigo, String modelo, String estado, int responsableId) {
        this.codigo = codigo;
        this.modelo = modelo;
        this.estado = estado;
        this.responsableId = responsableId;
    }

    public Unidad(int id, String codigo, String modelo, String estado, int responsableId) {
        this.id = id;
        this.codigo = codigo;
        this.modelo = modelo;
        this.estado = estado;
        this.responsableId = responsableId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getResponsableId() {
        return responsableId;
    }

    public void setResponsableId(int responsableId) {
        this.responsableId = responsableId;
    }

    @Override
    public String toString() {
        return "Unidad{id=" + id +
                ", codigo='" + codigo + '\'' +
                ", modelo='" + modelo + '\'' +
                ", estado='" + estado + '\'' +
                ", responsableId=" + responsableId + '}';
    }
}