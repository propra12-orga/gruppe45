package game.cube;

import game.Level;
import game.Player;

public class CubeExplosionHideExit extends Cube {

	public CubeExplosionHideExit() {
		super(Cube.IS_WALKABLE, Cube.IS_NOT_COLLECTABLE, Cube.IS_DESTROYABLE);
		this.hidesExit = true;
	}

	@Override
	public void change(Player player, Level level) {
		player.hitPlayer(25);
	}

}
