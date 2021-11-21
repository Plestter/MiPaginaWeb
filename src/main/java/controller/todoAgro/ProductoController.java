/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.todoAgro;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

import ejb.todoAgro.CategoriaFacadeLocal;
import ejb.todoAgro.ProductoFacadeLocal;
import model.todoAgro.Categoria;
import model.todoAgro.Producto;
import todoAgro.utilities.ReadCSV;
import todoAgro.utilities.TransactionConstants;

import org.apache.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.shaded.commons.io.FilenameUtils;
import javax.servlet.http.Part;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleDocxExporterConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@Named(value = "productoController")
@SessionScoped
@ViewScoped
@Resource(lookup = "java:app/primepoolagro")

public class ProductoController implements Serializable {

    @Resource(lookup = "java:app/primepoolagro")
    DataSource dataSource;

    private static final Logger LOG = Logger.getLogger(ProductoController.class);
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @EJB
    ProductoFacadeLocal productoEJB;
    @EJB
    CategoriaFacadeLocal categoriaEJB;

    private Producto producto;
    private Producto prodTemporal;
    private Categoria categoria;
    private UploadedFile file;
    private Part archivoExcel;
    private String productoDetallePath;
    public ProductoController() {
    }

    @PostConstruct
    public void init() {
        producto = new Producto();
        prodTemporal = new Producto();
        categoria = new Categoria();
    }

    public String getProductoDetallePath() {
        return productoDetallePath;
    }

    public void setProductoDetallePath(String productoDetallePath) {
        this.productoDetallePath = productoDetallePath;
    }

    public String navigate(String productoDetallePath, Long id) {
        this.productoDetallePath = productoDetallePath;
        return productoDetallePath + "?faces-redirect=true&id=" + id;
    }

