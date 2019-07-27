package de.vitbund.vitmaze.players;


/**
 * Klasse Sheet, um sheets zu zaehlen
 * 
 * @author Fabian Riede
 *
 */
public class Sheet {

	/**
	 * speichert Anzahl der Sheets nach Aufnahme
	 */
	private int sheets;	
	
	/**
	 * Standardkonstruktor
	 * 
	 */
	public Sheet() {}
	
	
	/**
	 * Konstruktor mit Uebergabeparameter sheets
	 * 
	 * @param sheets
	 */
	public Sheet(int sheets) {
		this.sheets = sheets;
	}

	
	/**
	 * Methode zum zaehlen der Blaetter, wenn eins aufgenommen wurde (eines hochzaehlen nach Aufnahme eines Blattes)
	 * 
	 * @param lastAction
	 */
	public void sheetStack(String lastAction) {
		if (lastAction.equals("OK SHEET")) {
			setSheets(getSheets() + 1);
		}
	}
	
	
	//getter und setter
	
	public int getSheets() {
		return sheets;
	}

	public void setSheets(int sheets) {
		this.sheets = sheets;
	}
	
	
}
