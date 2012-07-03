package control;

import game.Game;
import game.Level;
import game.Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import org.lwjgl.input.Keyboard;

import render.Menu;

/**
 * Enthaelt Steuerungsbefehle fuer den Spieler und veraendert dessen Position
 * und Blickwinkel. Ausserdem enthalten sind Befehle fuer das Legen von Bomben
 * und kurze Tastenbefehle, mit denen der Programmablauf gesteuert werden kann
 * (Beenden, Neustart...)
 */

public class Control_Keyboard extends Control {

	final int MILLISECS_PER_STEP = 10;
	public Timer timer;
	Level level;

	public Control_Keyboard(Player player, Level level) {
		super(player);
		this.level = level;
		timer = new Timer(MILLISECS_PER_STEP, new TimerKeyboard());
		timer.start();
	}

	public void keyboardDestroy() {
		Keyboard.destroy();
	}

	public void timeout(boolean a) {
		if (a) {
			try {
				timer.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			timer.notify();
		}
	}

	class TimerKeyboard implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (player.getGravity()) {
				player.sinkDown();
			}
			move_Control1();
		}

		public void move_Control1() {
			if (!Menu.menuOffen) {

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
					if (!(player.getGravity())) {
						player.moveUp();
					}
				}
				// runter:
				if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
					if (!(player.getGravity())) {
						player.moveDown();
					}
				}
				// Zurück zum Menü:
				if (Keyboard.isKeyDown(Keyboard.KEY_T)) {
					Game.disconnect();
				}
				// Programm beenden
				if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
					Game.disconnect();
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
				// Quicksave
				if (Keyboard.isKeyDown(Keyboard.KEY_O)) {
					level.save();
				}
				// Quickload
				if (Keyboard.isKeyDown(Keyboard.KEY_L)) {
					level.load();
				}
				// TAB
				if (Keyboard.isKeyDown(Keyboard.KEY_TAB)) {
					System.out.println("TAB Taste");
				}
			}
		}
	}
}
