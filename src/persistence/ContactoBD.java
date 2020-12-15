package persistence;
import controller.SisTurnos;
import model.DatosMed;
import model.DatosPaciente;
import model.DatosTurnos;

import java.awt.List;
import java.util.ArrayList;
import java.util.Vector;

import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;

public class ContactoBD {
	public static ContactoBD instance;
	
	public static ContactoBD getInstance() {
		if(instance==null) {
			instance=new ContactoBD();
		}
		return instance;
	}
	@SuppressWarnings("unchecked")
	public ArrayList<DatosMed> getMedicoFromBD()
	{
		ArrayList<DatosMed> datosmed = new ArrayList<DatosMed>();
		//Conecto a Mongo
		if (ConnectionAdmin.getInstance().connectMongo())//crea instancia de coneccion a BD
		{
			 MongoCollection<Document> datos = ConnectionAdmin.getInstance().connectCollection("DatosMedico");
			 
			 
			 //convierto documentos a objetos
			 MongoCursor<Document> cursor = datos.find().iterator();
			 try {
				 Document doc;
			     while (cursor.hasNext()) 
			     {
			    	 //leo documento y convierto a Contacto
			    	 doc = cursor.next();
			    	 
			    	 datosmed.add(new DatosMed(doc.getString("dniMedico"),doc.getString("nombre")
			    			 ,doc.getString("domicilio"),doc.getString("mail"),doc.getString("telefono"),
			    			 doc.getString("contrasena"),doc.getString("usuario"),   (ArrayList<String>) doc.get("dias"),doc.getInteger("Horario_Entrada")
			    			 ,doc.getInteger("Horario_Salida")));
			    	
			     }
			     cursor.close();
			 } finally {
			     cursor.close();
			 }
		}
		
		return datosmed;
	}
	
	public ArrayList<DatosPaciente> getPacienteFromDB()
	{
		ArrayList<DatosPaciente> datospac = new ArrayList<DatosPaciente>();
		//Conecto a Mongo
		if (ConnectionAdmin.getInstance().connectMongo())//crea instancia de coneccion a BD
		{
			 MongoCollection<Document> datos = ConnectionAdmin.getInstance().connectCollection("DatosPacientes");
			 
			 
			 //convierto documentos a objetos
			 MongoCursor<Document> cursor = datos.find().iterator();
			 try {
				 Document doc;
			     while (cursor.hasNext()) 
			     {
			    	 //leo documento y convierto a Contacto
			    	 doc = cursor.next();
			    	 
			    	 datospac.add(new DatosPaciente(doc.getString("dniPaciente"),doc.getString("nombre")
			    			 ,doc.getString("domicilio"),doc.getString("mail"),doc.getString("telefono"),
			    			 doc.getString("contrasena"),doc.getString("usuario"), doc.getString("numOS")));
			    	
			     }
			     cursor.close();
			 } finally {
			     cursor.close();
			 }
		}
		
		return datospac;
	}
	public ArrayList<DatosTurnos> getTurnoFromDB()
	{
		ArrayList<DatosTurnos> datosTur = new ArrayList<DatosTurnos>();
		//Conecto a Mongo
		if (ConnectionAdmin.getInstance().connectMongo())//crea instancia de coneccion a BD
		{
			 MongoCollection<Document> datos = ConnectionAdmin.getInstance().connectCollection("TurnosDisponibles");
			 
			 
			 //convierto documentos a objetos
			 MongoCursor<Document> cursor = datos.find().iterator();
			 try {
				 Document doc;
			     while (cursor.hasNext()) 
			     {
			    	 //leo documento y convierto a Contacto
			    	 doc = cursor.next();
			    	 
			    	 datosTur.add(new DatosTurnos(doc.getString("fecha"),doc.getString("día"),doc.getString("nombre"),doc.getString("hora")));
			    	
			     }
			     cursor.close();
			 } finally {
			     cursor.close();
			 }
		}
		
		return datosTur;
	}
	
	public boolean registrarMedico(int dni,String nombre,String domicilio,String mail,String telefono,String password,String user,List dias,Integer horario_ent,Integer horario_sal)
	{	
		try
		{
			
			//Crear filtro
			Bson filtro = Filters.eq("dni", dni);
			Document newDoc = new Document("dni", dni)
	                .append("nombre", nombre)
	                .append("domicilio", domicilio)
	                .append("email", mail)
	                .append("Telefono", telefono)
					.append("password",password)
					.append("user",user)
					.append("dias",dias)
					.append("horario_ent", horario_ent)
					.append("horario_sal", horario_sal);
	
			//me conecto a la coleccion
			 MongoCollection<Document> datos = ConnectionAdmin.getInstance().connectCollection("DatosMedico");
			
			 //defino opcion para que inserte el documento si no existe
			FindOneAndUpdateOptions options = new FindOneAndUpdateOptions();
			options.upsert(true);
			
			//inserto documento
			datos.findOneAndUpdate(filtro,new Document("$set",newDoc),options);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		

	}
}


