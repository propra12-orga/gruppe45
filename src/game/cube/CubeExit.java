package game.cube;
import game.Level;
import game.Player;


public class CubeExit extends Cube {

	public CubeExit() {
		super(Cube.IS_WALKABLE, Cube.IS_COLLECTABLE, Cube.IS_NOT_DESTROYABLE, Cube.HIDES_EXIT, "CubeExit");
	}

	@Override
	public void change(Player player, Level level) {
		System.exit(1);
	}

}
