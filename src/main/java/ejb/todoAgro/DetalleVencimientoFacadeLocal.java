package ejb.todoAgro;

import java.util.List;

import javax.ejb.Local;

import model.todoAgro.DetalleVencimiento;

@Local
public interface DetalleVencimientoFacadeLocal {

	void create(DetalleVencimiento detalleVencimiento);

	void edit(DetalleVencimiento detalleVencimiento);

	void remove(DetalleVencimiento detalleVencimiento);

	DetalleVencimiento find(Object id);

	List<DetalleVencimiento> findAll();

	List<DetalleVencimiento> findRange(int[] range);

	int count();
	
	List<DetalleVencimiento> getVencimientos();
	
	Boolean registrarVencimiento(DetalleVencimiento vencimiento);
}
