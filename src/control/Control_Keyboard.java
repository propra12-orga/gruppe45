package control;

/**
 * Diese Klasse greift auf ein Player-Objekt zu und verändert seine Position
 */
import game.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Control_Keyboard implements KeyListener {

	Player player;

	public Control_Keyboard(Player player) {
		this.player = player;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			player.moveForward();
			break;
		case KeyEvent.VK_S:
			player.moveBackward();
			break;
		case KeyEvent.VK_A:
			player.moveLeft();
			break;
		case KeyEvent.VK_D:
			player.moveRight();
			break;
		case KeyEvent.VK_Q:
			player.turnLeft();
			break;
		case KeyEvent.VK_E:
			player.turnRight();
			break;
		case KeyEvent.VK_R:
			player.turnUp();
			break;
		case KeyEvent.VK_F:
			player.turnDown();
			break;
		case KeyEvent.VK_T:
			System.exit(0);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
