package game.cube;

import game.Player;

public class CubeItemHealth extends Cube {

	CubeItemHealth() {
		super(Cube.IS_WALKABLE, Cube.IS_COLLECTABLE, Cube.IS_NOT_DESTROYABLE);
	}

	@Override
	public void change(Player player) {
		player.healPlayer(50);
	}

}
