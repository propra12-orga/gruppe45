package game.cube;

/**
 * Zerstoerbarer Wuerfel
 */
public class CubeObstacle extends Cube {

	public CubeObstacle() {
		super(Cube.IS_NOT_WALKABLE, Cube.IS_NOT_COLLECTABLE,
				Cube.IS_DESTROYABLE);
	}

}
