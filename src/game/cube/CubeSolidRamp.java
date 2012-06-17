package game.cube;

/**
 * Unzerstoerbarer Wuerfel
 */
public class CubeSolidRamp extends Cube {

	public CubeSolidRamp() {
		super(Cube.IS_WALKABLE, Cube.IS_NOT_COLLECTABLE, Cube.IS_NOT_DESTROYABLE);
	}

}
