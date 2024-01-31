package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kontroler.Kontroler;
import model.Bioskop;
import model.Sala;

import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class BioskopForma extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfIme;
	private JTextField tfGrad;
	private JTextField tfUlica;
	private JTextField tfFon;
	private JTextField tfEmail;
	private JTextField tfNazivSale;
	JPanel sala;
	JPanel bioskop;
	JLayeredPane layeredPane;
	JSpinner spinner;
	JSpinner spinner_1;
	private JTable table;
	JComboBox cbBioskopi;
	JPanel pregled;
	private SaleTableModel stm=new SaleTableModel();
	List<Sala>listaSala =new ArrayList<Sala>();
	
	public BioskopForma() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 582, 392);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 11, 546, 331);
		contentPane.add(layeredPane);
		          
		          pregled = new JPanel();
		          pregled.setLayout(null);
		          pregled.setBounds(0, 0, 546, 331);
		          layeredPane.add(pregled);
		          
		           cbBioskopi = new JComboBox();
		           cbBioskopi.addActionListener(new ActionListener() {
		           	public void actionPerformed(ActionEvent e) {
		           		popuniTabelu();
		           	}
		           });
		          cbBioskopi.setBounds(35, 24, 281, 22);
		          pregled.add(cbBioskopi);
		          
		          JButton btnNewButton_2 = new JButton("+");
		          btnNewButton_2.addActionListener(new ActionListener() {
		          	public void actionPerformed(ActionEvent e) {
		          		promeniPanel(bioskop);
		          	}
		          });
		          btnNewButton_2.setBounds(335, 24, 41, 23);
		          pregled.add(btnNewButton_2);
		          
		          JButton btnNewButton_3 = new JButton("+ Sala");
		          btnNewButton_3.addActionListener(new ActionListener() {
		          	public void actionPerformed(ActionEvent e) {
		          		promeniPanel(sala);
		          	}
		          });
		          btnNewButton_3.setBounds(401, 24, 89, 23);
		          pregled.add(btnNewButton_3);
		          
		          JScrollPane scrollPane = new JScrollPane();
		          scrollPane.setBounds(35, 103, 467, 217);
		          pregled.add(scrollPane);
		          
		          table = new JTable(stm);
		          scrollPane.setViewportView(table);
		        //ne prikazujemo id
		          table.removeColumn(table.getColumnModel().getColumn(0));
		         
		          bioskop = new JPanel();
		          bioskop.setBounds(0, 0, 536, 320);
		          layeredPane.add(bioskop);
		          bioskop.setLayout(null);
		          
		          JLabel lblNewLabel = new JLabel("Ime");
		          lblNewLabel.setBounds(28, 67, 103, 14);
		          bioskop.add(lblNewLabel);
		          
		          JLabel lblGrad = new JLabel("Grad");
		          lblGrad.setBounds(28, 92, 103, 14);
		          bioskop.add(lblGrad);
		          
		          JLabel lblUlicaIBr = new JLabel("Ulica i br");
		          lblUlicaIBr.setBounds(28, 117, 103, 14);
		          bioskop.add(lblUlicaIBr);
		          
		          JLabel lblTelefon = new JLabel("Telefon");
		          lblTelefon.setBounds(28, 142, 103, 14);
		          bioskop.add(lblTelefon);
		          
		          JLabel lblEmail = new JLabel("Email");
		          lblEmail.setBounds(28, 167, 103, 14);
		          bioskop.add(lblEmail);
		          
		          tfIme = new JTextField();
		          tfIme.setBounds(110, 64, 178, 20);
		          bioskop.add(tfIme);
		          tfIme.setColumns(10);
		          
		          tfGrad = new JTextField();
		          tfGrad.setColumns(10);
		          tfGrad.setBounds(110, 89, 178, 20);
		          bioskop.add(tfGrad);
		          
		          tfUlica = new JTextField();
		          tfUlica.setColumns(10);
		          tfUlica.setBounds(110, 114, 178, 20);
		          bioskop.add(tfUlica);
		          
		          tfFon = new JTextField();
		          tfFon.setColumns(10);
		          tfFon.setBounds(110, 139, 178, 20);
		          bioskop.add(tfFon);
		          
		          tfEmail = new JTextField();
		          tfEmail.setColumns(10);
		          tfEmail.setBounds(110, 164, 178, 20);
		          bioskop.add(tfEmail);
		          
		          JButton btnNewButton = new JButton("Sacuvaj");
		          btnNewButton.addActionListener(new ActionListener() {
		          	public void actionPerformed(ActionEvent e) {
		          		sacuvajNoviBioskop();
		          	}
		          });
		          btnNewButton.setBounds(198, 195, 89, 23);
		          bioskop.add(btnNewButton);
		          
		          JButton btnNewButton_4_1 = new JButton("x");
		          btnNewButton_4_1.addActionListener(new ActionListener() {
		          	public void actionPerformed(ActionEvent e) {
		          		promeniPanel(pregled);
		          	}
		          });
		          btnNewButton_4_1.setBounds(480, 11, 56, 23);
		          bioskop.add(btnNewButton_4_1);
		       
		        sala = new JPanel();
		        sala.setBounds(0, 0, 536, 320);
		        layeredPane.add(sala);
		        sala.setLayout(null);
		        
		        JLabel lblNewLabel_1 = new JLabel("Naziv");
		        lblNewLabel_1.setBounds(61, 84, 76, 14);
		        sala.add(lblNewLabel_1);
		        
		        JLabel lblNewLabel_2 = new JLabel("Kolona");
		        lblNewLabel_2.setBounds(61, 120, 46, 14);
		        sala.add(lblNewLabel_2);
		        
		        JLabel lblNewLabel_3 = new JLabel("Redova");
		        lblNewLabel_3.setBounds(61, 157, 46, 14);
		        sala.add(lblNewLabel_3);
		        
		        tfNazivSale = new JTextField();
		        tfNazivSale.setBounds(132, 84, 157, 20);
		        sala.add(tfNazivSale);
		        tfNazivSale.setColumns(10);
		        
		         spinner = new JSpinner();
		         spinner.setBounds(132, 117, 30, 20);
		         sala.add(spinner);
		         
		         spinner_1 = new JSpinner();
		         spinner_1.setBounds(132, 154, 30, 20);
		         sala.add(spinner_1);
		         
		         JButton btnNewButton_1 = new JButton("Sacuvaj");
		         btnNewButton_1.addActionListener(new ActionListener() {
		         	public void actionPerformed(ActionEvent e) {
		         		sacuvajNovuSalu();
		         	}
		         });
		         btnNewButton_1.setBounds(200, 200, 89, 23);
		         sala.add(btnNewButton_1);
		         
		         JButton btnNewButton_4 = new JButton("x");
		         btnNewButton_4.addActionListener(new ActionListener() {
		         	public void actionPerformed(ActionEvent e) {
		         		promeniPanel(pregled);
		         	}
		         });
		         btnNewButton_4.setBounds(480, 11, 56, 23);
		         sala.add(btnNewButton_4);
		     
		       popuniPodatke();
		       popuniTabelu();
		       promeniPanel(pregled);
		       
		      
	}

	protected void popuniTabelu() {
		// TODO Auto-generated method stub
		if(cbBioskopi.getSelectedIndex()>-1) {
		listaSala=Kontroler.getInstanca().vratiSaleZaBioskop(((Bioskop)cbBioskopi.getSelectedItem()).getId());
		}
		stm.setData(listaSala);
		stm.fireTableDataChanged();

	}

	private void popuniPodatke() {
		// TODO Auto-generated method stub
		for(Bioskop b:Kontroler.getInstanca().vratiSveBioskope()) {
			cbBioskopi.addItem(b);
		}
	}

	protected void promeniPanel(JPanel panel) {
		// TODO Auto-generated method stub
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}

	protected void sacuvajNovuSalu() {
		// TODO Auto-generated method stub
		Sala s=new Sala();
		s.setNaziv(tfNazivSale.getText());
		s.setKolona((Integer)spinner.getValue());
		s.setRedova((Integer)spinner_1.getValue());
		s.setIdBioskopa(((Bioskop)cbBioskopi.getSelectedItem()).getId());
		
		int id=Kontroler.getInstanca().upisiNovuSalu(s);
		
		if(id>0) {
			s.setId(id);
			stm.dodajSalu(s);
			stm.fireTableDataChanged();
			promeniPanel(pregled);
		}
		
		
	}

	protected void sacuvajNoviBioskop() {
		// TODO Auto-generated method stub
		Bioskop b=new Bioskop();
		b.setNaziv(tfIme.getText());
		b.setGrad(tfGrad.getText());
		b.setUlicu(tfUlica.getText());
		b.setEmail(tfEmail.getText());
		b.setTel(tfFon.getText());
		
		int id=Kontroler.getInstanca().upisiNoviBioskop(b);
		
		if(id>0) {
			b.setId(id);
			cbBioskopi.addItem(b);
			tfIme.setText("");
			tfGrad.setText("");
			tfUlica.setText("");
			tfEmail.setText("");
			tfFon.setText("");
			promeniPanel(pregled);
		}
		
	}
}
