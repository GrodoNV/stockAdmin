package repository;

import model.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductoRepository {

    private static final String URL = "jdbc:mysql://localhost:3306/mi_base_de_datos";  // Cambia la URL, base de datos, y puerto según tu configuración
    private static final String USER = "root";  // Cambia el usuario de la base de datos
    private static final String PASSWORD = "contraseña";  // Cambia la contraseña de la base de datos

    // Método para obtener una conexión a la base de datos
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Crear un producto
    public Producto save(Producto producto) {
        String query = "INSERT INTO productos (codigo, nombre, precioCompra, precioVenta) VALUES (?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, producto.getCodigo());
            statement.setString(2, producto.getNombre());
            statement.setDouble(3, producto.getPrecioCompra());
            statement.setDouble(4, producto.getPrecioVenta());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    producto.setCodigo(generatedKeys.getString(1));  // Asignamos el ID generado
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }

    // Listar todos los productos
    public List<Producto> findAll() {
        List<Producto> productos = new ArrayList<>();
        String query = "SELECT * FROM productos";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Producto producto = new Producto(
                        resultSet.getString("nombre"),
                        resultSet.getString("codigo"),
                        resultSet.getDouble("precioCompra"),
                        resultSet.getDouble("precioVenta")
                );
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    // Buscar un producto por código
    public Optional<Producto> findByCodigo(String codigo) {
        String query = "SELECT * FROM productos WHERE codigo = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, codigo);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Producto producto = new Producto(
                        resultSet.getString("nombre"),
                        resultSet.getString("codigo"),
                        resultSet.getDouble("precioCompra"),
                        resultSet.getDouble("precioVenta")
                );
                return Optional.of(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();  // Retorna un Optional vacío si no se encuentra el producto
    }

    // Eliminar un producto por código
    public boolean delete(Producto producto) {
        String query = "DELETE FROM productos WHERE codigo = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, producto.getCodigo());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Actualizar un producto
    public Producto update(Producto producto) {
        String query = "UPDATE productos SET nombre = ?, precioCompra = ?, precioVenta = ? WHERE codigo = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, producto.getNombre());
            statement.setDouble(2, producto.getPrecioCompra());
            statement.setDouble(3, producto.getPrecioVenta());
            statement.setString(4, producto.getCodigo());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                return producto;  // Si se actualiza correctamente, se devuelve el producto
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  // Si no se encuentra o no se actualiza, retorna null
    }
}
