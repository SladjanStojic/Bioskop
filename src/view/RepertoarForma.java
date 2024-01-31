package view;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import kontroler.Kontroler;
import model.Bioskop;
import model.Film;
import model.Repertoar;
import model.RepertoarPrikaz;
import model.User;
import model.Zanr;

import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class RepertoarForma extends JFrame {

	private JPanel contentPane;
	private JComboBox cbBioskopi ;
	private JDateChooser dateChooser;
	private List<RepertoarPrikaz> repertoar=new ArrayList<>();
	int idPrikazivanja=0;
	private JLabel lblNaslov;
	private JPanel panel;
	private RepertoarTableModel rtm=new RepertoarTableModel();
	private JTable table_1;
	private JLabel lblPoster;
	private JTextArea taOpis;
	private JLabel lblZanr;
	private JLabel lblDuzina;
	private JButton btnNewButton;
	private Image image;
	private User user;

	public RepertoarForma(User u) {
		this.user=u;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 891, 533);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		
		 dateChooser = new JDateChooser();
			dateChooser.setBounds(320, 11, 104, 20);
			dateChooser.setDate(new Date());
			contentPane.add(dateChooser);

			setContentPane(contentPane);
			
			cbBioskopi = new JComboBox();
			
			
			cbBioskopi.setBounds(38, 9, 272, 22);
			contentPane.add(cbBioskopi);
			
			JScrollPane scrollPane = new JScrollPane();
			
			scrollPane.setBounds(38, 42, 386, 441);
			contentPane.add(scrollPane);
			//rtm
			table_1 = new JTable(rtm);
			table_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int red=table_1.getSelectedRow();
					idPrikazivanja=Integer.parseInt(table_1.getModel().getValueAt(red, 0).toString());
					prikaziDetaljeFilma();
				}
			});
			scrollPane.setViewportView(table_1);
			table_1.removeColumn(table_1.getColumnModel().getColumn(0));
			
			
			
			panel = new JPanel();
			panel.setBounds(447, 42, 418, 441);
			contentPane.add(panel);
			panel.setLayout(null);
			
			lblNaslov = new JLabel("");
			lblNaslov.setBounds(10, 5, 408, 31);
			lblNaslov.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(lblNaslov);
			
			lblPoster = new JLabel("");
			lblPoster.setHorizontalAlignment(SwingConstants.CENTER);
			lblPoster.setBounds(10, 38, 398, 174);
			panel.add(lblPoster);
			
			taOpis = new JTextArea();
			taOpis.setEditable(false);
			taOpis.setBounds(0, 215, 418, 128);
			panel.add(taOpis);
			
			lblZanr = new JLabel("");
			lblZanr.setBounds(10, 344, 328, 31);
			panel.add(lblZanr);
			
			lblDuzina = new JLabel("");
			lblDuzina.setBounds(10, 376, 88, 14);
			panel.add(lblDuzina);
			
			btnNewButton = new JButton("Rezervisi");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					RezervacijaForma rf=new RezervacijaForma(idPrikazivanja,user);
					rf.setVisible(true);
				
				}
			});
			btnNewButton.setBounds(329, 376, 89, 23);
			panel.add(btnNewButton);
			
			
			
			popuniCbBioskope();
			
			dateChooser.addPropertyChangeListener(new PropertyChangeListener() {
			 	public void propertyChange(PropertyChangeEvent evt) {
			 		ucitajRepertoarZaBioskopIDan();
			 		
			 	}
			 });
			cbBioskopi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ucitajRepertoarZaBioskopIDan();
				}
			});
		
			dateChooser.setMinSelectableDate(new Date());
	}
	

	protected void prikaziDetaljeFilma() {
		// TODO Auto-generated method stub
		Film f=Kontroler.getInstanca().vratFilmPoIdRepertoara(idPrikazivanja);
		lblNaslov.setText(f.getNaslov());
		taOpis.setText(f.getOpis());
		lblDuzina.setText(String.valueOf(f.getDuzinu())+" min");
		String zanr="";
		for(Zanr z:f.getListaZanrova()) {
			zanr=zanr+z.getZanr()+" ";
		}
		lblZanr.setText(zanr);
		image=null;
		URL url;
		try {
			url = new URL(f.getPoster());
			 image = ImageIO.read(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			image=null;
		}
		
		if(image!=null) {
			lblPoster.setIcon(new ImageIcon(image));
		}else {
			lblPoster.setIcon(new ImageIcon());
		}
		
	}

	private void ucitajRepertoarZaBioskopIDan() {
		// TODO Auto-generated method stub
		
		
			Bioskop b=(Bioskop)cbBioskopi.getSelectedItem();
			repertoar=Kontroler.getInstanca().vratiRepertoarZaBioskopIDatum(b.getId(),dateChooser.getDate());
			rtm.setData(repertoar);
			rtm.fireTableDataChanged();
		
	}
	private void popuniCbBioskope() {
		for(Bioskop b:Kontroler.getInstanca().vratiSveBioskope()) {
			cbBioskopi.addItem(b);
		}
		
	}

}
