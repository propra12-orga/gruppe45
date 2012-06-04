package control;

import game.Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

/**
 * Enthaelt Steuerungsbefehle fuer den Spieler und veraendert dessen Position
 * und Blickwinkel. Ausserdem enthalten sind Befehle fuer das Legen von Bomben
 * und kurze Tastenbefehle, mit denen der Programmablauf gesteuert werden kann
 * (Beenden, Neustart...)
 * 
 * 
 * 
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
			}
			// rechts:
			if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
				player.moveRight();
			}
			// vorwaerts:
			if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
				player.moveForward();
			}
			// hoch:
			if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
				player.moveUp();
			}
			// runter:
			if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
				player.moveDown();
			}
			// Programm beenden:
			if (Keyboard.isKeyDown(Keyboard.KEY_T) || Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
				// Display.destroy(); // Die Klasse findet Dislpay anscheinend
				// nicht
				player.dies();
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
}
