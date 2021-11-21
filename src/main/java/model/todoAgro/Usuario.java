/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.todoAgro;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn; 
import javax.validation.constraints.Size;

/**
 *
 * @author nombre
 */
@Entity
@Table(name = "usuario")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByUsuUsuarioid", query = "SELECT u FROM Usuario u WHERE u.usuUsuarioid = :usuUsuarioid"),
    @NamedQuery(name = "Usuario.findByUsuTipodocumento", query = "SELECT u FROM Usuario u WHERE u.usuTipodocumento = :usuTipodocumento"),
    @NamedQuery(name = "Usuario.findByUsuNumerodocumento", query = "SELECT u FROM Usuario u WHERE u.usuNumerodocumento = :usuNumerodocumento"),
    @NamedQuery(name = "Usuario.findByUsuNombres", query = "SELECT u FROM Usuario u WHERE u.usuNombres = :usuNombres"),
    @NamedQuery(name = "Usuario.findByUsuApellidos", query = "SELECT u FROM Usuario u WHERE u.usuApellidos = :usuApellidos"),
    @NamedQuery(name = "Usuario.findByUsuCorreo", query = "SELECT u FROM Usuario u WHERE u.usuCorreo = :usuCorreo"),
    @NamedQuery(name = "Usuario.findByUsuClave", query = "SELECT u FROM Usuario u WHERE u.usuClave = :usuClave"),
    @NamedQuery(name = "Usuario.findByUsuTelefono", query = "SELECT u FROM Usuario u WHERE u.usuTelefono = :usuTelefono"),
    @NamedQuery(name = "Usuario.findByUsuDireccion", query = "SELECT u FROM Usuario u WHERE u.usuDireccion = :usuDireccion"),
    @NamedQuery(name = "Usuario.findByUsuEstado", query = "SELECT u FROM Usuario u WHERE u.usuEstado = :usuEstado")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "usu_usuarioid")
    private Integer usuUsuarioid;
    @Size(max = 45)
    @Column(name = "usu_tipodocumento")
    private String usuTipodocumento;
    @Column(name = "usu_numerodocumento")
    private BigInteger usuNumerodocumento;
    @Size(max = 45)
    @Column(name = "usu_nombres")
    private String usuNombres;
    @Size(max = 45)
    @Column(name = "usu_apellidos")
    private String usuApellidos;
    @Size(max = 45)
    @Column(name = "usu_correo")
    private String usuCorreo;
    @Size(max = 45)
    @Column(name = "usu_clave")
    private String usuClave;
    @Size(max = 45)
    @Column(name = "usu_telefono")
    private String usuTelefono;
    @Size(max = 45)
    @Column(name = "usu_direccion")
    private String usuDireccion;
    @Column(name = "usu_estado")
    private Short usuEstado;
    @ManyToMany(mappedBy = "usuarioList")
    private List<Rol> rolList;
    @OneToMany(mappedBy = "fkCliente")
    private List<Factura> facturaList;
    @OneToMany(mappedBy = "fkVendedor")
    private List<Factura> facturaList1;
    @OneToMany(mappedBy = "fkOperario")
    private List<Ordencompra> ordencompraList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioUsuUsuarioid")
    private List<Pqrs> pqrsList;
    @OneToMany(mappedBy = "fkProveedor")
    private List<OrdencompraProducto> ordencompraProductoList;
    @Size(max = 255)
    @Column(name = "usu_foto")
    private String usuFoto;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
      name = "usuario_has_rol", 
      joinColumns = @JoinColumn(name = "fk_usuarioid"), 
      inverseJoinColumns = @JoinColumn(name = "fk_rolid"))
    Set<Rol> roles;

    public Usuario() {
    }

    public Usuario(Integer usuUsuarioid) {
        this.usuUsuarioid = usuUsuarioid;
    }

    public Integer getUsuUsuarioid() {
        return usuUsuarioid;
    }

    public void setUsuUsuarioid(Integer usuUsuarioid) {
        this.usuUsuarioid = usuUsuarioid;
    }

    public String getUsuTipodocumento() {
        return usuTipodocumento;
    }

    public void setUsuTipodocumento(String usuTipodocumento) {
        this.usuTipodocumento = usuTipodocumento;
    }

    public BigInteger getUsuNumerodocumento() {
        return usuNumerodocumento;
    }

    public void setUsuNumerodocumento(BigInteger usuNumerodocumento) {
        this.usuNumerodocumento = usuNumerodocumento;
    }

    public String getUsuNombres() {
        return usuNombres;
    }

    public void setUsuNombres(String usuNombres) {
        this.usuNombres = usuNombres;
    }

    public String getUsuApellidos() {
        return usuApellidos;
    }

    public void setUsuApellidos(String usuApellidos) {
        this.usuApellidos = usuApellidos;
    }

    public String getUsuCorreo() {
        return usuCorreo;
    }

    public void setUsuCorreo(String usuCorreo) {
        this.usuCorreo = usuCorreo;
    }

    public String getUsuClave() {
        return usuClave;
    }

    public void setUsuClave(String usuClave) {
        this.usuClave = usuClave;
    }

    public String getUsuTelefono() {
        return usuTelefono;
    }

    public void setUsuTelefono(String usuTelefono) {
        this.usuTelefono = usuTelefono;
    }

    public String getUsuDireccion() {
        return usuDireccion;
    }

    public void setUsuDireccion(String usuDireccion) {
        this.usuDireccion = usuDireccion;
    }

    public Short getUsuEstado() {
        return usuEstado;
    }

    public void setUsuEstado(Short usuEstado) {
        this.usuEstado = usuEstado;
    }

    public List<Rol> getRolList() {
        return rolList;
    }

    public void setRolList(List<Rol> rolList) {
        this.rolList = rolList;
    }

    public List<Factura> getFacturaList() {
        return facturaList;
    }

    public void setFacturaList(List<Factura> facturaList) {
        this.facturaList = facturaList;
    }

    public List<Factura> getFacturaList1() {
        return facturaList1;
    }

    public void setFacturaList1(List<Factura> facturaList1) {
        this.facturaList1 = facturaList1;
    }

    public List<Ordencompra> getOrdencompraList() {
        return ordencompraList;
    }

    public void setOrdencompraList(List<Ordencompra> ordencompraList) {
        this.ordencompraList = ordencompraList;
    }

    public List<Pqrs> getPqrsList() {
        return pqrsList;
    }

    public void setPqrsList(List<Pqrs> pqrsList) {
        this.pqrsList = pqrsList;
    }

    public List<OrdencompraProducto> getOrdencompraProductoList() {
        return ordencompraProductoList;
    }

    public void setOrdencompraProductoList(List<OrdencompraProducto> ordencompraProductoList) {
        this.ordencompraProductoList = ordencompraProductoList;
    }

    public String getUsuFoto() {
		return usuFoto;
	}

	public void setUsuFoto(String usuFoto) {
		this.usuFoto = usuFoto;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (usuUsuarioid != null ? usuUsuarioid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.usuUsuarioid == null && other.usuUsuarioid != null) || (this.usuUsuarioid != null && !this.usuUsuarioid.equals(other.usuUsuarioid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.todoAgro.Usuario[ usuUsuarioid=" + usuUsuarioid + " ]";
    }
    
}
