package game.cube;

import game.Level;
import game.Player;

/**
 * 
 * Item, das die Gesundheit des Spielers wieder herstellt.
 * 
 */
public class CubeItemBombStrength extends Cube {
	
	final static public int SCORE = 100;

	CubeItemBombStrength() {
		super(Cube.IS_WALKABLE, Cube.IS_COLLECTABLE, Cube.IS_DESTROYABLE);
	}

	@Override
	public void change(Player player, Level level) {
		System.out.println("");
		if (player.getbombStrengthMultiplier() < player.getMaxBombStrengthMultiplier()) {
			player.increaseBombStrengthMultiplier();

			// TODO Testausgabe entfernen!
			System.out.println("Item used! +1 Strength  StrengthMulti now: " + player.getbombStrengthMultiplier());
		} else {
			// TODO Testausgabe entfernen!
			System.out.println("Du hast schon die maximale Durchschlagkraft! " + player.getbombStrengthMultiplier());
		}
		
		player.addScore(SCORE);
		System.out.println("Das Item bringt Dir " + SCORE + " Punkte.");
		System.out.println("Du hast jetzt " + player.getScore() + " Punkte!");
		System.out.println("");
		
		level.setCube(Cube.getCubeByName(Cube.CUBE_EMPTY), player.getCubeX(), player.getCubeY(), player.getCubeZ());
	}
}
