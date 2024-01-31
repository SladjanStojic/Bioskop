package model;

import java.sql.Time;
import java.util.Date;

public class Repertoar {
	
	private int id;
	private int idSale;
	private int idFilma;
	private int cena;
	private Date datum;
	private Time vreme;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdSale() {
		return idSale;
	}
	public void setIdSale(int idSale) {
		this.idSale = idSale;
	}
	public int getIdFilma() {
		return idFilma;
	}
	public void setIdFilma(int idFilma) {
		this.idFilma = idFilma;
	}
	public int getCena() {
		return cena;
	}
	public void setCena(int cena) {
		this.cena = cena;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	public Time getVreme() {
		return vreme;
	}
	public void setVreme(Time vreme) {
		this.vreme = vreme;
	}

	
}
