/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.todoAgro;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.todoAgro.TipoPqrsHasPqrs;

/**
 *
 * @author nombre
 */
@Stateless
public class TipoPqrsHasPqrsFacade extends AbstractFacade<TipoPqrsHasPqrs> implements TipoPqrsHasPqrsFacadeLocal {

    @PersistenceContext(unitName = "primepoolagro")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoPqrsHasPqrsFacade() {
        super(TipoPqrsHasPqrs.class);
    }
    
}
