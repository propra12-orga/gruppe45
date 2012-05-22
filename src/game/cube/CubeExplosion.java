package game.cube;

import game.Player;

public class CubeExplosion extends Cube {

	public CubeExplosion() {
		super(Cube.IS_WALKABLE, Cube.IS_NOT_COLLECTABLE);
	}

	@Override
	public void change(Player player) {
		player.hitPlayer(25);
	}

}
