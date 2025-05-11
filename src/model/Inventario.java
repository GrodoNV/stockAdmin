package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa el inventario general de productos.
 */
public class Inventario {
    private List<Producto> productos;

    public Inventario() {
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public void eliminarProducto(String codigo) {
        productos.removeIf(p -> p.getCodigo().equals(codigo));
    }

    public Producto buscarProducto(String codigo) {
        return productos.stream()
                .filter(p -> p.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }

    public List<Producto> listarProductos() {
        return productos;
    }

    public void actualizarProducto(Producto actualizado) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getCodigo().equals(actualizado.getCodigo())) {
                productos.set(i, actualizado);
                break;
            }
        }
    }
}
