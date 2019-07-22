package de.vitbund.vitmaze.players;

import java.util.ArrayList;
import java.util.List;

public class Karte {

	// Attribute
	private int sizeX;
	private int sizeY;
	private int posX;
	private int posY;
	private int playerId;
	private int formularZaehler = 1;
	private int formulareGesamt;
	private Feld[][] map;
	private List<String> moeglicheZuege = new ArrayList<>();

	// Konstruktor

	public Karte(int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;

		this.map = new Feld[sizeY][sizeX];

		for (int y = 0; y < this.sizeY; y++) {
			for (int x = 0; x < this.sizeX; x++) {
				map[y][x] = new Feld(null, false, 0);
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
				} else if (map[y][x].getStatus().equals("FINISH " + playerId + " " + getFormulareGesamt())) {
					System.err.print(" F ");
				} else if (map[y][x].getStatus().equals("FORM " + playerId + " " + formularZaehler)) {
					System.err.print(" " + formularZaehler + " ");
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
//	public void sucheWeg() {
//
//		List<String> moeglicheZuegeNew = new ArrayList<String>();
//
//		if (!map[posY + 1][posX].getStatus().equals("WALL")) {
//			moeglicheZuegeNew.add("go south");
//		}
//		if (!map[posY][posX - 1].getStatus().equals("WALL")) {
//			moeglicheZuegeNew.add("go west");
//		}
//		if (!map[posY - 1][posX].getStatus().equals("WALL")) {
//			moeglicheZuegeNew.add("go north");
//		}
//		if (!map[posY][posX + 1].getStatus().equals("WALL")) {
//			moeglicheZuegeNew.add("go east");
//		}
//
//		moeglicheZuege = moeglicheZuegeNew;
//	}

	
	
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
			setFormularZaehler(getFormularZaehler() + 1);
		}

	}

	
	
	public void leseFormulareGesamt(String currentPosition, String lastPosition, String northStatus, String eastStatus,
			String southStatus, String westStatus) {

		String test;

		if (formulareGesamt == 0) {
			if (currentPosition.length() > 8) {
				test = currentPosition.substring(0, 8);

				if (test.equals("FINISH " + playerId)) {
					String[] parts = currentPosition.split(" ", 3);
					int a = Integer.parseInt(parts[2]);
					setFormulareGesamt(a);
				}
			} else if (northStatus.length() > 8) {
				test = northStatus.substring(0, 8);

				if (test.equals("FINISH " + playerId)) {
					String[] parts = northStatus.split(" ", 3);
					int a = Integer.parseInt(parts[2]);
					setFormulareGesamt(a);
				}
			} else if (southStatus.length() > 8) {
				test = southStatus.substring(0, 8);

				if (test.equals("FINISH " + playerId)) {
					String[] parts = southStatus.split(" ", 3);
					int a = Integer.parseInt(parts[2]);
					setFormulareGesamt(a);
				}
			} else if (westStatus.length() > 8) {
				test = westStatus.substring(0, 8);

				if (test.equals("FINISH " + playerId)) {
					String[] parts = westStatus.split(" ", 3);
					int a = Integer.parseInt(parts[2]);
					setFormulareGesamt(a);
				}
			} else if (eastStatus.length() > 8) {
				test = eastStatus.substring(0, 8);

				if (test.equals("FINISH " + playerId)) {
					String[] parts = eastStatus.split(" ", 3);
					int a = Integer.parseInt(parts[2]);
					setFormulareGesamt(a);
				}
			}
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
	public String berechneWeg(int level) {

		String niedrigsterZug = "";

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

		switch (level) {
		case 1:
			if (getFormulareGesamt() != 0 && getFormularZaehler() > getFormulareGesamt()
					&& map[posY][posX].getStatus().equals("FINISH " + playerId + " " + formulareGesamt)) {
				niedrigsterZug = "finish";
			} else if (getFormulareGesamt() != 0 && getFormularZaehler() > getFormulareGesamt()
					&& map[posY + 1][posX].getStatus().equals("FINISH " + playerId + " " + formulareGesamt)) {
				niedrigsterZug = "go south";
			} else if (getFormulareGesamt() != 0 && getFormularZaehler() > getFormulareGesamt()
					&& map[posY][posX - 1].getStatus().equals("FINISH " + playerId + " " + formulareGesamt)) {
				niedrigsterZug = "go west";
			} else if (getFormulareGesamt() != 0 && getFormularZaehler() > getFormulareGesamt()
					&& map[posY - 1][posX].getStatus().equals("FINISH " + playerId + " " + formulareGesamt)) {
				niedrigsterZug = "go north";
			} else if (getFormulareGesamt() != 0 && getFormularZaehler() > getFormulareGesamt()
					&& map[posY][posX + 1].getStatus().equals("FINISH " + playerId + " " + formulareGesamt)) {
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

		default:
			if (getFormulareGesamt() != 0 && getFormularZaehler() > getFormulareGesamt()
					&& map[posY][posX].getStatus().equals("FINISH " + playerId + " " + formulareGesamt)) {
				niedrigsterZug = "finish";
			} else if (getFormulareGesamt() != 0 && getFormularZaehler() > getFormulareGesamt()
					&& map[posY + 1][posX].getStatus().equals("FINISH " + playerId + " " + formulareGesamt)) {
				niedrigsterZug = "go south";
			} else if (getFormulareGesamt() != 0 && getFormularZaehler() > getFormulareGesamt()
					&& map[posY][posX - 1].getStatus().equals("FINISH " + playerId + " " + formulareGesamt)) {
				niedrigsterZug = "go west";
			} else if (getFormulareGesamt() != 0 && getFormularZaehler() > getFormulareGesamt()
					&& map[posY - 1][posX].getStatus().equals("FINISH " + playerId + " " + formulareGesamt)) {
				niedrigsterZug = "go north";
			} else if (getFormulareGesamt() != 0 && getFormularZaehler() > getFormulareGesamt()
					&& map[posY][posX + 1].getStatus().equals("FINISH " + playerId + " " + formulareGesamt)) {
				niedrigsterZug = "go east";
			}

			else if (map[posY][posX].getStatus().equals("FORM " + playerId + " " + formularZaehler)) {
				niedrigsterZug = "take";
			} else if (map[posY + 1][posX].getStatus().equals("FORM " + playerId + " " + formularZaehler)) {
				niedrigsterZug = "go south";
			} else if (map[posY][posX - 1].getStatus().equals("FORM " + playerId + " " + formularZaehler)) {
				niedrigsterZug = "go west";
			} else if (map[posY - 1][posX].getStatus().equals("FORM " + playerId + " " + formularZaehler)) {
				niedrigsterZug = "go north";
			} else if (map[posY][posX + 1].getStatus().equals("FORM " + playerId + " " + formularZaehler)) {
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

	public int getFormularZaehler() {
		return formularZaehler;
	}

	public void setFormularZaehler(int formularZaehler) {
		this.formularZaehler = formularZaehler;
	}

	public int getFormulareGesamt() {
		return formulareGesamt;
	}

	public void setFormulareGesamt(int formulareGesamt) {
		this.formulareGesamt = formulareGesamt;
	}

	public Feld[][] getMap() {
		return map;
	}

	public void setMap(Feld[][] map) {
		this.map = map;
	}

	public List<String> getMoeglicheZuege() {
		return moeglicheZuege;
	}

	public void setMoeglicheZuege(List<String> moeglicheZuege) {
		this.moeglicheZuege = moeglicheZuege;
	}



}
