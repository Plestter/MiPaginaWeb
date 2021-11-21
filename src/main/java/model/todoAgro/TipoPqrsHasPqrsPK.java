/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.todoAgro;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author nombre
 */
@Embeddable
public class TipoPqrsHasPqrsPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_pqrs_idtipo_pqrs")
    private int tipoPqrsIdtipoPqrs;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pqrs_idpqrs")
    private int pqrsIdpqrs;

    public TipoPqrsHasPqrsPK() {
    }

    public TipoPqrsHasPqrsPK(int tipoPqrsIdtipoPqrs, int pqrsIdpqrs) {
        this.tipoPqrsIdtipoPqrs = tipoPqrsIdtipoPqrs;
        this.pqrsIdpqrs = pqrsIdpqrs;
    }

    public int getTipoPqrsIdtipoPqrs() {
        return tipoPqrsIdtipoPqrs;
    }

    public void setTipoPqrsIdtipoPqrs(int tipoPqrsIdtipoPqrs) {
        this.tipoPqrsIdtipoPqrs = tipoPqrsIdtipoPqrs;
    }

    public int getPqrsIdpqrs() {
        return pqrsIdpqrs;
    }

    public void setPqrsIdpqrs(int pqrsIdpqrs) {
        this.pqrsIdpqrs = pqrsIdpqrs;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) tipoPqrsIdtipoPqrs;
        hash += (int) pqrsIdpqrs;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPqrsHasPqrsPK)) {
            return false;
        }
        TipoPqrsHasPqrsPK other = (TipoPqrsHasPqrsPK) object;
        if (this.tipoPqrsIdtipoPqrs != other.tipoPqrsIdtipoPqrs) {
            return false;
        }
        if (this.pqrsIdpqrs != other.pqrsIdpqrs) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.todoAgro.TipoPqrsHasPqrsPK[ tipoPqrsIdtipoPqrs=" + tipoPqrsIdtipoPqrs + ", pqrsIdpqrs=" + pqrsIdpqrs + " ]";
    }
    
}
