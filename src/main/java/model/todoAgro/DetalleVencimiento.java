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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "detallevencimiento")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "DetalleVencimiento.findAll", query = "SELECT dv FROM DetalleVencimiento dv"),
		@NamedQuery(name = "DetalleVencimiento.findByDetalleVencimientoid", query = "SELECT dv FROM DetalleVencimiento dv WHERE dv.idvencimiento = :DetalleVencimientoid"),
		@NamedQuery(name = "DetalleVencimiento.findByDescripcion", query = "SELECT dv FROM DetalleVencimiento dv WHERE dv.descripcion = :Descripcion"),
		@NamedQuery(name = "DetalleVencimiento.findByCodigo", query = "SELECT dv FROM DetalleVencimiento dv WHERE dv.codigoVencimiento = :Codigo") })
public class DetalleVencimiento implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "idvencimiento")
	private Integer idvencimiento;
	@Column(name = "codigoVencimiento")
	private Integer codigoVencimiento;
	@Size(max = 50)
	@Column(name = "fechaIngreso")
	private String fechaIngreso;
	@Size(max = 50)
	@Column(name = "fechaCaducidad")
	private String fechaCaducidad;
	@Size(max = 50)
	@Column(name = "fechaAlerta")
	private String fechaAlerta;
	@Size(max = 255)
	@Column(name = "descripcion")
	private String descripcion;
	@JoinColumn(name = "producto_pdt_productoid", referencedColumnName = "pdt_productoid")
	@ManyToOne
	private Producto fkProducto;

	public Integer getIdvencimiento() {
		return idvencimiento;
	}

	public void setIdvencimiento(Integer idvencimiento) {
		this.idvencimiento = idvencimiento;
	}

	public Integer getCodigoVencimiento() {
		return codigoVencimiento;
	}

	public void setCodigoVencimiento(Integer codigoVencimiento) {
		this.codigoVencimiento = codigoVencimiento;
	}

	public String getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(String fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public String getFechaAlerta() {
		return fechaAlerta;
	}

	public void setFechaAlerta(String fechaAlerta) {
		this.fechaAlerta = fechaAlerta;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Producto getFkProducto() {
		return fkProducto;
	}

	public void setFkProducto(Producto fkProducto) {
		this.fkProducto = fkProducto;
	}

}
