package game;

/**
 * Abstrakte Klasse von der alle anderen Würfel abgeleitet werden
 */
public abstract class Cube {
	boolean walkable;

	/**
	 * @param walkable
	 *            Ist der Würfel begehbar(true) oder nicht(false)
	 */
	Cube(boolean walkable) {
		this.walkable = walkable;
	}

	/**
	 * @return Gibt aus ob der Würfel begehbar ist
	 */
	public boolean isWalkable() {
		return this.walkable;
	}
}
