package de.vitbund.vitmaze.players;

import java.util.Scanner;

public class BotAnna {

	/**
	 * Hauptmethode zum Ausf�hren des Bots
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Scanner zum Auslesen der Standardeingabe, welche Initialisierungs- und
		// Rundendaten liefert
		Scanner input = new Scanner(System.in);

		// INIT - Auslesen der Initialdaten
		// 1. Zeile: Maze Infos
		int sizeX = input.nextInt(); // X-Groesse des Spielfeldes (Breite)
		int sizeY = input.nextInt(); // Y-Groesse des Spielfeldes (Hoehe)
		int level = input.nextInt(); // Level des Matches
		input.nextLine(); // Beenden der ersten Zeile
		// 2. Zeile: Player Infos
		int playerId = input.nextInt(); // id dieses Players / Bots
		int startX = input.nextInt(); // X-Koordinate der Startposition dieses Player
		int startY = input.nextInt(); // Y-Koordinate der Startposition dieses Players
		input.nextLine(); // Beenden der zweiten Zeile

		Landkarte map = new Landkarte(sizeX, sizeY);

		int x = startX;
		int y = startY;
		
		// TURN (Wiederholung je Runde notwendig)
		while (input.hasNext()) {
			// Rundeninformationen auslesen
			String lastActionsResult = input.nextLine();
			String currentCellStatus = input.nextLine();
			String northCellStatus = input.nextLine();
			String eastCellStatus = input.nextLine();
			String southCellStatus = input.nextLine();
			String westCellStatus = input.nextLine();

			//x und y aendern
			if (lastActionsResult.equals("OK WEST")) {
				x = x - 1;
			}
			else if (lastActionsResult.equals("OK EAST")) {
				x = x + 1;
			}
			else if (lastActionsResult.equals("OK SOUTH")) {
				y = y + 1;
			}
			else if (lastActionsResult.equals("OK NORTH")) {
				y = y - 1;
			}
			
			map.merkeCurrentFeld(currentCellStatus, y, x, playerId);
			map.merkeFeldEast(eastCellStatus, y, x, playerId);
			map.merkeFeldNord(northCellStatus, y, x, playerId);
			map.merkeFeldSued(southCellStatus, y, x, playerId);
			map.merkeFeldWest(westCellStatus, y, x, playerId);

			// Debug Information ausgeben (optional m�glich)
			System.err.println("Ergebnis Vorrunde: " + lastActionsResult);
			System.err.println("aktueller Status: " + currentCellStatus);
			System.err.println("Norden: " + northCellStatus);
			System.err.println("Osten: " + eastCellStatus);
			System.err.println("Sueden: " + southCellStatus);
			System.err.println("Westen: " + westCellStatus);

			map.printMap();
			
			// Rundenaktion ausgeben
			if (currentCellStatus.equals("FINISH " + playerId + " 0")) {
				System.out.println("finish");
				break;}
			else if (westCellStatus.equals("FLOOR")) {
			System.out.println("go west");}
			else {System.out.println("go south");}
		}

		// Eingabe schliessen (letzte Aktion)
		input.close();
	}

}
