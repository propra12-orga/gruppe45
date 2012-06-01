package game.cube;
import game.Level;
import game.Player;



public class CubeItemHealth extends Cube {
	
	final static public int HEAL_POINTS = 50;

	CubeItemHealth() {
		super(Cube.IS_WALKABLE, Cube.IS_COLLECTABLE, Cube.IS_NOT_DESTROYABLE); 
	}

	@Override
	public void change(Player player, Level level) {		
		if (player.getHealthPoints() + HEAL_POINTS < player.MAX_HEALTH_POINTS) {		
			player.healPlayer(HEAL_POINTS);
			
			// TODO Testausgabe entfernen!
			System.out.println("Player geheilt! +50  HealthPoints: " + player.getHealthPoints());
		}
		else {
			player.setMaxHealth();
			// TODO Testausgabe entfernen!
			System.out.println("Player geheilt! HealthPoints maximal: " + player.getHealthPoints());
		}			
		level.setCube(Cube.getCubeByName(Cube.CUBE_EMPTY), player.getCubeX(), player.getCubeY(), player.getCubeZ());

	}

}
