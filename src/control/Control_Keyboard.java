package control;

import game.Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

/**
 * Die Klasse Control_Keyboard enthaelt Steuerungsbefehle fuer den Spieler und
 * veraendert dessen Position und Blickwinkel. Ausserdem enthalten sind Befehle
 * fuer das Legen von Bomben und kurze Tastenbefehle, mit denen der
 * Programmablauf gesteuert werden kann (Beenden, Neustart)
 * 
 * 
 * @author chlov101
 * 
 */

public class Control_Keyboard extends Control {

	final int MILLISECS_PER_STEP = 10;
	Timer timer;

	public Control_Keyboard(Player player) {
		super(player);
		timer = new Timer(MILLISECS_PER_STEP, new TimerKeyboard());
		timer.start();
	}

	class TimerKeyboard implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			move_Control1(player);
		}

		public void move_Control1(Player player) {
			// links:
			if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
				player.moveLeft();
			}
			// rueckwaerts:
			if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
				player.moveBackward();
				printPosition();
			}
			// rechts:
			if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
				player.moveRight();
				printPosition();
			}
			// vorwaerts:
			if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
				player.moveForward();
				printPosition();
			}
			// hoch:
			if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
				player.moveUp();
				printPosition();
			}
			// runter:
			if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
				player.moveDown();
				printPosition();
			}
			// Programm beenden:
			if (Keyboard.isKeyDown(Keyboard.KEY_T) || Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
				// Display.destroy(); // Die Klasse findet Dislpay anscheinend
				// nicht
				System.exit(0);
			}
			// Bombe legen:
			if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
				player.setBomb();
			}
			// nach rechts drehen:
			if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
				player.turnRight(0.012f);
			}
			// nach links drehen:
			if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
				player.turnLeft(0.012f);
			}
			// nach unten neigen:
			if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
				player.turnDown(0.009f);
			}
			// nach oben neigen:
			if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
				player.turnUp(0.009f);
			}
			// Durchlauf neustarten:
			if (Keyboard.isKeyDown(Keyboard.KEY_N)) {
				Display.destroy();
			}

		}

	}

	private void printPosition() {
		// System.out.println("Position:  X: " + player.getX() + "  Y: "
		// + player.getY() + " Z: " + player.getZ());
	}

}
