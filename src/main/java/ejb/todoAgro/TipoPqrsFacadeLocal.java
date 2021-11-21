/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.todoAgro;

import java.util.List;
import javax.ejb.Local;
import model.todoAgro.TipoPqrs;

/**
 *
 * @author nombre
 */
@Local
public interface TipoPqrsFacadeLocal {

    void create(TipoPqrs tipoPqrs);

    void edit(TipoPqrs tipoPqrs);

    void remove(TipoPqrs tipoPqrs);

    TipoPqrs find(Object id);

    List<TipoPqrs> findAll();

    List<TipoPqrs> findRange(int[] range);

    int count();
    
}
