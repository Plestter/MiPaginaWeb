package model.todoAgro;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.todoAgro.Producto;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-11-21T09:33:47")
@StaticMetamodel(DetalleVencimiento.class)
public class DetalleVencimiento_ { 

    public static volatile SingularAttribute<DetalleVencimiento, String> descripcion;
    public static volatile SingularAttribute<DetalleVencimiento, String> fechaIngreso;
    public static volatile SingularAttribute<DetalleVencimiento, String> fechaCaducidad;
    public static volatile SingularAttribute<DetalleVencimiento, String> fechaAlerta;
    public static volatile SingularAttribute<DetalleVencimiento, Integer> idvencimiento;
    public static volatile SingularAttribute<DetalleVencimiento, Producto> fkProducto;
    public static volatile SingularAttribute<DetalleVencimiento, Integer> codigoVencimiento;

}