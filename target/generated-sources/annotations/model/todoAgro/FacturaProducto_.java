package model.todoAgro;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.todoAgro.Factura;
import model.todoAgro.Producto;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-11-21T09:33:47")
@StaticMetamodel(FacturaProducto.class)
public class FacturaProducto_ { 

    public static volatile SingularAttribute<FacturaProducto, Factura> fkFactura;
    public static volatile SingularAttribute<FacturaProducto, Producto> fkProducto;
    public static volatile SingularAttribute<FacturaProducto, Double> fptValorunidad;
    public static volatile SingularAttribute<FacturaProducto, Integer> fptCantidad;
    public static volatile SingularAttribute<FacturaProducto, Integer> fptFacturaproductoid;

}