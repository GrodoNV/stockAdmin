package repository;

import model.Producto;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class ProductoRepository {


    // Guardar un producto
    public Producto save(Producto producto) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(producto);
            transaction.commit();
            return producto;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return null;
    }

    // Buscar producto por nombre (insensible a mayúsculas/minúsculas y espacios)
    public Producto findByNombre(String nombre) {
        List<Producto> productos = findAll(); // Ya definido por ti
        String buscado = toCamelCase(nombre);

        for (Producto p : productos) {
            if (toCamelCase(p.getNombre()).equals(buscado)) {
                return p;
            }
        }

        return null; // No se encontró
    }

    // Convierte una cadena a lowerCamelCase básico
    private String toCamelCase(String input) {
        if (input == null || input.isEmpty()) return "";
        input = input.trim().toLowerCase();
        return input.substring(0, 1).toLowerCase() + input.substring(1);
    }


    // Obtener todos los productos
    public List<Producto> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Producto", Producto.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    // Buscar producto por código
    public Optional<Producto> findByCodigo(String codigo) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Producto producto = session.get(Producto.class, codigo);
            return Optional.ofNullable(producto);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    // Eliminar producto
    public boolean delete(Producto producto) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(producto);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    // Actualizar producto
    public Producto update(Producto producto) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(producto);
            transaction.commit();
            return producto;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }
}
