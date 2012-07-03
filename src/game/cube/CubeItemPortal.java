package game.cube;
import java.util.Random;

import game.Level;
import game.Player;


/**
 * 
 * Item, das die Gesundheit des Spielers wieder herstellt.
 *
 */
public class CubeItemPortal extends Cube {
	
	final static public int SCORE = 100;
	
	CubeItemPortal() {
		super(Cube.IS_WALKABLE, Cube.IS_COLLECTABLE, Cube.IS_DESTROYABLE); 
	}

	@Override
	public void change(Player player, Level level) {	
		int destCubeX = 0, destCubeY = 0, destCubeZ = 0;
		
//		destCubeX = level.getSizeX() - player.getCubeX();
//		destCubeY = level.getSizeY() - player.getCubeY();
//		destCubeZ = level.getSizeZ() - player.getCubeZ();
		
		// FIXME @Philipp
		// Wenn das Portal zu einem besetzten Block schickt, dann suche
		// Nachbarblöcke statt Zufallposition
		// TODO Exit als mögliche Destination rausnehmen?)
		if (!(level.getCube(destCubeX, destCubeY, destCubeZ).isWalkable())) {
			do {
				Random randomx = new Random();
				destCubeX = 1 + Math.abs(randomx.nextInt()) % (level.getSizeX()-1);
				
				Random randomy = new Random();
				destCubeY = 1 + Math.abs(randomy.nextInt()) % (level.getSizeY()-1);
				
				Random randomz = new Random();
				destCubeZ = 1 + Math.abs(randomz.nextInt()) % (level.getSizeZ()-1);
				
			} while (!(level.getCube(destCubeX, destCubeY, destCubeZ).isWalkable()));
		}
	
		System.out.println("");
		System.out.println("Du hast ein Portal betreten!");		
		level.setCube(Cube.getCubeByName(Cube.CUBE_EMPTY), player.getCubeX(), player.getCubeY(), player.getCubeZ());
		System.out.println("Alte Position: x:" + player.getCubeX() + ", y:" + player.getCubeY() + ", z:" + player.getCubeZ());	
		player.setPlayerPosition(destCubeX, destCubeY, destCubeZ);		
		System.out.println("Neue Position: x:" + destCubeX + ", y:" + destCubeY + ", z:" + destCubeZ);
		
		player.addScore(SCORE);
		System.out.println("Das Portal bringt Dir " + SCORE + " Punkte.");
		System.out.println("Du hast jetzt " + player.getScore() + " Punkte!");
		System.out.println("");
	}
}
