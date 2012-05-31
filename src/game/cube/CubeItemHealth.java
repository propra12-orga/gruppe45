package game.cube;

import game.Player;

public class CubeItemHealth extends Cube {

	CubeItemHealth() {
		super(Cube.IS_WALKABLE, Cube.IS_COLLECTABLE, Cube.IS_NOT_DESTROYABLE, Cube.DOES_NOT_HIDE_EXIT, "CubeItemHealth"); 
	}

	@Override
	public void change(Player player) {
		player.healPlayer(50);
	}

}
