/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.todoAgro;

import java.util.List;
import java.util.TreeMap;

import javax.ejb.Local;
import model.todoAgro.Categoria;

/**
 *
 * @author nombre
 */
@Local
public interface CategoriaFacadeLocal {

    void create(Categoria categoria);

    void edit(Categoria categoria);

    void remove(Categoria categoria);

    Categoria find(Object id);

    List<Categoria> findAll();

    List<Categoria> findRange(int[] range);

    int count();
    
    TreeMap<String, Integer> getListCategoria();
    
    List<Categoria> getCategorias();
    
    Boolean registrarCategoria(Categoria categoria);
    
    Boolean registrarCategoriaCSV(String values);
    
}
