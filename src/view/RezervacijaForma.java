package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kontroler.Kontroler;
import model.Karta;
import model.RezervacijaModel;
import model.User;
import net.miginfocom.layout.UnitValue;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class RezervacijaForma extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int idPrikazivanja;
	private User user;
	private RezervacijaModel rm;
	JLabel lblBioskop;
	JLabel lblSala;
	JLabel lblVreme ;
	JLabel lblDatum;
	JLabel lblNaslov;
	private List<Karta>korpa;
	private JLabel lblNewLabel;
	private JLabel lblCena;
	private JLabel lblNewLabel_1;
	private JLabel lblUkupno;
	
	public RezervacijaForma(int idPrikazivanja, User user) {
		this.idPrikazivanja=idPrikazivanja;
		this.user=user;
		this.rm=Kontroler.getInstanca().vratiRezervacijaModel(idPrikazivanja);
		this.korpa=new ArrayList<Karta>();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 595, 558);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 lblBioskop = new JLabel("New label");
		lblBioskop.setBounds(10, 11, 136, 14);
		contentPane.add(lblBioskop);
		
		 lblSala = new JLabel("New label");
		lblSala.setBounds(154, 11, 136, 14);
		contentPane.add(lblSala);
		
		 lblVreme = new JLabel("New label");
		lblVreme.setBounds(338, 11, 84, 14);
		contentPane.add(lblVreme);
		
		 lblDatum = new JLabel("New label");
		lblDatum.setBounds(433, 11, 136, 14);
		contentPane.add(lblDatum);
		
		 lblNaslov = new JLabel("New label");
		lblNaslov.setHorizontalAlignment(SwingConstants.CENTER);
		lblNaslov.setBounds(10, 36, 559, 14);
		contentPane.add(lblNaslov);
		
		lblNewLabel = new JLabel("Cena");
		lblNewLabel.setBounds(10, 460, 46, 14);
		contentPane.add(lblNewLabel);
		
		lblCena = new JLabel("");
		lblCena.setBounds(66, 460, 46, 14);
		contentPane.add(lblCena);
		
		lblNewLabel_1 = new JLabel("Ukupno");
		lblNewLabel_1.setBounds(10, 485, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		lblUkupno = new JLabel("");
		lblUkupno.setBounds(66, 485, 46, 14);
		contentPane.add(lblUkupno);
		
		JButton btnNewButton = new JButton("Kupi");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Kontroler.getInstanca().kupiKarte(korpa);
				JOptionPane.showMessageDialog(null, "Uspesno ste kupili karte");
				dispose();
			}
		});
		btnNewButton.setBounds(480, 481, 89, 23);
		contentPane.add(btnNewButton);
		
		for(int i=0;i<rm.getRedova();i++) {
			for(int j=0;j<rm.getKolona();j++) {
				
			//	for(Karta k:rm.getLista()) {
			//		if(k.getRed()==i && k.getSediste()==j) {
			//			
			//		}
			//	}
				if(rm.getRaspored()[i][j]==0) {
					JToggleButton tglbtnNewToggleButton = new JToggleButton((i+1)+"-"+(j+1));
					tglbtnNewToggleButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(tglbtnNewToggleButton.isSelected()) {
								dodajUKorpu(tglbtnNewToggleButton.getText());
							}else {
								ukloniIzKorpe(tglbtnNewToggleButton.getText());
							}
						}
					});
					tglbtnNewToggleButton.setBounds(29+(j*60), 111+(i*60), 55, 23);
					contentPane.add(tglbtnNewToggleButton);
				}else {
					JToggleButton tglbtnNewToggleButton = new JToggleButton("x");
					tglbtnNewToggleButton.setBounds(29+(j*60), 111+(i*60), 55, 23);
					tglbtnNewToggleButton.setEnabled(false);;
					contentPane.add(tglbtnNewToggleButton);
				}
				
				
				
			}
			
		}
		
		
		
		
		
		
		
		
		
		
		postaviPodatke();
		
		
	}

	protected void ukloniIzKorpe(String text) {
		// TODO Auto-generated method stub
		Karta k=kreirajKartu(text);
		for(Karta temp:korpa) {
			if(temp.getRed()==k.getRed() && temp.getSediste()==k.getSediste()) {
				korpa.remove(temp);
				break;
			}
		}
		osveziPrikazCene();
	}

	protected void dodajUKorpu(String text) {
		// TODO Auto-generated method stub
		Karta k=kreirajKartu(text);
		korpa.add(k);
		osveziPrikazCene();
	}

	private void osveziPrikazCene() {
		// TODO Auto-generated method stub
		lblUkupno.setText(String.valueOf(korpa.size()*rm.getCena()));
	}

	private Karta kreirajKartu(String text) {
		// TODO Auto-generated method stub
		String[]delovi=text.split("-");
		String red=delovi[0];
		String kolona=delovi[1];
		
		Karta nova=new Karta();
		nova.setIdKorisnik(user.getId());
		nova.setIdRepertoar(idPrikazivanja);
		nova.setRed(Integer.valueOf(red));
		nova.setSediste(Integer.valueOf(kolona));
		
		return nova;
	}

	private void postaviPodatke() {
		// TODO Auto-generated method stub
		lblBioskop.setText(rm.getBioskop());
		lblSala.setText(rm.getSala());
		lblDatum.setText(rm.getDatum().toString());
		lblVreme.setText(rm.getVreme().toString());
		lblNaslov.setText(rm.getNaslov());
		lblCena.setText(String.valueOf(rm.getCena()));
	}
}
