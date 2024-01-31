package view;


import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;

import logovanje.IProxy;
import logovanje.ProxyLogin;
import model.User;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JPasswordField;
import javax.swing.Icon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Logovanje extends JFrame {

	private JPanel contentPane;
	private JTextField tfUser;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 * @throws UnsupportedLookAndFeelException 
	 */
	public static void main(String[] args) throws UnsupportedLookAndFeelException {
		//UIManager.setLookAndFeel(new FlatArcDarkIJTheme());
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Logovanje frame = new Logovanje();
					frame.setVisible(true);
					frame.setTitle("Projekat Bioskop");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Logovanje() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfUser = new JTextField();
		tfUser.setBounds(95, 81, 230, 20);
		contentPane.add(tfUser);
		tfUser.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(95, 133, 230, 20);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("Uloguj me");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ulogujMe();
			}
		});
		btnNewButton.setBounds(176, 164, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Nemate nalog - Registracija");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistracijaForma rf=new RegistracijaForma();
				rf.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(249, 227, 175, 23);
		contentPane.add(btnNewButton_1);
	}
	
	private void ulogujMe() {
		String user=tfUser.getText();
	    String pass=String.valueOf(passwordField.getPassword());
	    
	    User u= new User();
	    u.setEmail(user);
	    u.setPass(pass);
	    IProxy ip=new ProxyLogin();
	    ip.login(u);
	}

}
