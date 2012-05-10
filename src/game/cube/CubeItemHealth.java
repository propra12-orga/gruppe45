package game.cube;

import game.Player;

public class CubeItemHealth extends Cube {

	CubeItemHealth() {
		super(Cube.IS_WALKABLE, Cube.IS_COLECTABLE);
	}

	@Override
	public void changePlayer(Player player) {
		player.healPlayer(50);
	}

}
