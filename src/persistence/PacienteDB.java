package persistence;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;

import model.DatosMed;
import model.DatosPaciente;
import model.NuevoMedico;
import model.NuevoPaciente;

public class PacienteDB {
	public static PacienteDB instance;
	
	public static PacienteDB getInstance() {
		if(instance==null) {
			instance=new PacienteDB();
			}
			return instance;
		}


	public boolean grabarPaciente(NuevoPaciente pac) {
		try {
			if (ConnectionAdmin.getInstance().connectMongo()) {
				Bson filtroDni=Filters.eq("dniPaciente",pac.getDni().toString());
				Bson filtroNombre=Filters.eq("nombre",pac.getNombre());
				Bson filtro=Filters.and(filtroDni,filtroNombre);
				Document doc=new Document("nombre",pac.getNombre())
						.append("dniPaciente",pac.getDni())
						.append("domicilio",pac.getDomicilio())
						.append("mail",pac.getMail())
						.append("telefono", pac.getTelefono())
						.append("usuario",pac.getUser())
						.append("contrasena",new String(pac.getPassword()))
						.append("numOS",pac.getNumOS());
						
				MongoCollection<Document> datos=ConnectionAdmin.getInstance().connectCollection("DatosPacientes");
				FindOneAndUpdateOptions options= new FindOneAndUpdateOptions();
				options.upsert(true);
				datos.findOneAndUpdate(filtro, new Document("$set",doc),options);
			}
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public DatosPaciente buscarPaciente(String dni)
	{
		try
		{
			 
				//Crear filtro
				Bson filtro = Filters.eq("dniPaciente", dni);
				
				//Conecto a la coleccion
				 MongoCollection<Document> datos = ConnectionAdmin.getInstance().connectCollection("DatosPacientes");
				
				 //ejecuto la busqueda
				 Document resultado = datos.find(filtro).first();
				 
				 DatosPaciente pac = null;
				 if(resultado!=null)
				 {
					 
					 //transformo documento a Contacto
					pac = new DatosPaciente(resultado.getString("dniPaciente"),resultado.getString("domicilio"),
							resultado.getString("nombre"),resultado.getString("mail"),resultado.getString("telefono"),
							resultado.getString("contrasena"), resultado.getString("usuario"),resultado.getString("numOS"));
					
				 }
				 
				 return pac;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}

