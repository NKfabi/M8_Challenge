package de.vitbund.vitmaze.players;


/**
 * 
 * Klasse Sheet
 * 
 * @author Fabian Riede
 *
 */
public class Sheet {

	/**
	 * Attribut:
	 * 
	 */
	private int sheets;	
	
	/**
	 * Standardkonstruktor
	 * 
	 */
	public Sheet() {}
	
	
	/**
	 * Konstruktor 
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
	
	
	/**
	 * Getter und Setter 
	 * 
	 * 
	 */
	public int getSheets() {
		return sheets;
	}

	public void setSheets(int sheets) {
		this.sheets = sheets;
	}
	
	
}
