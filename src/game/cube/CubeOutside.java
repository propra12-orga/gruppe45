package game.cube;

/**
 * Unzerstoerbarer Wuerfel
 */
public class CubeOutside extends Cube {

	public CubeOutside() {
		super(Cube.IS_NOT_WALKABLE, Cube.IS_NOT_COLLECTABLE, Cube.IS_NOT_DESTROYABLE);
	}

}
