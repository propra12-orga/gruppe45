package game.cube;

import render.Menu;
import game.Level;
import game.Player;
import control.Control_Keyboard;

public class MenuCubeOptions extends Cube {
	
	public MenuCubeOptions() {
		super(Cube.IS_WALKABLE, Cube.IS_COLLECTABLE, Cube.IS_NOT_DESTROYABLE);
	}

	@Override
	public void change(Player player, Level level) {
		if (!Menu.menuOffen) {
			Menu.menuOffen = true;
			Menu menu = new Menu();
			player.setPosition((level.getSizeX() / 2) * 10 + 5,
					(level.getSizeY() / 2) * 10 + 5, 15);
				}

	}

}
