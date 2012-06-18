package game.cube;

import game.Level;
import game.Player;

/**
 * 
 * Item, das die Gesundheit des Spielers wieder herstellt.
 * 
 */
public class CubeItemBombStrength extends Cube {

	CubeItemBombStrength() {
		super(Cube.IS_WALKABLE, Cube.IS_COLLECTABLE, Cube.IS_DESTROYABLE);
	}

	@Override
	public void change(Player player, Level level) {
		if (player.getbombStrengthMultiplier() < Player.MAX_BOMB_STRENGTH_MULTIPLIER) {
			player.increaseBombStrengthMultiplier();

			// TODO Testausgabe entfernen!
			System.out.println("Item used! +1 Strength  StrengthMulti now: " + player.getbombStrengthMultiplier());
		} else {
			// TODO Testausgabe entfernen!
			System.out.println("Du hast schon die maximale Durchschlagkraft! " + player.getbombStrengthMultiplier());
		}
		level.setCube(Cube.getCubeByName(Cube.CUBE_EMPTY), player.getCubeX(), player.getCubeY(), player.getCubeZ());
	}
}
