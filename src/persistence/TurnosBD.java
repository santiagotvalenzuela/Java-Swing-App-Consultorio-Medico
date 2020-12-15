package persistence;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.IntStream;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;

import model.DatosPaciente;
import model.DatosTurnos;
import model.InfoTurnos;
import model.NuevoMedico;
import model.TurnosPaciente;

public class TurnosBD {
public static TurnosBD instance;
	
	public static TurnosBD getInstance() {
		if(instance==null) {
			instance=new TurnosBD();
			}
			return instance;
		}
	public boolean grabarTurno(NuevoMedico med) {
		ArrayList<String>dias=new ArrayList<>(31);
		ArrayList<String>fechas=new ArrayList<String>();
		ArrayList<String>info=med.getDias();
		System.out.println(info);
		LocalDate hoy=LocalDate.now();
		YearMonth mes=YearMonth.from(hoy);
		LocalDate local= mes.atDay(hoy.getDayOfMonth());
		while(YearMonth.from(local).equals(mes)) {
			String fecha=local.getDayOfWeek().getDisplayName(TextStyle.FULL,new Locale("es","ES"));
			if(info.contains(fecha.toUpperCase())) {
				dias.add(local.toString());
				fechas.add(fecha);
			}
			local=local.plusDays(1);
		}

		LocalTime start=LocalTime.of(med.getHorario_ent(),0);
		LocalTime endTime=LocalTime.of(med.getHorario_sal(),0);
		final int n=(endTime.getHour()-start.getHour())*2;
		Duration span=Duration.between(start, endTime).dividedBy(n);
		LocalTime[] timeFrame=IntStream.rangeClosed(0,n)
				.mapToObj(i->start.plus(span.multipliedBy(i)))
				.toArray(LocalTime[]::new);
		String horas=Arrays.toString(timeFrame);
		String partes[]=horas.split(" ");
		for(int i=0;i<partes.length;i++) {
			partes[i]=partes[i].replace("[", "");
			partes[i]=partes[i].replace("]", "");
			partes[i]=partes[i].replace(",", "");
		}
		
		try {
			if (ConnectionAdmin.getInstance().connectMongo()) {
				for(int j=0;j<dias.size();j++) {
					for(int i=0;i<partes.length;i++) {
						 Document doc=new Document("nombre",med.getNombre())
									.append("hora",partes[i])
									.append("fecha",dias.get(j))
									.append("día",fechas.get(j));
						 	MongoCollection<Document> datos=ConnectionAdmin.getInstance().connectCollection("TurnosDisponibles");
							FindOneAndUpdateOptions options= new FindOneAndUpdateOptions();
							options.upsert(true);
							datos.insertOne(doc);
					}
				}
			}
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean grabarNuevoTurno(InfoTurnos tur) {
		
		try {
			if (ConnectionAdmin.getInstance().connectMongo()) {
				Document doc=new Document("nombreMedico",tur.getNombreMedico())
									.append("hora",tur.getHorario())
									.append("fecha",tur.getFecha())
									.append("nombrePaciente",tur.getNombrePaciente());
						 	MongoCollection<Document> datos=ConnectionAdmin.getInstance().connectCollection("InfoTurnos");
							FindOneAndUpdateOptions options= new FindOneAndUpdateOptions();
							options.upsert(true);
							datos.insertOne(doc);
					}
				
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public DatosTurnos buscarTurno(String nombreMed,String hora,String date )
	{
		try
		{
				Bson filtro = Filters.eq("nombre", nombreMed);
				Bson filtro_2 = Filters.eq("hora", hora);
				Bson filtro_3 = Filters.eq("fecha",date);
				Bson superFiltro=Filters.and(filtro,filtro_2,filtro_3);
				
				 MongoCollection<Document> datos = ConnectionAdmin.getInstance().connectCollection("TurnosDisponibles");
				
				 Document resultado = datos.find(superFiltro).first();
				 
				 DatosTurnos tur = null;
				 if(resultado!=null)
				 {
					tur = new DatosTurnos(resultado.getString("fecha"),resultado.getString("nombre"),
							resultado.getString("hora"),resultado.getString("día"));
					
				 }
				 
				 return tur;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}


	public void eliminarTurnoBD(String nombreMed,String hora,String date) 
	{
		 MongoCollection<Document> datos = ConnectionAdmin.getInstance().connectCollection("TurnosDisponibles");

		//Crear filtro
		Bson filtro = Filters.eq("fecha", date);
		Bson filtro_2 = Filters.eq("nombre", nombreMed);
		Bson filtro_3 = Filters.eq("hora",hora);
		Bson superFiltro=Filters.and(filtro,filtro_2,filtro_3);
		
		Document prueba=datos.findOneAndDelete(superFiltro);
		
	}
	public boolean grabarTurnoRenovado(String nombreMed,String hora,String date) {
		
		try {
			if (ConnectionAdmin.getInstance().connectMongo()) {
				Document doc=new Document("nombre",nombreMed)
									.append("hora",hora)
									.append("fecha",date);
						 	MongoCollection<Document> datos=ConnectionAdmin.getInstance().connectCollection("TurnosDisponibles");
							FindOneAndUpdateOptions options= new FindOneAndUpdateOptions();
							options.upsert(true);
							datos.insertOne(doc);
					}
				
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public TurnosPaciente busqueda(String nombreMed,String hora,String date,String nombrePac )
	{
		try
		{
				Bson filtro = Filters.eq("nombreMedico", nombreMed);
				Bson filtro_2 = Filters.eq("hora", hora);
				Bson filtro_3 = Filters.eq("fecha",date);
				Bson filtro_4 = Filters.eq("nombrePaciente",nombrePac);
				Bson superFiltro=Filters.and(filtro,filtro_2,filtro_3,filtro_4);
				
				 MongoCollection<Document> datos = ConnectionAdmin.getInstance().connectCollection("InfoTurnos");
				
				 Document resultado = datos.find(superFiltro).first();
				 
				 TurnosPaciente tur = null;
				 if(resultado!=null)
				 {
					tur = new TurnosPaciente(resultado.getString("fecha"),resultado.getString("nombreMedico"),
							resultado.getString("hora"),resultado.getString("nombrePaciente"));
					
				 }
				 
				 return tur;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public void eliminarInfoTurno(String nombreMed,String hora,String date,String nombrePac) 
	{
		 MongoCollection<Document> datos = ConnectionAdmin.getInstance().connectCollection("InfoTurnos");

		//Crear filtro
		Bson filtro = Filters.eq("fecha", date);
		Bson filtro_2 = Filters.eq("nombreMedico", nombreMed);
		Bson filtro_3 = Filters.eq("hora",hora);
		Bson filtro_4 = Filters.eq("nombrePaciente",nombrePac);
		Bson superFiltro=Filters.and(filtro,filtro_2,filtro_3,filtro_4);
		
		Document prueba=datos.findOneAndDelete(superFiltro);
		
	}

}
