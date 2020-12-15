package view;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import controller.PacienteController;
import controller.SisTurnos;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class CrearPaciente extends JFrame {
	private JPanel contentPane;
	private JTextField numOS;
	private int rta;
	public CrearPaciente() {
		JFrame frame=new JFrame();
		setTitle("Ingrese Datos del Paciente");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 450, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblDni = new JLabel("DNI:");
		lblDni.setBounds(15, 46, 194, 20);
		contentPane.add(lblDni);
		JTextField dni = new JTextField();
		dni.setBounds(206, 43, 146, 26);
		contentPane.add(dni);
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(15,90, 194, 20);
		contentPane.add(lblNombre);
		JTextField nombre= new JTextField();
		nombre.setBounds(206, 88, 146, 26);
		contentPane.add(nombre);
		JLabel lblDomi = new JLabel("Domicilio:");
		lblDomi.setBounds(15,120, 194, 20);
		contentPane.add(lblDomi);
		JTextField domicilio= new JTextField();
		domicilio.setBounds(206, 118, 146, 26);
		contentPane.add(domicilio);
		JLabel lblMail = new JLabel("Correo:");
		lblMail.setBounds(15,160, 194, 20);
		contentPane.add(lblMail);
		JTextField mail= new JTextField();
		mail.setBounds(206, 157, 146, 26);
		contentPane.add(mail);
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(15,197, 194, 20);
		contentPane.add(lblTelefono);
		JTextField tel= new JTextField();
		tel.setBounds(206, 195, 146, 26);
		contentPane.add(tel);
		JLabel lblUsuario = new JLabel("Ingrese Nuevo Usuario:");
		lblUsuario.setBounds(15,294, 194, 20);
		contentPane.add(lblUsuario);
		JTextField usu= new JTextField();
		usu.setBounds(206, 291, 146, 26);
		contentPane.add(usu);
		JLabel lblContrasena = new JLabel("Ingrese Nueva Contraseña:");
		lblContrasena.setBounds(15,331, 194, 20);
		contentPane.add(lblContrasena);
		JPasswordField pass= new JPasswordField();
		pass.setBounds(206, 325, 146, 26);
		contentPane.add(pass);
		
		JLabel lblIngreseNumero = new JLabel("Ingrese Numero de Obra Social");
		lblIngreseNumero.setBounds(15, 240, 161, 32);
		contentPane.add(lblIngreseNumero);
		
		numOS = new JTextField();
		numOS.setBounds(206, 246, 146, 26);
		contentPane.add(numOS);
		numOS.setColumns(10);
		JButton conf=new JButton("Confirmar Datos");
		conf.setBounds(203, 378, 190, 55);
		contentPane.add(conf);
		
		conf.addActionListener(new ActionListener(){//accion para crear nuevo usuario
			public void actionPerformed(ActionEvent e) {
				String name=nombre.getText();
				String doc=dni.getText();
				String dom=domicilio.getText();
				String correo=mail.getText();
				String tele=tel.getText();
				String user=usu.getText();
				String contra= new String(pass.getPassword());
				String obras=numOS.getText();
				rta=(PacienteController.getController().generarPaciente(dni.getText()));
				if(rta==0) {
				PacienteController.getController().crearPaciente(doc, name, dom, correo, tele, user,contra,obras);	
				JOptionPane.showMessageDialog(contentPane, "Datos Registrados Correctamente");
				
				}
				else if (rta==1) {
					JOptionPane.showMessageDialog(contentPane, "El Medico Ingresado Ya Existe");
				}
			}
		});
	}
}

