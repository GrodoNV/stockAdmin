package model;

import jakarta.persistence.*;

@Entity
@Table(name = "presentaciones")
public class Presentacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;
    private int cantidad;
    private int unidadesPorPack;
    private double precioPack;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    public Presentacion() {
        // Constructor por defecto
    }

    public Presentacion(String tipo, int cantidad, int unidadesPorPack, double precioPack) {
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.unidadesPorPack = unidadesPorPack;
        this.precioPack = precioPack;
    }

    public Long getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getUnidadesPorPack() {
        return unidadesPorPack;
    }

    public void setUnidadesPorPack(int unidadesPorPack) {
        this.unidadesPorPack = unidadesPorPack;
    }

    public double getPrecioPack() {
        return precioPack;
    }

    public void setPrecioPack(double precioPack) {
        this.precioPack = precioPack;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getStockUnidades() {
        return cantidad * unidadesPorPack;
    }

    public double getPrecioUnidad() {
        return precioPack / unidadesPorPack;
    }
}
