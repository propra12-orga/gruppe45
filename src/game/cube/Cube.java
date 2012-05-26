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
	final static public boolean HIDES_EXIT = true;
	final static public boolean DOES_NOT_HIDE_EXIT = false;

	boolean walkable;
	boolean collectable;
	boolean destroyable;
	boolean hidesexit;
	String cubename; 

	/**
	 * @param walkable
	 *            Ist der Wuerfel begehbar(true) oder nicht(false)
	 */
	Cube(boolean walkable, boolean collectable, boolean destroyable, boolean hidesexit, String cubename) {
		this.walkable = walkable;
		this.collectable = collectable;
		this.destroyable = destroyable;
		this.hidesexit = hidesexit;
		this.cubename = cubename;
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
	
	public boolean hidesExit() {
		return this.hidesexit;
	}
	
	public String getCubename() {
		return this.cubename;
	}
	
	public void sethidesExit(boolean hidesexit) {
		this.hidesexit = hidesexit;
	}

	public void change(Player player) {

	}

	public void change() {

	}

}
