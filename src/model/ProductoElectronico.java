package model;

/**
 * Subclase que representa un producto electrónico con campos adicionales.
 */
public class ProductoElectronico extends Producto {
    private int garantiaMeses;
    private String marca;

    public ProductoElectronico(String nombre, String codigo, double precioCompra, double precioVenta,
                               Presentacion presentacion, int garantiaMeses, String marca) {
        super(nombre, codigo, precioCompra, precioVenta, presentacion);
        this.garantiaMeses = garantiaMeses;
        this.marca = marca;
    }

    public int getGarantiaMeses() {
        return garantiaMeses;
    }

    public void setGarantiaMeses(int garantiaMeses) {
        this.garantiaMeses = garantiaMeses;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
