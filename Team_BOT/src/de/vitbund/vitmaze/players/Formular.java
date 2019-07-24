package de.vitbund.vitmaze.players;

public class Formular {

	private int formularZaehler = 1;
	private int formulareGesamt;
	private int playerId;
	
	
	public Formular() {}
	
	
	public Formular(int formularZaehler, int formulareGesamt, int playerId) {
		this.formularZaehler = formularZaehler;
		this.formulareGesamt = formulareGesamt;
		this.playerId = playerId;
	}
	
	
	
	public void formOrder(String lastAction) {
		if (lastAction.equals("OK FORM")) {
			setFormularZaehler(getFormularZaehler() + 1);
		}

	}
	
	
	public int leseFormulareGesamt(String currentPosition, String lastPosition, String northStatus, String eastStatus,
			String southStatus, String westStatus) {

		String test;

		if (formulareGesamt == 0) {
			if (currentPosition.length() > 8) {
				test = currentPosition.substring(0, 8);

				if (test.equals("FINISH " + playerId)) {
					String[] parts = currentPosition.split(" ", 3);
					int a = Integer.parseInt(parts[2]);
					setFormulareGesamt(a);
				}
			} else if (northStatus.length() > 8) {
				test = northStatus.substring(0, 8);

				if (test.equals("FINISH " + playerId)) {
					String[] parts = northStatus.split(" ", 3);
					int a = Integer.parseInt(parts[2]);
					setFormulareGesamt(a);
				}
			} else if (southStatus.length() > 8) {
				test = southStatus.substring(0, 8);

				if (test.equals("FINISH " + playerId)) {
					String[] parts = southStatus.split(" ", 3);
					int a = Integer.parseInt(parts[2]);
					setFormulareGesamt(a);
				}
			} else if (westStatus.length() > 8) {
				test = westStatus.substring(0, 8);

				if (test.equals("FINISH " + playerId)) {
					String[] parts = westStatus.split(" ", 3);
					int a = Integer.parseInt(parts[2]);
					setFormulareGesamt(a);
				}
			} else if (eastStatus.length() > 8) {
				test = eastStatus.substring(0, 8);

				if (test.equals("FINISH " + playerId)) {
					String[] parts = eastStatus.split(" ", 3);
					int a = Integer.parseInt(parts[2]);
					setFormulareGesamt(a);
				}
			}
		}
		
		System.err.println(getFormulareGesamt());
		return getFormulareGesamt();
	}
	
	
	
	public int getFormularZaehler() {
		return formularZaehler;
	}
	public void setFormularZaehler(int formularZaehler) {
		this.formularZaehler = formularZaehler;
	}
	public int getFormulareGesamt() {
		return formulareGesamt;
	}
	public void setFormulareGesamt(int formulareGesamt) {
		this.formulareGesamt = formulareGesamt;
	}
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	
	
	
	
}
