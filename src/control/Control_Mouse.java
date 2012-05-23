package control;

import org.lwjgl.input.Mouse;

import game.Player;


public class Control_Mouse extends Control {

	public Control_Mouse(Player player) {
		super(player);
		
	}
<<<<<<< HEAD
	public void MouseMove(Player player){
		if(Mouse.getX() > 0 ){
			player.turnLeft();
			
		}
	}
	   
	   
	   
	   
   }
=======


	public void mouse_Move(Player player) {
		Mouse.setCursorPosition(400,300);
		float x=0,y=0;
		while (Mouse.next()) {
			x = x + Mouse.getDX();
			y = y + Mouse.getDY();
			if (x > 0) {
				Mouse.setCursorPosition(400,300);
				player.turnRight(Math.abs(x)/150);
			}else if (x < 0){
				Mouse.setCursorPosition(400,300);
				player.turnLeft(Math.abs(x)/150);
			}
			if (y < 0){
				Mouse.setCursorPosition(400,300);
				player.turnDown(Math.abs(y)/150);
			}else if(y>0){
				Mouse.setCursorPosition(400,300);
				player.turnUp(Math.abs(y)/150);
			}
		}

	}
}

>>>>>>> 671f5902b8de7aadbcdac2991effabef57ecfa4d
