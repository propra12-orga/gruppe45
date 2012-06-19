package game.cube;

import render.Menu;
import game.Level;
import game.Player;
import control.Control_Keyboard;

public class MenuCubeOptions extends Cube {

	public boolean menuOffen = Control_Keyboard.menueOffen;
	
	public MenuCubeOptions() {
		
		super(Cube.IS_WALKABLE, Cube.IS_COLLECTABLE, Cube.IS_NOT_DESTROYABLE);
	}

	@Override
	public void change(Player player, Level level) {
		this.menuOffen = Control_Keyboard.menueOffen;
		if (!menuOffen) {
			menuOffen = true;
			control.Control_Keyboard.menueOffen = true;
			Menu menu = new Menu(level, player);
			menuOffen = false;
			control.Control_Keyboard.menueOffen = false;
			player.setPosition((level.getSizeX() / 2) * 10 + 5,
					(level.getSizeY() / 2) * 10 + 5, 15);
				}

	}

}
