package control;

import org.lwjgl.input.Mouse;

import game.Player;


public class Control_Mouse extends Control {

	public Control_Mouse(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
	}

	public void mouse_Move(Player player) {
		Mouse.setCursorPosition(400,300);
		int x=0,y=0;
		while (Mouse.next()) {
			//System.out.println("X: " + Mouse.getDX() + "  Y: " + Mouse.getDY());
			// player.turnLeft();
			x = x + Mouse.getDX();
			y = y + Mouse.getDY();
			if (x > 0) {
				Mouse.setCursorPosition(400,300);
				player.turnRight();
			}else if (x < 0){
				Mouse.setCursorPosition(400,300);
				player.turnLeft();
			}
			if (y < 0){
				Mouse.setCursorPosition(400,300);
				player.turnDown();
			}else if(y>0){
				Mouse.setCursorPosition(400,300);
				player.turnUp();
			}
		}

	}
}
