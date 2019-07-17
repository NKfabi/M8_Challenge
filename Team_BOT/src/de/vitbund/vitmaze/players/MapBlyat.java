package de.vitbund.vitmaze.players;

import java.util.ArrayList;
import java.util.List;

public class MapBlyat {

	// Attribute
	private int sizeX;
	private int sizeY;
	private int posX = 0;
	private int posY = 0;
	private int playerId;
	private int formCount = 0;
	private int formFinal;
	private FieldBlyat[][] map;
	private List<String> moeglicheZuege = new ArrayList<>();

	// Konstruktor
	public MapBlyat(int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;

		map = new FieldBlyat[sizeY][sizeX];

		for (int y = 0; y < this.sizeY; y++) {
			for (int x = 0; x < this.sizeX; x++) {
				map[y][x] = new FieldBlyat(y, x, null, false, 0);

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
				if (map[y][x].getStatus() == null) {
					System.err.print(" ? ");
				} else if (map[y][x].getStatus().equals("FINISH " + playerId + formFinal)) {
					System.err.print(" F ");
				} else if (map[y][x].getStatus().equals("FORM " + playerId + formCount)) {
					System.err.print(" 0 ");
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

		if (!map[posY + 1][posX].getStatus().equals("WALL")) {
			moeglicheZuegeNew.add("go south");
		}
		if (!map[posY][posX - 1].getStatus().equals("WALL")) {
			moeglicheZuegeNew.add("go west");
		}
		if (!map[posY - 1][posX].getStatus().equals("WALL")) {
			moeglicheZuegeNew.add("go north");
		}
		if (!map[posY][posX + 1].getStatus().equals("WALL")) {
			moeglicheZuegeNew.add("go east");
		}

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

	public void formOrder(String lastAction) {
		if(lastAction.equals("OK FORM")) {
			formCount += 1;
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

		if (currentPosition.contains("FINISH " + playerId)) {
			String[] parts = currentPosition.split(" ", 3);
			int a = Integer.parseInt(parts[2]);

			if (currentPosition.equals("FINISH " + playerId + " " + a)) {
				setFormFinal(a);
			}
		}

		// Norden
		map[posY - 1][posX].setStatus(northStatus);
		map[posY - 1][posX].setGesehen(true);

		if (northStatus.contains("FINISH " + playerId)) {
			String[] parts1 = currentPosition.split(" ", 3);
			int a1 = Integer.parseInt(parts1[2]);

			if (northStatus.equals("FINISH " + playerId + " " + a1)) {
				setFormFinal(a1);
			}
		}
		// Sueden
		map[posY + 1][posX].setStatus(southStatus);
		map[posY + 1][posX].setGesehen(true);

		if (southStatus.contains("FINISH " + playerId)) {
			String[] parts2 = currentPosition.split(" ", 3);
			int a2 = Integer.parseInt(parts2[2]);
			if (southStatus.equals("FINISH " + playerId + " " + a2)) {
				setFormFinal(a2);
			}
		}

		// Westen
		map[posY][posX - 1].setStatus(westStatus);
		map[posY][posX - 1].setGesehen(true);

		if (westStatus.contains("FINISH " + playerId)) {
			String[] parts3 = currentPosition.split(" ", 3);
			int a3 = Integer.parseInt(parts3[2]);
			if (westStatus.equals("FINISH " + playerId + " " + a3)) {
				setFormFinal(a3);
			}
		}
		// Osten
		map[posY][posX + 1].setStatus(eastStatus);
		map[posY][posX + 1].setGesehen(true);

		if (eastStatus.contains("FINISH " + playerId)) {
			String[] parts4 = currentPosition.split(" ", 3);
			int a4 = Integer.parseInt(parts4[2]);

			if (eastStatus.equals("FINISH " + playerId + " " + a4)) {
				setFormFinal(a4);
			}
		}
	}

	/**
	 * berechnet den Weg des Bots und laesst in die Richtung, auf den Feldern auf
	 * denen er am wenigsten stand, gehen
	 * 
	 */
	public String berechneWeg() {

		String niedrigsterZug = "";

		if (map[posY][posX].getStatus().equals("FINISH " + playerId + getFormFinal())
				&& getFormCount() == getFormFinal()) {
			niedrigsterZug = "finish";
		} else if (map[posY][posX].getStatus().equals("FORM " + playerId + getFormCount())) {
			niedrigsterZug = "take";
		} else if (map[posY + 1][posX].getStatus().equals("FINISH " + playerId + getFormFinal())
				&& getFormCount() == getFormFinal()) {
			niedrigsterZug = "go south";
		} else if (map[posY][posX - 1].getStatus().equals("FINISH " + playerId + getFormFinal())
				&& getFormCount() == getFormFinal()) {
			niedrigsterZug = "go west";
		} else if (map[posY - 1][posX].getStatus().equals("FINISH " + playerId + getFormFinal())
				&& getFormCount() == getFormFinal()) {
			niedrigsterZug = "go north";
		} else if (map[posY][posX + 1].getStatus().equals("FINISH " + playerId + getFormFinal())
				&& getFormCount() == getFormFinal()) {
			niedrigsterZug = "go east";
		} else {
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
			}
		}

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

	public FieldBlyat[][] getMap() {
		return map;
	}

	public void setMap(FieldBlyat[][] map) {
		this.map = map;
	}

	public List<String> getMoeglicheZuege() {
		return moeglicheZuege;
	}

	public void setMoeglicheZuege(List<String> moeglicheZuege) {
		this.moeglicheZuege = moeglicheZuege;
	}

	public int getFormCount() {
		return formCount;
	}

	public void setFormCount(int formCount) {
		this.formCount = formCount;
	}

	public int getFormFinal() {
		return formFinal;
	}

	public void setFormFinal(int formFinal) {
		this.formFinal = formFinal;
	}

}
