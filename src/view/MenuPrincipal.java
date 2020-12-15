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


public class MenuPrincipal extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

public MenuPrincipal() {
		JFrame frame=new JFrame();
		setTitle("Menu Principal");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel menu=new JLabel("MENU PRINCIPAL");
		menu.setBounds(106,16,100,40);
		contentPane.add(menu);
		JButton paciente=new JButton("INGRESAR COMO PACIENTE");
		paciente.setBounds(106,90,200,40);
		contentPane.add(paciente);
		LoginPaciente pac= new LoginPaciente();
		paciente.addActionListener(new ActionListener(){//accion para cliente paciente
			public void actionPerformed(ActionEvent e) {
				pac.setVisible(true);
			}
		});
		
		JButton prof=new JButton("INGRESAR COMO PROFESIONAL");
		prof.setBounds(106,135,220,40);
		contentPane.add(prof);
		LoginMed valid=new LoginMed();
		prof.addActionListener(new ActionListener(){//accion para cliente paciente
			public void actionPerformed(ActionEvent e) {
				valid.setVisible(true);
			}
		});
	}
	
}
