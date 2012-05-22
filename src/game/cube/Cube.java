package game.cube;

import game.Player;

/**
 * Abstrakte Klasse von der alle anderen Würfel abgeleitet werden
 */
public abstract class Cube {
	final static public boolean IS_COLECTABLE = true;
	final static public boolean IS_NOT_COLECTABLE = false;
	final static public boolean IS_WALKABLE = true;
	final static public boolean IS_NOT_WALKABLE = false;

	boolean walkable;
	boolean colectable;

	/**
	 * @param walkable
	 *            Ist der Würfel begehbar(true) oder nicht(false)
	 */
	Cube(boolean walkable, boolean colectable) {
		this.walkable = walkable;
		this.colectable = colectable;
	}

	/**
	 * @return Gibt aus ob der Würfel begehbar ist
	 */
	public boolean isWalkable() {
		return this.walkable;
	}

	public boolean isColectable() {
		return this.colectable;
	}

	public void change(Player player) {

	}

	public void change() {

	}

}
