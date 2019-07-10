package de.vitbund.vitmaze.players;

import java.util.ArrayList;
import java.util.List;

public class Landkarte {

	private int xKord;
	private int yKord;
	private String[][] map;
	private String ausgabe;
	private List<Integer> xCur = new ArrayList<>();
	private List<Integer> yCur = new ArrayList<>();

	public Landkarte(int xKord, int yKord) {
		this.xKord = xKord;
		this.yKord = yKord;

		map = new String[yKord][xKord];

		for (int y = 0; y < this.yKord; y++) {
			for (int x = 0; x < this.xKord; x++) {
				map[y][x] = " ? ";
			}
		}
	}

	/**
	 * Methode soll Labyrinth zeichnen indem der Bot durch die Map läuft
	 * 
	 * @param x
	 * @param y
	 */
	public void printMap() {
		for (int y = 0; y < yKord; y++) {
			ausgabe = "";
			for (int x = 0; x < xKord; x++) {
				ausgabe = ausgabe + map[y][x];
			}
			System.err.println(ausgabe);
		}

	}

	public void merkeFeldNord(String direction, int y, int x, int id) {
		if (map[y - 1][x].equals(" ? ")) {
			if (direction.equals("WALL")) {
				map[y - 1][x] = " # ";
			} else if (direction.equals("FLOOR")) {
				map[y - 1][x] = "   ";
			} else if (direction.equals("FINISH " + id + " 0")) {
				map[y - 1][x] = " F ";
			} else {
				map[y - 1][x] = " ! ";
			}
		}
	}

	public void merkeFeldSued(String direction, int y, int x, int id) {
		if (map[y + 1][x].equals(" ? ")) {
			if (direction.equals("WALL")) {
				map[y + 1][x] = " # ";
			} else if (direction.equals("FLOOR")) {
				map[y + 1][x] = "   ";
			} else if (direction.equals("FINISH " + id + " 0")) {
				map[y + 1][x] = " F ";
			} else {
				map[y + 1][x] = " ! ";
			}
		}
	}

	public void merkeFeldWest(String direction, int y, int x, int id) {
		if (map[y][x - 1].equals(" ? ")) {
			if (direction.equals("WALL")) {
				map[y][x - 1] = " # ";
			} else if (direction.equals("FLOOR")) {
				map[y][x - 1] = "   ";
			} else if (direction.equals("FINISH " + id + " 0")) {
				map[y][x - 1] = " F ";
			} else {
				map[y][x - 1] = " ! ";
			}
		}
	}

	public void merkeFeldEast(String direction, int y, int x, int id) {
		if (map[y][x + 1].equals(" ? ")) {
			if (direction.equals("WALL")) {
				map[y][x + 1] = " # ";
			} else if (direction.equals("FLOOR")) {
				map[y][x + 1] = "   ";
			} else if (direction.equals("FINISH " + id + " 0")) {
				map[y][x + 1] = " F ";
			} else {
				map[y][x + 1] = " ! ";
			}
		}
	}

	public void merkeLetztesFeld(int y, int x) {
		xCur.add(x);
		yCur.add(y);

	}

	/**
	 * gibt gezeichnete Map aus
	 * 
	 */
	public void mapAusgabe(int yKord, int xKord, String[][] map) {

	}

	/**
	 * Methode, um zu sehen was in 4 Feldern um den BOT liegt
	 */
	public void umsehen() {
		// TODO

	}

	public int getxKord() {
		return xKord;
	}

	public void setxKord(int xKord) {
		this.xKord = xKord;
	}

	public int getyKord() {
		return yKord;
	}

	public void setyKord(int yKord) {
		this.yKord = yKord;
	}

	public String[][] getMap() {
		return map;
	}

	public void setMap(String[][] map) {
		this.map = map;
	}
}
