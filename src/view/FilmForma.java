package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import com.toedter.calendar.JDateChooser;

import kontroler.Kontroler;
import model.Film;
import model.Zanr;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FilmForma extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfNaslov;
	private JTextField tfPoster;
	private JTable table;
	private List<Zanr> listaSvihZanrova=Kontroler.getInstanca().vratiSveZanrove();
	List<Zanr> odabraniZanr =new ArrayList<Zanr>();
	JComboBox cbZanrovi;
	DefaultTableModel dtm=new DefaultTableModel();
	JSpinner spinner ;
	JDateChooser premijera;
	JDateChooser kraj;
	JTextArea opis;
	private JTable table_1;
	JButton btnPrethodna;
	JButton btnSledeca;
	JLabel brojTrenutneStranice;
	JLabel brojStranica;
	 private int ukupanBrojFilmova = Kontroler.getInstanca().vratiUkupanBrojFilmova("");
	    private int brojFilmovaPoStranici=10;
	    private int ukupanBrojStranica = ukupanBrojFilmova / brojFilmovaPoStranici;
	    private int brojTrenutneStrane = 0;
	    private JTextField tfSearch;
	    private List<Film> listaFilmova=new ArrayList<>();
	    FilmTableModel ftm=new FilmTableModel();
	    private JLabel lblNewLabel_5;

	public FilmForma() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 675, 684);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Naslov");
		lblNewLabel.setBounds(10, 21, 46, 14);
		contentPane.add(lblNewLabel);
		
		tfNaslov = new JTextField();
		tfNaslov.setBounds(66, 18, 147, 20);
		contentPane.add(tfNaslov);
		tfNaslov.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Opis");
		lblNewLabel_1.setBounds(256, 21, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		 opis = new JTextArea();
		opis.setBounds(312, 16, 335, 308);
		contentPane.add(opis);
		
		JLabel lblNewLabel_2 = new JLabel("Poster");
		lblNewLabel_2.setBounds(10, 52, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		tfPoster = new JTextField();
		tfPoster.setText("");
		tfPoster.setBounds(66, 49, 147, 20);
		contentPane.add(tfPoster);
		tfPoster.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Trajanje min");
		lblNewLabel_3.setBounds(10, 77, 95, 14);
		contentPane.add(lblNewLabel_3);
		
		 spinner = new JSpinner();
		spinner.setBounds(145, 80, 68, 20);
		contentPane.add(spinner);
		
		JLabel lblNewLabel_4 = new JLabel("Datum Premijere");
		lblNewLabel_4.setBounds(10, 118, 95, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("Datum Poslednjeg prikayivanja");
		lblNewLabel_4_1.setBounds(10, 143, 100, 14);
		contentPane.add(lblNewLabel_4_1);
		
		 premijera = new JDateChooser();
		premijera.setBounds(129, 118, 84, 20);
		contentPane.add(premijera);
		
		 kraj = new JDateChooser();
		kraj.setBounds(128, 143, 85, 20);
		contentPane.add(kraj);
		
	cbZanrovi = new JComboBox();
		cbZanrovi.setBounds(10, 188, 203, 22);
		contentPane.add(cbZanrovi);
		
		JButton btnNewButton = new JButton("+");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dodajZanr();
			}
		});
		btnNewButton.setBounds(217, 188, 46, 23);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 233, 281, 93);
		contentPane.add(scrollPane);
		
		table = new JTable(dtm);
		scrollPane.setViewportView(table);
		Object[]kolone = {"ID","Naziv"};
		dtm.addColumn(kolone[0]);
		dtm.addColumn(kolone[1]);
		//ne prikazujemo id
		table.removeColumn(table.getColumnModel().getColumn(0));
		
		JButton btnNewButton_1 = new JButton("Sacuvaj");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sacuvajNoviFilm();
			}
		});
		btnNewButton_1.setBounds(558, 335, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 421, 640, 186);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable(ftm);
		scrollPane_1.setViewportView(table_1);
		//ne prikazujemo id
		table_1.removeColumn(table_1.getColumnModel().getColumn(0));
		 btnPrethodna = new JButton("<");
		btnPrethodna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(brojTrenutneStrane>0) {
					brojTrenutneStrane=brojTrenutneStrane-1;
					brojTrenutneStranice.setText(String.valueOf(brojTrenutneStrane+1));
					ucitajPodatke();
					
					}
			}
		});
		btnPrethodna.setBounds(145, 618, 68, 23);
		contentPane.add(btnPrethodna);
		
		 btnSledeca = new JButton(">");
		btnSledeca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(ukupanBrojStranica>brojTrenutneStrane) {
					brojTrenutneStrane=brojTrenutneStrane+1;
					brojTrenutneStranice.setText(String.valueOf(brojTrenutneStrane+1));
					ucitajPodatke();
					
					}
			}
		});
		btnSledeca.setBounds(400, 618, 68, 23);
		contentPane.add(btnSledeca);
		
		 brojTrenutneStranice = new JLabel("1");
		brojTrenutneStranice.setBounds(261, 622, 30, 14);
		contentPane.add(brojTrenutneStranice);
		
		 brojStranica = new JLabel("10");
		brojStranica.setBounds(326, 622, 30, 14);
		contentPane.add(brojStranica);
		
		JLabel lblNewLabel_7 = new JLabel("/");
		lblNewLabel_7.setBounds(296, 618, 20, 20);
		contentPane.add(lblNewLabel_7);
		
		tfSearch = new JTextField();
		tfSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				postaviPaginacijuZaPretragu();
			}
		});
		tfSearch.setToolTipText("Pretraga");
		tfSearch.setBounds(66, 389, 281, 20);
		contentPane.add(tfSearch);
		tfSearch.setColumns(10);
		
		lblNewLabel_5 = new JLabel("Pretraga");
		lblNewLabel_5.setBounds(10, 392, 68, 14);
		contentPane.add(lblNewLabel_5);
		popuniPodatke();
		postaviPaginaciju();
		ucitajPodatke();
	}

	protected void sacuvajNoviFilm() {
		// TODO Auto-generated method stub
		Film f=new Film();
		f.setNaslov(tfNaslov.getText());
		f.setDuzinu((Integer)spinner.getValue());
		f.setKraj(kraj.getDate());
		f.setPoster(tfPoster.getText());
		f.setPremijera(premijera.getDate());
		f.setOpis(opis.getText());
		f.setListaZanrova(odabraniZanr);
		
		int id= Kontroler.getInstanca().sacuvajNoviFilm(f);
		if(id>0) {
			f.setId(id);
			ftm.dodajFilm(f);
			dtm.fireTableDataChanged();
		}else {
			JOptionPane.showMessageDialog(null, "Doslo je do greske");
		}
		
	}

	private void popuniPodatke() {
		// TODO Auto-generated method stub
		for(Zanr z:listaSvihZanrova) {
			 cbZanrovi.addItem(z);
		}
	}

	protected void dodajZanr() {
		// TODO Auto-generated method stub
		Zanr z= ((Zanr)cbZanrovi.getSelectedItem());
				
		odabraniZanr.add(z);
		cbZanrovi.removeItem(z);
		dtm.setRowCount(0);
		for(Zanr k:odabraniZanr) {
			Object[]red= {k.getId(),k.getZanr()};
			dtm.addRow(red);
		}
	}
	
	private void postaviPaginaciju() {
		
		brojStranica.setText(String.valueOf(ukupanBrojStranica+1));
		brojTrenutneStranice.setText(String.valueOf(brojTrenutneStrane+1));
	}

	private void ucitajPodatke() {
		String pretraga=tfSearch.getText();
		listaFilmova=Kontroler.getInstanca().ucitajSveFilmovePaginacija(brojFilmovaPoStranici,brojTrenutneStrane*brojFilmovaPoStranici,pretraga);
		ftm.setData(listaFilmova);
		ftm.fireTableDataChanged();
		if(ukupanBrojStranica<=brojTrenutneStrane) {
			btnSledeca.setVisible(false);
		}else {
			btnSledeca.setVisible(true);
		}
		if(brojTrenutneStrane<=0) {
			btnPrethodna.setVisible(false);
		}else {
			btnPrethodna.setVisible(true);
		}
	}
	
	protected void postaviPaginacijuZaPretragu() {
		// TODO Auto-generated method stub
		String pretraga=tfSearch.getText();
		ukupanBrojFilmova = Kontroler.getInstanca().vratiUkupanBrojFilmova(pretraga);
		brojFilmovaPoStranici=10;
	    ukupanBrojStranica = ukupanBrojFilmova / brojFilmovaPoStranici;
	   brojTrenutneStrane = 0;
	   postaviPaginaciju();
	   ucitajPodatke();
	}
}
