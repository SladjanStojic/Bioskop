package model;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class RezervacijaModel {
	
	private int redova;
	private int kolona;
	private String bioskop;
	private String sala;
	private Date datum;
	private Time vreme;
	private String naslov;
	private int cena;
	//private List<Karta>lista;
	private int[][]raspored;
	
	public int getRedova() {
		return redova;
	}
	public void setRedova(int redova) {
		this.redova = redova;
	}
	public int getKolona() {
		return kolona;
	}
	public void setKolona(int kolona) {
		this.kolona = kolona;
	}
	public String getBioskop() {
		return bioskop;
	}
	public void setBioskop(String bioskop) {
		this.bioskop = bioskop;
	}
	public String getSala() {
		return sala;
	}
	public void setSala(String sala) {
		this.sala = sala;
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
	public String getNaslov() {
		return naslov;
	}
	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}
	public int getCena() {
		return cena;
	}
	public void setCena(int cena) {
		this.cena = cena;
	}
	public int[][] getRaspored() {
		return raspored;
	}
	public void setRaspored(int[][] raspored) {
		this.raspored = raspored;
	}
	
	
	
	

}
