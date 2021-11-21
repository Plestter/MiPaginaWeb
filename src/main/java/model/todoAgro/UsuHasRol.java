/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.todoAgro;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nombre
 */
@Entity
@Table(name = "usuario_has_rol")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuHasRol.findAll", query = "SELECT t FROM UsuHasRol t"),
    @NamedQuery(name = "UsuHasRol.findByUsuHasRol", query = "SELECT t FROM UsuHasRol t WHERE t.usuHasRolPK.usuarioId = :usuarioId")})
public class UsuHasRol implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsuHasRolPK usuHasRolPK;

    public UsuHasRol() {
    }

    public UsuHasRol(UsuHasRolPK usuHasRolPK) {
        this.usuHasRolPK = usuHasRolPK;
    }

    public UsuHasRol(int usuarioId, int rolId) {
        this.usuHasRolPK = new UsuHasRolPK(usuarioId, rolId);
    }

    public UsuHasRolPK getUsuHasRolPK() {
        return usuHasRolPK;
    }

    public void setUsuHasRolPK(UsuHasRolPK usuHasRolPK) {
        this.usuHasRolPK = usuHasRolPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuHasRolPK != null ? usuHasRolPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuHasRol)) {
            return false;
        }
        UsuHasRol other = (UsuHasRol) object;
        if ((this.usuHasRolPK == null && other.usuHasRolPK != null) || (this.usuHasRolPK != null && !this.usuHasRolPK.equals(other.usuHasRolPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.todoAgro.TipoPqrsHasPqrs[ tipoPqrsHasPqrsPK=" + usuHasRolPK + " ]";
    }
    
  
}
