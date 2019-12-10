package src.co.com.codesa.correo.principal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class JavaTweet {

	static String consumerKeyStr = "";
	static String consumerSecretStr = "";
	static String accessTokenStr = "";
	static String accessTokenSecretStr = "";

	public static void main(String[] args) {
		
		String strmensajeTwitter = "Hola, calomora!";

		try {
			Twitter twitter = new TwitterFactory().getInstance();
			

			try {
				/* Creamos un Objeto de tipo Properties */
				Properties propiedades = new Properties();
				propiedades
						.load(SendMailSSL.class
								.getClassLoader()
								.getResourceAsStream(
										"co/com/codesa/correo/resources/DatosMensajeria.properties"));

				/** Obtenemos los parametros definidos en el archivo */
				consumerKeyStr = propiedades.getProperty("consumerKeyStr");
				consumerSecretStr = propiedades.getProperty("consumerSecretStr");
				accessTokenStr = propiedades.getProperty("accessTokenStr");
				accessTokenSecretStr = propiedades.getProperty("accessTokenSecretStr");

			} catch (Exception e) {
				// TODO: handle exception
			}
						
			twitter.setOAuthConsumer(consumerKeyStr, consumerSecretStr);
			AccessToken accessToken = new AccessToken(accessTokenStr,
					accessTokenSecretStr);

			twitter.setOAuthAccessToken(accessToken);
			String fechahoras = fechaHora();

			twitter.updateStatus("**Jenkins Notification"+ ' ' + fechahoras + ' ' + strmensajeTwitter);

			System.out.println("Successfully updated the status in Twitter.");
		} catch (TwitterException te) {
			te.printStackTrace();
		}
	}
	
	public static String fechaHora() {
		
		Date date = new Date();
		
		DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
		String fechahora = hourdateFormat.format(date);
			
		return fechahora ;
		
	}

}
