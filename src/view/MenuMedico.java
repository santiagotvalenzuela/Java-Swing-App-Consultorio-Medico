package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.SisTurnos;
import model.TurnosPaciente;

import java.awt.Font;

public class MenuMedico extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private ArrayList<TurnosPaciente>turnos;

public MenuMedico() {
		JFrame frame=new JFrame();
		setTitle("Turnos Reservados");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 481);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel menu=new JLabel("Turnos Reservados");
		menu.setFont(new Font("Tahoma", Font.PLAIN, 17));
		menu.setBounds(25,11,145,40);
		contentPane.add(menu);
		//turnos=SisTurnos.getController().turnosReservados();
		DefaultListModel<TurnosPaciente> dlm = new DefaultListModel<TurnosPaciente>();
		JList<TurnosPaciente> list = new JList<TurnosPaciente>(dlm);
		JScrollPane scrollPane = new JScrollPane(list);

		for(TurnosPaciente tur : turnos)
		{
		    dlm.addElement(tur);
		}
}
}

