/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.todoAgro;

import java.util.List;
import javax.ejb.Local;
import model.todoAgro.Pqrs;
import model.todoAgro.Usuario;

/**
 *
 * @author nombre
 */
@Local
public interface PqrsFacadeLocal {

    void create(Pqrs pqrs);

    void edit(Pqrs pqrs);

    void remove(Pqrs pqrs);

    Pqrs find(Object id);

    List<Pqrs> findAll();

    List<Pqrs> findRange(int[] range);

    int count();
    
    Boolean registrarPqr(Pqrs pqr);
    
    Boolean responderPQR(Pqrs pqr,  Usuario usuario, String respuesta);
    
    List<Pqrs> getPQRs();
    
}
