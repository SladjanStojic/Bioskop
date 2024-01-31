package model;

import java.util.Date;
import java.util.List;

public class Film {
	
	private int id;
	private String naslov;
	private String opis;
	private Date premijera;
	private Date kraj;
	private int duzinu;
	private String poster;
	
	private List<Zanr> listaZanrova;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNaslov() {
		return naslov;
	}
	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public Date getPremijera() {
		return premijera;
	}
	public void setPremijera(Date premijera) {
		this.premijera = premijera;
	}
	public Date getKraj() {
		return kraj;
	}
	public void setKraj(Date kraj) {
		this.kraj = kraj;
	}
	public int getDuzinu() {
		return duzinu;
	}
	public void setDuzinu(int duzinu) {
		this.duzinu = duzinu;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public List<Zanr> getListaZanrova() {
		return listaZanrova;
	}
	public void setListaZanrova(List<Zanr> listaZanrova) {
		this.listaZanrova = listaZanrova;
	}
	@Override
	public String toString() {
		return "Film " + naslov + " ";
	}
	
	
	
	

}
