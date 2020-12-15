package view;
import view.LoginMed;
import view.LoginPaciente;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.util.Vector;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import controller.SisTurnos;
import controller.TurnosController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;

	public class CrearTurno extends JFrame{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JPanel contentPane;
		private JTextField txtEscribaProfesional;
		private JTextField textField;
		private JTextField nombrePaciente;
	public CrearTurno() {
			JFrame frame=new JFrame();
			setTitle("Creacion de Turno");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 450, 481);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			JLabel menu=new JLabel("CREAR NUEVO TURNO");
			menu.setBounds(25,11,145,40);
			contentPane.add(menu);
			ArrayList<String>dias=new ArrayList<String>();
			ArrayList<String>fechas=new ArrayList<String>();
			LocalDate hoy=LocalDate.now();
			YearMonth mes=YearMonth.from(hoy);
			LocalDate local= mes.atDay(hoy.getDayOfMonth());
			while(YearMonth.from(local).equals(mes)) {
				String fecha=local.getDayOfWeek().getDisplayName(TextStyle.FULL,new Locale("es","ES"));
				dias.add(local.toString());
				fechas.add(fecha);
				local=local.plusDays(1);
			}
			Object []dates=dias.toArray();
			
			JComboBox comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(dates));
			comboBox.setToolTipText("Fecha");
			comboBox.setBounds(258, 82, 109, 20);
			contentPane.add(comboBox);
			
			JLabel lblElegirMes = new JLabel("Elegir Fecha");
			lblElegirMes.setBounds(25, 80, 109, 24);
			contentPane.add(lblElegirMes);
			
			JLabel lblElegirHorario = new JLabel("Elegir Horario");
			lblElegirHorario.setBounds(25, 120, 97, 20);
			contentPane.add(lblElegirHorario);
			
			JComboBox comboBox_2 = new JComboBox();
			comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00"}));
			comboBox_2.setBounds(258, 120, 109, 20);
			contentPane.add(comboBox_2);
			
			JButton btnConfirmarTurno = new JButton("Chequear Disponibilidad");
			btnConfirmarTurno.setBounds(124, 245, 176, 72);
			contentPane.add(btnConfirmarTurno);
			
			JLabel lblElijaMdicoCon = new JLabel(" Elija M\u00E9dico Con El Que Desea Atenderse:");
			lblElijaMdicoCon.setBounds(103, 183, 283, 20);
			contentPane.add(lblElijaMdicoCon);
			
			txtEscribaProfesional = new JTextField();
			txtEscribaProfesional.setForeground(Color.GRAY);
			txtEscribaProfesional.setBounds(142, 214, 138, 20);
			contentPane.add(txtEscribaProfesional);
			txtEscribaProfesional.setColumns(10);
			
			JLabel lblEscribaSuNombre = new JLabel("Escriba su Nombre");
			lblEscribaSuNombre.setBounds(25, 49, 115, 20);
			contentPane.add(lblEscribaSuNombre);
			
			nombrePaciente = new JTextField();
			nombrePaciente.setForeground(Color.GRAY);
			nombrePaciente.setColumns(10);
			nombrePaciente.setBounds(248, 51, 138, 20);
			contentPane.add(nombrePaciente);
			
			
			
			btnConfirmarTurno.addActionListener(new ActionListener() {//accion para validar entrada de datos
				public void actionPerformed(ActionEvent e) {
					String fechaIng=(String) comboBox.getSelectedItem();
					String horaIng=(String) comboBox_2.getSelectedItem();
					String medicoID=txtEscribaProfesional.getText();
					String pacienteId=nombrePaciente.getText();
					int rta=(TurnosController.getController().generarTurno(medicoID, horaIng, fechaIng,pacienteId));
					if (rta==0) {
						JOptionPane.showMessageDialog(contentPane, "Turno Reservado");
					}
					else {
						JOptionPane.showMessageDialog(contentPane, "Turno Seleccionado No Disponible");
					}
				}
				});
			
		}
	}

