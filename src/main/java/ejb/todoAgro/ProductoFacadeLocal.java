/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.todoAgro;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import javax.ejb.Local;
import model.todoAgro.Producto;

/**
 *
 * @author nombre
 */
@Local
public interface ProductoFacadeLocal {

    void create(Producto producto);

    void edit(Producto producto);

    void remove(Producto producto);

    Producto find(Object id);

    List<Producto> findAll();

    List<Producto> findRange(int[] range);

    int count();
    
    Boolean registrarProducto(Producto producto);
    
    List<Producto> getProductos();
    
    HashMap<String, Integer> getMapProductos();
    
    HashSet<Producto> getImagenesProductos();
    
    Boolean registrarProductoCSV(String values);
    
}
