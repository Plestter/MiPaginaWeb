package controller.todoAgro;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import ejb.todoAgro.DetalleVencimientoFacadeLocal;
import ejb.todoAgro.ProductoFacadeLocal;
import java.io.File;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import model.todoAgro.DetalleVencimiento;
import model.todoAgro.Producto;

import org.apache.log4j.Logger;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
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

@Named(value = "vencimientoController")
@SessionScoped

@Resource(lookup = "java:app/primepoolagro")

public class DetalleVencimientoController implements Serializable {

    @Resource(lookup = "java:app/primepoolagro")
    DataSource dataSource;

    private static final Logger LOG = Logger.getLogger(DetalleVencimientoController.class);
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @EJB
    ProductoFacadeLocal productoEJB;
    @EJB
    DetalleVencimientoFacadeLocal vencimientoEJB;

    private DetalleVencimiento vencimiento;
    private DetalleVencimiento vencimientoTemporal;
    private Producto producto;

    public DetalleVencimientoController() {

    }

    @PostConstruct
    public void init() {
        LOG.info("PostConstruct log**************");
        vencimiento = new DetalleVencimiento();
        vencimientoTemporal = new DetalleVencimiento();
        producto = new Producto();
    }

    public HashSet<Producto> getImagenesProductos() {
        return productoEJB.getImagenesProductos();
    }

    public HashMap<String, Integer> getListaProductos() {
        return productoEJB.getMapProductos();
    }

    public Producto getProducto() {
        return producto;
    }

    public DetalleVencimiento getVencimiento() {
        return vencimiento;
    }

    public void guardarTemporal(DetalleVencimiento vencimiento) {
        vencimientoTemporal = vencimiento;
    }

    public DetalleVencimiento getVencimientoTemporal() {
        return vencimientoTemporal;
    }

    public void setVencimientoTemporal(DetalleVencimiento vencimientoTemporal) {
        this.vencimientoTemporal = vencimientoTemporal;
    }

    public void crearVencimiento() throws IOException {
        try {
            LOG.info("Into crearVencimiento");
            this.vencimiento.setFkProducto(producto);
            vencimientoEJB.registrarVencimiento(vencimiento);
            FacesContext.getCurrentInstance().addMessage("msg1",
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Vencimiento Creado Correctamente."));
        } catch (Exception e) {
            LOG.error("Exception crearVencimiento: " + e.getCause().getMessage() + " || " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msg1",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error Al Crear Vencimiento."));
        } finally {
            LOG.info("Fially crearVencimiento");
            clear();
        }
    }

    public List<DetalleVencimiento> getVencimientos() {
        try {
            LOG.info("Into getVencimientos");
            return vencimientoEJB.getVencimientos();
        } catch (Exception e) {
            LOG.error("Exception getVencimientos: " + e.getCause().getMessage() + " || " + e.getMessage());
            return null;
        }
    }

    public void deleteVencimiento(DetalleVencimiento vencimiento) {
        try {
            LOG.info("Into deleteVencimiento");
            vencimientoEJB.remove(vencimiento);
            FacesContext.getCurrentInstance().addMessage("msg1",
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Vencimiento Eliminado Correctamente."));
        } catch (Exception e) {
            LOG.error("Exception deleteVencimiento: " + e.getCause().getMessage() + " || " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msg1",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error Al Eliminar Vencimiento."));
        }
    }

    public void editarVencimiento() {
        try {
            LOG.info("Into editarVencimiento");
            this.vencimientoTemporal.setFkProducto(producto);
            vencimientoEJB.edit(vencimientoTemporal);
            FacesContext.getCurrentInstance().addMessage("msg3",
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Vencimiento Actualizado Correctamente."));
        } catch (Exception e) {
            LOG.error("Exception editarVencimiento: " + e.getCause().getMessage() + " || " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msg3",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error Al Actualizar Vencimiento."));
        }
    }

    public void generarArchivo(String tipoArchivo) throws JRException, IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = facesContext.getExternalContext();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        File jasper = new File(context.getRealPath("/reportes/reporVencimiento.jasper"));
        try {
            JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), new HashMap(), dataSource.getConnection());
            switch (tipoArchivo) {
                case "pdf":
                    response.setContentType("application/pdf");
                    response.addHeader("Content-disposition", "attachment; filename=Lista Vencimiento.pdf");
                    OutputStream os = response.getOutputStream();
                    JasperExportManager.exportReportToPdfStream(jp, os);
                    os.flush();
                    os.close();
                    break;

                case "xlsx":
                    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                    response.addHeader("Content-disposition", "attachment; filename=Lista Vencimiento.xlsx");

                    JRXlsxExporter exporter = new JRXlsxExporter(); // initialize exporter 
                    exporter.setExporterInput(new SimpleExporterInput(jp)); // set compiled report as input
                    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));

                    SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
                    configuration.setOnePagePerSheet(true); // setup configuration
                    configuration.setDetectCellType(true);
                    configuration.setSheetNames(new String[]{"reporVencimiento"});
                    exporter.setConfiguration(configuration); // set configuration    
                    exporter.exportReport();
                    break;

                case "docx":
                    response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                    response.addHeader("Content-disposition", "attachment; filename=Lista Vencimiento.docx");

                    JRDocxExporter exporterDoc = new JRDocxExporter(); // initialize exporter 
                    exporterDoc.setExporterInput(new SimpleExporterInput(jp)); // set compiled report as input
                    exporterDoc.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));

                    SimpleDocxExporterConfiguration configurationDoc = new SimpleDocxExporterConfiguration();
                    configurationDoc.setMetadataAuthor("SistemAgroWeb."); // setup configuration
                    configurationDoc.setMetadataTitle("Reporte de Vencimiento");
                    configurationDoc.setMetadataSubject("Listado de Vencimiento");

                    exporterDoc.setConfiguration(configurationDoc); // set configuration    
                    exporterDoc.exportReport();
                    break;

                default:
                    System.err.println(" No se encontro este caso :: DetalleVencimientoController::generarArchivo");
                    break;

            }
            facesContext.responseComplete();

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(DetalleVencimientoController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void clear() {
        vencimiento.setCodigoVencimiento(null);
        vencimiento.setDescripcion(null);
        vencimiento.setFechaAlerta(null);
        vencimiento.setFechaCaducidad(null);
        vencimiento.setFechaIngreso(null);
        producto.setPdtProductoid(null);
    }
}
