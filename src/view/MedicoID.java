package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.PacienteController;
import controller.SisTurnos;
import model.TurnosPaciente;
import javax.swing.JButton;

public class MedicoID extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel content2;
	private JTextField textField;
	private ArrayList<TurnosPaciente>turnos;
	private JTextField textField_1;

public MedicoID() {
		JFrame frame=new JFrame();
		setTitle("Ingrese Nombre M\u00E9dico");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 481);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel menu=new JLabel("Ingrese Su Nombre:");
		menu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		menu.setBounds(25,23,230,40);
		contentPane.add(menu);
		
		textField_1 = new JTextField();
		textField_1.setBounds(107, 74, 181, 27);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(147, 113, 119, 23);
		contentPane.add(btnConfirmar);
		btnConfirmar.addActionListener(new ActionListener() {//accion para validar entrada de datos
			public void actionPerformed(ActionEvent e) {
				String nombreMed=textField_1.getText();
				ArrayList<String> rta=(SisTurnos.getController().turnosReservados(nombreMed));
				if (rta!=null) {
					JOptionPane.showMessageDialog(contentPane, "Datos ingresados");
					JFrame cuadro=new JFrame();
					cuadro.setTitle("Turnos Registrados");
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					cuadro.setBounds(100, 100, 450, 481);
					content2 = new JPanel();
					content2.setBorder(new EmptyBorder(5, 5, 5, 5));
					cuadro.setContentPane(content2);
					content2.setLayout(null);
					DefaultListModel<String> dlm = new DefaultListModel<String>();
					JList<String> list = new JList<String>(dlm);
					for(String tur : rta)
					{
					    dlm.addElement(tur);
					}
					JScrollPane listScrollPane = new JScrollPane(list);
					content2.add(listScrollPane);
					listScrollPane.setBounds(80,120,258,204);
					JLabel menu=new JLabel("Turnos Registrados Para:"+" "+nombreMed);
					menu.setFont(new Font("Tahoma", Font.PLAIN, 14));
					menu.setBounds(25,23,230,40);
					content2.add(menu);
					cuadro.setVisible(true);
					frame.dispose();
					
				}
				else {
					JOptionPane.showMessageDialog(contentPane, "Datos ingresados incorrectos");
				}
			}
			});
		
}
}

