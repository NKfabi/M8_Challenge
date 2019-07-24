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
	private Feld[][] map;
	private Feld[][] grenzen;
	private List<String> moeglicheZuege = new ArrayList<>();
	private Formular form;

	// Konstruktor

	public Karte(int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;

		this.map = new Feld[sizeY][sizeX];
		this.grenzen = new Feld[sizeY][sizeX];

		for (int y = 0; y < this.sizeY; y++) {
			for (int x = 0; x < this.sizeX; x++) {
				map[y][x] = new Feld(null, false, 0);
			}
		}

//		int n = 0;
//		while (n == 0) {
//			for (int x = 0; x < this.sizeX; x++) {
//				grenzen[n][x] = new Feld(null, false, 0);
//			}
//			n++;
//		}
//
//		int s = sizeY - 1;
//		while (s == sizeY - 1) {
//			for (int x = 0; x < this.sizeX; x++) {
//				grenzen[s][x] = new Feld(null, false, 0);
//			}
//			s++;
//		}
//
//		int w = 0;
//		while (w == 0) {
//			for (int y = 0; y < this.sizeY; y++) {
//				grenzen[y][w] = new Feld(null, false, 0);
//			}
//			w++;
//		}
//
//		int e = sizeX - 1;
//		while (e == sizeX - 1) {
//			for (int y = 0; y < this.sizeY; y++) {
//				grenzen[y][e] = new Feld(null, false, 0);
//			}
//			e++;
//		}
	}

	/**
	 * Methode zeichnet Map auf Basis der Groesse des Labyrinths
	 * 
	 * @param x
	 * @param y
	 */
	public void printMap(Formular form) {
		for (int y = 0; y < sizeY; y++) {
			for (int x = 0; x < sizeX; x++) {
				if (map[y][x].getStatus() == null) {
					System.err.print(" ? ");
				} else if (map[y][x].getStatus().equals("FINISH " + playerId + " " + form.getFormulareGesamt())) {
					System.err.print(" F ");
				} else if (map[y][x].getStatus().equals("FORM " + playerId + " " + form.getFormularZaehler())) {
					System.err.print(" " + form.getFormularZaehler() + " ");
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

//	public void printGrenzen() {
//		int n = 0;
//		int s = sizeY - 1;
//		int w = 0;
//		int e = sizeX - 1;
//		
//		while (n == 0 && s == sizeY - 1 && w == 0 && e == sizeX - 1) {
//			for (int x = 0; x < this.sizeX; x++) {
//				if (grenzen[n][x].getStatus() == null) {
//					System.err.print(" ? ");
//				}
//			}
//			n++;
//			System.err.println();
//			for (int x = 0; x < this.sizeX; x++) {
//				if (grenzen[s][x].getStatus() == null) {
//					System.err.print(" ? ");
//				}
//			}
//			s++;
//			System.err.println();
//			for (int y = 0; y < this.sizeY; y++) {
//				if (grenzen[y][w].getStatus() == null) {
//					System.err.print(" ? ");
//				}
//			}
//			w++;
//			
//			System.err.println();
//			for (int y = 0; y < this.sizeY; y++) {
//				if (grenzen[y][e].getStatus() == null) {
//					System.err.print(" ? ");
//				}
//			}
//			e++;
//			
//			System.err.println();
//		}
//
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
	public String berechneWeg(int level, Formular form) {

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
			if (map[posY][posX].getStatus().equals("FINISH " + playerId + " " + form.getFormulareGesamt())) {
				niedrigsterZug = "finish";
			} else if (map[posY + 1][posX].getStatus().equals("FINISH " + playerId + " " + form.getFormulareGesamt())) {
				niedrigsterZug = "go south";
			} else if (map[posY][posX - 1].getStatus().equals("FINISH " + playerId + " " + form.getFormulareGesamt())) {
				niedrigsterZug = "go west";
			} else if (map[posY - 1][posX].getStatus().equals("FINISH " + playerId + " " + form.getFormulareGesamt())) {
				niedrigsterZug = "go north";
			} else if (map[posY][posX + 1].getStatus().equals("FINISH " + playerId + " " + form.getFormulareGesamt())) {
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

			if (form.getFormulareGesamt() != 0 && form.getFormularZaehler() > form.getFormulareGesamt()
					&& map[posY][posX].getStatus().equals("FINISH " + playerId + " " + form.getFormulareGesamt())) {
				niedrigsterZug = "finish";
			} else if (form.getFormulareGesamt() != 0 && form.getFormularZaehler() > form.getFormulareGesamt()
					&& map[posY + 1][posX].getStatus().equals("FINISH " + playerId + " " + form.getFormulareGesamt())) {
				niedrigsterZug = "go south";
			} else if (form.getFormulareGesamt() != 0 && form.getFormularZaehler() > form.getFormulareGesamt()
					&& map[posY][posX - 1].getStatus().equals("FINISH " + playerId + " " + form.getFormulareGesamt())) {
				niedrigsterZug = "go west";
			} else if (form.getFormulareGesamt() != 0 && form.getFormularZaehler() > form.getFormulareGesamt()
					&& map[posY - 1][posX].getStatus().equals("FINISH " + playerId + " " + form.getFormulareGesamt())) {
				niedrigsterZug = "go north";
			} else if (form.getFormulareGesamt() != 0 && form.getFormularZaehler() > form.getFormulareGesamt()
					&& map[posY][posX + 1].getStatus().equals("FINISH " + playerId + " " + form.getFormulareGesamt())) {
				niedrigsterZug = "go east";
			}

			else if (map[posY][posX].getStatus().equals("FORM " + playerId + " " + form.getFormularZaehler())) {
				niedrigsterZug = "take";
			} else if (map[posY + 1][posX].getStatus().equals("FORM " + playerId + " " + form.getFormularZaehler())) {
				niedrigsterZug = "go south";
			} else if (map[posY][posX - 1].getStatus().equals("FORM " + playerId + " " + form.getFormularZaehler())) {
				niedrigsterZug = "go west";
			} else if (map[posY - 1][posX].getStatus().equals("FORM " + playerId + " " + form.getFormularZaehler())) {
				niedrigsterZug = "go north";
			} else if (map[posY][posX + 1].getStatus().equals("FORM " + playerId + " " + form.getFormularZaehler())) {
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

	public Formular getForm() {
		return form;
	}

	public void setForm(Formular form) {
		this.form = form;
	}

	public Feld[][] getGrenzen() {
		return grenzen;
	}

	public void setGrenzen(Feld[][] grenzen) {
		this.grenzen = grenzen;
	}

}
