package model.todoAgro;

import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.todoAgro.Factura;
import model.todoAgro.Ordencompra;
import model.todoAgro.OrdencompraProducto;
import model.todoAgro.Pqrs;
import model.todoAgro.Rol;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-11-21T09:33:47")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> usuCorreo;
    public static volatile ListAttribute<Usuario, OrdencompraProducto> ordencompraProductoList;
    public static volatile ListAttribute<Usuario, Pqrs> pqrsList;
    public static volatile SingularAttribute<Usuario, String> usuNombres;
    public static volatile SingularAttribute<Usuario, Integer> usuUsuarioid;
    public static volatile SingularAttribute<Usuario, String> usuTelefono;
    public static volatile ListAttribute<Usuario, Ordencompra> ordencompraList;
    public static volatile SetAttribute<Usuario, Rol> roles;
    public static volatile SingularAttribute<Usuario, BigInteger> usuNumerodocumento;
    public static volatile ListAttribute<Usuario, Rol> rolList;
    public static volatile SingularAttribute<Usuario, Short> usuEstado;
    public static volatile SingularAttribute<Usuario, String> usuClave;
    public static volatile SingularAttribute<Usuario, String> usuDireccion;
    public static volatile SingularAttribute<Usuario, String> usuFoto;
    public static volatile ListAttribute<Usuario, Factura> facturaList1;
    public static volatile ListAttribute<Usuario, Factura> facturaList;
    public static volatile SingularAttribute<Usuario, String> usuApellidos;
    public static volatile SingularAttribute<Usuario, String> usuTipodocumento;

}