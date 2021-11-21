/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.todoAgro;

import java.util.List;
import javax.ejb.Local;
import model.todoAgro.TipoPqrsHasPqrs;

/**
 *
 * @author nombre
 */
@Local
public interface TipoPqrsHasPqrsFacadeLocal {

    void create(TipoPqrsHasPqrs tipoPqrsHasPqrs);

    void edit(TipoPqrsHasPqrs tipoPqrsHasPqrs);

    void remove(TipoPqrsHasPqrs tipoPqrsHasPqrs);

    TipoPqrsHasPqrs find(Object id);

    List<TipoPqrsHasPqrs> findAll();

    List<TipoPqrsHasPqrs> findRange(int[] range);

    int count();
    
}
