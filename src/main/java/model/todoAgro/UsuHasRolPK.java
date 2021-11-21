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
public class UsuHasRolPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
	@NotNull
	@Column(name = "fk_usuarioid")
	private int usuarioId;
	@Basic(optional = false)
	@NotNull
	@Column(name = "fk_rolid")
	private int rolId;

	public UsuHasRolPK() {
	}

	public UsuHasRolPK(int usuarioId, int rolId) {
		this.usuarioId = usuarioId;
		this.rolId = rolId;
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public int getRolId() {
		return rolId;
	}

	public void setRolId(int rolId) {
		this.rolId = rolId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) usuarioId;
		hash += (int) rolId;
		return hash;
	}
	
	@Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuHasRolPK)) {
            return false;
        }
        UsuHasRolPK other = (UsuHasRolPK) object;
        if (this.usuarioId != other.usuarioId) {
            return false;
        }
        if (this.rolId != other.rolId) {
            return false;
        }
        return true;
    }

}
