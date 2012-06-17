package control;

import game.Player;

/**
 * 
 * Oberklasse der Steuerung
 * 
 */
public abstract class Control {
	Player player;

	public Control(Player player) {
		this.player = player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}
