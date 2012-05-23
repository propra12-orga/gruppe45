package game.cube;

import game.Player;

/**
 * Abstrakte Klasse von der alle anderen Würfel abgeleitet werden
 */
public abstract class Cube {
	final static public boolean IS_COLLECTABLE = true;
	final static public boolean IS_NOT_COLLECTABLE = false;
	final static public boolean IS_WALKABLE = true;
	final static public boolean IS_NOT_WALKABLE = false;
	final static public boolean IS_DESTROYABLE = true;
	final static public boolean IS_NOT_DESTROYABLE = false;
	

	boolean walkable;
	boolean collectable;
	boolean destroyable;

	/**
	 * @param walkable
	 *            Ist der Wuerfel begehbar(true) oder nicht(false)
	 */
	Cube(boolean walkable, boolean collectable, boolean destroyable) {
		this.walkable = walkable;
		this.collectable = collectable;
		this.destroyable = destroyable;
	}

	/**
	 * @return Gibt aus ob der Wuerfel begehbar ist
	 */
	public boolean isWalkable() {
		return this.walkable;
	}

	public boolean isCollectable() {
		return this.collectable;
	}
	
	public boolean isDestroyable() {
		return this.destroyable;
	}

	public void change(Player player) {

	}

	public void change() {

	}

}
