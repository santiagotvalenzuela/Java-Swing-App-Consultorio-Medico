package model;

import persistence.PacienteDB;

public class NuevoPaciente {
	private String dni;
	private String nombre;
	private String domicilio;
	private String mail;
	private String telefono;
	private String user;
	private String password;
	private String numOS;
	
	public NuevoPaciente(String dni,String nombre,String domicilio,String mail,String telefono,String user,String password,String numOS)
	{	super();
		this.dni=dni;
		this.domicilio=domicilio;
		this.nombre=nombre;
		this.mail=mail;
		this.telefono=telefono;
		this.user=user;
		this.password=password;
		this.setNumOS(numOS);
		PacienteDB.getInstance().grabarPaciente(this);
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
	public String getNumOS() {
		return numOS;
	}
	public void setNumOS(String numOS) {
		this.numOS = numOS;
	}

}

