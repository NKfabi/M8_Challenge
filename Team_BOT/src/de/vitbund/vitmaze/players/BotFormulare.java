package de.vitbund.vitmaze.players;

import java.util.Scanner;

public class BotFormulare {

	/**
	 * Hauptmethode zum Ausführen des Bots
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
		
		MapFormulare map = new MapFormulare(sizeX, sizeY);
		
		map.setPosX(startX);
		map.setPosY(startY);
		map.setPlayerId(playerId);


		// TURN (Wiederholung je Runde notwendig)
		while (input.hasNext()) {
			// Rundeninformationen auslesen
			String lastActionsResult = input.nextLine();
			String currentCellStatus = input.nextLine();
			String northCellStatus = input.nextLine();
			String eastCellStatus = input.nextLine();
			String southCellStatus = input.nextLine();
			String westCellStatus = input.nextLine();

			
			map.botPosition(lastActionsResult);
			
			map.formOrder(lastActionsResult);
			
			map.updateUmfeld(currentCellStatus, lastActionsResult, northCellStatus, eastCellStatus, southCellStatus,
					westCellStatus);
			
			map.printMap();
			
			map.sucheWeg();

			String naechsterZug = map.berechneWeg();

			// Debug Information ausgeben (optional möglich)
			System.err.println("Ergebnis Vorrunde: " + lastActionsResult);
			System.err.println("aktueller Status: " + currentCellStatus);
			System.err.println("Norden: " + northCellStatus);
			System.err.println("Osten: " + eastCellStatus);
			System.err.println("Sueden: " + southCellStatus);
			System.err.println("Westen: " + westCellStatus);
			
			// Zugausgabe
			System.out.println(naechsterZug);

		}

		// Eingabe schliessen (letzte Aktion)
		input.close();
	}

}
