/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.todoAgro;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author nombre
 */
@Entity
@Table(name = "bodega")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bodega.findAll", query = "SELECT b FROM Bodega b"),
    @NamedQuery(name = "Bodega.findByBdgBodegaid", query = "SELECT b FROM Bodega b WHERE b.bdgBodegaid = :bdgBodegaid"),
    @NamedQuery(name = "Bodega.findByBdgNombre", query = "SELECT b FROM Bodega b WHERE b.bdgNombre = :bdgNombre"),
    @NamedQuery(name = "Bodega.findByBdgDireccion", query = "SELECT b FROM Bodega b WHERE b.bdgDireccion = :bdgDireccion"),
    @NamedQuery(name = "Bodega.findByBdgTelefono", query = "SELECT b FROM Bodega b WHERE b.bdgTelefono = :bdgTelefono")})
public class Bodega implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "bdg_bodegaid")
    private Integer bdgBodegaid;
    @Size(max = 45)
    @Column(name = "bdg_nombre")
    private String bdgNombre;
    @Size(max = 255)
    @Column(name = "bdg_direccion")
    private String bdgDireccion;
    @Column(name = "bdg_telefono")
    private Integer bdgTelefono;
    @OneToMany(mappedBy = "fkBodega")
    private List<OrdencompraProducto> ordencompraProductoList;

    public Bodega() {
    }

    public Bodega(Integer bdgBodegaid) {
        this.bdgBodegaid = bdgBodegaid;
    }

    public Integer getBdgBodegaid() {
        return bdgBodegaid;
    }

    public void setBdgBodegaid(Integer bdgBodegaid) {
        this.bdgBodegaid = bdgBodegaid;
    }

    public String getBdgNombre() {
        return bdgNombre;
    }

    public void setBdgNombre(String bdgNombre) {
        this.bdgNombre = bdgNombre;
    }

    public String getBdgDireccion() {
        return bdgDireccion;
    }

    public void setBdgDireccion(String bdgDireccion) {
        this.bdgDireccion = bdgDireccion;
    }

    public Integer getBdgTelefono() {
        return bdgTelefono;
    }

    public void setBdgTelefono(Integer bdgTelefono) {
        this.bdgTelefono = bdgTelefono;
    }

    @XmlTransient
    public List<OrdencompraProducto> getOrdencompraProductoList() {
        return ordencompraProductoList;
    }

    public void setOrdencompraProductoList(List<OrdencompraProducto> ordencompraProductoList) {
        this.ordencompraProductoList = ordencompraProductoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bdgBodegaid != null ? bdgBodegaid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bodega)) {
            return false;
        }
        Bodega other = (Bodega) object;
        if ((this.bdgBodegaid == null && other.bdgBodegaid != null) || (this.bdgBodegaid != null && !this.bdgBodegaid.equals(other.bdgBodegaid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.todoAgro.Bodega[ bdgBodegaid=" + bdgBodegaid + " ]";
    }
    
}
