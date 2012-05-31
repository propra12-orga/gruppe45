package game.cube;

/**
 * Spielfeldbegrenzender Wuerfel
 */
public class CubeOutside extends Cube {

	public CubeOutside() {
		super(Cube.IS_NOT_WALKABLE, Cube.IS_NOT_COLLECTABLE, Cube.IS_NOT_DESTROYABLE, Cube.DOES_NOT_HIDE_EXIT, "CubeOutside");
	}

}
