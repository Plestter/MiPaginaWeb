package model.todoAgro;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.todoAgro.FacturaProducto;
import model.todoAgro.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-11-21T09:33:47")
@StaticMetamodel(Factura.class)
public class Factura_ { 

    public static volatile SingularAttribute<Factura, String> facDirrecionentrega;
    public static volatile SingularAttribute<Factura, String> facTelefono;
    public static volatile SingularAttribute<Factura, Usuario> fkVendedor;
    public static volatile ListAttribute<Factura, FacturaProducto> facturaProductoList;
    public static volatile SingularAttribute<Factura, String> facCiudad;
    public static volatile SingularAttribute<Factura, Integer> facTotalproductos;
    public static volatile SingularAttribute<Factura, Usuario> fkCliente;
    public static volatile SingularAttribute<Factura, Integer> facFacturaid;
    public static volatile SingularAttribute<Factura, Date> facFecha;
    public static volatile SingularAttribute<Factura, Double> facValortotal;
    public static volatile SingularAttribute<Factura, Double> facImpuestos;

}