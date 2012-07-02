package game.cube;

import game.Level;
import game.Player;

/**
 * 
 * Item, das die Gesundheit des Spielers wieder herstellt.
 * 
 */
public class CubeItemHealth extends Cube {
	
	final static public int SCORE = 100;

	final static public int HEAL_POINTS = 50;

	CubeItemHealth() {
		super(Cube.IS_WALKABLE, Cube.IS_COLLECTABLE, Cube.IS_DESTROYABLE);
	}

	@Override
	public void change(Player player, Level level) {
		System.out.println("");
		if (player.getHealthPoints() + HEAL_POINTS < player.getMaxHealthPoints()) {
			player.healPlayer(HEAL_POINTS);

			// TODO Testausgabe entfernen!
			System.out.println("Player geheilt! +50  HealthPoints: " + player.getHealthPoints());
		} else {
			player.setMaxHealth();
			// TODO Testausgabe entfernen!
			System.out.println("Player geheilt! HealthPoints maximal: " + player.getHealthPoints());
		}
		
		player.addScore(SCORE);
		System.out.println("Das Item bringt Dir " + SCORE + " Punkte.");
		System.out.println("Du hast jetzt " + player.getScore() + " Punkte!");
		System.out.println("");
		
		level.setCube(Cube.getCubeByName(Cube.CUBE_EMPTY), player.getCubeX(), player.getCubeY(), player.getCubeZ());

	}

}
