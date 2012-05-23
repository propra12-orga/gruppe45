package game.cube;

/**
 * Unzerstoerbarer Wuerfel
 */
public class CubeSolid extends Cube {

	public CubeSolid() {
		super(Cube.IS_NOT_WALKABLE, Cube.IS_NOT_COLLECTABLE);
	}

}
