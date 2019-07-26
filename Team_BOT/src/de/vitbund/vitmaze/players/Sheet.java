package de.vitbund.vitmaze.players;

public class Sheet {

	private int sheets;	
	
	public Sheet() {}
	
	public Sheet(int sheets) {
		this.sheets = sheets;
	}

	
	public void sheetStack(String lastAction) {
		if (lastAction.equals("OK SHEET")) {
			setSheets(getSheets() + 1);
		}
	}
	
	public int getSheets() {
		return sheets;
	}

	public void setSheets(int sheets) {
		this.sheets = sheets;
	}
	
	
}
