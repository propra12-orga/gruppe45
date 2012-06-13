package game.cube;

import game.Level;
import game.Player;

/**
 * 
 * Item, das die Gesundheit des Spielers wieder herstellt.
 * 
 */
public class CubeItemXtraBomb extends Cube {

	CubeItemXtraBomb() {
		super(Cube.IS_WALKABLE, Cube.IS_COLLECTABLE, Cube.IS_DESTROYABLE);
	}

	@Override
	public void change(Player player, Level level) {
		if (player.getBombs() < Player.MAX_SIMULTAN_BOMBS) {
			player.increaseBombs();

			// TODO Testausgabe entfernen!
			System.out.println("Item used! +1 Bomb  MaxBombs now: " + player.getBombs());
		} else {
			// TODO Testausgabe entfernen!
			System.out.println("Du hast schon die maximale Anzahl an Bomben! " + player.getBombs());
		}
		level.setCube(Cube.getCubeByName(Cube.CUBE_EMPTY), player.getCubeX(), player.getCubeY(), player.getCubeZ());
	}
}
