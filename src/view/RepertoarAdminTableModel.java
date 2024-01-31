package view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Film;
import model.RepertoarAdmin;

public class RepertoarAdminTableModel extends AbstractTableModel{
	
	private List<RepertoarAdmin> lista;
	private String[] colNames= {"ID","Vreme","Naslov","Duzina","Cena"};

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
		RepertoarAdmin s=lista.get(rowIndex);
		switch (columnIndex) {
		case 0:return s.getId();
		case 1:return s.getVreme();
		case 2:return s.getNaziv();
		case 3:return s.getDuzina();
		case 4:return s.getCena();
		}
		return null;
	}
	
	public void setData(List<RepertoarAdmin> lista) {
		this.lista=lista;
	}

	public void dodajFilm(RepertoarAdmin s) {
		// TODO Auto-generated method stub
		lista.add(s);
	}
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return colNames[column];
	}

}
