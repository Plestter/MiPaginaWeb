/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.todoAgro;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.todoAgro.UsuHasRol;

/**
 *
 * @author nombre
 */
@Stateless
public class UsuHasRolFacade extends AbstractFacade<UsuHasRol> implements UsuHasRolFacadeLocal {

	@PersistenceContext(unitName = "primepoolagro")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuHasRolFacade() {
        super(UsuHasRol.class);
    }
    
    @Override
   	public Boolean registrarUsuario(Integer idUsu) {
   		try {
   			Query q = em.createNativeQuery(
   					"INSERT INTO usuario_has_rol (fk_usuarioid, fk_rolid) VALUES (?, ?)");
   			q.setParameter(1, idUsu);
   			q.setParameter(2, 3);		
   			q.executeUpdate();
   			return true;
   		} catch (Exception e) {
   			return false;
   		}
   	}

}
