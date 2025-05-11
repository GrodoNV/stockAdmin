package app;

import java.util.Scanner;

import model.Presentacion;
import model.Producto;
import service.InventarioService;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InventarioService servicio = new InventarioService();

        while (true) {
            System.out.println("\n--- Menú de Inventario ---");
            System.out.println("1. Agregar producto");
            System.out.println("2. Mostrar stock de producto");
            System.out.println("3. Agregar stock a presentación");
            System.out.println("4. Eliminar presentación");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del producto: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Código del producto: ");
                    String codigo = scanner.nextLine();
                    System.out.print("Precio de compra: ");
                    double compra = scanner.nextDouble();
                    System.out.print("Precio de venta: ");
                    double venta = scanner.nextDouble();
                    scanner.nextLine(); // limpiar buffer

                    Producto p = new Producto(nombre, codigo, compra, venta);

                    while (true) {
                        System.out.print("Agregar presentación (s/n)? ");
                        String resp = scanner.nextLine();
                        if (!resp.equalsIgnoreCase("s")) break;

                        System.out.print("Tipo (Ej: Caja, Unidad): ");
                        String tipo = scanner.nextLine();
                        System.out.print("Cantidad de packs: ");
                        int cantidad = scanner.nextInt();
                        System.out.print("Unidades por pack: ");
                        int unidadesPorPack = scanner.nextInt();
                        System.out.print("Precio por pack: ");
                        double precioPack = scanner.nextDouble();
                        scanner.nextLine(); // limpiar buffer

                        p.agregarPresentacion(new Presentacion(tipo, cantidad, unidadesPorPack, precioPack));
                    }

                    servicio.agregarProducto(p);
                    System.out.println("Producto agregado exitosamente.");
                    break;

                case 2:
                    System.out.print("Código del producto: ");
                    String codConsulta = scanner.nextLine();
                    servicio.mostrarStockPorPresentacion(codConsulta);
                    System.out.println("Stock total: " + servicio.obtenerStockTotalUnidades(codConsulta));
                    break;

                case 3:
                    System.out.print("Código del producto: ");
                    String codStock = scanner.nextLine();
                    System.out.print("Tipo de presentación: ");
                    String tipoPres = scanner.nextLine();
                    System.out.print("Cantidad a agregar: ");
                    int cant = scanner.nextInt();
                    scanner.nextLine();
                    servicio.agregarStock(codStock, tipoPres, cant);
                    break;

                case 4:
                    System.out.print("Código del producto: ");
                    String codElim = scanner.nextLine();
                    System.out.print("Tipo de presentación: ");
                    String tipoElim = scanner.nextLine();
                    servicio.eliminarPresentacion(codElim, tipoElim);
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
