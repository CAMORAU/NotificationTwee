package src.co.com.codesa.correo.principal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Inicio {
	
	public static SimpleDateFormat sdf;

	public static void main(String[] args) {

		EnviarCorreo2 enviarCorreo = new EnviarCorreo2();
		String fechaHoy = null;
		String correo = null;
		String asunto = null;
		String texto = null;
		boolean flag = true;
	
		sdf = new SimpleDateFormat("yyyy-MM-dd");
						
		if (args.length > 0) {   // si hay argumentos la conexion es via web
				
			try {

		        correo = args[0];
				asunto = args[1];
				texto = args[2];
                    
                System.out.println("WEB");	
				System.out.println("correo......"+correo);
				System.out.println("asunto......"+asunto);
				System.out.println("texto......."+texto);
				
				enviarCorreo.enviarCorreo2(correo, asunto, texto);
				System.out.println("Correo Enviado");	
				
			}
			catch (Exception e) {
					System.out.println("entro al catch" + e.getMessage());
					e.printStackTrace();
			    }
			
		    return;
		    
		}
		
	   }    

    }

