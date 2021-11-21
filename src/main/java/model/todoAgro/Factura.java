/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.todoAgro;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author nombre
 */
@Entity
@Table(name = "factura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f"),
    @NamedQuery(name = "Factura.findByFacFacturaid", query = "SELECT f FROM Factura f WHERE f.facFacturaid = :facFacturaid"),
    @NamedQuery(name = "Factura.findByFacFecha", query = "SELECT f FROM Factura f WHERE f.facFecha = :facFecha"),
    @NamedQuery(name = "Factura.findByFacTotalproductos", query = "SELECT f FROM Factura f WHERE f.facTotalproductos = :facTotalproductos"),
    @NamedQuery(name = "Factura.findByFacValortotal", query = "SELECT f FROM Factura f WHERE f.facValortotal = :facValortotal"),
    @NamedQuery(name = "Factura.findByFacImpuestos", query = "SELECT f FROM Factura f WHERE f.facImpuestos = :facImpuestos"),
    @NamedQuery(name = "Factura.findByFacDirrecionentrega", query = "SELECT f FROM Factura f WHERE f.facDirrecionentrega = :facDirrecionentrega"),
    @NamedQuery(name = "Factura.findByFacTelefono", query = "SELECT f FROM Factura f WHERE f.facTelefono = :facTelefono"),
    @NamedQuery(name = "Factura.findByFacCiudad", query = "SELECT f FROM Factura f WHERE f.facCiudad = :facCiudad")})
public class Factura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "fac_facturaid")
    private Integer facFacturaid;
    @Column(name = "fac_fecha")
    @Temporal(TemporalType.DATE)
    private Date facFecha;
    @Column(name = "fac_totalproductos")
    private Integer facTotalproductos;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "fac_valortotal")
    private Double facValortotal;
    @Column(name = "fac_impuestos")
    private Double facImpuestos;
    @Size(max = 255)
    @Column(name = "fac_dirrecionentrega")
    private String facDirrecionentrega;
    @Size(max = 30)
    @Column(name = "fac_telefono")
    private String facTelefono;
    @Size(max = 45)
    @Column(name = "fac_ciudad")
    private String facCiudad;
    @JoinColumn(name = "fk_cliente", referencedColumnName = "usu_usuarioid")
    @ManyToOne
    private Usuario fkCliente;
    @JoinColumn(name = "fk_vendedor", referencedColumnName = "usu_usuarioid")
    @ManyToOne
    private Usuario fkVendedor;
    @OneToMany(mappedBy = "fkFactura")
    private List<FacturaProducto> facturaProductoList;

    public Factura() {
    }

    public Factura(Integer facFacturaid) {
        this.facFacturaid = facFacturaid;
    }

    public Integer getFacFacturaid() {
        return facFacturaid;
    }

    public void setFacFacturaid(Integer facFacturaid) {
        this.facFacturaid = facFacturaid;
    }

    public Date getFacFecha() {
        return facFecha;
    }

    public void setFacFecha(Date facFecha) {
        this.facFecha = facFecha;
    }

    public Integer getFacTotalproductos() {
        return facTotalproductos;
    }

    public void setFacTotalproductos(Integer facTotalproductos) {
        this.facTotalproductos = facTotalproductos;
    }

    public Double getFacValortotal() {
        return facValortotal;
    }

    public void setFacValortotal(Double facValortotal) {
        this.facValortotal = facValortotal;
    }

    public Double getFacImpuestos() {
        return facImpuestos;
    }

    public void setFacImpuestos(Double facImpuestos) {
        this.facImpuestos = facImpuestos;
    }

    public String getFacDirrecionentrega() {
        return facDirrecionentrega;
    }

    public void setFacDirrecionentrega(String facDirrecionentrega) {
        this.facDirrecionentrega = facDirrecionentrega;
    }

    public String getFacTelefono() {
        return facTelefono;
    }

    public void setFacTelefono(String facTelefono) {
        this.facTelefono = facTelefono;
    }

    public String getFacCiudad() {
        return facCiudad;
    }

    public void setFacCiudad(String facCiudad) {
        this.facCiudad = facCiudad;
    }

    public Usuario getFkCliente() {
        return fkCliente;
    }

    public void setFkCliente(Usuario fkCliente) {
        this.fkCliente = fkCliente;
    }

    public Usuario getFkVendedor() {
        return fkVendedor;
    }

    public void setFkVendedor(Usuario fkVendedor) {
        this.fkVendedor = fkVendedor;
    }

    @XmlTransient
    public List<FacturaProducto> getFacturaProductoList() {
        return facturaProductoList;
    }

    public void setFacturaProductoList(List<FacturaProducto> facturaProductoList) {
        this.facturaProductoList = facturaProductoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (facFacturaid != null ? facFacturaid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Factura)) {
            return false;
        }
        Factura other = (Factura) object;
        if ((this.facFacturaid == null && other.facFacturaid != null) || (this.facFacturaid != null && !this.facFacturaid.equals(other.facFacturaid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.todoAgro.Factura[ facFacturaid=" + facFacturaid + " ]";
    }
    
}
