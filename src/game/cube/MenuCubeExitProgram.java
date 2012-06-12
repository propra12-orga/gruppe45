package game.cube;
import java.util.ArrayList;
import java.util.List;

import game.Level;
import game.Player;

public class MenuCubeExitProgram extends Cube {

	public MenuCubeExitProgram() {
		super(Cube.IS_WALKABLE, Cube.IS_COLLECTABLE, Cube.IS_NOT_DESTROYABLE);
	}

	@Override
	public void change(Player player, Level level) {
		System.out.println("Du hast das Programm verlassen!");
		System.exit(1);
	}
}
