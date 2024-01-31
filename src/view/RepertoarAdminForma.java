package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import kontroler.Kontroler;
import model.Bioskop;
import model.Film;
import model.Repertoar;
import model.RepertoarAdmin;
import model.Sala;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Time;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class RepertoarAdminForma extends JFrame {

	private JPanel contentPane;
	JDateChooser prikazivanje;
	JComboBox cbBioskop;
	JComboBox cbSala;
	JComboBox cbFilm;
	JSpinner sCena;
	JSpinner sSat;
	JSpinner sMin;
	private JScrollPane scrollPane;
	private JTable table;
	RepertoarAdminTableModel ratm=new RepertoarAdminTableModel();

	public RepertoarAdminForma() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 473, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		prikazivanje = new JDateChooser();
		prikazivanje.setBounds(10, 59, 101, 20);
		//setujemo danasnji dan
		prikazivanje.setDate(new Date());
		contentPane.add(prikazivanje);
		
		
		 cbBioskop = new JComboBox();
		 cbBioskop.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		postaviSale();
		 		postaviRepertoar();
		 	}
		 });
		cbBioskop.setBounds(10, 11, 214, 22);
		contentPane.add(cbBioskop);
		
		cbSala = new JComboBox();
		cbSala.setBounds(234, 11, 214, 22);
		contentPane.add(cbSala);
		
		cbFilm = new JComboBox();
		cbFilm.setBounds(144, 59, 304, 22);
		contentPane.add(cbFilm);
		
		JLabel lblNewLabel = new JLabel("Cena");
		lblNewLabel.setBounds(10, 100, 46, 14);
		contentPane.add(lblNewLabel);
		
		sCena = new JSpinner();
		sCena.setBounds(66, 97, 76, 20);
		contentPane.add(sCena);
		
		JLabel lblNewLabel_1 = new JLabel("Vreme prikazivanja");
		lblNewLabel_1.setBounds(169, 100, 113, 14);
		contentPane.add(lblNewLabel_1);
		
		//model definise pocetnu min max i korak vrednost
		SpinnerNumberModel sat=new SpinnerNumberModel(12, 0, 23, 1);
		 sSat = new JSpinner(sat);
		sSat.setBounds(280, 97, 46, 20);
		contentPane.add(sSat);
		SpinnerNumberModel min=new SpinnerNumberModel(0, 0, 59, 1);
		 sMin = new JSpinner(min);
		sMin.setBounds(346, 97, 46, 20);
		contentPane.add(sMin);
		
		JLabel lblNewLabel_2 = new JLabel(":");
		lblNewLabel_2.setBounds(336, 100, 19, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Sacuvaj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sacuvajNovoNaRepertoaru();
			}
		});
		btnNewButton.setBounds(359, 134, 89, 23);
		contentPane.add(btnNewButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 167, 437, 283);
		contentPane.add(scrollPane);
		//ratm
		table = new JTable(ratm);
		scrollPane.setViewportView(table);
		table.removeColumn(table.getColumnModel().getColumn(0));
		
		
		prikazivanje.addPropertyChangeListener(new PropertyChangeListener() {
		 	public void propertyChange(PropertyChangeEvent evt) {
				ucitajDostupneFilmoveZaDatum();
				postaviRepertoar();
		 	}
		 });
		postaviPodatke();
		prikazivanje.setMinSelectableDate(new Date());
	}

	private void postaviRepertoar() {
		Bioskop b= (Bioskop)cbBioskop.getSelectedItem();
		ratm.setData(Kontroler.getInstanca().vratiRepertoarAdminZaDanIBioskop(b.getId(),prikazivanje.getDate()));
		ratm.fireTableDataChanged();
	}

	private void ucitajDostupneFilmoveZaDatum() {
		// TODO Auto-generated method stub
		cbFilm.removeAllItems();
		for(Film f:Kontroler.getInstanca().vratiSveDostupneFilmoveZaDatum(prikazivanje.getDate())) {
			cbFilm.addItem(f);
		}
	}

	private void postaviSale() {
		// TODO Auto-generated method stub
		cbSala.removeAllItems();
		for(Sala b:Kontroler.getInstanca().vratiAktivneSaleZaBioskop(((Bioskop)cbBioskop.getSelectedItem()).getId())) {
			cbSala.addItem(b);
		}
		
	}

	private void postaviPodatke() {
		// TODO Auto-generated method stub
		for(Bioskop b:Kontroler.getInstanca().vratiSveBioskope()) {
			cbBioskop.addItem(b);
		}
	}

	private void sacuvajNovoNaRepertoaru() {
		// TODO Auto-generated method stub
		Repertoar r=new Repertoar();
		r.setDatum(prikazivanje.getDate());
		r.setCena((Integer)sCena.getValue());
		Film f=((Film)cbFilm.getSelectedItem());
		r.setIdFilma(f.getId());
		r.setIdSale(((Sala)cbSala.getSelectedItem()).getId());
		Time vreme=new Time((Integer)sSat.getValue(), (Integer)sMin.getValue(),0); 
		r.setVreme(vreme);
		
		int id=Kontroler.getInstanca().upisiNovoPrikazivanje(r);
		if(id>0) {
			RepertoarAdmin ra=new RepertoarAdmin();
			ra.setCena(r.getCena());
			ra.setDuzina(f.getDuzinu());
			ra.setId(id);
			ra.setNaziv(f.getNaslov());
			ra.setVreme(r.getVreme());
			ratm.dodajFilm(ra);
			ratm.fireTableDataChanged();
		}
		System.out.println("save");
	}

}
