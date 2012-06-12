package control;

import game.Level;
import game.Player;


/**
 * 
 * Oberklasse der Steuerung
 *
 */
public abstract class Control {
	Player player;
	Level level;

	public Control(Player player, Level level) {
		this.player = player;
		this.level = level;
	}

}
