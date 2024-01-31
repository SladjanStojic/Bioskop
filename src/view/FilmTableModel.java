package view;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Film;
import model.Sala;

public class FilmTableModel extends AbstractTableModel{
	
	private List<Film> lista;
	private String[] colNames= {"ID","Naslov","Premijera","Poslednje prikazivanje"};

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Film s=lista.get(rowIndex);
		switch (columnIndex) {
		case 0:return s.getId();
		case 1:return s.getNaslov();
		case 2:return s.getPremijera();
		case 3:return s.getKraj();
		}
		return null;
	}
	
	public void setData(List<Film> lista) {
		this.lista=lista;
	}

	public void dodajFilm(Film s) {
		// TODO Auto-generated method stub
		lista.add(s);
	}
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return colNames[column];
	}

}
