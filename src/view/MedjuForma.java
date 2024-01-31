package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logovanje.IProxy;
import model.User;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MedjuForma extends JFrame implements IProxy{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private User user;

	public MedjuForma() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 339);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBioskop = new JButton("Bioskop-Sala");
		btnBioskop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BioskopForma bf=new BioskopForma();
				bf.setVisible(true);
				
			}
		});
		btnBioskop.setBounds(10, 70, 143, 23);
		contentPane.add(btnBioskop);
		
		JButton btnNewButton = new JButton("Novi Film");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FilmForma ff=new FilmForma();
				ff.setVisible(true);
			}
		});
		btnNewButton.setBounds(157, 70, 149, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Repertoar Admin");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RepertoarAdminForma rf=new RepertoarAdminForma();
				rf.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(311, 70, 143, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Repertoar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RepertoarForma rf=new RepertoarForma(user);
				rf.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(76, 142, 161, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("Ulaznice");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2_1.setBounds(247, 142, 161, 23);
		contentPane.add(btnNewButton_2_1);
	}

	@Override
	public void login(User u) {
		// TODO Auto-generated method stub
		this.user=u;
		setVisible(true);
	}

}
