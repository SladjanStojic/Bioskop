package model;

import java.sql.Time;

public class RepertoarAdmin {
	
	private int id;
	private Time vreme;
	private String naziv;
	private int duzina;
	private int cena;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Time getVreme() {
		return vreme;
	}
	public void setVreme(Time vreme) {
		this.vreme = vreme;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public int getDuzina() {
		return duzina;
	}
	public void setDuzina(int duzina) {
		this.duzina = duzina;
	}
	public int getCena() {
		return cena;
	}
	public void setCena(int cena) {
		this.cena = cena;
	}
	
	

}
