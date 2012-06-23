package game.cube;

public class CubeBomb extends Cube {

	/**
	 * Erzeugt einen Würfel der Klasse Bombe.
	 * 
	 * Bomben darf man nicht durchlaufen und nicht einsammeln (in Form von Items).
	 * Bomben dürfen jedoch (für Kettenreaktionen) zerstört werden.
	 */
	public CubeBomb() {
		super(Cube.IS_NOT_WALKABLE, Cube.IS_NOT_COLLECTABLE, Cube.IS_DESTROYABLE);
	}

}
