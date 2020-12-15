package persistence;



import java.io.FileInputStream;
import java.util.Properties;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ConnectionAdmin
{
	private String urlBD;
	private String urlBDFin;
	private String urlConexion;
	private String nombreBD;
	private String mongoJDBC;
	private String user;
	private String pass;
	
	//Guarda conexion a instancia Mongo
	private MongoClient mongoClient;
	//Guarda conexion a Base de dato
	private MongoDatabase database;
	
	//implementacion singleton
	private static ConnectionAdmin instance;
	
	public static ConnectionAdmin getInstance()
	{
		if (instance==null)
			instance = new ConnectionAdmin();
		return instance;
	}
	
	private void getParametros()
	{
		String configuracion = "ConfigBD.txt";
	    Properties propiedades;
	 
	    // Carga del fichero de propiedades 
	    try 
	    {
	       FileInputStream f = new FileInputStream(configuracion);	 
	       propiedades = new Properties();
	       propiedades.load(f);
	       f.close();
	 
       // Leo los valores de configuracion
	       user = propiedades.getProperty("usuario"); 
	       pass = propiedades.getProperty("password");
	       nombreBD = propiedades.getProperty("nombreBase");
	       mongoJDBC= propiedades.getProperty("mongoJDBC");
	       urlBD= propiedades.getProperty("urlBD");
	       urlBDFin= propiedades.getProperty("urlBDFin");
	       System.out.println(urlBD);
	       
	     } 
	    catch (Exception e) 
	     {
				System.out.println("Mensaje Error: " + e.getMessage());
				System.out.println("Stack Trace: " + e.getStackTrace());
	     }

	}
	private ConnectionAdmin()
	{

		//obtengo parametros de conexion
		getParametros();
		
		//armo string conexion
		urlConexion= mongoJDBC + user + ":" + pass + urlBD + nombreBD + urlBDFin;
		
		
	}
	//Se conecta a una Base de datos de una  instancia de MongoDB Cloud 
	public boolean connectMongo()
	{
		
		mongoClient = MongoClients.create(urlConexion);
		//get database
		database = mongoClient.getDatabase(nombreBD);
		
		return (mongoClient!=null & database!=null);
	}
	
	//devuelve la coleccion de documentos de una Collection
	public MongoCollection<Document> connectCollection(String collectionName)
	{
		 
		 //access collection
		 MongoCollection<Document> collection = database.getCollection(collectionName);
		 return collection;
	}
	
	//cerrar conexion
	public void cerrarConexion()
	{
		mongoClient.close();
	}

	
}