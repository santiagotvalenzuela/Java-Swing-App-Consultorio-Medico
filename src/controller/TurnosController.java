package controller;

import java.util.ArrayList;
import model.DatosTurnos;
import model.InfoTurnos;
import model.DatosPaciente;
import model.NuevoPaciente;
import model.TurnosPaciente;
import persistence.ContactoBD;
import persistence.PacienteDB;
import persistence.TurnosBD;

public class TurnosController {
	private static TurnosController  controller;
	private ArrayList<DatosTurnos> datosTur;
	private ArrayList<TurnosPaciente>infoTur;
	
	public static TurnosController getController() {
		if (controller==null) {
			controller=new TurnosController();
		}
		return controller;
	}
	private TurnosController() {
		datosTur=ContactoBD.getInstance().getTurnoFromDB();
	}
	
	public int generarTurno(String nombreMed,String hora,String date, String nombrePac){
		DatosTurnos tur=buscarTurno(nombreMed,hora,date);
		if(tur!=null){
			new InfoTurnos(date,nombreMed,hora,nombrePac);
			manejarTurno(nombreMed,hora,date);
			return 0;
		}
		else {
			return 1;
		}
	}
	
	public int eliminarTurno(String nombreMed,String hora,String date, String nombrePac) {
		TurnosPaciente tur=searchTurno(nombreMed,hora,date,nombrePac);
		if(tur!=null) {
			return 0;
		}
		return 1;
	}
	
	public void manejador2(String nombreMed,String hora,String date,String nombrePac) {
		TurnosBD.getInstance().grabarTurnoRenovado(nombreMed, hora, date);
		TurnosBD.getInstance().eliminarInfoTurno(nombreMed, hora, date, nombrePac);
	}
	
	private DatosTurnos buscarTurno(String nombreMed,String hora,String date)
	{	
		DatosTurnos v = TurnosBD.getInstance().buscarTurno(nombreMed,hora,date);
		if (v!=null)
		{
			datosTur.add(v);
			return v;
		}
		return null;
	}
	
	private void manejarTurno(String nombreMed,String hora,String date) {
		TurnosBD.getInstance().eliminarTurnoBD(nombreMed,hora,date);
	}
	private TurnosPaciente searchTurno(String nombreMed,String hora,String date, String nombrePac) {
		TurnosPaciente f = TurnosBD.getInstance().busqueda(nombreMed,hora,date,nombrePac);
		if (f!=null)
		{
			return f;
		}
		return null;
	}
	
	
}
