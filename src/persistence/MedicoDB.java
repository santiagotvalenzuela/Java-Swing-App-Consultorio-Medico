package persistence;
import java.awt.List;
import java.util.ArrayList;
import java.util.Vector;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;

import model.DatosMed;
import model.DatosPaciente;
import model.NuevoMedico;
import model.TurnosPaciente;
public class MedicoDB {
	public static MedicoDB instance;
	
	public static MedicoDB getInstance() {
		if(instance==null) {
			instance=new MedicoDB();
			}
			return instance;
		}


	public boolean grabarMedico(NuevoMedico med) {
		try {
			if (ConnectionAdmin.getInstance().connectMongo()) {
				Bson filtroDni=Filters.eq("dniMedico",med.getDni().toString());
				Bson filtroNombre=Filters.eq("nombre",med.getNombre());
				Bson filtro=Filters.and(filtroDni,filtroNombre);
				Document doc=new Document("nombre",med.getNombre())
						.append("dniMedico",med.getDni())
						.append("domicilio",med.getDomicilio())
						.append("mail",med.getMail())
						.append("telefono", med.getTelefono())
						.append("usuario",med.getUser())
						.append("contrasena",new String(med.getPassword()))
						.append("dias", med.getDias())
						.append("Horario_Entrada",med.getHorario_ent())
						.append("Horario_Salida",med.getHorario_sal());
						
				MongoCollection<Document> datos=ConnectionAdmin.getInstance().connectCollection("DatosMedico");
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
	
	@SuppressWarnings("unchecked")
	public DatosMed buscarMedico(String dni)
	{
		try
		{
			 
				//Crear filtro
				Bson filtro = Filters.eq("dniMedico", dni);
				
				//Conecto a la coleccion
				 MongoCollection<Document> datos = ConnectionAdmin.getInstance().connectCollection("DatosMedico");
				
				 //ejecuto la busqueda
				 Document resultado = datos.find(filtro).first();
				 
				 DatosMed med = null;
				 if(resultado!=null)
				 {
					med = new DatosMed(resultado.getString("dniMedico"),resultado.getString("domicilio"),
							resultado.getString("nombre"),resultado.getString("mail"),resultado.getString("telefono"),
							resultado.getString("contrasena"), resultado.getString("usuario"),   (ArrayList<String>) resultado.get("dias"),
							resultado.getInteger("Horario_Entrada"),resultado.getInteger("Horario_Salida"));
				 }
				 
				 return med;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public ArrayList<TurnosPaciente> getTurnosFromDB(String nombreMed)
	{
		ArrayList<TurnosPaciente> datospac = new ArrayList<TurnosPaciente>();
		//Conecto a Mongo
		if (ConnectionAdmin.getInstance().connectMongo())//crea instancia de coneccion a BD
		{
			 MongoCollection<Document> datos = ConnectionAdmin.getInstance().connectCollection("InfoTurnos");
			 
			 Bson filtro=Filters.eq("nombreMedico",nombreMed);
			 //convierto documentos a objetos
			 MongoCursor<Document> cursor = datos.find(filtro).iterator();
			 try {
				 Document doc;
			     while (cursor.hasNext()) 
			     {
			    	 //leo documento y convierto a Contacto
			    	 doc = cursor.next();
			    	 
			    	 datospac.add(new TurnosPaciente(doc.getString("fecha"),doc.getString("nombreMedico")
			    			 ,doc.getString("hora"),doc.getString("nombrePaciente")));
			    	
			     }
			     cursor.close();
			 } finally {
			     cursor.close();
			 }
		}
		
		return datospac;
	}
}

