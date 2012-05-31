package game.cube;

import game.Player;

public class CubeExplosion extends Cube {

	public CubeExplosion() {
		super(Cube.IS_WALKABLE, Cube.IS_NOT_COLLECTABLE, Cube.IS_DESTROYABLE, Cube.DOES_NOT_HIDE_EXIT, "CubeExplosion");
	}
	
	public CubeExplosion(boolean hidestheexit) {
		super(Cube.IS_WALKABLE, Cube.IS_NOT_COLLECTABLE, Cube.IS_DESTROYABLE, hidestheexit, "CubeExplosion");
	}

	@Override
	public void change(Player player) {
		player.hitPlayer(25);
	}

}
