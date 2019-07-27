package de.vitbund.vitmaze.players;

public class Maze {
	private int sizeX;
	private int sizeY;
	private int posX = 0;
	private int posY = 0;
	private FeldFuerMap[][] map;

//Konstruktor
	public Maze(int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;

		map = new FeldFuerMap[sizeX][sizeY];

		for (int i = 0; i < this.sizeY; i++) {
			for (int j = 0; j < this.sizeX; j++) {
				map[i][j] = new FeldFuerMap(i, j, "?", 1234);
			}
		}
	}
	
	public void printMap() {
		for (int i = 0; i < sizeY; i++) {
			for (int j = 0; j < sizeX; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public void berechneKosten() {
		
		
	}

	//Getter und Setter	
	
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

	public FeldFuerMap[][] getMap() {
		return map;
	}

	public void setMap(FeldFuerMap[][] map) {
		this.map = map;
	}
}