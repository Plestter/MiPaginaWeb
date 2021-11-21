/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.todoAgro;

import java.util.List;
import java.util.Properties;

import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.todoAgro.Pqrs;
import model.todoAgro.Usuario;

/**
 *
 * @author nombre
 */
@Stateless
public class PqrsFacade extends AbstractFacade<Pqrs> implements PqrsFacadeLocal {

	@PersistenceContext(unitName = "primepoolagro")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public PqrsFacade() {
		super(Pqrs.class);
	}

	@Override
	public Boolean registrarPqr(Pqrs pqr) {
		try {
			Query q = em.createNativeQuery(
					"INSERT INTO pqrs (ciudad, descripcion, codigo_radicado, usuario_usu_usuarioid) VALUES (?, ?, ?, ?)");
			q.setParameter(1, pqr.getCiudad());
			q.setParameter(2, pqr.getDescripcion());
			q.setParameter(3, pqr.getCodigoRadicado());
			q.setParameter(4, pqr.getUsuarioUsuUsuarioid().getUsuUsuarioid());
			q.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean responderPQR(Pqrs pqr, Usuario oUsuario, String respuesta) {
		final String usuario = "usuCorreo@proveedor.com";
        final String clave = "claveCorreo"; 

        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com"); // envia 
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "25");
        props.setProperty("mail.smtp.starttls.required", "false");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, clave);
            }
        });

        try {
            MimeMessage mensage = new MimeMessage(session);
            mensage.setFrom(new InternetAddress(usuario));
            mensage.addRecipient(Message.RecipientType.TO, new InternetAddress(oUsuario.getUsuCorreo()));
            mensage.setSubject("Respuesta PQR No: " + pqr.getCodigoRadicado());
            mensage.setContent("<center> "
                    + "<img src='https://thumbs.dreamstime.com/b/protecci%C3%B3n-de-la-clave-de-la-seguridad-de-la-contrase%C3%B1a-de-los-datos-de-usuario-79323179.jpg' width='200px' height='200px' >"
                    + "</center>"
                    + "<br/>"
                    + "<h1> Hola, "+ oUsuario.getUsuNombres() + " " + oUsuario.getUsuApellidos() +" </h1>"
                    + "<br/>"
                    + "Respuesta a radicado No. : " + pqr.getCodigoRadicado()
                    + "<br/>"
                    + respuesta,
                    "text/html");
            Transport.send(mensage);
            return true;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
	}
	
	@Override
	public List<Pqrs> getPQRs() {
		try {
			em.getEntityManagerFactory().getCache().evictAll();
			Query qt = em.createQuery("SELECT p FROM Pqrs p ");
			return qt.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

}
