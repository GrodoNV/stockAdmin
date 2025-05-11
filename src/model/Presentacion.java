package model;

/**
 * Define la forma en la que se presenta un producto (caja, unidad, etc).
 */
public class Presentacion {
    private String tipo; // Ej: "Caja", "Unidad"
    private int unidadesPorPresentacion;

    public Presentacion(String tipo, int unidadesPorPresentacion) {
        this.tipo = tipo;
        this.unidadesPorPresentacion = unidadesPorPresentacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getUnidadesPorPresentacion() {
        return unidadesPorPresentacion;
    }

    public void setUnidadesPorPresentacion(int unidadesPorPresentacion) {
        this.unidadesPorPresentacion = unidadesPorPresentacion;
    }
}
