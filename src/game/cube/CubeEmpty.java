package game.cube;

/**
 * Leere Felder
 * 
 * Diese sind begehbar und zerstörbar.
 * Man kann leere Felder nicht einsammeln.
 */
public class CubeEmpty extends Cube {

	public CubeEmpty() {
		super(Cube.IS_WALKABLE, Cube.IS_NOT_COLLECTABLE, Cube.IS_DESTROYABLE);
	}
}
