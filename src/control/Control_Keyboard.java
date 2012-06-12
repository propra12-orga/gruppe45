package control;

import game.Level;
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
	Level level;

	public Control_Keyboard(Player player, Level level) {
		super(player);
		this.level = level;
		timer = new Timer(MILLISECS_PER_STEP, new TimerKeyboard());
		timer.start();
	}

	class TimerKeyboard implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			move_Control1(player, level);
		}

		public void move_Control1(Player player, Level level) {
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
			    level.showMenu();
			    player.setPosition((level.getSizeX() / 2) * 10 + 5  , (level.getSizeY() / 2) * 10 + 5, 15);
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
/*TODO: Funktionsfähig machen nach dem Vorbild des Menüs:
 			if (Keyboard.isKeyDown(Keyboard.KEY_N)) {
				Display.destroy();
			}
*/
			if(Keyboard.isKeyDown(Keyboard.KEY_O)){
				level.save();
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_L)){
				level.load();
			}
		}
	}
}
