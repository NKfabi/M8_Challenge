package de.vitbund.vitmaze.players;

public class Landkarte {

	private int xKord;
	private int yKord;
	private String[][] map;
	private String ausgabe;

	public Landkarte(int xKord, int yKord) {
		this.xKord = xKord;
		this.yKord = yKord;

		map = new String[yKord][xKord];
		
		for (int y = 0; y < this.yKord; y++) {
			for (int x = 0; x < this.xKord; x++) {
				map[y][x] = " ?? ";
			}
		}
	}

	/**
	 * Methode soll Labyrinth zeichnen indem der Bot durch die Map l�uft
	 * 
	 * @param x
	 * @param y
	 */
	public void printMap() {
		for (int y = 0; y < yKord; y++) {
			ausgabe = "";
			for (int x = 0; x < xKord; x++) {
				ausgabe = ausgabe + map[y][x];
			}
		System.err.println(ausgabe);	
		}


	}

	public void merkeFeldNord(String direction, int y, int x, int id) {
		if (direction.equals("WALL")) {
			map[y-1][x] = " WW ";
		}
		else if (direction.equals("FLOOR")) {
			map[y-1][x] = " FF ";
		}
		else if (direction.equals("FINISH " + id + " 0")) {
			map[y-1][x] = " FI ";
		}
		else {
			map[y-1][x] = " AS ";
		}
	}
	
	public void merkeFeldSued(String direction, int y, int x, int id) {
		if (direction.equals("WALL")) {
			map[y+1][x] = " WW ";
		}
		else if (direction.equals("FLOOR")) {
			map[y+1][x] = " FF ";
		}
		else if (direction.equals("FINISH " + id + " 0")) {
			map[y+1][x] = " FI ";
		}
		else {
			map[y+1][x] = " AS ";
		}
	}
	
	public void merkeFeldWest(String direction, int y, int x, int id) {
		if (direction.equals("WALL")) {
			map[y][x-1] = " WW ";
		}
		else if (direction.equals("FLOOR")) {
			map[y][x-1] = " FF ";
		}
		else if (direction.equals("FINISH " + id + " 0")) {
			map[y][x-1] = " FI ";
		}
		else {
			map[y][x-1] = " AS ";
		}
	}
	
	public void merkeFeldEast(String direction, int y, int x, int id) {
		if (direction.equals("WALL")) {
			map[y][x+1] = " WW ";
		}
		else if (direction.equals("FLOOR")) {
			map[y][x+1] = " FF ";
		}
		else if (direction.equals("FINISH " + id + " 0")) {
			map[y][x+1] = " FI ";
		}
		else {
			map[y][x+1] = " AS ";
		}
	}
	
	
	public void merkeFeldAktuell(int y, int x) {
		map[y][x] = "FF";
	}
	
	/**
	 * gibt gezeichnete Map aus
	 * 
	 */
	public void mapAusgabe(int yKord, int xKord, String[][] map) {
		
		
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
