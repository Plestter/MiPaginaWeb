/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.todoAgro;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nombre
 */
@Entity
@Table(name = "pqrs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pqrs.findAll", query = "SELECT p FROM Pqrs p"),
    @NamedQuery(name = "Pqrs.findByIdpqrs", query = "SELECT p FROM Pqrs p WHERE p.idpqrs = :idpqrs"),
    @NamedQuery(name = "Pqrs.findByCodigoRadicado", query = "SELECT p FROM Pqrs p WHERE p.codigoRadicado = :codigoRadicado"),
    @NamedQuery(name = "Pqrs.findByDescripcion", query = "SELECT p FROM Pqrs p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Pqrs.findByCiudad", query = "SELECT p FROM Pqrs p WHERE p.ciudad = :ciudad")})
public class Pqrs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpqrs")
    private Integer idpqrs;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_radicado")
    private int codigoRadicado;
    @Size(max = 45)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 45)
    @Column(name = "ciudad")
    private String ciudad;
    @JoinColumn(name = "usuario_usu_usuarioid", referencedColumnName = "usu_usuarioid")
    @ManyToOne(optional = false)
    private Usuario usuarioUsuUsuarioid;

    public Pqrs() {
    }

    public Pqrs(Integer idpqrs) {
        this.idpqrs = idpqrs;
    }

    public Pqrs(Integer idpqrs, int codigoRadicado) {
        this.idpqrs = idpqrs;
        this.codigoRadicado = codigoRadicado;
    }

    public Integer getIdpqrs() {
        return idpqrs;
    }

    public void setIdpqrs(Integer idpqrs) {
        this.idpqrs = idpqrs;
    }

    public int getCodigoRadicado() {
        return codigoRadicado;
    }

    public void setCodigoRadicado(int codigoRadicado) {
        this.codigoRadicado = codigoRadicado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Usuario getUsuarioUsuUsuarioid() {
        return usuarioUsuUsuarioid;
    }

    public void setUsuarioUsuUsuarioid(Usuario usuarioUsuUsuarioid) {
        this.usuarioUsuUsuarioid = usuarioUsuUsuarioid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpqrs != null ? idpqrs.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pqrs)) {
            return false;
        }
        Pqrs other = (Pqrs) object;
        if ((this.idpqrs == null && other.idpqrs != null) || (this.idpqrs != null && !this.idpqrs.equals(other.idpqrs))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.todoAgro.Pqrs[ idpqrs=" + idpqrs + " ]";
    }
    
}
