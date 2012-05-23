package control;

import org.lwjgl.input.Mouse;

import game.Player;

public class Control_Mouse extends Control {

	public Control_Mouse(Player player) {
		super(player);
		
	}
	public void MouseMove(Player player){
		if(Mouse.getX() > 0 ){
			player.turnLeft();
			
		}
	}
	   
	   
	   
	   
   }
