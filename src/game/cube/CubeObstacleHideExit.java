package game.cube;

/**
 * Zerstoerbarer Wuerfel
 */
public class CubeObstacleHideExit extends Cube {

	public CubeObstacleHideExit() {
		super(Cube.IS_NOT_WALKABLE, Cube.IS_NOT_COLLECTABLE, Cube.IS_DESTROYABLE);
	}

}
