package model.todoAgro;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.todoAgro.Categoria;
import model.todoAgro.DetalleVencimiento;
import model.todoAgro.FacturaProducto;
import model.todoAgro.OrdencompraProducto;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-11-21T09:33:47")
@StaticMetamodel(Producto.class)
public class Producto_ { 

    public static volatile SingularAttribute<Producto, String> pdtDescripcion;
    public static volatile ListAttribute<Producto, OrdencompraProducto> ordencompraProductoList;
    public static volatile ListAttribute<Producto, DetalleVencimiento> detalleVencimientoList;
    public static volatile ListAttribute<Producto, FacturaProducto> facturaProductoList;
    public static volatile SingularAttribute<Producto, Double> pdtValorventa;
    public static volatile SingularAttribute<Producto, Categoria> fkCategoria;
    public static volatile SingularAttribute<Producto, String> pdtNombre;
    public static volatile SingularAttribute<Producto, String> pdtImagen;
    public static volatile SingularAttribute<Producto, Integer> pdtProductoid;
    public static volatile SingularAttribute<Producto, Integer> pdtTotal;

}