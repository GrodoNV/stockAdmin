package model;

import java.util.ArrayList;
import java.util.List;

public class Producto {
    protected String nombre;
    protected double precioCompra;
    protected double precioVenta;
    protected List<Presentacion> presentaciones;  // Cambiado a lista

    public Producto(String nombre, double precioCompra, double precioVenta) {
        this.nombre = nombre;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.presentaciones = new ArrayList<>();  // Inicializa la lista
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public List<Presentacion> getPresentaciones() {
        return presentaciones;
    }

    public void setPresentaciones(List<Presentacion> presentaciones) {
        this.presentaciones = presentaciones;
    }

    // Método para agregar una nueva presentación
    public void agregarPresentacion(Presentacion presentacion) {
        this.presentaciones.add(presentacion);
    }
}
