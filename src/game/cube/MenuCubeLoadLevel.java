package game.cube;

import game.Level;
import game.Player;

public class MenuCubeLoadLevel extends Cube {

	public MenuCubeLoadLevel() {
		super(Cube.IS_WALKABLE, Cube.IS_COLLECTABLE, Cube.IS_NOT_DESTROYABLE);
	}

	@Override
	public void change(Player player, Level level) {
		System.out.println("Spiele Ladelevel");
		level.load();

		int player1_start_x = 0;
		if (level.getSizeX() % 2 == 0) { // Größe in X gerade
			player1_start_x = level.getSizeX() * 10 - 15;
		} else { // Größe in X ungerade
			if (level.getSizeY() % 2 == 0) { // Größe in Y gerade
				player1_start_x = level.getSizeX() * 10 - 15;
			} else { // Größe in Y ungerade
				player1_start_x = level.getSizeX() * 10 - 25;
			}
		}

		player.setPosition(player1_start_x, level.getSizeY() * 10 - 15, 15);
		player.setHealthPoints(100);
		player.setBombs(2);
		player.setAngleX(0);
		player.setAngleY(0);
	}
}
