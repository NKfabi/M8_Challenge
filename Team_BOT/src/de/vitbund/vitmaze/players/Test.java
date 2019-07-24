package de.vitbund.vitmaze.players;

public class Test {

	public static void main(String[] args) {
		// TODO Automatisch generierter Methodenstub

//		Landkarte map = new Landkarte(10, 10);
//		
//
//		map.printMap();
		
		int playerId = 1;
		String test = "FINISH " + playerId;
		
		System.out.println(test.substring(0, 8));
		
		System.out.println(playerId+1);
		
		Formular form = new Formular();
		
		System.out.println(form.getFormularZaehler());
	}

}
