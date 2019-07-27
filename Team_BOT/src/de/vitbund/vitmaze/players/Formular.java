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
	 * Attribute:
	 * 
	 * mit Zaehler für die Formulare, wieviele insgesamt benoetigt werden und die playerId
	 * 
	 */
	private int formularZaehler = 1;
	private int formulareGesamt;
	private int playerId;
	
	
	/**
	 * Standardkonstruktor
	 * 
	 */
	public Formular() {}
	
	/**
	 * Konstruktor
	 * 
	 * @param playerId
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
	 * @param currentPosition
	 * @param lastPosition
	 * @param northStatus
	 * @param eastStatus
	 * @param southStatus
	 * @param westStatus
	 * @return
	 */
	public int leseFormulareGesamt(String currentPosition, String lastPosition, String northStatus, String eastStatus,
			String southStatus, String westStatus) {

		String test;

		if (formulareGesamt == 0) {
			if (currentPosition.length() > 8) {
				test = currentPosition.substring(0, 8);

				if (test.equals("FINISH " + playerId)) {
					String[] parts = currentPosition.split(" ", 4);
					int a = Integer.parseInt(parts[2]);
					setFormulareGesamt(a);
				}
			} else if (northStatus.length() > 8) {
				test = northStatus.substring(0, 8);

				if (test.equals("FINISH " + playerId)) {
					String[] parts = northStatus.split(" ", 4);
					int a = Integer.parseInt(parts[2]);
					setFormulareGesamt(a);
				}
			} else if (southStatus.length() > 8) {
				test = southStatus.substring(0, 8);

				if (test.equals("FINISH " + playerId)) {
					String[] parts = southStatus.split(" ", 4);
					int a = Integer.parseInt(parts[2]);
					setFormulareGesamt(a);
				}
			} else if (westStatus.length() > 8) {
				test = westStatus.substring(0, 8);

				if (test.equals("FINISH " + playerId)) {
					String[] parts = westStatus.split(" ", 4);
					int a = Integer.parseInt(parts[2]);
					setFormulareGesamt(a);
				}
			} else if (eastStatus.length() > 8) {
				test = eastStatus.substring(0, 8);

				if (test.equals("FINISH " + playerId)) {
					String[] parts = eastStatus.split(" ", 4);
					int a = Integer.parseInt(parts[2]);
					setFormulareGesamt(a);
				}
			}
		}

		return getFormulareGesamt();
	}
	
	
	
	/**
	 * Getter und Setter 
	 * 
	 * 
	 */
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
