package view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.RepertoarAdmin;
import model.RepertoarPrikaz;

public class RepertoarTableModel extends AbstractTableModel{

	private List<RepertoarPrikaz> lista;
	private String[] colNames= {"ID","Verme","Naziv","Cena","Slobodno"};

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		RepertoarPrikaz s=lista.get(rowIndex);
		switch (columnIndex) {
		case 0:return s.getId();
		case 1:return s.getVreme();
		case 2:return s.getNaziv();
		case 3:return s.getCena();
		case 4:return s.getSlobodno();
		}
		return null;
	}
	
	public void setData(List<RepertoarPrikaz> lista) {
		this.lista=lista;
	}

	public void dodajFilm(RepertoarPrikaz s) {
		// TODO Auto-generated method stub
		lista.add(s);
	}
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return colNames[column];
	}

}
