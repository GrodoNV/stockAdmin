package model;

public class Presentacion {
    private String tipo;  // "Unidad", "Caja", etc.
    private int cantidad; // Número de cajas o unidades
    private int unidadesPorPack;
    private double precioPack;

    public Presentacion(String tipo, int cantidad, int unidadesPorPack, double precioPack) {
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.unidadesPorPack = unidadesPorPack;
        this.precioPack = precioPack;
    }

    // Getters y Setters
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    // Método para obtener el stock de unidades
    public int getStockUnidades() {
        return cantidad * unidadesPorPack;  // Esto te da el total de unidades por presentación
    }

    // Método para obtener el precio por unidad
    public double getPrecioUnidad() {
        return precioPack / unidadesPorPack;
    }
}
