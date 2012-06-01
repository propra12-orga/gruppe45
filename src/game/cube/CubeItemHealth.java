package game.cube;
import game.Level;
import game.Player;
import game.cube.CubeEmpty;

public class CubeItemHealth extends Cube {

	public CubeItemHealth() {
		super(Cube.IS_WALKABLE, Cube.IS_COLLECTABLE, Cube.IS_NOT_DESTROYABLE);
	}

	@Override
	public void change(Player player, Level level) {
		player.healPlayer(50);
		//TODO Zeile löschen! Ausgabe nur als TEST!


		System.out.println("Du wurdest geheilt!   +50    Healthpoints: " + player.getHealthPoints());
		
	}

}
