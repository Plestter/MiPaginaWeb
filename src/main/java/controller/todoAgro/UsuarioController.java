 package controller.todoAgro;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;

import ejb.todoAgro.UsuHasRolFacadeLocal;
import ejb.todoAgro.UsuarioFacadeLocal;
import model.todoAgro.Rol;
import model.todoAgro.Usuario;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.PrimeFaces;
import org.primefaces.shaded.commons.io.FilenameUtils;

import todoAgro.utilities.Mail;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

@Named(value = "usuController")
@SessionScoped
public class UsuarioController implements Serializable {
	private static final Logger LOG = Logger.getLogger(UsuarioController.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	UsuarioFacadeLocal usuEJB;
	@EJB
	UsuHasRolFacadeLocal rolEJB;

	private Usuario usuLog;
	private String correoIn;
	private String claveIn;
	private Part archivoFoto;
	private String rolSession;
	private String nombre;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	private Part archivoExcel;

	public UsuarioController() {
	}

	@PostConstruct
	public void init() {
		usuLog = new Usuario();
	}

	public void validarUsuario() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			LOG.info("Into validarUsuario");
			usuLog = usuEJB.validarUsuario(correoIn, claveIn);
			if (usuLog != null) {
				if (usuLog.getUsuEstado() == Short.parseShort("1")) {
					Set<Rol> rol = usuLog.getRoles();
					rolSession = getRol(rol);
					LOG.info(rolSession);
					fc.getExternalContext().getSessionMap().put("usuLogin", usuLog);
					fc.getExternalContext().redirect("usuario/formUsuario.xhtml");
				} else {
					fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "Usuario Inactivo."));
				}
			} else {
				fc.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Usuario y/o Clave Incorrecta."));
			}

		} catch (Exception e) {
			fc.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se puede realizar esta accion."));
		}
	}

	public void recuperarClave() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			usuLog = usuEJB.getInfoUsuario(correoIn);
			if (usuLog != null) {

				Mail.recuperarClaves(usuLog.getUsuNombres() + usuLog.getUsuApellidos(), usuLog.getUsuCorreo(),
						usuLog.getUsuClave());
				fc.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Clave Enviada Correctamente."));

			} else {
				fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Correo Incorrecto."));
			}

		} catch (Exception e) {
			fc.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se puede realizar esta accion."));
		}

	}

	public void validarSession() {
		try {
			FacesContext fc = FacesContext.getCurrentInstance();
			Usuario usuLogin = (Usuario) fc.getExternalContext().getSessionMap().get("usuLogin");
			if (usuLogin == null) {
				fc.getExternalContext().redirect("../pagLogin.xhtml");
			}
		} catch (Exception e) {

		}
	}

	public void editarUsuario() {
		try {
			LOG.info("Into editarUsuario");
			usuLog.setUsuClave(claveIn);
			usuEJB.edit(usuLog);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Usuario Actualizado Correctamente."));
		} catch (Exception e) {
			LOG.error("Exception editarProducto: " + e.getCause().getMessage() + " || " + e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error Al Actualizar Usuario."));
		}
	}

	public void cargarFotoPerfil() {
		if (archivoFoto != null) {
			if (archivoFoto.getSize() > 700000 || archivoFoto.getSize() < 10000) {
				PrimeFaces.current()
						.executeScript("Swal.fire({" + "  title: 'El archivo !',"
								+ "  text: 'No se puede cargar por el tamaño !!!'," + "  icon: 'error',"
								+ "  confirmButtonText: 'Ok'" + "})");
			} else if (archivoFoto.getContentType().equalsIgnoreCase("image/png")
					|| archivoFoto.getContentType().equalsIgnoreCase("image/jpeg")) {

				try (InputStream is = archivoFoto.getInputStream()) {
					File carpeta = new File("C:\\todoAgro\\usuarios\\fotoperfil");
					if (!carpeta.exists()) {
						carpeta.mkdirs();
					}
					Calendar hoy = Calendar.getInstance();
					String nuevoNombre = sdf.format(hoy.getTime()) + ".";
					nuevoNombre += FilenameUtils.getExtension(archivoFoto.getSubmittedFileName());
					Files.copy(is, (new File(carpeta, nuevoNombre)).toPath(), StandardCopyOption.REPLACE_EXISTING);
					usuLog.setUsuFoto(nuevoNombre);
					usuEJB.edit(usuLog);

				} catch (Exception e) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error", "No se puede realizar esta accion."));
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No es una imagen .png o .jpeg !!!"));
			}

		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se puede realizar esta accion."));
		}
		PrimeFaces.current().executeScript("document.getElementById('resetform').click()");

	}

	public void registrarUsuario() {
		try {
			LOG.info("Into registrarUsuario");
			Short active = Short.parseShort("1");
			FacesContext fc = FacesContext.getCurrentInstance();
			Usuario usuReg = new Usuario();
			usuReg.setUsuCorreo(correoIn);
			usuReg.setUsuNombres(nombre);
			usuReg.setUsuClave(claveIn);
			usuReg.setUsuEstado(active);
			usuEJB.create(usuReg);
			Integer idUsuNew = usuReg.getUsuUsuarioid();
			if (idUsuNew != 0) {
				LOG.info("Id UsuNew: " + idUsuNew);
				rolEJB.registrarUsuario(idUsuNew);
				fc.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Usuario Creado Correctamente."));
			} else {
				fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error Al Crear Usuario."));
			}

		} catch (Exception e) {
			LOG.error("Exception registrarUsuario: " + e.getCause().getMessage() + " || " + e.getMessage());
			FacesContext.getCurrentInstance().addMessage("msg1",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error Al Crear Usuario."));
		} finally {
			LOG.info("Fially registrarUsuario");
			clear();
		}
	}

	public void cargarExcel() throws IOException {
		if (archivoExcel != null) {
			LOG.info(" *** archivoExcel: " + archivoExcel.getContentType());

			File carpeta = new File("C:\\todoAgro\\excel");
			if (!carpeta.exists()) {
				carpeta.mkdirs();
			}
			String nuevoNombre = archivoExcel.getSubmittedFileName();
			Files.copy(archivoExcel.getInputStream(), (new File(carpeta, nuevoNombre)).toPath(),
					StandardCopyOption.REPLACE_EXISTING);
			Boolean flag = false;
			String path = "C:\\todoAgro\\excel\\" + nuevoNombre;

			try (FileInputStream file = new FileInputStream(new File(path))) {
				// leer archivo excel
				XSSFWorkbook worbook = new XSSFWorkbook(file);
				// obtener la hoja que se va leer
				XSSFSheet sheet = worbook.getSheetAt(0);
				// obtener todas las filas de la hoja excel
				Iterator<Row> rowIterator = sheet.iterator();

				Row row;
				// se recorre cada fila hasta el final
				while (rowIterator.hasNext()) {
					if (flag) {
						break;
					}
					row = rowIterator.next();
					// se obtiene las celdas por fila
					Iterator<Cell> cellIterator = row.cellIterator();
					Cell cell;
					// se recorre cada celda
					while (cellIterator.hasNext()) {
						// se obtiene la celda en específico y se la imprime
						cell = cellIterator.next();
						if (cell.getRow().getRowNum() == 0) {
							LOG.info(" ** Header Excel");
							LOG.info(String.valueOf(cell.getStringCellValue()) + " | ");
						} else {
							LOG.info(" ** Body Excel");
							if (cell.getColumnIndex() == 2) {
								LOG.info(String.valueOf(cell.getNumericCellValue()) + " | ");
							} else {
								if (cell.getStringCellValue().isEmpty()) {
									flag = true;
									break;
								}
								LOG.info(String.valueOf(cell.getStringCellValue()) + " | ");
							}
						}
					}
					LOG.info(" ** End Row");
				}
				LOG.info(" ** End Excel");
				worbook.close();
				Path patDel = Paths.get(path);
				Files.delete(patDel);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Archivo Cargado Correctamente."));
			} catch (Exception e) {
				LOG.info(e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se puede realizar esta accion."));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se puede realizar esta accion."));
		}
		PrimeFaces.current().executeScript("document.getElementById('resetform').click()");

	}

	public String getCorreoIn() {
		return correoIn;
	}

	public void setCorreoIn(String correoIn) {
		this.correoIn = correoIn;
	}

	public String getClaveIn() {
		return claveIn;
	}

	public void setClaveIn(String claveIn) {
		this.claveIn = claveIn;
	}

	public Usuario getUsuLog() {
		return usuLog;
	}

	public void setUsuLog(Usuario usuLog) {
		this.usuLog = usuLog;
	}

	public Part getArchivoFoto() {
		return archivoFoto;
	}

	public void setArchivoFoto(Part archivoFoto) {
		this.archivoFoto = archivoFoto;
	}

	public String getRolSession() {
		return rolSession;
	}

	public void setRolSession(String rolSession) {
		this.rolSession = rolSession;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Part getArchivoExcel() {
		return archivoExcel;
	}

	public void setArchivoExcel(Part archivoExcel) {
		this.archivoExcel = archivoExcel;
	}

	private String getRol(Set<Rol> set) {
		String rolInit = "";
		for (Iterator<Rol> it = set.iterator(); it.hasNext();) {
			Rol rol = it.next();
			rolInit = rol.getRolNombre();
		}
		return rolInit;
	}

	public void cerrarSesion() throws IOException {
		clear();
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.getExternalContext().invalidateSession();
		fc.getExternalContext().redirect("../index.xhtml");
	}

	public void clear() {
		setCorreoIn(null);
		setClaveIn(null);
		usuLog = null;
	}

}
