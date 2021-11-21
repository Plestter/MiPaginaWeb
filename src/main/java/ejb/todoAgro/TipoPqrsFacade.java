/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.todoAgro;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.todoAgro.TipoPqrs;

/**
 *
 * @author nombre
 */
@Stateless
public class TipoPqrsFacade extends AbstractFacade<TipoPqrs> implements TipoPqrsFacadeLocal {

    @PersistenceContext(unitName = "primepoolagro")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoPqrsFacade() {
        super(TipoPqrs.class);
    }
    
}
