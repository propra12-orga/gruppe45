package game.cube;
import game.Level;
import game.Player;

public class CubeExplosion extends Cube {

	public CubeExplosion() {
		super(Cube.IS_WALKABLE, Cube.IS_NOT_COLLECTABLE, Cube.IS_DESTROYABLE);
	}

	@Override
	public void change(Player player, Level level) {
		player.hitPlayer(25);
	}

}
