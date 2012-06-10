package game.cube;
import game.Level;
import game.Player;


/**
 * 
 * Item, das die Gesundheit des Spielers wieder herstellt.
 *
 */
public class CubeItemXtraBomb extends Cube {
	
	final static public int HEAL_POINTS = 50;

	CubeItemXtraBomb() {
		super(Cube.IS_WALKABLE, Cube.IS_COLLECTABLE, Cube.IS_NOT_DESTROYABLE); 
	}

	@Override
	public void change(Player player, Level level) {		
		player.increaseMaxBombs();
		
		// TODO Testausgabe entfernen!
		System.out.println("Item used! +1 Bomb  MaxBombs now: " + player.getMaxBombs());
	
		level.setCube(Cube.getCubeByName(Cube.CUBE_EMPTY), player.getCubeX(), player.getCubeY(), player.getCubeZ());

	}

}
