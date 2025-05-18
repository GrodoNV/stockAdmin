package app;

import model.Presentacion;
import model.Producto;
import service.InventarioService;
import utils.Utils;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InventarioService servicio = new InventarioService();

        while (true) {
            System.out.println("\n--- Menú de Inventario ---");
            System.out.println("1. Agregar producto");
            System.out.println("2. Mostrar productos");
            System.out.println("3. Editar producto");
            System.out.println("4. Eliminar presentación");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del producto: ");
                    String nombre = scanner.nextLine();

                    System.out.print("Precio de compra: ");
                    double compra = Double.parseDouble(scanner.nextLine());

                    System.out.print("Precio de venta base: ");
                    double venta = Double.parseDouble(scanner.nextLine());

                    Producto producto = new Producto(nombre, compra, venta);

                    // 🟡 Pedir lista de presentaciones
                    List<Presentacion> presentaciones = Utils.pedirPresentaciones(scanner);

                    // 🟡 Agregar usando el método del servicio
                    try {
                        servicio.agregarProductoConPresentaciones(producto, presentaciones);
                        System.out.println("Producto agregado exitosamente.");
                    } catch (Exception e) {
                        System.out.println("Error al agregar producto: " + e.getMessage());
                    }
                    break;

                case 2:
                    servicio.mostrarStockDeTodosLosProductos();
                    break;

                case 3:
                    System.out.print("Nombre del producto: ");
                    String nombreStock = scanner.nextLine();
                    System.out.print("Tipo de presentación: ");
                    String tipoPres = scanner.nextLine();
                    System.out.print("Cantidad a agregar: ");
                    int cant = Integer.parseInt(scanner.nextLine());
                    servicio.agregarStock(nombreStock, tipoPres, cant);
                    break;

                case 4:
                    System.out.print("Nombre del producto a eliminar: ");
                    String nombreElim = scanner.nextLine();
                    servicio.eliminarProducto(nombreElim);
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    return;

                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}
