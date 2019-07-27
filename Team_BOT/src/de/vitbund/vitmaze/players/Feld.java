package de.vitbund.vitmaze.players;

/*
  * Klasse Feld als Teil der Karte
  * 
  *  @author Fabian Riede
  */

public class Feld {
	
	/**
	 * status fuer den Status des Feldes, ob es eine Wand, ein Gang, o.ae. ist
	 */
	private String status;
	/**
	 *  gesehen dient dazu um dem Bot zu sagen, ob er das Feld schon mal gesehen hat
	 */
	private boolean gesehen;
	/**
	 * zaehlerBetreten sagt dem Bot wie oft er das Feld betreten hat
	 */
	private int zaehlerBetreten;
	
	/**
	 * Konstruktor
	 * 
	 * @param status - uebergibt den Parameter Status
	 * @param gesehen - uebergibt den Parameter gesehen
	 * @param zaehlerBetreten - uebergibt den Parameter zaehlerbetreten
	 */
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
