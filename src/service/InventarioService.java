package service;

import model.Presentacion;
import model.Producto;
import repository.ProductoRepository;
import repository.InventarioRepository;

import java.util.List;

public class InventarioService {

    private ProductoRepository productoRepo;
    private InventarioRepository inventarioRepo;

    public InventarioService() {
        this.productoRepo = new ProductoRepository();
        this.inventarioRepo = new InventarioRepository();
    }

    // Agrega un producto con sus presentaciones al sistema
    public void agregarProducto(Producto producto) {
        productoRepo.save(producto);
        for (Presentacion p : producto.getPresentaciones()) {
            inventarioRepo.save(producto.getCodigo(), p);
        }
    }

    // Obtiene el stock total en unidades de un producto
    public int obtenerStockTotalUnidades(String codigoProducto) {
        List<Presentacion> presentaciones = inventarioRepo.findByProductoCodigo(codigoProducto);
        int total = 0;
        for (Presentacion p : presentaciones) {
            total += p.getStockUnidades();
        }
        return total;
    }

    // Muestra el stock de un producto por tipo de presentación
    public void mostrarStockPorPresentacion(String codigoProducto) {
        List<Presentacion> presentaciones = inventarioRepo.findByProductoCodigo(codigoProducto);
        System.out.println("Stock del producto " + codigoProducto + ":");
        for (Presentacion p : presentaciones) {
            System.out.println("- " + p.getTipo() + ": " + p.getCantidad() + " pack(s), " +
                    p.getStockUnidades() + " unidades en total");
        }
    }

    // Aumenta la cantidad de una presentación específica
    public void agregarStock(String codigoProducto, String tipo, int cantidadAdicional) {
        List<Presentacion> presentaciones = inventarioRepo.findByProductoCodigo(codigoProducto);
        for (Presentacion p : presentaciones) {
            if (p.getTipo().equalsIgnoreCase(tipo)) {
                int nuevaCantidad = p.getCantidad() + cantidadAdicional;
                inventarioRepo.updateCantidad(codigoProducto, tipo, nuevaCantidad);
                return;
            }
        }
        System.out.println("No se encontró la presentación: " + tipo);
    }

    // Elimina una presentación específica del inventario
    public void eliminarPresentacion(String codigoProducto, String tipo) {
        inventarioRepo.delete(codigoProducto, tipo);
    }
}
