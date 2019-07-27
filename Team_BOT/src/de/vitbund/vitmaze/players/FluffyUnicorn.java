package de.vitbund.vitmaze.players;

import java.util.Scanner;

/**
 * pink fluffy unicorns !! :D :D:D 
 *
 * Bot-Klasse mit main-Methode zum Ausfuheren der Methoden der verschiedenen Klassen
 *
 */
public class FluffyUnicorn {

/**
 * Hauptmethode zum Ausfuehren des Bots
 * 
 * @author Fabian Riede
 * 
 * @param args
 */
	public static void main(String[] args) {
			/**
			 * Scanner zum Auslesen der Standardeingabe, welche Initialisierungs- und
			 * Rundendaten liefert
			 */
			Scanner input = new Scanner(System.in);

			/**
			 * INIT - Auslesen der Initialdaten
			 * 1. Zeile: Maze Infos
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
			/**
			* Neue Instanz der Klassen Karte, Formular und Sheet wird angelegt und die Attribute werden ueberbegen 
			*/
			Karte map = new Karte(sizeX, sizeY);
			Formular form = new Formular(playerId);
			Sheet sheets = new Sheet(sheet);
			/**
			* Der Klasse Map werden die Koordinaten uebergeben und die PlayerId
			*/
			map.setPosX(startX);
			map.setPosY(startY);
			map.setPlayerId(playerId);


			/**
			 * Endlosschleife der Zugausfuehrung
			 */
			while (true) {
				// Rundeninformationen auslesen
				String lastActionsResult = input.nextLine();
				String currentCellStatus = input.nextLine();
				String northCellStatus = input.nextLine();
				String eastCellStatus = input.nextLine();
				String southCellStatus = input.nextLine();
				String westCellStatus = input.nextLine();
				/*
				 * lastActionResult wird uebergeben und die Methode sheetStack aus der Klasse Sheet wird aufgerufen
				 */
				sheets.sheetStack(lastActionsResult);

				/**
				 * lastActionResult wird uebergeben und die Methode botPosition aus der Klasse Karte wird aufgerufen
				 */
				map.botPosition(lastActionsResult);
			
				/**
				 * lastActionResult wird uebergeben und die Methode formOrder aus der Klasse Formular wird aufgerufen
				 */
				form.formOrder(lastActionsResult);
				/*
				 * Die Map (Karte des Maze) wird aktualisiert, anhand der Rundeninformationen
				 */
				map.updateUmfeld(currentCellStatus, lastActionsResult, northCellStatus, eastCellStatus, southCellStatus,
						westCellStatus);
				
				/**
				 * Rundeninformationen werden uebergeben und die Methode leseFormulareGesamt der Klasse Formluar wird aufgerufen 
				 */
				form.leseFormulareGesamt(currentCellStatus, lastActionsResult, northCellStatus, eastCellStatus, southCellStatus, westCellStatus);
				
				/*
				 * Ausgabe der Map durch Aufruf der Methode printMap aus der Klasse Karte
				 */
				map.printMap(form);
				
				/*
				 * eine neuer String wird erstellt und wird gleich dem Aufruf von berechneWeg aus der Klasse Karte gesetzt und bekommt den besten Zug (String aus der Methode)
				 */
				String naechsterZug = map.berechneWeg(level, form, sheets);

				/**
				 * Debug Information ausgeben
				 */
				System.err.println("Ergebnis Vorrunde: " + lastActionsResult);
				System.err.println("aktueller Status: " + currentCellStatus);
				System.err.println("Norden: " + northCellStatus);
				System.err.println("Osten: " + eastCellStatus);
				System.err.println("Sueden: " + southCellStatus);
				System.err.println("Westen: " + westCellStatus);
				
				/**
				 * Zugausgabe 
				 */
				System.out.println(naechsterZug);

			}
		}


}
