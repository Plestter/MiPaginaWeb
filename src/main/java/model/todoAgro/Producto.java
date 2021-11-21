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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p"),
    @NamedQuery(name = "Producto.findByPdtProductoid", query = "SELECT p FROM Producto p WHERE p.pdtProductoid = :pdtProductoid"),
    @NamedQuery(name = "Producto.findByPdtNombre", query = "SELECT p FROM Producto p WHERE p.pdtNombre = :pdtNombre"),
    @NamedQuery(name = "Producto.findByPdtDescripcion", query = "SELECT p FROM Producto p WHERE p.pdtDescripcion = :pdtDescripcion"),
    @NamedQuery(name = "Producto.findByPdtValorventa", query = "SELECT p FROM Producto p WHERE p.pdtValorventa = :pdtValorventa"),
    @NamedQuery(name = "Producto.findByPdtTotal", query = "SELECT p FROM Producto p WHERE p.pdtTotal = :pdtTotal")})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pdt_productoid")
    private Integer pdtProductoid;
    @Size(max = 255)
    @Column(name = "pdt_nombre")
    private String pdtNombre;
    @Size(max = 255)
    @Column(name = "pdt_descripcion")
    private String pdtDescripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "pdt_valorventa")
    private Double pdtValorventa;
    @Column(name = "pdt_total")
    private Integer pdtTotal;
    @OneToMany(mappedBy = "fkProducto")
    private List<FacturaProducto> facturaProductoList;
    @OneToMany(mappedBy = "fkProducto")
    private List<OrdencompraProducto> ordencompraProductoList;
    @JoinColumn(name = "fk_categoria", referencedColumnName = "cat_categoriaid")
    @ManyToOne
    private Categoria fkCategoria;
    @OneToMany(mappedBy = "fkProducto")
    private List<DetalleVencimiento> detalleVencimientoList;
    @Column(name = "pdt_imagen")
    private String pdtImagen;
    
    public Producto() {
    }

    public Producto(Integer pdtProductoid) {
        this.pdtProductoid = pdtProductoid;
    }

    public Integer getPdtProductoid() {
        return pdtProductoid;
    }

    public void setPdtProductoid(Integer pdtProductoid) {
        this.pdtProductoid = pdtProductoid;
    }

    public String getPdtNombre() {
        return pdtNombre;
    }

    public void setPdtNombre(String pdtNombre) {
        this.pdtNombre = pdtNombre;
    }

    public String getPdtDescripcion() {
        return pdtDescripcion;
    }

    public void setPdtDescripcion(String pdtDescripcion) {
        this.pdtDescripcion = pdtDescripcion;
    }

    public Double getPdtValorventa() {
        return pdtValorventa;
    }

    public void setPdtValorventa(Double pdtValorventa) {
        this.pdtValorventa = pdtValorventa;
    }

    public Integer getPdtTotal() {
        return pdtTotal;
    }

    public void setPdtTotal(Integer pdtTotal) {
        this.pdtTotal = pdtTotal;
    }

    @XmlTransient
    public List<FacturaProducto> getFacturaProductoList() {
        return facturaProductoList;
    }

    public void setFacturaProductoList(List<FacturaProducto> facturaProductoList) {
        this.facturaProductoList = facturaProductoList;
    }

    @XmlTransient
    public List<OrdencompraProducto> getOrdencompraProductoList() {
        return ordencompraProductoList;
    }

    public void setOrdencompraProductoList(List<OrdencompraProducto> ordencompraProductoList) {
        this.ordencompraProductoList = ordencompraProductoList;
    }

    public Categoria getFkCategoria() {
        return fkCategoria;
    }

    public void setFkCategoria(Categoria fkCategoria) {
        this.fkCategoria = fkCategoria;
    }

    public String getPdtImagen() {
		return pdtImagen;
	}

	public void setPdtImagen(String pdtImagen) {
		this.pdtImagen = pdtImagen;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (pdtProductoid != null ? pdtProductoid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.pdtProductoid == null && other.pdtProductoid != null) || (this.pdtProductoid != null && !this.pdtProductoid.equals(other.pdtProductoid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "oProducto= nombre: "+ this.pdtNombre + " descripcion: " + this.pdtDescripcion + " valor: " + this.pdtValorventa;
    }

	public List<DetalleVencimiento> getDetalleVencimientoList() {
		return detalleVencimientoList;
	}

	public void setDetalleVencimientoList(List<DetalleVencimiento> detalleVencimientoList) {
		this.detalleVencimientoList = detalleVencimientoList;
	}
    
}
