package de.vitbund.vitmaze.players;


/**
 * 
 * Klasse Formular mit einer Methode zum Zaehlen der Formulare, um zu wissen welches der Bot als naechstes aufheben soll und mit einer Methode zum Auslesen der Anzahl der Gesamtformulare
 * 
 * @author Fabian Riede
 *
 */
public class Formular {
	
	/**
	 * speichert Anzahl der Formulare und zaehlt es in der Methode formOrder um eins hoch (=1, weil das erste Formular den Index 1 hat)
	 */
	private int formularZaehler = 1;
	/**
	 * Anzahl der Gesamtformulare, die in leseFormulareGesamt ausgelesen wird
	 */
	private int formulareGesamt;
	/**
	 * Spieler-Id, die in der Main uebergeben wird
	 */
	private int playerId;
	
	
	/**
	 * Standardkonstruktor
	 * 
	 */
	public Formular() {}
	
	/**
	 * Konstruktor
	 * 
	 * @param playerId - uebergibt den Parameter der playerId
	 */
	public Formular(int playerId) {
		this.playerId = playerId;
	}
	
	
	/**
	 * Methode für Reihenfolge der Formulare (um sie nachher beim Sachbearbeiter richtig abzugeben)
	 * 
	 * @param lastAction
	 */
	public void formOrder(String lastAction) {
		if (lastAction.equals("OK FORM")) {
			setFormularZaehler(getFormularZaehler() + 1);
		}

	}
	
	
	/**
	 * Methode für das Auslesen der Gesamtformulare vom Sachbearbeiter - damit der Bot weiss, wieviele Formulare er einreichen muss
	 * 
	 * @param currentPosition - wird in der Main-Klasse uber System.in uebergeben
	 * @param lastPosition - wird in der Main-Klasse uber System.in uebergeben
	 * @param northStatus - wird in der Main-Klasse uber System.in uebergeben
	 * @param eastStatus - wird in der Main-Klasse uber System.in uebergeben
	 * @param southStatus - wird in der Main-Klasse uber System.in uebergeben
	 * @param westStatus - wird in der Main-Klasse uber System.in uebergeben
	 * @return
	 */
	public int leseFormulareGesamt(String currentPosition, String lastPosition, String northStatus, String eastStatus,
			String southStatus, String westStatus) {

		String formulare;

		if (formulareGesamt == 0) {
			if (currentPosition.length() > 8) {
				formulare = currentPosition.substring(0, 8);

				if (formulare.equals("FINISH " + playerId)) {
					String[] parts = currentPosition.split(" ", 4);
					int a = Integer.parseInt(parts[2]);
					setFormulareGesamt(a);
				}
			} else if (northStatus.length() > 8) {
				formulare = northStatus.substring(0, 8);

				if (formulare.equals("FINISH " + playerId)) {
					String[] parts = northStatus.split(" ", 4);
					int a = Integer.parseInt(parts[2]);
					setFormulareGesamt(a);
				}
			} else if (southStatus.length() > 8) {
				formulare = southStatus.substring(0, 8);

				if (formulare.equals("FINISH " + playerId)) {
					String[] parts = southStatus.split(" ", 4);
					int a = Integer.parseInt(parts[2]);
					setFormulareGesamt(a);
				}
			} else if (westStatus.length() > 8) {
				formulare = westStatus.substring(0, 8);

				if (formulare.equals("FINISH " + playerId)) {
					String[] parts = westStatus.split(" ", 4);
					int a = Integer.parseInt(parts[2]);
					setFormulareGesamt(a);
				}
			} else if (eastStatus.length() > 8) {
				formulare = eastStatus.substring(0, 8);

				if (formulare.equals("FINISH " + playerId)) {
					String[] parts = eastStatus.split(" ", 4);
					int a = Integer.parseInt(parts[2]);
					setFormulareGesamt(a);
				}
			}
		}

		return getFormulareGesamt();
	}
	
	
	
	//getter und setter
	
	public int getFormularZaehler() {
		return formularZaehler;
	}
	public void setFormularZaehler(int formularZaehler) {
		this.formularZaehler = formularZaehler;
	}
	public int getFormulareGesamt() {
		return formulareGesamt;
	}
	public void setFormulareGesamt(int formulareGesamt) {
		this.formulareGesamt = formulareGesamt;
	}
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	
	
	
	
}
