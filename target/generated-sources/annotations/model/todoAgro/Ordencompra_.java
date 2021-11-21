package model.todoAgro;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.todoAgro.OrdencompraProducto;
import model.todoAgro.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-11-21T09:33:47")
@StaticMetamodel(Ordencompra.class)
public class Ordencompra_ { 

    public static volatile ListAttribute<Ordencompra, OrdencompraProducto> ordencompraProductoList;
    public static volatile SingularAttribute<Ordencompra, String> odcOrdennumero;
    public static volatile SingularAttribute<Ordencompra, Date> odcFechaIngreso;
    public static volatile SingularAttribute<Ordencompra, Integer> odcCatidad;
    public static volatile SingularAttribute<Ordencompra, Integer> odcOrdencompraid;
    public static volatile SingularAttribute<Ordencompra, Usuario> fkOperario;
    public static volatile SingularAttribute<Ordencompra, Double> odcValorcompra;
    public static volatile SingularAttribute<Ordencompra, String> odcNovedad;

}