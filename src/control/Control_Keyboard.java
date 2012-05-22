package control;

import game.Player;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public class Control_Keyboard extends Control {

	public Control_Keyboard(Player player) {
		super(player);
		// TODO Auto-generated constructor stub

	}

	// Steuerungskontrolle:
	public void move_Control1(Player player) {
		// links:
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			player.moveLeft();
		}
		// rueckwaerts:
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			player.moveBackward();
		}
		// rechts:
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			player.moveRight();
		}
		// vorwaerts:
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			player.moveForward();
		}
		// Programm beenden:
		if (Keyboard.isKeyDown(Keyboard.KEY_T)
				|| Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			Display.destroy();
			System.exit(0);
		}
		// Bombe legen:
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			player.setBomb();
		}
		// nach rechts drehen:
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			player.turnRight();
		}
		// nach links drehen:
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			player.turnLeft();
		}
		// nach unten neigen:
		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			player.turnDown();
		}
		// nach oben neigen:
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			player.turnUp();
		}
	}


}
