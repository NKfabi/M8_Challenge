package de.vitbund.vitmaze.players;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasse Karte mit saemtlichen Berechnungen und der Ausgabe der Karte mit Sys.err
 * Wir haben uns gegen das Kicken entschieden, weil es einen unnoetigen Zug kostet und diesen moechten wir nicht verschwenden
 * Auch gegen das Ausweichen haben wir uns entschieden, da es ggf. zu Fehlern kommen koennte in kleinen Maps und ggf. noch mehr Zuege kosten wuerde,
 * als das Gespraech ueber sich ergehen zu lassen
 *   
 * @author Fabian Riede
 *
 */
public class Karte {

	/**
	 * Attribute
	 * sizeX und sizeY geben die Informationen wie gross das Maze ist
	 * durch posX und posY wird die Startposition des Bots bekanntgegeben und die PlayerId
	 * das Feld-Array map wird gebraucht um die Map mit Feldern zu befuellen
	 * in der String Array-List werden die moeglichen Zuege des Bots zwischengespeichert 
	 * und Sheet uebergibt die sheets
	 * 
	 */
	private int sizeX;
	private int sizeY;
	private int posX;
	private int posY;
	private int playerId;
	private Feld[][] map;
	private List<String> moeglicheZuege = new ArrayList<>();
	private Formular form;
	private Sheet sheets;

