package control;

import game.Player;

import org.lwjgl.input.Mouse;

public class Control_Mouse extends Control {

	public Control_Mouse(Player player) {
		super(player);

	}

	public void mouse_Move(Player player) {
		Mouse.setCursorPosition(400, 300);
		float x = 0, y = 0;
		while (Mouse.next()) {
			x = x + Mouse.getDX();
			y = y + Mouse.getDY();
			if (x > 0) {
				Mouse.setCursorPosition(400, 300);
				player.turnRight(Math.abs(x) / 150);
			} else if (x < 0) {
				Mouse.setCursorPosition(400, 300);
				player.turnLeft(Math.abs(x) / 150);
			}
			if (y < 0) {
				Mouse.setCursorPosition(400, 300);
				player.turnDown(Math.abs(y) / 150);
			} else if (y > 0) {
				Mouse.setCursorPosition(400, 300);
				player.turnUp(Math.abs(y) / 150);
			}
		}

	}
}
