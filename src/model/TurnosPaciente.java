package model;

import java.util.ArrayList;

import persistence.TurnosBD;

public class TurnosPaciente {
	private String fecha;
	private String nombreMedico;
	private String horario;
	private String nombrePaciente;
	
	public TurnosPaciente(String fecha,String nombreMedico,String horario,String nombrePaciente) {
		super();
		this.fecha=fecha;
		this.setNombreMedico(nombreMedico);
		this.setHorario(horario);
		this.setNombrePaciente(nombrePaciente);
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getNombreMedico() {
		return nombreMedico;
	}
	public void setNombreMedico(String nombreMedico) {
		this.nombreMedico = nombreMedico;
	}

	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getNombrePaciente() {
		return nombrePaciente;
	}
	public void setNombrePaciente(String nombrePaciente) {
		this.nombrePaciente = nombrePaciente;
	}
	public boolean esElNombre(String nombreMed) {
		return(nombreMed.equals(nombreMedico));
	}
}

