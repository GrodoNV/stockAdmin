package service;

import model.Inventario;
import model.Presentacion;
import model.Producto;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class ProductoService {

    public boolean agregarProductoConPresentaciones(Producto producto, List<Presentacion> presentaciones) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            // Asignar las presentaciones al producto (establecer relación)
            for (Presentacion pres : presentaciones) {
                producto.agregarPresentacion(pres);
            }

            // Guardar producto (con cascade, se guardan presentaciones)
            session.persist(producto);

            // Guardar en inventario (opcional, si manejas inventario aparte)
            for (Presentacion p : presentaciones) {
                Inventario inv = new Inventario();
                inv.setProducto(producto);
                inv.setProductoCodigo(producto.getCodigo());
                inv.setTipo(p.getTipo());
                inv.setCantidad(p.getCantidad());
                inv.setUnidadesPorPack(p.getUnidadesPorPack());
                inv.setPrecioPack(p.getPrecioPack());

                session.persist(inv);
            }

            tx.commit();
            return true;

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }
}
