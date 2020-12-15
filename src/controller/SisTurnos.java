package controller;
import persistence.MedicoDB;
import persistence.ContactoBD;
import persistence.ConnectionAdmin;
import view.MenuPrincipal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import model.DatosPaciente;
import model.NuevoMedico;
import model.TurnosPaciente;
import model.DatosMed;
import persistence.ConnectionAdmin;

public class SisTurnos {
	private ArrayList<DatosMed> datosmed;
	private ArrayList<NuevoMedico>datosnewmed;
	private ArrayList<TurnosPaciente>turPac;
	private static SisTurnos controller;
	
	//----------Controller de Medico o Profesional--------------------
	
	public static SisTurnos getController() {
		if (controller==null) {
			controller=new SisTurnos();
		}
		return controller;
	}
	
	private SisTurnos() {
		datosmed=ContactoBD.getInstance().getMedicoFromBD();
	}
	
	public ArrayList<String> turnosReservados(String nombreMed)
	{
		turPac=MedicoDB.getInstance().getTurnosFromDB(nombreMed);
		ArrayList<String>turnos=new ArrayList<String>();
		for(int i=0;i<turPac.size();i++) {
			if(i>0) {
				turnos.add("\n");
			}
			turnos.add("Fecha:"+" "+turPac.get(i).getFecha());
			turnos.add("Nombre Médico:"+" "+turPac.get(i).getNombreMedico());
			turnos.add("Hora:"+" "+turPac.get(i).getHorario());
			turnos.add("Nombre Paciente:"+" "+turPac.get(i).getNombrePaciente());
		}
		
		return turnos;
		
	}
	
	
	public void crearMedico(String dni,String nombre,String domicilio,String mail,String telefono,String password,String user, ArrayList<String> dias, Integer horario_ent, Integer horario_sal)
	{
		new NuevoMedico(dni,nombre,domicilio,mail,telefono,password,user,dias,horario_ent,horario_sal);
	}
	
	public int generarMedico(String dniMedico) {
		ContactoBD.getInstance().getMedicoFromBD();
		DatosMed med=buscarMedico(dniMedico);
		
		if(med!=null && med.esElDni(dniMedico)==true){
			return 1;
		}
		else {
			return 0;
		}
	}
	
	public int loginMedico(String nombreMed, String contraMed) {
		ContactoBD.getInstance().getMedicoFromBD();
		DatosMed med=loginChecker(nombreMed, contraMed);
		if(med!=null)
		{
			return 0;
		}
		else {
			return 1;
		}
	}
	
	private DatosMed buscarMedico(String dniMedico)
	{	
		for (DatosMed v : datosmed)
		{
		if (v.esElDni(dniMedico)) 
			{
			return v;
			}
		}
		DatosMed v = MedicoDB.getInstance().buscarMedico(dniMedico);
		if (v!=null)
		{
			datosmed.add(v);
			return v;
		}
		return null;
	}
	
	private DatosMed loginChecker(String nombreMed,String contraMed) {
		
		for(DatosMed v:datosmed) 
		{
			for(DatosMed f:datosmed) {
				if(v.esElUsuario(nombreMed)&& f.esLaContra(contraMed)) {
					return v;
				}
			}
			
		}
		return null;
	}
}