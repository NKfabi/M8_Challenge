package de.vitbund.vitmaze.players;

public class Landkarte {

	private int xKord;
	private int yKord;
	private String[][] map;

	public Landkarte(int xKord, int yKord) {
		this.xKord = xKord;
		this.yKord = yKord;

	}

	/**
	 * Methode soll Labyrinth zeichnen indem der Bot durch die Map läuft
	 * 
	 * @param x
	 * @param y
	 */
	public String[][] printMap(int yKord, int xKord) {
		for (int y = 0; y < yKord; y++) {
			for (int x = 0; x < xKord; x++) {
				map[y][x] = "?";
			}
		}
		
		for (int y = 0; y < yKord; y++) {
			for (int x = 0; x < xKord; x++) {
				System.err.println(map[y][x]);
			}
		}
		return map;

	}

	/**
	 * gibt gezeichnete Map aus
	 * 
	 */
	public void mapAusgabe(int yKord, int xKord, String[][] map) {
		for (int y = 0; y < yKord; y++) {
			for (int x = 0; x < xKord; x++) {
				System.err.println(map[y][x]);
			}
		}
	}

	/**
	 * Methode, um zu sehen was in 4 Feldern um den BOT liegt
	 */
	public void umsehen() {
		// TODO

	}

	public int getxKord() {
		return xKord;
	}

	public void setxKord(int xKord) {
		this.xKord = xKord;
	}

	public int getyKord() {
		return yKord;
	}

	public void setyKord(int yKord) {
		this.yKord = yKord;
	}

	public String[][] getMap() {
		return map;
	}

	public void setMap(String[][] map) {
		this.map = map;
	}
}
