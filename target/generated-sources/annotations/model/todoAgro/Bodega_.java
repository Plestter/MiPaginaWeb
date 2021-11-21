package model.todoAgro;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.todoAgro.OrdencompraProducto;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-11-21T09:33:47")
@StaticMetamodel(Bodega.class)
public class Bodega_ { 

    public static volatile ListAttribute<Bodega, OrdencompraProducto> ordencompraProductoList;
    public static volatile SingularAttribute<Bodega, Integer> bdgTelefono;
    public static volatile SingularAttribute<Bodega, Integer> bdgBodegaid;
    public static volatile SingularAttribute<Bodega, String> bdgDireccion;
    public static volatile SingularAttribute<Bodega, String> bdgNombre;

}