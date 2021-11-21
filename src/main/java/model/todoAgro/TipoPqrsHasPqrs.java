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
@Table(name = "tipo_pqrs_has_pqrs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoPqrsHasPqrs.findAll", query = "SELECT t FROM TipoPqrsHasPqrs t"),
    @NamedQuery(name = "TipoPqrsHasPqrs.findByTipoPqrsIdtipoPqrs", query = "SELECT t FROM TipoPqrsHasPqrs t WHERE t.tipoPqrsHasPqrsPK.tipoPqrsIdtipoPqrs = :tipoPqrsIdtipoPqrs"),
    @NamedQuery(name = "TipoPqrsHasPqrs.findByPqrsIdpqrs", query = "SELECT t FROM TipoPqrsHasPqrs t WHERE t.tipoPqrsHasPqrsPK.pqrsIdpqrs = :pqrsIdpqrs")})
public class TipoPqrsHasPqrs implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TipoPqrsHasPqrsPK tipoPqrsHasPqrsPK;

    public TipoPqrsHasPqrs() {
    }

    public TipoPqrsHasPqrs(TipoPqrsHasPqrsPK tipoPqrsHasPqrsPK) {
        this.tipoPqrsHasPqrsPK = tipoPqrsHasPqrsPK;
    }

    public TipoPqrsHasPqrs(int tipoPqrsIdtipoPqrs, int pqrsIdpqrs) {
        this.tipoPqrsHasPqrsPK = new TipoPqrsHasPqrsPK(tipoPqrsIdtipoPqrs, pqrsIdpqrs);
    }

    public TipoPqrsHasPqrsPK getTipoPqrsHasPqrsPK() {
        return tipoPqrsHasPqrsPK;
    }

    public void setTipoPqrsHasPqrsPK(TipoPqrsHasPqrsPK tipoPqrsHasPqrsPK) {
        this.tipoPqrsHasPqrsPK = tipoPqrsHasPqrsPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tipoPqrsHasPqrsPK != null ? tipoPqrsHasPqrsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPqrsHasPqrs)) {
            return false;
        }
        TipoPqrsHasPqrs other = (TipoPqrsHasPqrs) object;
        if ((this.tipoPqrsHasPqrsPK == null && other.tipoPqrsHasPqrsPK != null) || (this.tipoPqrsHasPqrsPK != null && !this.tipoPqrsHasPqrsPK.equals(other.tipoPqrsHasPqrsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.todoAgro.TipoPqrsHasPqrs[ tipoPqrsHasPqrsPK=" + tipoPqrsHasPqrsPK + " ]";
    }
    
}
