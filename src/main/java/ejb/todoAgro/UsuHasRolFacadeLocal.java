/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.todoAgro;

import java.util.List;
import javax.ejb.Local;

import model.todoAgro.UsuHasRol;

/**
 *
 * @author nombre
 */
@Local
public interface UsuHasRolFacadeLocal {

    void create(UsuHasRol usuario);

    void edit(UsuHasRol usuario);

    void remove(UsuHasRol usuario);

    UsuHasRol find(Object id);

    List<UsuHasRol> findAll();

    List<UsuHasRol> findRange(int[] range);

    int count();
    
    Boolean registrarUsuario(Integer idUsu);
}
