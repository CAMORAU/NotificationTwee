package src.co.com.codesa.correo.principal;

import java.io.FileInputStream;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailSSL {

	static String strCorreoElectronico = "";
	static String strContrasenaCorreo = "";

	public static void main(String[] args) {

		String strDestinatario = args[0];
		String strAsuntoCorreo = args[1];
		String strCuerpoCorreo = args[2];

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		try {
			/* Creamos un Objeto de tipo Properties */
			Properties propiedades = new Properties();
			propiedades
					.load(SendMailSSL.class
							.getClassLoader()
							.getResourceAsStream(
									"co/com/codesa/correo/resources/DatosMensajeria.properties"));

			/** Obtenemos los parametros definidos en el archivo */
			strCorreoElectronico = propiedades
					.getProperty("correo_electronico");
			strContrasenaCorreo = propiedades.getProperty("contrasena");

		} catch (Exception e) {
			// TODO: handle exception
		}

		/** Cargamos el archivo desde la ruta especificada */

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(strCorreoElectronico,
								strContrasenaCorreo);
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(strCorreoElectronico));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(strDestinatario));
			message.setSubject(strAsuntoCorreo);
			message.setText(strCuerpoCorreo);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}