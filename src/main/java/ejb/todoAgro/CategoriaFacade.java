/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.todoAgro;

import java.util.List;
import java.util.TreeMap;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.todoAgro.Categoria;

/**
 *
 * @author nombre
 */
@Stateless
public class CategoriaFacade extends AbstractFacade<Categoria> implements CategoriaFacadeLocal {

	@PersistenceContext(unitName = "primepoolagro")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public CategoriaFacade() {
		super(Categoria.class);
	}

	@Override
	public TreeMap<String, Integer> getListCategoria() {
		List<Categoria> listCategoria = findAll();
		TreeMap<String, Integer> list = new TreeMap<String, Integer>();

		for (Categoria categoria : listCategoria) {
			list.put(categoria.getCatNombre(), categoria.getCatCategoriaid());
		}
		return list;
	}
	
	@Override
	public List<Categoria> getCategorias() {
		try {
			em.getEntityManagerFactory().getCache().evictAll();
			Query qt = em.createQuery("SELECT distinct c FROM Categoria c");
			return qt.getResultList();
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public Boolean registrarCategoria(Categoria categoria) {
		try {
			Query q = em.createNativeQuery(
					"INSERT INTO categoria (cat_nombre, cat_descripcion) VALUES (?, ?)");
			q.setParameter(1, categoria.getCatNombre());
			q.setParameter(2, categoria.getCatDescripcion());			
			q.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public Boolean registrarCategoriaCSV(String values) {
		try {
			Query q = em.createNativeQuery(
					"INSERT INTO categoria (cat_nombre, cat_descripcion) VALUES " + values);
			q.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
