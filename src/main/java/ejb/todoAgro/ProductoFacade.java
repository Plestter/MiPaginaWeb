/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.todoAgro;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.todoAgro.Producto;

/**
 *
 * @author nombre
 */
@Stateless
public class ProductoFacade extends AbstractFacade<Producto> implements ProductoFacadeLocal {

	@PersistenceContext(unitName = "primepoolagro")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public ProductoFacade() {
		super(Producto.class);
	}

	@Override
	public Boolean registrarProducto(Producto producto) {
		try {
			Query q = em.createNativeQuery(
					"INSERT INTO producto (pdt_nombre, pdt_descripcion, pdt_valorventa, fk_categoria, pdt_imagen) VALUES (?, ?, ?, ?, ?)");
			q.setParameter(1, producto.getPdtNombre());
			q.setParameter(2, producto.getPdtDescripcion());
			q.setParameter(3, producto.getPdtValorventa());
			q.setParameter(4, producto.getFkCategoria().getCatCategoriaid());
			q.setParameter(5, producto.getPdtImagen());
			q.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Producto> getProductos() {
		try {
			em.getEntityManagerFactory().getCache().evictAll();
			Query qt = em.createQuery("SELECT distinct p FROM Producto p JOIN p.fkCategoria c");
			return qt.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public HashMap<String, Integer> getMapProductos() {
		List<Producto> listProd = findAll();
		HashMap<String, Integer> list = new HashMap<String, Integer>();
		
		for (Producto producto : listProd) {
			list.put(producto.getPdtProductoid() + " - " + producto.getPdtNombre(), producto.getPdtProductoid());
		}
		return list;
	}
	
	@Override
	public HashSet<Producto> getImagenesProductos() {
		List<Producto> listProd = findAll();
		HashSet<Producto> list = new HashSet<Producto>();
		
		for (Producto producto : listProd) {
			list.add(producto);
		}
		return list;
	}
	
	@Override
	public Boolean registrarProductoCSV(String values) {
		try {
			Query q = em.createNativeQuery(
					"INSERT INTO producto (pdt_nombre, pdt_descripcion, pdt_valorventa, fk_categoria) VALUES " + values);
			q.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
