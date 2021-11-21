package controller.todoAgro;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import ejb.todoAgro.PqrsFacadeLocal;
import ejb.todoAgro.UsuarioFacadeLocal;
import model.todoAgro.Pqrs;
import model.todoAgro.Usuario;

import org.apache.log4j.Logger;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

@Named(value = "pqrController")
@SessionScoped
public class PQRController implements Serializable {
	private static final Logger LOG = Logger.getLogger(PQRController.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	PqrsFacadeLocal pqrEJB;
	@EJB
	UsuarioFacadeLocal usuarioEJB;

	private Pqrs pqr;
	private Pqrs pqrTemporal;
	private String respuesta;
	private Integer idUsuario;

	public PQRController() {
	}

	@PostConstruct
	public void init() {
		pqr = new Pqrs();
		pqrTemporal = new Pqrs();
	}

	public void crearPQR() {
		try {
			LOG.info("Into crearPQR ");
			FacesContext fc = FacesContext.getCurrentInstance();

			String idUsuario = fc.getExternalContext().getRequestParameterMap().get("idUsuario");
			pqr.setCodigoRadicado(ThreadLocalRandom.current().nextInt());
			Usuario usu = new Usuario();
			usu.setUsuUsuarioid(Integer.parseInt(idUsuario));
			pqr.setUsuarioUsuUsuarioid(usu);
			if (pqrEJB.registrarPqr(pqr)) {
				fc.addMessage("msg1",
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "PQR Creada Correctamente."));
			} else {
				fc.addMessage("msg1", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error Al Crear PQR."));
			}
		} catch (Exception e) {
			LOG.error("Exception crearPQR: " + e.getCause().getMessage() + " || " + e.getMessage());
			FacesContext.getCurrentInstance().addMessage("msg1",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error Al Crear PQR."));
		} finally {
			LOG.info("Fially crearPQR");
			clear();
		}
	}

	public void responderPQR() {
		try {
			LOG.info("Into responderPQR");

			Usuario usuario = usuarioEJB.getInfoUsuario(pqrTemporal.getUsuarioUsuUsuarioid().getUsuUsuarioid());
			pqrEJB.responderPQR(pqrTemporal, usuario, respuesta);

			FacesContext.getCurrentInstance().addMessage("msg3",
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "PQR Contestada Correctamente."));
		} catch (Exception e) {
			LOG.error("Exception responderPQR: " + e.getCause().getMessage() + " || " + e.getMessage());
			FacesContext.getCurrentInstance().addMessage("msg3",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error Al Contestar PQR."));
		}
	}

	public List<Pqrs> getPQRs() {
		try {
			LOG.info("Into getPQRs");
			return pqrEJB.getPQRs();
		} catch (Exception e) {
			LOG.error("Exception getPQRs: " + e.getCause().getMessage() + " || " + e.getMessage());
			return null;
		}
	}

	public void guardarTemporal(Pqrs pqr) {
		pqrTemporal = pqr;
	}

	public Pqrs getPqr() {
		return pqr;
	}

	public void setPqr(Pqrs pqr) {
		this.pqr = pqr;
	}

	public Pqrs getPqrTemporal() {
		return pqrTemporal;
	}

	public void setPqrTemporal(Pqrs pqrTemporal) {
		this.pqrTemporal = pqrTemporal;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public void clear() {
		pqr.setCiudad(null);
		pqr.setCodigoRadicado(0);
		pqr.setDescripcion(null);
		pqr.setUsuarioUsuUsuarioid(null);
	}
}
