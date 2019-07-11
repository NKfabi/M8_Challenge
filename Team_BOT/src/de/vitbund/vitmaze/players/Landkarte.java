package de.vitbund.vitmaze.players;

import java.util.ArrayList;
import java.util.List;

public class Landkarte {

	private int xKord;
	private int yKord;
	private Feld[][] map;
	private String ausgabe;
	private List<Integer> xCur = new ArrayList<>();
	private List<Integer> yCur = new ArrayList<>();

	public Landkarte(int xKord, int yKord) {
		this.xKord = xKord;
		this.yKord = yKord;
//
		map = new Feld[yKord][xKord];

		for (int y = 0; y < this.yKord; y++) {
			for (int x = 0; x < this.xKord; x++) {
				map[y][x] = new Feld(" ? ", null, false, false, 0);

			}
		}
	}

	/**
	 * Methode soll Labyrinth zeichnen indem der Bot durch die Map läuft
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
	 * TODO Strings müssen durch Instanzen von Feld ersetzt werden
	 * 
	 * @param direction
	 * @param y
	 * @param x
	 * @param id
	 */
	public void merkeFeldNord(String direction, int y, int x, int id) {
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
	 * TODO Strings müssen durch Instanzen von Feld ersetzt werden
	 * 
	 * @param direction
	 * @param y
	 * @param x
	 * @param id
	 */
	public void merkeFeldSued(String direction, int y, int x, int id) {
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
	 * TODO Strings müssen durch Instanzen von Feld ersetzt werden
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

	public void merkeBetretenesFeld(String position, String lastAction, int y, int x, int id) {
//		if (lastAction.equals("OK") || lastAction.equals("OK NORTH") || lastAction.equals("OK EAST")
//				|| lastAction.equals("OK SOUTH") || lastAction.equals("OK WEST")) {)
			if (map[y][x].isBetreten() == false) {
				map[y][x].setBetreten(true);
				
				map[y][x].setTyp(" v ");
				map[y][x].setZaehlerBetreten(+1);
			} else if (map[y][x].isBetreten() == true){
				map[y][x].setZaehlerBetreten(+1);
			}else if (map[y][x].isBetreten() == false && map[y][x].getTyp().equals(" F ")) {
				map[y][x].setStatus("FINISH " + id + " 0");
			}
//		}
	}

//	public void sucheWeg(String position, String lastAction, int y, int x, int id) {
//		if (map[y][x].getTyp().equals(" F ")) {
//			System.out.println("finish");
//		} else if (map[y - 1][x].isBetreten() == false && map[y - 1][x].getTyp().equals(" F ") || map[y - 1][x].isBetreten() == false && map[y - 1][x].getTyp().equals("   ")) {
//			System.out.println("go north");
//		} else if (map[y + 1][x].isBetreten() == false && map[y - 1][x].getTyp().equals(" F ") || map[y + 1][x].isBetreten() == false && map[y + 1][x].getTyp().equals("   ")) {
//			System.out.println("go south");
//		} else if (map[y][x -1].isBetreten() == false && map[y - 1][x].getTyp().equals(" F ") || map[y][x - 1].isBetreten() == false && map[y][x - 1].getTyp().equals("   ")) {
//			System.out.println("go west");
//		} else if (map[y][x + 1].isBetreten() == false && map[y - 1][x].getTyp().equals(" F ") || map[y][x + 1].isBetreten() == false && map[y][x + 1].getTyp().equals("   ")) {
//			System.out.println("go east");
//		}
//
//		
//	}

	public void sucheWeg(String position, String lastAction, int y, int x, int id) {
		if (map[y][x].getStatus().equals("FINISH " + id + " 0")) {
			System.out.println("finish");
		}

		else if (map[y - 1][x].isBetreten() == false && map[y - 1][x].getStatus().equals("FINISH " + id + " 0")) {
			System.out.println("go north");
		} else if (map[y - 1][x].isBetreten() == false && !map[y - 1][x].getStatus().equals("WALL")) {
			System.out.println("go north");
		}

		else if (map[y + 1][x].isBetreten() == false && map[y + 1][x].getStatus().equals("FINISH " + id + " 0")) {
			System.out.println("go south");
		} else if (map[y + 1][x].isBetreten() == false && !map[y + 1][x].getStatus().equals("WALL")) {
			System.out.println("go south");
		}

		else if (map[y][x - 1].isBetreten() == false && map[y][x - 1].getStatus().equals("FINISH " + id + " 0")) {
			System.out.println("go west");
		} else if (map[y][x - 1].isBetreten() == false && !map[y][x - 1].getStatus().equals("WALL")) {
			System.out.println("go west");
		}

		else if (map[y][x + 1].isBetreten() == false && map[y][x + 1].getStatus().equals("FINISH " + id + " 0")) {
			System.out.println("go east");
		} else if (map[y][x + 1].isBetreten() == false && !map[y][x + 1].getStatus().equals("WALL")) {
			System.out.println("go east");
		}

		else if (map[y - 1][x].getZaehlerBetreten() >= 1 && !map[y - 1][x].getStatus().equals("WALL")) {
			System.out.println("go north");
		} else if (map[y + 1][x].getZaehlerBetreten() >= 1 && !map[y + 1][x].getStatus().equals("WALL")) {
			System.out.println("go south");
		} else if (map[y][x - 1].getZaehlerBetreten() >= 1 && !map[y][x - 1].getStatus().equals("WALL")) {
			System.out.println("go west");
		} else if (map[y][x + 1].getZaehlerBetreten() >= 1 && !map[y][x + 1].getStatus().equals("WALL")) {
			System.out.println("go east");
		}

		else if (map[y - 1][x].getZaehlerBetreten() >= 1 && map[y - 1][x].getStatus().equals("FINISH " + id + " 0")) {
			System.out.println("go north");
		} else if (map[y + 1][x].getZaehlerBetreten() >= 1 && map[y + 1][x].getStatus().equals("FINISH " + id + " 0")) {
			System.out.println("go south");
		} else if (map[y][x - 1].getZaehlerBetreten() >= 1 && map[y][x - 1].getStatus().equals("FINISH " + id + " 0")) {
			System.out.println("go west");
		} else if (map[y][x + 1].getZaehlerBetreten() >= 1 && map[y][x + 1].getStatus().equals("FINISH " + id + " 0")) {
			System.out.println("go east");
		}
	}

	/**
	 * TODO
	 * 
	 * @param y
	 * @param x
	 */
	public void merkeLetztesFeld(int y, int x) {
		xCur.add(x);
		yCur.add(y);

	}

	/**
	 * gibt gezeichnete Map aus
	 * 
	 */
//	public void mapAusgabe(int yKord, int xKord, String[][] map) {
//
//	}

	/**
	 * Methode, um zu sehen was in 4 Feldern um den BOT liegt
	 */
//	public void umsehen() {
//		// TODO
//
//	}

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

	public Feld[][] getMap() {
		return map;
	}

	public void setMap(Feld[][] map) {
		this.map = map;
	}
}
