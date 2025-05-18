package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    // Configura los parámetros de conexión
    private static final String URL = "jdbc:postgresql://localhost:5432/admin_stock_db"; // Cambia la URL según tu configuración
    private static final String USER = "postgres_user"; // Usuario de PostgreSQL
    private static final String PASSWORD = "postgres_password"; // Contraseña de PostgreSQL

    // Método para obtener la conexión
    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new SQLException("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    // Método principal para probar la conexión
    public static void main(String[] args) {
        try (Connection connection = getConnection()) {
            if (connection != null) {
                System.out.println("Conexión exitosa a PostgreSQL!");
            } else {
                System.out.println("No se pudo establecer conexión.");
            }
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
    }
}
