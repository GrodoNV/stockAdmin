package model;

public class Producto {
    protected String nombre;
    protected String codigo;
    protected double precioCompra;
    protected double precioVenta;
    protected Presentacion presentacion;

    public Producto(String nombre, String codigo, double precioCompra, double precioVenta, Presentacion presentacion) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.presentacion = presentacion;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Presentacion getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(Presentacion presentacion) {
        this.presentacion = presentacion;
    }
}
