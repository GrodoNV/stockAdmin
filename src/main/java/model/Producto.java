package model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", unique = true, nullable = false)
    private String codigo;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "precio_compra")
    private double precioCompra;

    @Column(name = "precio_venta")
    private double precioVenta;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Presentacion> presentaciones = new ArrayList<>();

    public Producto() {
        // Hibernate necesita constructor vacío
    }

    public Producto(String nombre, double precioCompra, double precioVenta) {
        this.codigo = generarCodigo();
        this.nombre = nombre;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
    }

    private String generarCodigo() {
        return "P" + System.currentTimeMillis();
    }

    public void agregarPresentacion(Presentacion presentacion) {
        if (presentacion != null) {
            presentacion.setProducto(this); // MUY IMPORTANTE para establecer la relación
            this.presentaciones.add(presentacion);
        }
    }

    public List<Presentacion> getPresentaciones() {
        return presentaciones;
    }

    public void setPresentaciones(List<Presentacion> presentaciones) {
        this.presentaciones = presentaciones;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecioCompra() { return precioCompra; }
    public void setPrecioCompra(double precioCompra) { this.precioCompra = precioCompra; }

    public double getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(double precioVenta) { this.precioVenta = precioVenta; }
}
