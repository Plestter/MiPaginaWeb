package controller.todoAgro;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import ejb.todoAgro.CategoriaFacadeLocal;
import java.io.File;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import model.todoAgro.Categoria;
import todoAgro.utilities.ReadCSV;
import todoAgro.utilities.TransactionConstants;
import org.apache.log4j.Logger;
import org.primefaces.PrimeFaces;

import javax.inject.Named;
import javax.servlet.http.Part;
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

@Named(value = "catController")
@SessionScoped

@Resource(lookup = "java:app/primepoolagro")

public class CategoriaController implements Serializable {

    @Resource(lookup = "java:app/primepoolagro")
    DataSource dataSource;

    private static final Logger LOG = Logger.getLogger(CategoriaController.class);
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @EJB
    CategoriaFacadeLocal categoriaEJB;

    private Categoria categoria;
    private Categoria catTemporal;
    private Part archivoExcel;

    public CategoriaController() {
    }

    @PostConstruct
    public void init() {
        categoria = new Categoria();
        catTemporal = new Categoria();
    }

    public TreeMap<String, Integer> getListCategoria() {
        return categoriaEJB.getListCategoria();
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Categoria getCatTemporal() {
        return catTemporal;
    }

    public void setCatTemporal(Categoria catTemporal) {
        this.catTemporal = catTemporal;
    }

    public Part getArchivoExcel() {
        return archivoExcel;
    }

    public void setArchivoExcel(Part archivoExcel) {
        this.archivoExcel = archivoExcel;
    }

    public List<Categoria> getCategorias() {
        try {
            LOG.info("Into getCategorias");
            return categoriaEJB.getCategorias();
        } catch (Exception e) {
            LOG.error("Exception getCategorias: " + e.getCause().getMessage() + " || " + e.getMessage());
            return null;
        }
    }

    public void crearCategoria() {
        try {
            LOG.info("Into crearCategoria");
            FacesContext fc = FacesContext.getCurrentInstance();
            if (categoriaEJB.registrarCategoria(categoria)) {
                fc.addMessage("msg1",
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Categoria Creada Correctamente."));
            } else {
                fc.addMessage("msg1",
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error Al Crear Categoria."));
            }

        } catch (Exception e) {
            LOG.error("Exception crearCategoria: " + e.getCause().getMessage() + " || " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msg1",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error Al Crear Categoria."));
        } finally {
            LOG.info("Fially crearProducto");
            clear();
        }
    }

    public void deleteCategoria(Categoria cat) {
        try {
            LOG.info("Into deleteCategoria");
            categoriaEJB.remove(cat);
            FacesContext.getCurrentInstance().addMessage("msg1",
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Categoria Eliminada Correctamente."));
        } catch (Exception e) {
            LOG.error("Exception deleteCategoria: " + e.getCause().getMessage() + " || " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msg1",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error Al Eliminar Categoria."));
        }
    }

    public void editarCategoria() {
        try {
            LOG.info("Into editarCategoria");
            categoriaEJB.edit(catTemporal);
            FacesContext.getCurrentInstance().addMessage("msg3",
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Categoria Actualizada Correctamente."));
        } catch (Exception e) {
            LOG.error("Exception editarCategoria: " + e.getCause().getMessage() + " || " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msg3",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error Al Actualizar Categoria."));
        }
    }

    public void cargarCategoriasCSV() throws IOException {
        if (archivoExcel != null) {
            LOG.info(" *** archivoExcel: " + archivoExcel.getContentType());
            String cadInsert = "";
            try {
                List<String[]> listCats = ReadCSV.getInfoCSV(archivoExcel, TransactionConstants.pathExcelCats);
                for (String[] ls : listCats) {
                    LOG.info(Arrays.toString(ls));
                    cadInsert += getValuesCategoriaCSV(ls);
                }
                cadInsert = ReadCSV.delLastElement(cadInsert);
                if (!categoriaEJB.registrarCategoriaCSV(cadInsert)) {
                    throw new Exception("**Error Al Ejecutar EL Insert registrarCategoriaCSV");
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

    public void generarArchivo(String tipoArchivo) throws JRException, IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = facesContext.getExternalContext();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        File jasper = new File(context.getRealPath("/reportes/reporCategoria.jasper"));
        try {
            JasperPrint jp = JasperFillManager.fillReport(jasper.getPath(), new HashMap(), dataSource.getConnection());
            switch (tipoArchivo) {
                case "pdf":
                    response.setContentType("application/pdf");
                    response.addHeader("Content-disposition", "attachment; filename=Lista Categoria.pdf");
                    OutputStream os = response.getOutputStream();
                    JasperExportManager.exportReportToPdfStream(jp, os);
                    os.flush();
                    os.close();
                    break;

                case "xlsx":
                    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                    response.addHeader("Content-disposition", "attachment; filename=Lista Categoria.xlsx");

                    JRXlsxExporter exporter = new JRXlsxExporter(); // initialize exporter 
                    exporter.setExporterInput(new SimpleExporterInput(jp)); // set compiled report as input
                    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));

                    SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
                    configuration.setOnePagePerSheet(true); // setup configuration
                    configuration.setDetectCellType(true);
                    configuration.setSheetNames(new String[]{"reporCategoria"});
                    exporter.setConfiguration(configuration); // set configuration    
                    exporter.exportReport();
                    break;

                case "docx":
                    response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                    response.addHeader("Content-disposition", "attachment; filename=Lista Categoria.docx");

                    JRDocxExporter exporterDoc = new JRDocxExporter(); // initialize exporter 
                    exporterDoc.setExporterInput(new SimpleExporterInput(jp)); // set compiled report as input
                    exporterDoc.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));

                    SimpleDocxExporterConfiguration configurationDoc = new SimpleDocxExporterConfiguration();
                    configurationDoc.setMetadataAuthor("SistemAgroWeb."); // setup configuration
                    configurationDoc.setMetadataTitle("Reporte de Categoria");
                    configurationDoc.setMetadataSubject("Listado de Categoria");

                    exporterDoc.setConfiguration(configurationDoc); // set configuration    
                    exporterDoc.exportReport();
                    break;

                default:
                    System.err.println(" No se encontro este caso :: CategoriaController::generarArchivo");
                    break;

            }
            facesContext.responseComplete();

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(CategoriaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private String getValuesCategoriaCSV(String[] list) {
        String values = "(";
        for (String row : list) {
            values += "\"" + row + "\",";
        }
        values = ReadCSV.delLastElement(values);
        values += "),";
        return values;
    }

    public void guardarTemporal(Categoria categoria) {
        catTemporal = categoria;
    }

    public void clear() {
        categoria.setCatNombre(null);
        categoria.setCatDescripcion(null);
        categoria.setCatCategoriaid(null);
    }
}
