package game.cube;
import game.Level;
import game.Player;
import game.cube.CubeEmpty;

public class CubeItemHealth extends Cube {

	public CubeItemHealth() {
		super(Cube.IS_WALKABLE, Cube.IS_COLLECTABLE, Cube.IS_NOT_DESTROYABLE, Cube.DOES_NOT_HIDE_EXIT, "CubeItemHealth"); 
	}

	@Override
	public void change(Player player, Level level) {
		player.healPlayer(50);
		level.setCube(new CubeEmpty(), player.getCubeX(), player.getCubeY(), player.getCubeZ());
		System.out.println("Du wurdest geheilt!   +50    Healthpoints: " + player.getHealthPoints());
		
	}

}
