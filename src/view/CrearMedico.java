package view;

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
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JList;
import java.awt.List;
import javax.swing.AbstractListModel;
import java.awt.Cursor;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.TextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class CrearMedico extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int rta;
	
	@SuppressWarnings("unchecked")
	public CrearMedico() {
		JFrame frame=new JFrame();
		setTitle("Ingrese Datos del Profesional");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 450, 600);
		contentPane = new JPanel();
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(15, 24, 194, 20);
		contentPane.add(lblNombre);
		JTextField nombre= new JTextField();
		nombre.setBounds(206, 21, 146, 26);
		contentPane.add(nombre);
		JLabel lblDni = new JLabel("DNI:");
		lblDni.setBounds(15,55, 194, 20);
		contentPane.add(lblDni);
		JTextField dni = new JTextField();
		dni.setBounds(206, 55, 146, 26);
		contentPane.add(dni);
		JLabel lblDomi = new JLabel("Domicilio:");
		lblDomi.setBounds(15,95, 194, 20);
		contentPane.add(lblDomi);
		JTextField domicilio= new JTextField();
		domicilio.setBounds(206, 92, 146, 26);
		contentPane.add(domicilio);
		JLabel lblMail = new JLabel("Correo:");
		lblMail.setBounds(15,126, 194, 20);
		contentPane.add(lblMail);
		JTextField mail= new JTextField();
		mail.setBounds(206, 126, 146, 26);
		contentPane.add(mail);
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(15,163, 194, 20);
		contentPane.add(lblTelefono);
		JTextField tel= new JTextField();
		tel.setBounds(206, 163, 146, 26);
		contentPane.add(tel);
		JLabel lblUsuario = new JLabel("Ingrese Usuario Nuevo:");
		lblUsuario.setBounds(15,194, 194, 20);
		contentPane.add(lblUsuario);
		JTextField usu= new JTextField();
		usu.setBounds(206, 194, 146, 26);
		contentPane.add(usu);
		JLabel lblContrasena = new JLabel("Ingrese Nueva Contraseña:");
		lblContrasena.setBounds(15,234, 194, 20);
		contentPane.add(lblContrasena);
		JPasswordField con= new JPasswordField();
		con.setBounds(206, 231, 146, 26);
		contentPane.add(con);
		JButton conf=new JButton("Confirmar Datos");
		conf.setBounds(206, 495, 190, 55);
		contentPane.add(conf);
		
		
		JPanel panel = new JPanel();
		panel.setToolTipText("");
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "D\u00EDas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(194, 265, 158, 119);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JList list_1 = new JList();
		list_1.setBounds(6, 16, 146, 96);
		panel.add(list_1);
		list_1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		list_1.setModel(new AbstractListModel() {
			String[] values = new String[] {"LUNES", "MARTES", "MIÉRCOLES", "JUEVES", "VIERNES", "SÁBADO"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		JLabel lblSeleccioneDasHbiles = new JLabel("Seleccione D\u00EDas H\u00E1biles");
		lblSeleccioneDasHbiles.setBounds(37, 265, 121, 20);
		contentPane.add(lblSeleccioneDasHbiles);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(7, 7, 12, 1));
		spinner.setBounds(204, 395, 148, 20);
		contentPane.add(spinner);
		
		JLabel lblIngreseHorarioDe = new JLabel("Ingrese Horario de Entrada");
		lblIngreseHorarioDe.setBounds(37, 397, 131, 17);
		contentPane.add(lblIngreseHorarioDe);
		
		JLabel lblIngreseHorarioDe_1 = new JLabel("Ingrese Horario de Salida");
		lblIngreseHorarioDe_1.setBounds(37, 425, 138, 31);
		contentPane.add(lblIngreseHorarioDe_1);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(12, 12, 20, 1));
		spinner_1.setBounds(206, 426, 146, 20);
		contentPane.add(spinner_1);
		
		
		conf.addActionListener(new ActionListener(){//accion para crear nuevo usuario
			public void actionPerformed(ActionEvent e) {
				String name=nombre.getText();
				String doc=dni.getText();
				String dom=domicilio.getText();
				String correo=mail.getText();
				String tele=tel.getText();
				String user=usu.getText();
				String contra= new String(con.getPassword());
				ArrayList<String> dias=(ArrayList<String>) list_1.getSelectedValuesList();
				Integer horario_ent= (Integer) spinner.getValue();
				Integer horario_sal=(Integer) spinner_1.getValue();
				rta=(SisTurnos.getController().generarMedico(dni.getText()));
				if(rta==0) {
				SisTurnos.getController().crearMedico(doc, name, dom, correo, tele, contra, user, dias,horario_ent,horario_sal);	
				JOptionPane.showMessageDialog(contentPane, "Datos Registrados Correctamente");
				
				}
				else if (rta==1) {
					JOptionPane.showMessageDialog(contentPane, "El Medico Ingresado Ya Existe");
				}
			}
		});
		
	}
}
