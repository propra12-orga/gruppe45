package game.cube;

/**
 * Unzerstörbarer Würfel
 */
public class CubeSolid extends Cube {

	public CubeSolid() {
		super(Cube.IS_NOT_WALKABLE, Cube.IS_NOT_COLECTABLE);
	}

}
