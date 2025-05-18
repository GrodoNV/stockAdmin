package service;

import jakarta.transaction.SystemException;
import model.Presentacion;
import model.Producto;
import model.Inventario;
import repository.ProductoRepository;
import repository.InventarioRepository;

import java.util.List;

public class InventarioService {

    private final ProductoRepository productoRepo;
    private final InventarioRepository inventarioRepo;

    public InventarioService() {
        this.productoRepo = new ProductoRepository();
        this.inventarioRepo = new InventarioRepository();
    }

    public void agregarProductoConPresentaciones(Producto producto, List<Presentacion> presentaciones) throws SystemException {
        ProductoService productoService = new ProductoService();

        boolean exito = productoService.agregarProductoConPresentaciones(producto, presentaciones);

        if (!exito) {
            throw new SystemException("No se pudo agregar el producto con inventario.");
        }
    }



    public void mostrarStockDeTodosLosProductos() {
        List<Inventario> inventarios = inventarioRepo.findAllInventario();

        if (inventarios.isEmpty()) {
            System.out.println("No hay productos registrados en inventario.");
            return;
        }

        System.out.println("Listado de productos con stock:");
        System.out.println("--------------------------------------------------------");
        System.out.printf("%-4s | %-8s | %-12s | %-15s | %-8s%n", "ID", "Código", "Nombre", "Presentación", "Cantidad");
        System.out.println("--------------------------------------------------------");

        for (Inventario i : inventarios) {
            Producto p = i.getProducto();
            System.out.printf("%-4d | %-8s | %-12s | %-15s | %-8d%n",
                    p.getId(), p.getCodigo(), p.getNombre(), i.getPresentacion(), i.getCantidad());
        }
    }

    public void agregarStock(String nombreStock, String tipoPres, int cant) {
    }

    public void eliminarProducto(String nombreElim) {
    }
}
