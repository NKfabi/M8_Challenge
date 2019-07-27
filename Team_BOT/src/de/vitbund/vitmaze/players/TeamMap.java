package de.vitbund.vitmaze.players;

import java.util.ArrayList;
import java.util.List;

public class TeamMap {

	// Attribute
	private int sizeX;
	private int sizeY;
	private int posX = 0;
	private int posY = 0;
	private int playerId;
	private TeamField[][] map;
	private List<String> moeglicheZuege = new ArrayList<>();

	// Konstruktor
	public TeamMap(int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;

		map = new TeamField[sizeY][sizeX];

		for (int y = 0; y < this.sizeY; y++) {
			for (int x = 0; x < this.sizeX; x++) {
				map[y][x] = new TeamField(y, x, " leer ", false, false, 0);

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
		for (int y = 0; y < sizeY; y++) {
			for (int x = 0; x < sizeX; x++) {
				if (map[y][x].getStatus().equals(" leer ")) {
					System.err.print(" ? ");
				} else if (map[y][x].getStatus().equals("FINISH " + playerId + " 0")) {
					System.err.print(" F ");
				} else if (map[y][x].getStatus().equals("FLOOR")) {
					System.err.print("   ");
				} else if (map[y][x].getStatus().equals("WALL")) {
					System.err.print(" # ");
				} else {
					System.err.print(" ! ");
				}
			}
			System.err.println();
		}

	}

	/**
	 * sucht den Weg in dem es moegliche Zuege in eine Liste schreibt
	 * 
	 */
	public void sucheWeg() {

		List<String> moeglicheZuegeNew = new ArrayList<String>();

			if (!map[posY +1][posX].getStatus().equals("WALL") && ((map[posY +2][posX].getStatus().equals(" leer ") || map[posY +1][posX +1].getStatus().equals(" leer ") 
					|| map[posY +1][posX -1].getStatus().equals(" leer ")))) {
				moeglicheZuegeNew.add("go south");
			}
			else if (!map[posY +1][posX].getStatus().equals("WALL")) {
				moeglicheZuegeNew.add("go south");
			}
			if (!map[posY][posX -1].getStatus().equals("WALL") && ((map[posY +1][posX -1].getStatus().equals(" leer ") || map[posY -1][posX -1].getStatus().equals(" leer ")
					|| map[posY][posX -2].getStatus().equals(" leer ")))) {
				moeglicheZuegeNew.add("go west");
			}
			else if (!map[posY][posX -1].getStatus().equals("WALL")) {
				moeglicheZuegeNew.add("go west");
			}
			if (!map[posY -1][posX].getStatus().equals("WALL") && ((map[posY -2][posX].getStatus().equals(" leer ") || map[posY -1][posX -1].getStatus().equals(" leer ")
					|| map[posY +1][posX +1].getStatus().equals(" leer ")))) {
				moeglicheZuegeNew.add("go north");
			}
			else if (!map[posY -1][posX].getStatus().equals("WALL")) {
				moeglicheZuegeNew.add("go north");
			}
			if (!map[posY][posX +1].getStatus().equals("WALL") && ((map[posY +1][posX +1].getStatus().equals(" leer ") || map[posY][posX +2].getStatus().equals(" leer ")
					|| map[posY -1][posX +1].getStatus().equals(" leer ")))) {
				moeglicheZuegeNew.add("go east");
			}
			else if (!map[posY][posX +1].getStatus().equals("WALL")) {
				moeglicheZuegeNew.add("go east");
			}
			
			System.err.println("Mögliche Züge: " + moeglicheZuegeNew.size());
			
			moeglicheZuege = moeglicheZuegeNew;
		
	}

	/**
	 * updatet die Spielerposition je nach letztem Zug
	 * 
	 * @param lastAction
	 */
	public void botPosition(String lastAction) {
		if (lastAction.equals("OK")) {
		} else if (lastAction.equals("OK WEST")) {
			posX -= 1;
		} else if (lastAction.equals("OK EAST")) {
			posX += 1;
		} else if (lastAction.equals("OK SOUTH")) {
			posY += 1;
		} else if (lastAction.equals("OK NORTH")) {
			posY -= 1;
		}

	}

	/**
	 * updatet das Umfeld des Bots also die 4 umliegenden Felder
	 * 
	 * @param currentPosition
	 * @param lastPosition
	 * @param northStatus
	 * @param eastStatus
	 * @param southStatus
	 * @param westStatus
	 * @param playerId
	 */
	public void updateUmfeld(String currentPosition, String lastPosition, String northStatus, String eastStatus,
			String southStatus, String westStatus) {
		// aktuelles Feld
		map[posY][posX].setStatus(currentPosition);
		map[posY][posX].setGesehen(true);
		map[posY][posX].setZaehlerBetreten(map[posY][posX].getZaehlerBetreten() + 1);

		// Norden
		map[posY - 1][posX].setStatus(northStatus);
		map[posY - 1][posX].setGesehen(true);

		// Sueden
		map[posY + 1][posX].setStatus(southStatus);
		map[posY + 1][posX].setGesehen(true);

		// Westen
		map[posY][posX - 1].setStatus(westStatus);
		map[posY][posX - 1].setGesehen(true);

		// Osten
		map[posY][posX + 1].setStatus(eastStatus);
		map[posY][posX + 1].setGesehen(true);

	}

	/**
	 * berechnet den Weg des Bots und laesst in die Richtung, auf den Feldern auf
	 * denen er am wenigsten stand, gehen
	 * 
	 */
	public String berechneWeg() {
		
		String niedrigsterZug = " ";
		
		if (map[posY][posX].getStatus().equals("FINISH " + playerId + " 0")) {
			niedrigsterZug = "finish";
		}
		else if (map[posY + 1][posX].getStatus().equals("FINISH " + playerId + " 0")) {
			niedrigsterZug = "go south";
		}
		else if (map[posY][posX - 1].getStatus().equals("FINISH " + playerId + " 0")) {
			niedrigsterZug = "go west";
		}
		else if (map[posY - 1][posX].getStatus().equals("FINISH " + playerId + " 0")) {
			niedrigsterZug = "go north";
		}
		else if (map[posY][posX + 1].getStatus().equals("FINISH " + playerId + " 0")) {
			niedrigsterZug = "go east";
		}
		else {
		int niedrigsteAnzahl = 999;
		
		niedrigsterZug = moeglicheZuege.get(0);

		for (String zug : moeglicheZuege) {
			switch (zug) {

			case "go south":
				if (map[posY + 1][posX].getZaehlerBetreten() < niedrigsteAnzahl) {
					niedrigsterZug = "go south";
					niedrigsteAnzahl = map[posY + 1][posX].getZaehlerBetreten();
				}
				break;

			case "go west":
				if (map[posY][posX - 1].getZaehlerBetreten() < niedrigsteAnzahl) {
					niedrigsterZug = "go west";
					niedrigsteAnzahl = map[posY][posX - 1].getZaehlerBetreten();
				}
				break;

			case "go north":
				if (map[posY - 1][posX].getZaehlerBetreten() < niedrigsteAnzahl) {
					niedrigsterZug = "go north";
					niedrigsteAnzahl = map[posY - 1][posX].getZaehlerBetreten();
				}
				break;

			case "go east":
				if (map[posY][posX + 1].getZaehlerBetreten() < niedrigsteAnzahl) {
					niedrigsterZug = "go east";
					niedrigsteAnzahl = map[posY][posX + 1].getZaehlerBetreten();
				}
				break;

			}
		
		}}
		return niedrigsterZug;
	}

	// getter und setter
	public int getSizeX() {
		return sizeX;
	}

	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public TeamField[][] getMap() {
		return map;
	}

	public void setMap(TeamField[][] map) {
		this.map = map;
	}

	public List<String> getMoeglicheZuege() {
		return moeglicheZuege;
	}

	public void setMoeglicheZuege(List<String> moeglicheZuege) {
		this.moeglicheZuege = moeglicheZuege;
	}

}
