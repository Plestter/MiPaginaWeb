package model.todoAgro;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import model.todoAgro.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-11-21T09:33:47")
@StaticMetamodel(Rol.class)
public class Rol_ { 

    public static volatile SingularAttribute<Rol, String> rolDescripcion;
    public static volatile ListAttribute<Rol, Usuario> usuarioList;
    public static volatile SingularAttribute<Rol, Integer> rolRolid;
    public static volatile SetAttribute<Rol, Usuario> usuarios;
    public static volatile SingularAttribute<Rol, String> rolNombre;

}