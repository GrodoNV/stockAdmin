package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Presentacion;

public class Utils {

    public static List<Presentacion> pedirPresentaciones(Scanner scanner) {
        List<Presentacion> presentaciones = new ArrayList<>();

        while (true) {
            System.out.print("¿Agregar una presentación? (s/n): ");
            String respuesta = scanner.nextLine().trim();
            if (!respuesta.equalsIgnoreCase("s")) {
                break;
            }

            System.out.print("Tipo de presentación: ");
            String tipo = scanner.nextLine();

            System.out.print("Cantidad: ");
            int cantidad = Integer.parseInt(scanner.nextLine());

            System.out.print("Unidades por pack: ");
            int unidadesPorPack = Integer.parseInt(scanner.nextLine());

            System.out.print("Precio del pack: ");
            double precioPack = Double.parseDouble(scanner.nextLine());

            Presentacion p = new Presentacion(tipo, cantidad, unidadesPorPack, precioPack);
            presentaciones.add(p);
        }

        return presentaciones;
    }
}
