package de.vitbund.vitmaze.players;

public class Feld {

	private String typ;
	private boolean gesehen;
	private boolean betreten;
	private int zaehlerBetreten;
	
	public Feld(String typ, boolean gesehen, boolean betreten, int zaehlerBetreten) {
		this.typ = typ;
		this.gesehen = gesehen;
		this.betreten = betreten;
		this.zaehlerBetreten = zaehlerBetreten;
	}
	
	
	public String getTyp() {
		return typ;
	}
	public void setTyp(String typ) {
		this.typ = typ;
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

	
	public void feldBetreten() {
		/**
		 * TODO
		 */
//		if 
		
	}
	
}

