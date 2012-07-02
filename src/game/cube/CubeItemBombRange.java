package game.cube;

import game.Level;
import game.Player;

/**
 * 
 * Item, das die Reichweite der Bomben eines Spielers erhöht.
 * 
 */
public class CubeItemBombRange extends Cube {
	
	final static public int SCORE = 100;

	CubeItemBombRange() {
		super(Cube.IS_WALKABLE, Cube.IS_COLLECTABLE, Cube.IS_DESTROYABLE);
	}

	@Override
	public void change(Player player, Level level) {
		System.out.println("");
		if (player.getRadius() < player.getMaxBombRadius()) {
			player.increaseRadius();

			// TODO Testausgabe entfernen!
			System.out.println("Item used! +1 Radius  Radius neu: " + player.getRadius());
		} else {
			// TODO Testausgabe entfernen!
			System.out.println("Du hast schon die maximalen Wirkungsradius! " + player.getRadius());
		}
		
		player.addScore(SCORE);
		System.out.println("Das Item bringt Dir " + SCORE + " Punkte.");
		System.out.println("Du hast jetzt " + player.getScore() + " Punkte!");
		System.out.println("");
		level.setCube(Cube.getCubeByName(Cube.CUBE_EMPTY), player.getCubeX(), player.getCubeY(), player.getCubeZ());
	}
}
