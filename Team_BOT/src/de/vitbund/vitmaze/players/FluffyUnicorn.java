package de.vitbund.vitmaze.players;

import java.util.Scanner;

public class FluffyUnicorn {

/**
 * Hauptmethode zum Ausfuehren des Bots
 * 
 * @param args
 */
	public static void main(String[] args) {
			/**
			 * Scanner zum Auslesen der Standardeingabe, welche Initialisierungs- und
			 * Rundendaten liefert
			 * 
			 */
			Scanner input = new Scanner(System.in);

			/**
			 * INIT - Auslesen der Initialdaten
			 * 1. Zeile: Maze Infos
			 *
			 *
			 * X-Groesse des Spielfeldes (Breite)
			 */
			int sizeX = input.nextInt();
			/**
			 * Y-Groesse des Spielfeldes (Hoehe)
			 */
			int sizeY = input.nextInt();
			/**
			 * Level des Matches
			 */
			int level = input.nextInt();
			/**
			 * Beenden der ersten Zeile
			 */
			input.nextLine();
			/**
			 * 2. Zeile: Player Infos
			 *
			 * 
			 * id dieses Players / Bots
			 */
			int playerId = input.nextInt();
			/**
			 * X-Koordinate der Startposition dieses Player
			 */
			int startX = input.nextInt();
			/**
			 * Y-Koordinate der Startposition dieses Players
			 */
			int startY = input.nextInt();
			/**
			 * Anzahl der Sheets, die der Bot traegt
			 */
			int sheet = 0;
			/**
			 * Bedingung, um ab Lvl 5 Sheets aufzuheben
			 */
			if (level == 5) {
				sheet = input.nextInt();
				
			}
			/**
			 * Beenden der zweiten Zeile
			 */
			input.nextLine();
			
			Karte map = new Karte(sizeX, sizeY);
			Formular form = new Formular(playerId);
			Sheet sheets = new Sheet(sheet);
			
			map.setPosX(startX);
			map.setPosY(startY);
			map.setPlayerId(playerId);


			// TURN (Wiederholung je Runde notwendig)
			while (true) {
				// Rundeninformationen auslesen
				String lastActionsResult = input.nextLine();
				String currentCellStatus = input.nextLine();
				String northCellStatus = input.nextLine();
				String eastCellStatus = input.nextLine();
				String southCellStatus = input.nextLine();
				String westCellStatus = input.nextLine();
				
				sheets.sheetStack(lastActionsResult);

				map.botPosition(lastActionsResult);
			
				form.formOrder(lastActionsResult);
					
				map.updateUmfeld(currentCellStatus, lastActionsResult, northCellStatus, eastCellStatus, southCellStatus,
						westCellStatus);
				
				form.leseFormulareGesamt(currentCellStatus, lastActionsResult, northCellStatus, eastCellStatus, southCellStatus, westCellStatus);

				map.printMap(form);

				String naechsterZug = map.berechneWeg(level, form, sheets);

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
		}


}
