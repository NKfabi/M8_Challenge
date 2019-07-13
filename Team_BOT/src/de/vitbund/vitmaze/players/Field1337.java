package de.vitbund.vitmaze.players;

public class Field1337 {

	
	//Attribute
	private int xWert;
	private int yWert;
	private String typ;
	private String status;
	private boolean gesehen;
	private boolean betreten;
	private int zaehlerBetreten;
	
	
	//Konstruktor
	public Field1337(int xWert, int yWert, String typ, String status, boolean gesehen, boolean betreten, int zaehlerBetreten) {
		this.xWert = xWert;
		this.yWert = yWert;
		this.typ = typ;
		this.status = status;
		this.gesehen = gesehen;
		this.betreten = betreten;
		this.zaehlerBetreten = zaehlerBetreten;
	}
	
	
	
	//getter und setter
	public int getxWert() {
		return xWert;
	}
	public void setxWert(int xWert) {
		this.xWert = xWert;
	}

	public int getyWert() {
		return yWert;
	}
	public void setyWert(int yWert) {
		this.yWert = yWert;
	}

	public String getTyp() {
		return typ;
	}
	public void setTyp(String typ) {
		this.typ = typ;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isGesehen() {
		return gesehen;
	}
	public void setGesehen(boolean gesehen) {
		this.gesehen = gesehen;
	}
	
	public boolean isBetreten() {
		return betreten;
	}
	public void setBetreten(boolean betreten) {
		this.betreten = betreten;
	}
	
	public int getZaehlerBetreten() {
		return zaehlerBetreten;
	}
	public void setZaehlerBetreten(int zaehlerBetreten) {
		this.zaehlerBetreten = zaehlerBetreten;
	}


	
}

