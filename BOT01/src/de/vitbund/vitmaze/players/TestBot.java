package de.vitbund.vitmaze.players;

import java.util.Scanner;

public class TestBot {

	public static void main(String[] args) {
		// TODO Automatisch generierter Methodenstub
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

		int[][] position = { { startX }, { startY } };

		int[][] maze = { { sizeX }, { sizeY } };

		Landkarte map = new Landkarte();

		// TURN (Wiederholung je Runde notwendig)
		while (input.hasNext()) {
			// Rundeninformationen auslesen
			String lastActionsResult = input.nextLine();
			String currentCellStatus = input.nextLine();
			String northCellStatus = input.nextLine();
			String eastCellStatus = input.nextLine();
			String southCellStatus = input.nextLine();
			String westCellStatus = input.nextLine();

			// Debug Information ausgeben (optional möglich)
			System.err.println("Ergebnis Vorrunde: " + lastActionsResult);
			System.err.println("aktueller Status: " + currentCellStatus);
			System.err.println("Norden: " + northCellStatus);
			System.err.println("Osten: " + eastCellStatus);
			System.err.println("Sueden: " + southCellStatus);
			System.err.println("Westen: " + westCellStatus);

			if (currentCellStatus.equals("FINISH " + playerId + " 0")) {
				System.out.println("FINISH");
				break;
			}

			if (lastActionsResult.equals("OK") || westCellStatus.equals("WALL") || westCellStatus.equals("FLOOR") || eastCellStatus.equals("FLOOR")) {
				System.out.println("go east");
			} 
			
			if (eastCellStatus.equals("WALL") || southCellStatus.equals("FLOOR")) {
				System.out.println("go south");
			}

//			else if (northCellStatus.equals("WALL") && southCellStatus.equals("WALL") && westCellStatus.equals("WALL")) {
//				System.out.println("go east");
//			}
//			else if (northCellStatus.equals("WALL") && westCellStatus.equals("WALL") && eastCellStatus.equals("WALL")) {
//				System.out.println("go south");
//			}
//			else if (southCellStatus.equals("WALL") && westCellStatus.equals("WALL") && eastCellStatus.equals("WALL")) {
//				System.out.println("go north");
//			}

//			System.out.println("position");

//			// Rundenaktion ausgeben
//			System.out.println("go east");
		}

		// Eingabe schliessen (letzte Aktion)
		input.close();
	}

}
