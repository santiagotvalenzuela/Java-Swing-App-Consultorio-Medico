package controller;

import java.util.ArrayList;

import model.DatosMed;
import model.DatosPaciente;
import model.NuevoPaciente;
import persistence.ContactoBD;
import persistence.MedicoDB;
import persistence.PacienteDB;

public class PacienteController {
	private static PacienteController  controller;
	private ArrayList<DatosPaciente> datospac;
	private ArrayList<NuevoPaciente> datosnewpac;

	
	public static PacienteController getController() {
		if (controller==null) {
			controller=new PacienteController();
		}
		return controller;
	}
	private PacienteController() {
		datospac=ContactoBD.getInstance().getPacienteFromDB();
	}
	public void crearPaciente(String dni,String nombre,String domicilio,String mail,String telefono,String user,String password,String numOS) {
		new NuevoPaciente( dni, nombre, domicilio, mail, telefono, user, password, numOS);
	}
	
	public int generarPaciente(String dniPaciente){
			ContactoBD.getInstance().getPacienteFromDB();
			DatosPaciente pac=buscarPaciente(dniPaciente);
			
			if(pac!=null && pac.esElDni(dniPaciente)==true){
				return 1;
			}
			else {
				return 0;
			}
		}
	private DatosPaciente buscarPaciente(String dniPaciente)
	{	
		for (DatosPaciente v : datospac)
		{
		if (v.esElDni(dniPaciente)) 
			{
			return v;
			}
		}
		DatosPaciente v = PacienteDB.getInstance().buscarPaciente(dniPaciente);
		if (v!=null)
		{
			datospac.add(v);
			return v;
		}
		return null;
	}
	
	public int loginPaciente(String nombrePac, String contraPac) {
		ContactoBD.getInstance().getPacienteFromDB();
		DatosPaciente pac=loginChecker(nombrePac, contraPac);
		if(pac!=null)
		{
			return 0;
		}
		else {
			return 1;
		}
	}
	
	private DatosPaciente loginChecker(String nombrePac,String contraPac) {
		
		for(DatosPaciente v:datospac) 
		{
			for(DatosPaciente f:datospac) {
				if(v.esElUsuario(nombrePac)&& f.esLaContra(contraPac)) {
					return v;
				}
			}
			
		}
		return null;
	}
}
