/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.todoAgro;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.todoAgro.FacturaProducto;

/**
 *
 * @author nombre
 */
@Stateless
public class FacturaProductoFacade extends AbstractFacade<FacturaProducto> implements FacturaProductoFacadeLocal {

    @PersistenceContext(unitName = "primepoolagro")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacturaProductoFacade() {
        super(FacturaProducto.class);
    }
    
}
