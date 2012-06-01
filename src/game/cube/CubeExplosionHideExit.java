package game.cube;

import game.Player;

public class CubeExplosionHideExit extends Cube {

	public CubeExplosionHideExit() {
		super(Cube.IS_WALKABLE, Cube.IS_NOT_COLLECTABLE, Cube.IS_DESTROYABLE);
		this.hidesExit = true;
	}

	@Override
	public void change(Player player) {
		player.hitPlayer(25);
	}

}
