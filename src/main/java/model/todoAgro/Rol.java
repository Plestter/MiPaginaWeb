/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.todoAgro;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author nombre
 */
@Entity
@Table(name = "rol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rol.findAll", query = "SELECT r FROM Rol r"),
    @NamedQuery(name = "Rol.findByRolRolid", query = "SELECT r FROM Rol r WHERE r.rolRolid = :rolRolid"),
    @NamedQuery(name = "Rol.findByRolNombre", query = "SELECT r FROM Rol r WHERE r.rolNombre = :rolNombre"),
    @NamedQuery(name = "Rol.findByRolDescripcion", query = "SELECT r FROM Rol r WHERE r.rolDescripcion = :rolDescripcion")})
public class Rol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rol_rolid")
    private Integer rolRolid;
    @Size(max = 45)
    @Column(name = "rol_nombre")
    private String rolNombre;
    @Size(max = 255)
    @Column(name = "rol_descripcion")
    private String rolDescripcion;
    @JoinTable(name = "usuario_has_rol", joinColumns = {
        @JoinColumn(name = "fk_rolid", referencedColumnName = "rol_rolid")}, inverseJoinColumns = {
        @JoinColumn(name = "fk_usuarioid", referencedColumnName = "usu_usuarioid")})
    @ManyToMany
    private List<Usuario> usuarioList;
    @ManyToMany(mappedBy = "roles")
    Set<Usuario> usuarios;

    public Rol() {
    }

    public Rol(Integer rolRolid) {
        this.rolRolid = rolRolid;
    }

    public Integer getRolRolid() {
        return rolRolid;
    }

    public void setRolRolid(Integer rolRolid) {
        this.rolRolid = rolRolid;
    }

    public String getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(String rolNombre) {
        this.rolNombre = rolNombre;
    }

    public String getRolDescripcion() {
        return rolDescripcion;
    }

    public void setRolDescripcion(String rolDescripcion) {
        this.rolDescripcion = rolDescripcion;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (rolRolid != null ? rolRolid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) object;
        if ((this.rolRolid == null && other.rolRolid != null) || (this.rolRolid != null && !this.rolRolid.equals(other.rolRolid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.todoAgro.Rol[ rolRolid=" + rolRolid + " ]";
    }
    
}
