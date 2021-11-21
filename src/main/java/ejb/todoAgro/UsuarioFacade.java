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

import model.todoAgro.Usuario;

/**
 *
 * @author nombre
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "primepoolagro")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    @Override
    public Usuario getInfoUsuario(Integer idUsuario) {
        try {
        	Query qt = em.createQuery("SELECT u FROM Usuario u WHERE u.usuUsuarioid = :usuUsuarioid");        	
            qt.setParameter("usuUsuarioid", idUsuario);
            return (Usuario) qt.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public Usuario getInfoUsuario(String email) {
        try {
        	Query qt = em.createQuery("SELECT u FROM Usuario u WHERE u.usuCorreo = :usuUsuarioid");        	
            qt.setParameter("usuUsuarioid", email);
            return (Usuario) qt.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public Usuario validarUsuario(String correoIn, String claveIn) {
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query qt = em.createQuery("SELECT u FROM Usuario u WHERE u.usuCorreo = :correoIn AND u.usuClave = :claveIn");
            qt.setParameter("correoIn", correoIn);
            qt.setParameter("claveIn", claveIn);
            return (Usuario) qt.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
   
    
}
