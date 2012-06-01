package game.cube;

/**
 * Freies Feld
 */
public class CubeEmpty extends Cube {

	public CubeEmpty() {
		super(Cube.IS_WALKABLE, Cube.IS_NOT_COLLECTABLE, Cube.IS_DESTROYABLE);
	}
}
