package de.vitbund.vitmaze.players;

public class Feld {
	private String feldname;
	private boolean betreten;
	private int betretungsAnzahl;
	
	
	
	
	
	public String getFeldname() {
		return feldname;
	}
	public void setFeldname(String feldname) {
		this.feldname = feldname;
	}
	public boolean isBetreten() {
		return betreten;
	}
	public void setBetreten(boolean betreten) {
		this.betreten = betreten;
	}
	public int getBetretungsAnzahl() {
		return betretungsAnzahl;
	}
	public void setBetretungsAnzahl(int betretungsAnzahl) {
		this.betretungsAnzahl = betretungsAnzahl;
	}

	

}
