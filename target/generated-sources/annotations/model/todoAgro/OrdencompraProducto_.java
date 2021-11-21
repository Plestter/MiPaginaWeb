package model.todoAgro;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.todoAgro.Bodega;
import model.todoAgro.Ordencompra;
import model.todoAgro.Producto;
import model.todoAgro.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-11-21T09:33:47")
@StaticMetamodel(OrdencompraProducto.class)
public class OrdencompraProducto_ { 

    public static volatile SingularAttribute<OrdencompraProducto, Bodega> fkBodega;
    public static volatile SingularAttribute<OrdencompraProducto, Usuario> fkProveedor;
    public static volatile SingularAttribute<OrdencompraProducto, Producto> fkProducto;
    public static volatile SingularAttribute<OrdencompraProducto, Integer> orpOrdencompraproductoid;
    public static volatile SingularAttribute<OrdencompraProducto, Ordencompra> fkOrdencompra;
    public static volatile SingularAttribute<OrdencompraProducto, Integer> orpCantidad;
    public static volatile SingularAttribute<OrdencompraProducto, Double> orpValorcompra;

}