    public TreeMap<String, Integer> getListCategoria() {
        return categoriaEJB.getListCategoria();
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Producto getProducto() {
        return producto;
    }

    public void guardarTemporal(Producto producto) {
        prodTemporal = producto;
    }

    public Producto getProdTemporal() {
        return prodTemporal;
    }

    public void setProdTemporal(Producto prodTemporal) {
        this.prodTemporal = prodTemporal;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public Part getArchivoExcel() {
        return archivoExcel;
    }

    public void setArchivoExcel(Part archivoExcel) {
        this.archivoExcel = archivoExcel;
    }

    public void crearProducto() {
        try {
            LOG.info("Into crearProducto");
            FacesContext fc = FacesContext.getCurrentInstance();
            String fileName = "";
            String extension = "";
            if (this.file != null) {
                Integer rnd = ThreadLocalRandom.current().nextInt();
                fileName = FilenameUtils.getBaseName(this.file.getFileName()) + rnd;
                extension = FilenameUtils.getExtension(this.file.getFileName());
                producto.setPdtImagen(fileName + "." + extension);
            }
            this.producto.setFkCategoria(categoria);
            if (productoEJB.registrarProducto(producto)) {
                saveFileFolder(fileName + "." + extension);
                fc.addMessage("msg1",
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Producto Creado Correctamente."));
            } else {
                fc.addMessage("msg1",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error Al Crear Producto."));
            }

        } catch (Exception e) {
            LOG.error("Exception crearProducto: " + e.getCause().getMessage() + " || " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msg1",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error Al Crear Producto."));
        } finally {
            LOG.info("Fially crearProducto");
            clear();
        }
    }

    private void saveFileFolder(String filename) throws IOException {
        File carpeta = new File("C:\\todoAgro\\productos\\imgs");
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
        try (InputStream input = this.file.getInputStream()) {
            Files.copy(input, (new File(carpeta, filename)).toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    public List<Producto> getProductos() {
        try {
            LOG.info("Into getProductos");
            return productoEJB.getProductos();
        } catch (Exception e) {
            LOG.error("Exception getProductos: " + e.getCause().getMessage() + " || " + e.getMessage());
            return null;
        }
    }

    public void deleteProducto(Producto prod) {
        try {
            LOG.info("Into deleteProducto");
            productoEJB.remove(prod);
            FacesContext.getCurrentInstance().addMessage("msg1",
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Producto Eliminado Correctamente."));
        } catch (Exception e) {
            LOG.error("Exception deleteProducto: " + e.getCause().getMessage() + " || " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msg1",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error Al Eliminar Producto."));
        }
    }

    public void editarProducto() {
        try {
            LOG.info("Into editarProducto");
            this.prodTemporal.setFkCategoria(categoria);
            productoEJB.edit(prodTemporal);

            if (this.file != null) {
                saveFileFolder(prodTemporal.getPdtImagen());
            }

            FacesContext.getCurrentInstance().addMessage("msg3",
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Producto Actualizado Correctamente."));
        } catch (Exception e) {
            LOG.error("Exception editarProducto: " + e.getCause().getMessage() + " || " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msg3",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error Al Actualizar Producto."));
        }
    }

    public void generarArchivo(String tipoArchivo) throws JRException, IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = facesContext.getExternalContext();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        File jasper = new File(context.getRealPath("/reportes/reporProductos.jasper"));
        try {
            JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), new HashMap(), dataSource.getConnection());
            switch (tipoArchivo) {
                case "pdf":
                    response.setContentType("application/pdf");
                    response.addHeader("Content-disposition", "attachment; filename=Lista Productos.pdf");
                    OutputStream os = response.getOutputStream();
                    JasperExportManager.exportReportToPdfStream(jp, os);
                    os.flush();
                    os.close();
                    break;

                case "xlsx":
                    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                    response.addHeader("Content-disposition", "attachment; filename=Lista Productos.xlsx");

                    JRXlsxExporter exporter = new JRXlsxExporter(); // initialize exporter 
                    exporter.setExporterInput(new SimpleExporterInput(jp)); // set compiled report as input
                    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));

                    SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
                    configuration.setOnePagePerSheet(true); // setup configuration
                    configuration.setDetectCellType(true);
                    configuration.setSheetNames(new String[]{"reporProductos"});
                    exporter.setConfiguration(configuration); // set configuration    
                    exporter.exportReport();
                    break;

                case "docx":
                    response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                    response.addHeader("Content-disposition", "attachment; filename=Lista Productos.docx");

                    JRDocxExporter exporterDoc = new JRDocxExporter(); // initialize exporter 
                    exporterDoc.setExporterInput(new SimpleExporterInput(jp)); // set compiled report as input
                    exporterDoc.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));

                    SimpleDocxExporterConfiguration configurationDoc = new SimpleDocxExporterConfiguration();
                    configurationDoc.setMetadataAuthor("SistemAgroWeb."); // setup configuration
                    configurationDoc.setMetadataTitle("Reporte de Productos");
                    configurationDoc.setMetadataSubject("Listado de Productos");

                    exporterDoc.setConfiguration(configurationDoc); // set configuration    
                    exporterDoc.exportReport();
                    break;

                default:
                    System.err.println(" No se encontro este caso :: ProductoController::generarArchivo");
                    break;

            }
            facesContext.responseComplete();

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cargarProductosCSV() throws IOException {
        if (archivoExcel != null) {
            LOG.info(" *** archivoExcel: " + archivoExcel.getContentType());
            String cadInsert = "";
            try {
                List<String[]> listProds = ReadCSV.getInfoCSV(archivoExcel, TransactionConstants.pathExcelProds);
                for (String[] ls : listProds) {
                    LOG.info(Arrays.toString(ls));
                    cadInsert += getValuesProdsCSV(ls);
                }
                cadInsert = ReadCSV.delLastElement(cadInsert);
                if (!productoEJB.registrarProductoCSV(cadInsert)) {
                    throw new Exception("**Error Al Ejecutar EL Insert registrarProductoCSV");
                }
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                        TransactionConstants.typeMessageInfo, "Archivo Cargado Correctamente."));
            } catch (Exception e) {
                LOG.info(e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        TransactionConstants.typeMessageError, "No se puede realizar esta accion."));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    TransactionConstants.typeMessageError, "No se puede realizar esta accion."));
        }
        PrimeFaces.current().executeScript("document.getElementById('resetform').click()");
    }

    private String getValuesProdsCSV(String[] list) {
        String values = "(";
        for (String row : list) {
            values += "\"" + row + "\",";
        }
        values = ReadCSV.delLastElement(values);
        values += "),";
        return values;
    }

    public void clear() {
        producto.setPdtNombre(null);
        producto.setPdtValorventa(null);
        producto.setPdtDescripcion(null);
        categoria.setCatCategoriaid(null);
    }
}
