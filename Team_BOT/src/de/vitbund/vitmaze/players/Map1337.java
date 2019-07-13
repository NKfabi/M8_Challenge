package de.vitbund.vitmaze.players;

import java.util.ArrayList;
import java.util.List;

public class Map1337 {

	// Attribute
	private int xKord;
	private int yKord;
	private Field1337[][] map;
	private String ausgabe;
	private List<String> moeglicheZuege = new ArrayList<>();


	// Konstruktor
	public Map1337(int xKord, int yKord) {
		this.xKord = xKord;
		this.yKord = yKord;

		map = new Field1337[yKord][xKord];

		for (int y = 0; y < this.yKord; y++) {
			for (int x = 0; x < this.xKord; x++) {
				map[y][x] = new Field1337(y, x, " ? ", null, false, false, 0);

			}
		}
	}

	/**
	 * Methode zeichnet Map auf Basis der Groesse des Labyrinths
	 * 
	 * @param x
	 * @param y
	 */
	public void printMap() {
		for (int y = 0; y < yKord; y++) {
			ausgabe = "";
			for (int x = 0; x < xKord; x++) {
				ausgabe = ausgabe + map[y][x].getTyp();
			}
			System.err.println(ausgabe);
		}

	}

	/**
	 * merkt sich das Feld im Norden
	 * 
	 * @param direction
	 * @param y
	 * @param x
	 * @param id
	 */
	public void merkeFeldNorth(String direction, int y, int x, int id) {
		if (map[y - 1][x].getTyp().equals(" ? ")) {
			if (direction.equals("WALL")) {
				map[y - 1][x].setTyp(" # ");
				map[y - 1][x].setStatus("WALL");
				map[y - 1][x].setGesehen(true);
			} else if (direction.equals("FLOOR")) {
				map[y - 1][x].setTyp("   ");
				map[y - 1][x].setStatus("FLOOR");
				map[y - 1][x].setGesehen(true);
			} else if (direction.equals("FINISH " + id + " 0")) {
				map[y - 1][x].setTyp(" F ");
				map[y - 1][x].setStatus("FINISH " + id + " 0");
				map[y - 1][x].setGesehen(true);
			} else {
				map[y - 1][x].setTyp(" ! ");
				map[y - 1][x].setStatus("aSB");
				map[y - 1][x].setGesehen(true);
			}
		}
	}

	/**
	 * merkt sich das Feld im Sueden
	 * 
	 * @param direction
	 * @param y
	 * @param x
	 * @param id
	 */
	public void merkeFeldSouth(String direction, int y, int x, int id) {
		if (map[y + 1][x].getTyp().equals(" ? ")) {
			if (direction.equals("WALL")) {
				map[y + 1][x].setTyp(" # ");
				map[y + 1][x].setStatus("WALL");
				map[y + 1][x].setGesehen(true);
			} else if (direction.equals("FLOOR")) {
				map[y + 1][x].setTyp("   ");
				map[y + 1][x].setStatus("FLOOR");
				map[y + 1][x].setGesehen(true);
			} else if (direction.equals("FINISH " + id + " 0")) {
				map[y + 1][x].setTyp(" F ");
				map[y + 1][x].setStatus("FINISH " + id + " 0");
				map[y + 1][x].setGesehen(true);
			} else {
				map[y + 1][x].setTyp(" ! ");
				map[y + 1][x].setStatus("aSB");
				map[y + 1][x].setGesehen(true);
			}
		}
	}

	/**
	 * merkt sich das Feld im Westen
	 * 
	 * @param direction
	 * @param y
	 * @param x
	 * @param id
	 */
	public void merkeFeldWest(String direction, int y, int x, int id) {
		if (map[y][x - 1].getTyp().equals(" ? ")) {
			if (direction.equals("WALL")) {
				map[y][x - 1].setTyp(" # ");
				map[y][x - 1].setStatus("WALL");
				map[y][x - 1].setGesehen(true);
			} else if (direction.equals("FLOOR")) {
				map[y][x - 1].setTyp("   ");
				map[y][x - 1].setStatus("FLOOR");
				map[y][x - 1].setGesehen(true);
			} else if (direction.equals("FINISH " + id + " 0")) {
				map[y][x - 1].setTyp(" F ");
				map[y][x - 1].setStatus("FINISH " + id + " 0");
				map[y][x - 1].setGesehen(true);
			} else {
				map[y][x - 1].setTyp(" ! ");
				map[y][x - 1].setStatus("aSB");
				map[y][x - 1].setGesehen(true);
			}
		}
	}

