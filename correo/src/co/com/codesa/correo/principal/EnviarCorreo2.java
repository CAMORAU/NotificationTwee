package src.co.com.codesa.correo.principal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
// import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

//import javax.activation.DataHandler;
//import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

public class EnviarCorreo2 {

	String from="roberth.system@gmail.com";
	
	String host="smtp.gmail.com";
	String puerto="465";

	
	public void enviarCorreo2(String correo, String pAsunto, String pContenido) 
	{

    	    String to = correo;
		    String texto = pContenido;
			String subject = pAsunto;

			Properties props = System.getProperties();
			
			props.put("mail.smtp.starttls.enable", "false");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.user", from);
			props.put("mail.smtp.password", pass);
			props.put("mail.smtp.port", puerto);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.ssl.trust", "true");
			props.put("mail.smtp.ssl.enable", "true");
			props.put("mail.smtp.debug", "false");
			
			Session session = Session.getDefaultInstance(props);
			MimeMessage message = new MimeMessage(session);

			try {
				message.setFrom(new InternetAddress(from));
				InternetAddress toAddress = new InternetAddress(to);

				toAddress = new InternetAddress(to);

		
				message.addRecipient(Message.RecipientType.TO, toAddress);
				

				BodyPart cuerpoMensaje = new MimeBodyPart();
				cuerpoMensaje.setText(texto);

				MimeMultipart multiparte = new MimeMultipart();

				multiparte.addBodyPart(cuerpoMensaje);
				
				message.setSubject(subject);
				message.setContent(multiparte);
				Transport transport = session.getTransport("smtp");
				transport.connect(host, Integer.parseInt(puerto), from, pass);
				transport.sendMessage(message, message.getAllRecipients());
				transport.close();
				return;

			} catch (AddressException ae) {
				ae.printStackTrace();
			} catch (MessagingException me) {
				me.printStackTrace();
			}
		}
	
	
	
	
	
	String pass="clavexxx";
}
