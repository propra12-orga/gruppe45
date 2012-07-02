package game.cube;

import game.Level;
import game.Player;

public class CubeExplosion extends Cube {
	
	final static public int DAMAGE_POINTS = 25;
	final static public int SCORE_HIT_PLAYER = -200;

	public CubeExplosion() {
		super(Cube.IS_WALKABLE, Cube.IS_NOT_COLLECTABLE, Cube.IS_DESTROYABLE);
	}

	@Override
	public void change(Player player, Level level) {
		player.hitPlayer(DAMAGE_POINTS);
		
		// TODO Testausgabe entfernen!
		System.out.println("");
		System.out.println("Spieler getroffen! -25  HealthPoints: " + player.getHealthPoints());
	
		// Man verliert Punkte, wenn man getroffen wird
		player.addScore(SCORE_HIT_PLAYER);
		System.out.println("Durch den Treffer verlierst Du " + (-1) * SCORE_HIT_PLAYER + " Punkte.");
		System.out.println("Du hast jetzt nur noch " + player.getScore() + " Punkte!");
		System.out.println("");
	}

}
