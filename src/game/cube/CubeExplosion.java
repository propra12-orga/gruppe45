package game.cube;

import game.Level;
import game.Player;

public class CubeExplosion extends Cube {
	
	final static public int DAMAGE_POINTS = 25;

	public CubeExplosion() {
		super(Cube.IS_WALKABLE, Cube.IS_NOT_COLLECTABLE, Cube.IS_DESTROYABLE);
	}

	@Override
	public void change(Player player, Level level) {
		player.hitPlayer(DAMAGE_POINTS);
		
		// TODO Testausgabe entfernen!
		System.out.println("Spieler getroffen! -25  HealthPoints: " + player.getHealthPoints());
	}

}
