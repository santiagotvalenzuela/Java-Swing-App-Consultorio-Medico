package view;

import java.util.Scanner;
import java.util.Vector;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import controller.SisTurnos;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class LoginMed extends JFrame {
	private JPanel contentPane;
	private int rta;
//------------Login Menu---------------
	public  LoginMed() {
		JFrame frame=new JFrame();
		setTitle("Ingresar como Profesional");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(15, 46, 194, 20);
		contentPane.add(lblUsuario);
		JTextField usuario = new JTextField();
		usuario.setBounds(206, 43, 146, 26);
		contentPane.add(usuario);
		JLabel lblContra = new JLabel("Contraseña:");
		lblContra.setBounds(15,90, 194, 20);
		contentPane.add(lblContra);
		JPasswordField contrasena= new JPasswordField();
		contrasena.setBounds(206, 88, 146, 26);
		contentPane.add(contrasena);
		JButton ingresar=new JButton("Ingresar");
		ingresar.setBounds(206,125,100,40);
		contentPane.add(ingresar);
		MedicoID menu=new MedicoID();
		ingresar.addActionListener(new ActionListener() {//accion para validar entrada de datos
			public void actionPerformed(ActionEvent e) {
				String usuIng=usuario.getText();
				String passIng= new String(contrasena.getPassword());
				rta=(SisTurnos.getController().loginMedico(usuIng,passIng));
				if (rta==0) {
					JOptionPane.showMessageDialog(contentPane, "Datos ingresados");
					menu.setVisible(true);
					
				}
				else {
					JOptionPane.showMessageDialog(contentPane, "Datos ingresados incorrectos");
				}
			}
			});
		
		JLabel lblPregunta = new JLabel("¿No tienes Usuario?");
		lblPregunta.setBounds(15, 190, 194, 20);
		contentPane.add(lblPregunta);
		JButton crearUs=new JButton("Crear Usuario");
		crearUs.setBounds(206,185,120,40);
		contentPane.add(crearUs);
		CrearMedico datos=new CrearMedico();
		crearUs.addActionListener(new ActionListener(){//accion para crear nuevo usuario
			public void actionPerformed(ActionEvent e) {
				datos.setVisible(true);
			}
		});
}
}
