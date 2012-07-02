package game.cube;

import game.Game;
import game.Level;
import game.Player;

public class MenuCubeServer extends Cube {
	public MenuCubeServer() {
		super(Cube.IS_WALKABLE, Cube.IS_COLLECTABLE, Cube.IS_NOT_DESTROYABLE);
	}

	@Override
	public void change(Player player, Level level) {
		level.setCube(Cube.getCubeByName(Cube.CUBE_EMPTY), player.getCubeX(), player.getCubeY(), player.getCubeZ());
		level.setInMenu(false);
		Game.host();
	}
}
