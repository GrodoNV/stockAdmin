package repository;

import model.Presentacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventarioRepository {

    private static final String URL = "jdbc:mysql://localhost:3306/mi_base_de_datos";
    private static final String USER = "root";
    private static final String PASSWORD = "contraseña";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Obtener todas las presentaciones de un producto
    public List<Presentacion> findByProductoCodigo(String codigo) {
        List<Presentacion> presentaciones = new ArrayList<>();
        String query = "SELECT * FROM inventario WHERE producto_codigo = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, codigo);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Presentacion p = new Presentacion(
                        rs.getString("tipo"),
                        rs.getInt("cantidad"),
                        rs.getInt("unidades_por_pack"),
                        rs.getDouble("precio_pack")
                );
                presentaciones.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return presentaciones;
    }

    // Agregar una presentación al inventario
    public void save(String productoCodigo, Presentacion p) {
        String query = "INSERT INTO inventario (producto_codigo, tipo, cantidad, unidades_por_pack, precio_pack) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, productoCodigo);
            stmt.setString(2, p.getTipo());
            stmt.setInt(3, p.getCantidad());
            stmt.setInt(4, p.getUnidadesPorPack());
            stmt.setDouble(5, p.getPrecioPack());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Actualizar stock de una presentación
    public void updateCantidad(String productoCodigo, String tipo, int nuevaCantidad) {
        String query = "UPDATE inventario SET cantidad = ? WHERE producto_codigo = ? AND tipo = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, nuevaCantidad);
            stmt.setString(2, productoCodigo);
            stmt.setString(3, tipo);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Eliminar una presentación específica
    public void delete(String productoCodigo, String tipo) {
        String query = "DELETE FROM inventario WHERE producto_codigo = ? AND tipo = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, productoCodigo);
            stmt.setString(2, tipo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
