package game.cube;

import game.Level;
import game.Player;

/**
 * 
 * Item, das die Gesundheit des Spielers wieder herstellt.
 * 
 */
public class CubeItemDoubleScore extends Cube {
	
	final static public int SCORE = 1000;
	
	CubeItemDoubleScore() {
		super(Cube.IS_WALKABLE, Cube.IS_COLLECTABLE, Cube.IS_DESTROYABLE);
	}

	@Override
	public void change(Player player, Level level) {
		
		player.addScore(SCORE);
		System.out.println("");
		System.out.println("Das Item hat dir " + SCORE + " Punkte gebracht.");
		System.out.println("Nun hast du " + player.getScore() + " Punkte!");
		System.out.println("");
		
		level.setCube(Cube.getCubeByName(Cube.CUBE_EMPTY), player.getCubeX(), player.getCubeY(), player.getCubeZ());
	}
}
