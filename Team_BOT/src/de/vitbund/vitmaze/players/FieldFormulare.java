package de.vitbund.vitmaze.players;

public class FieldFormulare {

	
	//Attribute
	private int xWert;
	private int yWert;
	private String status;
	private boolean gesehen;
	private int zaehlerBetreten;
	private int kosten;

	
	
	//Konstruktor
	public FieldFormulare(int xWert, int yWert, String status, boolean gesehen, int zaehlerBetreten, int kosten) {
		this.xWert = xWert;
		this.yWert = yWert;
		this.status = status;
		this.gesehen = gesehen;
		this.zaehlerBetreten = zaehlerBetreten;
		this.kosten = kosten;
		
		
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
	
	public int getZaehlerBetreten() {
		return zaehlerBetreten;
	}
	public void setZaehlerBetreten(int zaehlerBetreten) {
		this.zaehlerBetreten = zaehlerBetreten;
	}



	public int getKosten() {
		return kosten;
	}



	public void setKosten(int kosten) {
		this.kosten = kosten;
	}



	
}

