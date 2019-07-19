package de.vitbund.vitmaze.players;

import java.util.ArrayList;
import java.util.List;

public class MapLvl2 {

	// Attribute
	private int sizeX;
	private int sizeY;
	private int posX = 0;
	private int posY = 0;
	private int playerId;
	private int formCount = 1;
	private int formFinal;
	private FieldLvl2[][] map;
	private List<String> moeglicheZuege = new ArrayList<>();

	// Konstruktor
	public MapLvl2(int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;

		map = new FieldLvl2[sizeY][sizeX];

		for (int y = 0; y < this.sizeY; y++) {
			for (int x = 0; x < this.sizeX; x++) {
				map[y][x] = new FieldLvl2(y, x, null, false, 0, 4242);

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
				} else if (map[y][x].getStatus().equals("FINISH " + playerId + " " + getFormFinal())) {
					System.err.print(" F ");
				} else if (map[y][x].getStatus().equals("FORM " + playerId + " " + formCount)) {
					System.err.print(" " + formCount + " ");
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
		if (lastAction.equals("OK FORM")) {
			setFormCount(getFormCount() + 1);
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

		String test;
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

		if (formFinal == 0) {
			if (currentPosition.length() > 8) {
				test = currentPosition.substring(0, 8);

				if (test.equals("FINISH " + playerId)) {
					String[] parts = currentPosition.split(" ", 3);
					int a = Integer.parseInt(parts[2]);
					setFormFinal(a);
				}
			} else if (northStatus.length() > 8) {
				test = northStatus.substring(0, 8);

				if (test.equals("FINISH " + playerId)) {
					String[] parts = northStatus.split(" ", 3);
					int a = Integer.parseInt(parts[2]);
					setFormFinal(a);
				}
			} else if (southStatus.length() > 8) {
				test = southStatus.substring(0, 8);

				if (test.equals("FINISH " + playerId)) {
					String[] parts = southStatus.split(" ", 3);
					int a = Integer.parseInt(parts[2]);
					setFormFinal(a);
				}
			} else if (westStatus.length() > 8) {
				test = westStatus.substring(0, 8);

				if (test.equals("FINISH " + playerId)) {
					String[] parts = westStatus.split(" ", 3);
					int a = Integer.parseInt(parts[2]);
					setFormFinal(a);
				}
			} else if (eastStatus.length() > 8) {
				test = eastStatus.substring(0, 8);

				if (test.equals("FINISH " + playerId)) {
					String[] parts = eastStatus.split(" ", 3);
					int a = Integer.parseInt(parts[2]);
					setFormFinal(a);
				}
			}
		}

	}

	/**
	 * berechnet den Weg des Bots und laesst in die Richtung, auf den Feldern auf
	 * denen er am wenigsten stand, gehen
	 * 
	 */
	public String berechneWeg() {

		System.err.println("Anzahl Formulare: " + getFormFinal());
		System.err.println("Formularcounter: " + getFormCount());
		System.err.println(formCount + 1);

		String niedrigsterZug = "";

		if (getFormFinal() != 0 && getFormCount() > getFormFinal()
				&& map[posY][posX].getStatus().equals("FINISH " + playerId + " " + formFinal)) {
			niedrigsterZug = "finish";
		} else if (getFormFinal() != 0 && getFormCount() > getFormFinal()
				&& map[posY + 1][posX].getStatus().equals("FINISH " + playerId + " " + formFinal)) {
			niedrigsterZug = "go south";
		} else if (getFormFinal() != 0 && getFormCount() > getFormFinal()
				&& map[posY][posX - 1].getStatus().equals("FINISH " + playerId + " " + formFinal)) {
			niedrigsterZug = "go west";
		} else if (getFormFinal() != 0 && getFormCount() > getFormFinal()
				&& map[posY - 1][posX].getStatus().equals("FINISH " + playerId + " " + formFinal)) {
			niedrigsterZug = "go north";
		} else if (getFormFinal() != 0 && getFormCount() > getFormFinal()
				&& map[posY][posX + 1].getStatus().equals("FINISH " + playerId + " " + formFinal)) {
			niedrigsterZug = "go east";
		}

		else if (map[posY][posX].getStatus().equals("FORM " + playerId + " " + formCount)) {
			niedrigsterZug = "take";
		} else if (map[posY + 1][posX].getStatus().equals("FORM " + playerId + " " + formCount)) {
			niedrigsterZug = "go south";
		} else if (map[posY][posX - 1].getStatus().equals("FORM " + playerId + " " + formCount)) {
			niedrigsterZug = "go west";
		} else if (map[posY - 1][posX].getStatus().equals("FORM " + playerId + " " + formCount)) {
			niedrigsterZug = "go north";
		} else if (map[posY][posX + 1].getStatus().equals("FORM " + playerId + " " + formCount)) {
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

	public FieldLvl2[][] getMap() {
		return map;
	}

	public void setMap(FieldLvl2[][] map) {
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
