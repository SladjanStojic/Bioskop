package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.User;
import registracija.IProxyR;
import registracija.ProxyReg;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegistracijaForma extends JFrame {

	private JPanel contentPane;
	private JTextField tfIme;
	private JTextField tfPrezime;
	private JTextField tfEmail;
	private JPasswordField pf;
	private JPasswordField pf1;

	public RegistracijaForma() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ime");
		lblNewLabel.setBounds(10, 42, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblPrezime = new JLabel("Prezime");
		lblPrezime.setBounds(10, 67, 46, 14);
		contentPane.add(lblPrezime);
		
		JLabel lblEmail = new JLabel("email");
		lblEmail.setBounds(10, 97, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblLozinka = new JLabel("Lozinka");
		lblLozinka.setBounds(10, 125, 46, 14);
		contentPane.add(lblLozinka);
		
		JLabel lblPonoviLozinku = new JLabel("Ponovi lozinku");
		lblPonoviLozinku.setBounds(10, 151, 72, 14);
		contentPane.add(lblPonoviLozinku);
		
		tfIme = new JTextField();
		tfIme.setBounds(107, 39, 183, 20);
		contentPane.add(tfIme);
		tfIme.setColumns(10);
		
		tfPrezime = new JTextField();
		tfPrezime.setColumns(10);
		tfPrezime.setBounds(107, 64, 183, 20);
		contentPane.add(tfPrezime);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(107, 94, 183, 20);
		contentPane.add(tfEmail);
		
		pf = new JPasswordField();
		pf.setBounds(107, 122, 183, 20);
		contentPane.add(pf);
		
		pf1 = new JPasswordField();
		pf1.setBounds(107, 148, 183, 20);
		contentPane.add(pf1);
		
		JButton btnNewButton = new JButton("Registruj me");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrujMe();
			}
		});
		btnNewButton.setBounds(170, 189, 120, 23);
		contentPane.add(btnNewButton);
	}
	
	private void registrujMe() {
		
		if(pf.getPassword().length>5 && String.valueOf(pf.getPassword()).equals(String.valueOf(pf1.getPassword()))) {
			User u=new User();
			u.setIme(tfIme.getText());
			u.setPrezime(tfPrezime.getText());
			u.setEmail(tfEmail.getText());
			u.setPass(String.valueOf(pf.getPassword()));
			
			IProxyR reg =new ProxyReg();
			reg.regUser(u);
			
			JOptionPane.showMessageDialog(null, "Uspesno ste se registrovali");
			dispose();
		}else {
			JOptionPane.showMessageDialog(null, "Passwordi se ne poklapaju ili su kraci od 5");
		}
		
	}

}
