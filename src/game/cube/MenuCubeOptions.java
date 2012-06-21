package game.cube;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.lwjgl.input.Keyboard;

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
			try {
				Robot rob = new Robot();
				rob.delay(500);
				rob.keyRelease(KeyEvent.VK_W);
				rob.keyRelease(KeyEvent.VK_UP);
	    	} catch (AWTException e) {}
			Menu menu = new Menu(level, player);
			player.setPosition((level.getSizeX() / 2) * 10 + 5,
					(level.getSizeY() / 2) * 10 + 5, 15);
				}

	}

}
