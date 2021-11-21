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
import javax.persistence.Id;
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
@Table(name = "tipo_pqrs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoPqrs.findAll", query = "SELECT t FROM TipoPqrs t"),
    @NamedQuery(name = "TipoPqrs.findByIdtipoPqrs", query = "SELECT t FROM TipoPqrs t WHERE t.idtipoPqrs = :idtipoPqrs"),
    @NamedQuery(name = "TipoPqrs.findByNombreTipo", query = "SELECT t FROM TipoPqrs t WHERE t.nombreTipo = :nombreTipo")})
public class TipoPqrs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtipo_pqrs")
    private Integer idtipoPqrs;
    @Size(max = 45)
    @Column(name = "nombre_tipo")
    private String nombreTipo;

    public TipoPqrs() {
    }

    public TipoPqrs(Integer idtipoPqrs) {
        this.idtipoPqrs = idtipoPqrs;
    }

    public Integer getIdtipoPqrs() {
        return idtipoPqrs;
    }

    public void setIdtipoPqrs(Integer idtipoPqrs) {
        this.idtipoPqrs = idtipoPqrs;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipoPqrs != null ? idtipoPqrs.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPqrs)) {
            return false;
        }
        TipoPqrs other = (TipoPqrs) object;
        if ((this.idtipoPqrs == null && other.idtipoPqrs != null) || (this.idtipoPqrs != null && !this.idtipoPqrs.equals(other.idtipoPqrs))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.todoAgro.TipoPqrs[ idtipoPqrs=" + idtipoPqrs + " ]";
    }
    
}
