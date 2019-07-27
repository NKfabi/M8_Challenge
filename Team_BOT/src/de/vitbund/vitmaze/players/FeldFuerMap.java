package de.vitbund.vitmaze.players;

public class FeldFuerMap {
	private int xWert;
	private int yWert;
	private String status;
	private int kosten;
	
	//Konstruktor
	public FeldFuerMap(int xWert, int yWert, String status, int kosten) {
		this.xWert = xWert;
		this.yWert = yWert;
		this.status = status;
		this.kosten = kosten;
	}

	//Getter und Setter
	public int getxWert() {
		return xWert;
	}

	public void setxWert(int xWert) {
		this.xWert = xWert;
	}

	public int getyWert() {
		return yWert;
	}

	public void setyWert(int yWert) {
		this.yWert = yWert;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String toString() {
		return status + "=" + kosten;
	}
	
	public void setKosten(int kosten) {
		this.kosten = kosten;
	}
	
	public int getKosten() {
		return kosten;
	}
	
}
