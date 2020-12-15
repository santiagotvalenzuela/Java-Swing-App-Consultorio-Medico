package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class DatosTurnos {
	private String fecha;
	private String nombreMedico;
	private String horario;
	private String dia;
	public DatosTurnos(String fecha, String dia,String nombreMedico,String horario) {
		super();
		this.fecha=fecha;
		this.setNombreMedico(nombreMedico);
		this.setHorario(horario);
		this.setDia(dia);
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
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public boolean esElMedico(String nombreMed) {
		return(nombreMed.equals(nombreMedico));
	}
	public boolean esLaHora(String hora) {
		return(hora.equals(horario));
	}
	public boolean esLaFecha(String date) {
		return(date.equals(fecha));
	}

}