	/**
	 * Konstruktor
	 * 
	 * 
	 * @param sizeX
	 * @param sizeY
	 * 
	 * generiert in der for-Schleife Standardwerte für die Koordinaten der Map
	 */
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
	 * Methode zeichnet Map auf Basis der Groesse des Labyrinths und beschriftet unbekannte Felder mit einem " ? "
	 * an der Stelle Finisch (Sachbearbeiter) wird ein Z für Ziel eingesetzt, 
	 * fuer Formulare wird ein "F" eingefuegt, fuer einen Gang "  ", fuer eine Wand " W ", fuer ein Sheet " S " und fuer 
	 * Gegner ein " ! "
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
					System.err.print(" Z ");
				} else if (map[y][x].getStatus().equals("FORM " + playerId + " " + form.getFormularZaehler())) {
					System.err.print(" F ");
				} else if (map[y][x].getStatus().equals("FLOOR")) {
					System.err.print("   ");
				} else if (map[y][x].getStatus().equals("WALL")) {
					System.err.print(" W ");
				} else if (map[y][x].getStatus().equals("SHEET")) {
					System.err.print(" S ");
				} else {
					System.err.print(" ! ");
				}
			}
			System.err.println();
		}

	}

	/**
	 * updatet die Spielerposition je nach letztem Zug
	 * 
	 * @param lastAction
	 */
	public void botPosition(String lastAction) {
		if (lastAction.equals("OK")) {
		} else if (lastAction.equals("OK WEST") && posX == 0) {
			posX = sizeX - 1;
		} else if (lastAction.equals("OK EAST") && posX == sizeX - 1) {
			posX = 0;
		} else if (lastAction.equals("OK SOUTH") && posY == sizeY - 1) {
			posY = 0;
		} else if (lastAction.equals("OK NORTH") && posY == 0) {
			posY = sizeY - 1;
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
	 * hier werden die Grenzen im Norden ueberprueft um zu schauen, ob eine Wand das Maze abgrenzt oder nicht, 
	 * wenn nicht soll der Bot von oben nach unten springen
	 * 
	 * @return
	 */
	public int pruefeGrenzeNorden() {
		int zahl = 0;

		// Norden
		if (posY == 0) {
			zahl = sizeY - 1;
		} else if (posY != 0) {
			zahl = posY - 1;
		}

		return zahl;
	}

	/**
	 * hier werden die Grenzen im Sueden ueberprueft um zu schauen, ob eine Wand das Maze abgrenzt oder nicht, 
	 * wenn nicht soll der Bot von unten nach oben springen
	 * 
	 * @return
	 */
	public int pruefeGrenzeSueden() {
		int zahl = 0;

		// Sueden
		if (posY == sizeY - 1) {
			zahl = 0;
		} else if (posY != sizeY - 1) {
			zahl = posY + 1;
		}

		return zahl;
	}

	/**
	 * hier werden die Grenzen im Westen ueberprueft um zu schauen, ob eine Wand das Maze abgrenzt oder nicht, 
	 * wenn nicht soll der Bot von links nach rechts springen
	 * 
	 * @return
	 */
	public int pruefeGrenzeWesten() {
		int zahl = 0;

		// Westen
		if (posX == 0) {
			zahl = sizeX - 1;
		} else if (posX != 0) {
			zahl = posX - 1;
		}

		return zahl;
	}

	/**
	 * hier werden die Grenzen im Osten ueberprueft um zu schauen, ob eine Wand das Maze abgrenzt oder nicht, 
	 * wenn nicht soll der Bot von rechts nach links springen
	 * 
	 * @return
	 */
	public int pruefeGrenzeOsten() {
		int zahl = 0;

		// Osten
		if (posX == sizeX - 1) {
			zahl = 0;
		} else if (posX != sizeX - 1) {
			zahl = posX + 1;
		}

		return zahl;
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
	 */
	public void updateUmfeld(String currentPosition, String lastPosition, String northStatus, String eastStatus,
			String southStatus, String westStatus) {
		// aktuelles Feld
		map[posY][posX].setStatus(currentPosition);
		map[posY][posX].setGesehen(true);
		map[posY][posX].setZaehlerBetreten(map[posY][posX].getZaehlerBetreten() + 1);

		// Norden
		map[pruefeGrenzeNorden()][posX].setStatus(northStatus);
		map[pruefeGrenzeNorden()][posX].setGesehen(true);

		// Sueden
		map[pruefeGrenzeSueden()][posX].setStatus(southStatus);
		map[pruefeGrenzeSueden()][posX].setGesehen(true);

		// Westen
		map[posY][pruefeGrenzeWesten()].setStatus(westStatus);
		map[posY][pruefeGrenzeWesten()].setGesehen(true);

		// Osten
		map[posY][pruefeGrenzeOsten()].setStatus(eastStatus);
		map[posY][pruefeGrenzeOsten()].setGesehen(true);

	}

	/**
	 * berechnet den Weg des Bots und gibt ihm das Feld mit dem niedrigsten Wert fuer zaehlerBetreten wieder aus
	 * des Weiteren schaut der Bot, dass er zunaechst in Felder geht, in denen ein " ? " in den umliegenden Feldern liegt und dass er natuerlich nicht in
	 * eine Wand laeuft :-)
	 * Auch wird eine Fallunterscheidung der Level getroffen anhand des Switch Case, weil der Bot je nach Level unterschiedliche Funktionen/Aktionen hat die
	 * er ausfuehren darf bzw. muss 
	 * 
	 * Wir haben uns gezielt dagegen entschieden den Bot kicken zu lassen und den Gegnern auszuweichen, wir sind offen fuer Gespraeche mit anderen Bots 
	 * (Kaffeepausen sind sehr wichtig!) und kicken moechten wir auch nicht fuer mehr Naechstenliebe :-)
	 * 
	 * 
	 * @param level
	 * @param form
	 * @param sheets
	 * @return
	 */
	public String berechneWeg(int level, Formular form, Sheet sheets) {

		String niedrigsterZug = "";

		List<String> moeglicheZuegeNew = new ArrayList<String>();

		if (!map[posY][posX].getStatus().equals("WALL")
				&& !map[pruefeGrenzeSueden()][posX].getStatus().equals("WALL")) {
			moeglicheZuegeNew.add("go south");
		}
		if (!map[posY][posX].getStatus().equals("WALL")
				&& !map[posY][pruefeGrenzeWesten()].getStatus().equals("WALL")) {
			moeglicheZuegeNew.add("go west");
		}
		if (!map[posY][posX].getStatus().equals("WALL")
				&& !map[pruefeGrenzeNorden()][posX].getStatus().equals("WALL")) {
			moeglicheZuegeNew.add("go north");
		}
		if (!map[posY][posX].getStatus().equals("WALL") && !map[posY][pruefeGrenzeOsten()].getStatus().equals("WALL")) {
			moeglicheZuegeNew.add("go east");
		}

		moeglicheZuege = moeglicheZuegeNew;

		switch (level) {
		case 1:
			if (map[posY][posX].getStatus().equals("FINISH " + playerId + " " + form.getFormulareGesamt())) {
				niedrigsterZug = "finish";
			} else if (map[pruefeGrenzeSueden()][posX].getStatus()
					.equals("FINISH " + playerId + " " + form.getFormulareGesamt())) {
				niedrigsterZug = "go south";
			} else if (map[posY][pruefeGrenzeWesten()].getStatus()
					.equals("FINISH " + playerId + " " + form.getFormulareGesamt())) {
				niedrigsterZug = "go west";
			} else if (map[pruefeGrenzeNorden()][posX].getStatus()
					.equals("FINISH " + playerId + " " + form.getFormulareGesamt())) {
				niedrigsterZug = "go north";
			} else if (map[posY][pruefeGrenzeOsten()].getStatus()
					.equals("FINISH " + playerId + " " + form.getFormulareGesamt())) {
				niedrigsterZug = "go east";
			} else {
				int niedrigsteAnzahl = 999;

				niedrigsterZug = moeglicheZuege.get(0);

				for (String zug : moeglicheZuege) {
					switch (zug) {

					case "go south":
						if (map[pruefeGrenzeSueden()][posX].getZaehlerBetreten() < niedrigsteAnzahl) {
							niedrigsterZug = "go south";
							niedrigsteAnzahl = map[pruefeGrenzeSueden()][posX].getZaehlerBetreten();
						}
						break;

					case "go west":
						if (map[posY][pruefeGrenzeWesten()].getZaehlerBetreten() < niedrigsteAnzahl) {
							niedrigsterZug = "go west";
							niedrigsteAnzahl = map[posY][pruefeGrenzeWesten()].getZaehlerBetreten();
						}
						break;

					case "go north":
						if (map[pruefeGrenzeNorden()][posX].getZaehlerBetreten() < niedrigsteAnzahl) {
							niedrigsterZug = "go north";
							niedrigsteAnzahl = map[pruefeGrenzeNorden()][posX].getZaehlerBetreten();
						}
						break;

					case "go east":
						if (map[posY][pruefeGrenzeOsten()].getZaehlerBetreten() < niedrigsteAnzahl) {
							niedrigsterZug = "go east";
							niedrigsteAnzahl = map[posY][pruefeGrenzeOsten()].getZaehlerBetreten();
						}
						break;
					}
				}
			}
			return niedrigsterZug;

		default:

			if (map[posY][posX].getStatus().contains("SHEET")) {
				niedrigsterZug = "take";
			} else if (form.getFormulareGesamt() != 0 && form.getFormularZaehler() > form.getFormulareGesamt()
					&& map[posY][posX].getStatus().equals("FINISH " + playerId + " " + form.getFormulareGesamt())) {
				niedrigsterZug = "finish";
			} else if (form.getFormulareGesamt() != 0 && form.getFormularZaehler() > form.getFormulareGesamt()
					&& map[pruefeGrenzeSueden()][posX].getStatus()
							.equals("FINISH " + playerId + " " + form.getFormulareGesamt())) {
				niedrigsterZug = "go south";
			} else if (form.getFormulareGesamt() != 0 && form.getFormularZaehler() > form.getFormulareGesamt()
					&& map[posY][pruefeGrenzeWesten()].getStatus()
							.equals("FINISH " + playerId + " " + form.getFormulareGesamt())) {
				niedrigsterZug = "go west";
			} else if (form.getFormulareGesamt() != 0 && form.getFormularZaehler() > form.getFormulareGesamt()
					&& map[pruefeGrenzeNorden()][posX].getStatus()
							.equals("FINISH " + playerId + " " + form.getFormulareGesamt())) {
				niedrigsterZug = "go north";
			} else if (form.getFormulareGesamt() != 0 && form.getFormularZaehler() > form.getFormulareGesamt()
					&& map[posY][pruefeGrenzeOsten()].getStatus()
							.equals("FINISH " + playerId + " " + form.getFormulareGesamt())) {
				niedrigsterZug = "go east";
			}

			else if (map[posY][posX].getStatus().equals("FORM " + playerId + " " + form.getFormularZaehler())) {
				niedrigsterZug = "take";
			} else if (map[pruefeGrenzeSueden()][posX].getStatus()
					.equals("FORM " + playerId + " " + form.getFormularZaehler())) {
				niedrigsterZug = "go south";
			} else if (map[posY][pruefeGrenzeWesten()].getStatus()
					.equals("FORM " + playerId + " " + form.getFormularZaehler())) {
				niedrigsterZug = "go west";
			} else if (map[pruefeGrenzeNorden()][posX].getStatus()
					.equals("FORM " + playerId + " " + form.getFormularZaehler())) {
				niedrigsterZug = "go north";
			} else if (map[posY][pruefeGrenzeOsten()].getStatus()
					.equals("FORM " + playerId + " " + form.getFormularZaehler())) {
				niedrigsterZug = "go east";
			} else {
				int niedrigsteAnzahl = 999;

				niedrigsterZug = moeglicheZuege.get(0);

				for (String zug : moeglicheZuege) {
					switch (zug) {

					case "go south":
						if (map[pruefeGrenzeSueden()][posX].getZaehlerBetreten() < niedrigsteAnzahl) {
							niedrigsterZug = "go south";
							niedrigsteAnzahl = map[pruefeGrenzeSueden()][posX].getZaehlerBetreten();
						}
						break;

					case "go west":
						if (map[posY][pruefeGrenzeWesten()].getZaehlerBetreten() < niedrigsteAnzahl) {
							niedrigsterZug = "go west";
							niedrigsteAnzahl = map[posY][pruefeGrenzeWesten()].getZaehlerBetreten();
						}
						break;

					case "go north":
						if (map[pruefeGrenzeNorden()][posX].getZaehlerBetreten() < niedrigsteAnzahl) {
							niedrigsterZug = "go north";
							niedrigsteAnzahl = map[pruefeGrenzeNorden()][posX].getZaehlerBetreten();
						}
						break;

					case "go east":
						if (map[posY][pruefeGrenzeOsten()].getZaehlerBetreten() < niedrigsteAnzahl) {
							niedrigsterZug = "go east";
							niedrigsteAnzahl = map[posY][pruefeGrenzeOsten()].getZaehlerBetreten();
						}
						break;
					}
				}
			}
			return niedrigsterZug;
		}

	}

	/**
	 * Getter und Setter
	 * 
	 * 
	 */
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

	public Sheet getSheets() {
		return sheets;
	}

	public void setSheets(Sheet sheets) {
		this.sheets = sheets;
	}

}
