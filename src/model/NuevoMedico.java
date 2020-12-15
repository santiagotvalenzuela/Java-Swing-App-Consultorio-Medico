package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import persistence.MedicoDB;
import persistence.TurnosBD;
import persistence.ContactoBD;


public class NuevoMedico {
	private String dni;
	private String nombre;
	private String domicilio;
	private String mail;
	private String telefono;
	private String password;
	private String user;
	private ArrayList<String> dias;
	private Integer horario_ent;
	private Integer horario_sal;
	
	public NuevoMedico(String dni,String nombre,String domicilio,String mail,String telefono,String password,String user,ArrayList<String> dias, Integer horario_ent, Integer horario_sal )
	{	super();
		this.dni=dni;
		this.domicilio=domicilio;
		this.nombre=nombre;
		this.mail=mail;
		this.telefono=telefono;
		this.password=password;
		this.user=user;
		this.dias=dias;
		this.horario_ent=horario_ent;
		this.horario_sal=horario_sal;
		MedicoDB.getInstance().grabarMedico(this);
		TurnosBD.getInstance().grabarTurno(this);
	}

	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setUser(String user) {
		this.user=user;
	}
	public String getUser() {
		return user;
	}
	public void setPassword(String password) {
		this.password=password;
	}
	public String getPassword() {
		return password;
	}
	public void setDias(ArrayList<String> dias) {
		this.dias=dias;
	}
	public ArrayList<String> getDias() {
		return dias;
	}
	public void setHorario_ent(Integer horario_ent) {
		this.horario_ent=horario_ent;
	}
	public Integer getHorario_ent() {
		return horario_ent;
	}
	public void setHorario_sal(Integer horario_sal) {
		this.horario_sal=horario_sal;
	}
	public Integer getHorario_sal() {
		return horario_sal;
	}
	
}