	/**
	 * merkt sich das Feld im Osten
	 * 
	 * @param direction
	 * @param y
	 * @param x
	 * @param id
	 */
	public void merkeFeldEast(String direction, int y, int x, int id) {
		if (map[y][x + 1].getTyp().equals(" ? ")) {
			if (direction.equals("WALL")) {
				map[y][x + 1].setTyp(" # ");
				map[y][x + 1].setStatus("WALL");
				map[y][x + 1].setGesehen(true);
			} else if (direction.equals("FLOOR")) {
				map[y][x + 1].setTyp("   ");
				map[y][x + 1].setStatus("FLOOR");
				map[y][x + 1].setGesehen(true);
			} else if (direction.equals("FINISH " + id + " 0")) {
				map[y][x + 1].setTyp(" F ");
				map[y][x + 1].setStatus("FINISH " + id + " 0");
				map[y][x + 1].setGesehen(true);
			} else {
				map[y][x + 1].setTyp(" ! ");
				map[y][x + 1].setStatus("aSB");
				map[y][x + 1].setGesehen(true);
			}
		}
	}

	/**
	 * merkt sich jedes betretene Feld
	 * 
	 */
	public void merkeBetretenesFeld(String position, String lastAction, int y, int x, int id) {
		if (!lastAction.equals("NOK BLOCKED")) {
			map[y][x].setBetreten(true);
			map[y][x].setTyp(" v ");
			map[y][x].setZaehlerBetreten(+1);
			if (position.equals("FINISH " + id + " 0")) {
				map[y][x].setStatus("FINISH " + id + " 0");
			} else {
				map[y][x].setStatus("aSB");
			}
		}
	}

	public void sucheWeg(String position, String lastAction, int y, int x, int id) {
		if (position.equals("FINISH " + id + " 0")) {
			System.out.println("finish");
		}

		else if (map[y - 1][x].getStatus().equals("FINISH " + id + " 0")) {
			System.out.println("go north");
		} else if (map[y + 1][x].getStatus().equals("FINISH " + id + " 0")) {
			System.out.println("go south");
		} else if (map[y][x - 1].getStatus().equals("FINISH " + id + " 0")) {
			System.out.println("go west");
		} else if (map[y][x + 1].getStatus().equals("FINISH " + id + " 0")) {
			System.out.println("go east");
		}

		else if (map[y - 1][x].isBetreten() == false && !map[y - 1][x].getStatus().equals("WALL")) {
			System.out.println("go north");
		} else if (map[y + 1][x].isBetreten() == false && !map[y + 1][x].getStatus().equals("WALL")) {
			System.out.println("go south");
		} else if (map[y][x - 1].isBetreten() == false && !map[y][x - 1].getStatus().equals("WALL")) {
			System.out.println("go west");
		} else if (map[y][x + 1].isBetreten() == false && !map[y][x + 1].getStatus().equals("WALL")) {
			System.out.println("go east");
		}

		else if (map[y - 1][x].getZaehlerBetreten() >= 1 && map[y - 1][x].getStatus().equals("FLOOR")) {
			System.out.println("go north");
		} else if (map[y + 1][x].getZaehlerBetreten() >= 1 && map[y + 1][x].getStatus().equals("FLOOR")) {
			System.out.println("go south");
		} else if (map[y][x - 1].getZaehlerBetreten() >= 1 && map[y][x - 1].getStatus().equals("FLOOR")) {
			System.out.println("go west");
		} else if (map[y][x + 1].getZaehlerBetreten() >= 1 && map[y][x + 1].getStatus().equals("FLOOR")) {
			System.out.println("go east");
		}
	}

	

	// getter und setter
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

	public String getAusgabe() {
		return ausgabe;
	}

	public void setAusgabe(String ausgabe) {
		this.ausgabe = ausgabe;
	}

	public Field1337[][] getMap() {
		return map;
	}

	public void setMap(Field1337[][] map) {
		this.map = map;
	}
}
