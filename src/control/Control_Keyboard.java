package control;

import game.Player;
import org.lwjgl.input.Keyboard;

public class Control_Keyboard extends Control {

	public Control_Keyboard(Player player) {
		super(player);
		// TODO Auto-generated constructor stub

	}

	public void move_Control() {
			
		if(Keyboard.getEventKeyState()){
			switch (Keyboard.getEventKey()){
			case Keyboard.KEY_A:
				player.moveLeft();
			}
		}
		
		
	}

}
