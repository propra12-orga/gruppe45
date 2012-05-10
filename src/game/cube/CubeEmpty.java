package game.cube;

/**
 * Freies Feld
 */
public class CubeEmpty extends Cube {

	public CubeEmpty() {
		super(Cube.IS_WALKABLE, Cube.IS_NOT_COLECTABLE);
	}

}
