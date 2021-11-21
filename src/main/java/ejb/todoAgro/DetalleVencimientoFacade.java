package ejb.todoAgro;

import java.time.LocalDate;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.todoAgro.DetalleVencimiento;

@Stateless
public class DetalleVencimientoFacade extends AbstractFacade<DetalleVencimiento>
		implements DetalleVencimientoFacadeLocal {

	@PersistenceContext(unitName = "primepoolagro")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public DetalleVencimientoFacade() {
		super(DetalleVencimiento.class);
	}

	@Override
	public List<DetalleVencimiento> getVencimientos() {
		try {
			em.getEntityManagerFactory().getCache().evictAll();
			Query qt = em.createQuery("SELECT distinct dv FROM DetalleVencimiento dv JOIN dv.fkProducto p");
			return qt.getResultList();
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public Boolean registrarVencimiento(DetalleVencimiento vencimiento) {
		try {
			Query q = em.createNativeQuery(
					"INSERT INTO detallevencimiento (codigoVencimiento, fechaIngreso, fechaCaducidad, fechaAlerta, descripcion, producto_pdt_productoid) VALUES (?, ?, ?, ?, ?, ?)");
			q.setParameter(1, vencimiento.getCodigoVencimiento());
			q.setParameter(2, LocalDate.now());
			q.setParameter(3, vencimiento.getFechaCaducidad());
			q.setParameter(4, vencimiento.getFechaAlerta());
			q.setParameter(5, vencimiento.getDescripcion());
			q.setParameter(6, vencimiento.getFkProducto().getPdtProductoid());
			q.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
