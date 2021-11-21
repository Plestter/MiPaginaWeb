/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.todoAgro;

import ejb.todoAgro.CategoriaFacadeLocal;
import ejb.todoAgro.ProductoFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.sql.DataSource;
import model.todoAgro.Categoria;
import model.todoAgro.Producto;
import model.todoAgro.ProductoCar;
import org.apache.log4j.Logger;
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author daviddaza
 */
@Named(value = "detalleProductoController")
@SessionScoped

@Resource(lookup = "java:app/primepoolagro")
public class DetalleProductoController implements Serializable {

    @Resource(lookup = "java:app/primepoolagro")
    DataSource dataSource;

    private static final Logger LOG = Logger.getLogger(DetalleProductoController.class);
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @EJB
    ProductoFacadeLocal productoEJB;
    @EJB
    CategoriaFacadeLocal categoriaEJB;
    private String productoDetallePath;
    private Producto producto;

    public String navigate(String productoDetallePath, Long id, Producto obj) {
        this.producto = obj;
        this.productoDetallePath = productoDetallePath;
        return productoDetallePath + "?faces-redirect=true&id=" + id;
    }

    public String navigateToCar(String productoDetallePath) {

        return productoDetallePath + "?faces-redirect=true";
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    private Map<Producto, Integer> productoIntoCar = new HashMap<Producto, Integer>();
    Integer inCar = 0;
    private List<ProductoCar> productoCar = new ArrayList<>();

    public void addToCar(Producto obj) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Integer cantidad = Integer.parseInt(request.getParameter("quantity"));

        if (productoIntoCar.containsKey(obj)) {
            Integer anterior = productoIntoCar.get(obj);
            Integer total = (anterior + cantidad);
            if (total <= obj.getPdtTotal()) {
                productoIntoCar.put(obj, (anterior + cantidad));

            } else {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "No se ha podido agregar, el limite en stock es "
                        + obj.getPdtTotal() + " la cantidad actual es " + anterior, null);

            }
        } else {
            productoIntoCar.put(obj, cantidad);
        }

    }
private Double subtotal;
    public List<ProductoCar> getProductoCar() {
        productoCar = new ArrayList<>(); 
        subtotal=0.0;
        for (Map.Entry<Producto, Integer> entry : productoIntoCar.entrySet()) {
            ProductoCar producto = new ProductoCar();
            Producto productoDb = entry.getKey();
            producto.setCantidad(entry.getValue());
            producto.setPdtDescripcion(productoDb.getPdtDescripcion());
            producto.setPdtImagen(productoDb.getPdtImagen());
            producto.setPdtNombre(productoDb.getPdtNombre());
            producto.setPdtProductoid(productoDb.getPdtProductoid());
            producto.setPdtTotal(productoDb.getPdtTotal());
            
            producto.setPdtValorventa(productoDb.getPdtValorventa());
           
            if (!productoCar.contains(producto)) {
                subtotal+=(entry.getValue()*productoDb.getPdtValorventa());
                productoCar.add(producto);

            }
        }

        return productoCar;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
    

    public void setProductoCar(List<ProductoCar> productoCar) {
        this.productoCar = productoCar;
    }

    public Integer getInCar() {
        return productoIntoCar.size();
    }

    public void setInCar(Integer inCar) {
        this.inCar = inCar;
    }

    public Map<Producto, Integer> getProductoIntoCar() {
        return productoIntoCar;
    }

    public void setProductoIntoCar(Map<Producto, Integer> productoIntoCar) {
        this.productoIntoCar = productoIntoCar;
    }

}
