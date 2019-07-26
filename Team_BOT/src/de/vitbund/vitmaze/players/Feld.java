package de.vitbund.vitmaze.players;

public class Feld {


	
	//Attribute
	private String status;
	private boolean gesehen;
	private int zaehlerBetreten;

	//Konstruktor
	public Feld(String status,
			boolean gesehen, int zaehlerBetreten) {
		this.status = status;
		this.gesehen = gesehen;
		this.zaehlerBetreten = zaehlerBetreten;
	}
	
	
	//getter und setter
	
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

}
