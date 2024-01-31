package model;

public class Sala {
	
	private int id;
	private String naziv;
	private int kolona;
	private int redova;
	private int idBioskopa;
	private boolean aktivna;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public int getKolona() {
		return kolona;
	}
	public void setKolona(int kolona) {
		this.kolona = kolona;
	}
	public int getRedova() {
		return redova;
	}
	public void setRedova(int redova) {
		this.redova = redova;
	}
	public int getIdBioskopa() {
		return idBioskopa;
	}
	public void setIdBioskopa(int idBioskopa) {
		this.idBioskopa = idBioskopa;
	}
	public boolean isAktivna() {
		return aktivna;
	}
	public void setAktivna(boolean aktivna) {
		this.aktivna = aktivna;
	}
	@Override
	public String toString() {
		return naziv ;
	}
	
	

}
