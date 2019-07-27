package de.vitbund.vitmaze.players;

/*
  * Klasse Feld als Teil der Karte
  * 
  *  @author Fabian Riede
  */

public class Feld {

	/*
	 * Attribute 
	 * status fuer den Status des Feldes, ob es eine Wand, ein Gang, o.ae. ist
	 * gesehen dient dazu um dem Bot zu sagen, ob er das Feld schon mal gesehen hat
	 * und der zaehlerBetreten sagt dem Bot wie oft er das Feld betreten hat
	 */
	private String status;
	private boolean gesehen;
	private int zaehlerBetreten;
	
	/*
	 * Konstruktor
	 */
	public Feld(String status,
			boolean gesehen, int zaehlerBetreten) {
		this.status = status;
		this.gesehen = gesehen;
		this.zaehlerBetreten = zaehlerBetreten;
	}
	
	/*
	 * Getter und Setter
	 */
	
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
