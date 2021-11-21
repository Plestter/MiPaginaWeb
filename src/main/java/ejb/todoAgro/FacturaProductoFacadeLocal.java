/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.todoAgro;

import java.util.List;
import javax.ejb.Local;
import model.todoAgro.FacturaProducto;

/**
 *
 * @author nombre
 */
@Local
public interface FacturaProductoFacadeLocal {

    void create(FacturaProducto facturaProducto);

    void edit(FacturaProducto facturaProducto);

    void remove(FacturaProducto facturaProducto);

    FacturaProducto find(Object id);

    List<FacturaProducto> findAll();

    List<FacturaProducto> findRange(int[] range);

    int count();
    
}
