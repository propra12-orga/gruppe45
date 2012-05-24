package control;

import game.Player;

import org.lwjgl.input.Mouse;

public class Control_Mouse extends Control {

	public Control_Mouse(Player player) {
		super(player);
		Mouse.setGrabbed(true);
	}

	public void mouse_Move(Player player) {
		while (Mouse.next()) {
			player.yaw((-Mouse.getDX()) / 100f);
			player.pitch((Mouse.getDY()) / 100f);
		}
	}

}
