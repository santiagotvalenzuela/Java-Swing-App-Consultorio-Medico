package view;
import view.LoginMed;
import view.LoginPaciente;
import java.util.Scanner;
import java.util.Vector;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class RegistrarTurno extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

public RegistrarTurno() {
		JFrame frame=new JFrame();
		setTitle("Menu Paciente");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel menu=new JLabel("MENU DE PACIENTE");
		menu.setBounds(70,28,125,40);
		contentPane.add(menu);
		JButton paciente=new JButton("Registrar Turno");
		paciente.setBounds(106,90,200,40);
		contentPane.add(paciente);
		
		JButton btnEliminarTurnoReservado = new JButton("Eliminar Turno Reservado");
		btnEliminarTurnoReservado.setBounds(106, 142, 200, 40);
		contentPane.add(btnEliminarTurnoReservado);
		CrearTurno pac=new CrearTurno();
		paciente.addActionListener(new ActionListener(){//accion para cliente paciente
			public void actionPerformed(ActionEvent e) {
				pac.setVisible(true);
			}
		});
		EliminarTurno delete=new EliminarTurno();
		btnEliminarTurnoReservado.addActionListener(new ActionListener(){//accion para cliente paciente
			public void actionPerformed(ActionEvent e) {
				delete.setVisible(true);
			}
		});
	}
}