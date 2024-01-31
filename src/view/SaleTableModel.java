package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import kontroler.Kontroler;
import model.Sala;

public class SaleTableModel extends AbstractTableModel{
	
	private List<Sala> lista;
	private String[] colNames= {"ID","Naziv","Kolona","Redova","Aktivan"};
	

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
		Sala s=lista.get(rowIndex);
		switch (columnIndex) {
		case 0:return s.getId();
		case 1:return s.getNaziv();
		case 2:return s.getKolona();
		case 3:return s.getRedova();
		case 4:return s.isAktivna();
		}
		return null;
	}
	
	public void setData(List<Sala> lista) {
		this.lista=lista;
	}

	public void dodajSalu(Sala s) {
		// TODO Auto-generated method stub
		lista.add(s);
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return colNames[column];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		switch (columnIndex) {
		case 4:return true;
		}
		return false;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		// TODO Auto-generated method stub
		switch (columnIndex) {
		case 4:return Boolean.class;

		default:
			return String.class;
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		switch (columnIndex) {
		case 4: boolean status=Kontroler.getInstanca().promeniStatusSale(lista.get(rowIndex).getId(),(Boolean)aValue);
		if(status) {
			lista.get(rowIndex).setAktivna((Boolean)aValue);
		}
		};
	}
	
	
	
	
	
	

	
}
