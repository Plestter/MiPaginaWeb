/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.todoAgro;

import java.util.List;
import javax.ejb.Local;
import model.todoAgro.OrdencompraProducto;

/**
 *
 * @author nombre
 */
@Local
public interface OrdencompraProductoFacadeLocal {

    void create(OrdencompraProducto ordencompraProducto);

    void edit(OrdencompraProducto ordencompraProducto);

    void remove(OrdencompraProducto ordencompraProducto);

    OrdencompraProducto find(Object id);

    List<OrdencompraProducto> findAll();

    List<OrdencompraProducto> findRange(int[] range);

    int count();
    
}
