package model;

public class Zanr {
	
	private int id;
	private String zanr;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getZanr() {
		return zanr;
	}
	public void setZanr(String zanr) {
		this.zanr = zanr;
	}
	@Override
	public String toString() {
		return zanr;
	}
	
	

}
