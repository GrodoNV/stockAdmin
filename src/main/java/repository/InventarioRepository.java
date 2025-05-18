package repository;

import jakarta.transaction.SystemException;
import jakarta.transaction.Transaction;
import model.Presentacion;
import model.Producto;
import model.Inventario;
import org.hibernate.Session;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventarioRepository {

    private static final String URL = "jdbc:postgresql://localhost:5432/admin_stock_db";
    private static final String USER = "postgres_user";
    private static final String PASSWORD = "postgres_password";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // ✅ Obtener todas las filas de la tabla inventario
    public List<Inventario> findAllInventario() {
        List<Inventario> inventarioList = new ArrayList<>();
        String query = "SELECT i.*, p.id AS p_id, p.codigo AS p_codigo, p.nombre AS p_nombre " +
                "FROM inventario i " +
                "JOIN productos p ON i.producto_id = p.id";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Inventario inv = new Inventario();
                inv.setProductoCodigo(rs.getString("producto_codigo"));
                inv.setTipo(rs.getString("tipo"));
                inv.setCantidad(rs.getInt("cantidad"));
                inv.setUnidadesPorPack(rs.getInt("unidades_por_pack"));
                inv.setPrecioPack(rs.getDouble("precio_pack"));

                Producto prod = new Producto();
                prod.setId(rs.getLong("p_id"));            // uso alias para evitar conflicto
                prod.setCodigo(rs.getString("p_codigo"));
                prod.setNombre(rs.getString("p_nombre"));

                inv.setProducto(prod);

                inventarioList.add(inv);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return inventarioList;
    }

    public void save(Inventario inventario) throws SystemException {
        Session session = util.HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = (Transaction) session.beginTransaction();
            session.persist(inventario);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    // 🔍 Obtener todas las presentaciones de un producto
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

    // ➕ Agregar una presentación al inventario
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

    // 🔄 Actualizar stock de una presentación
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

    // ❌ Eliminar una presentación específica
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
