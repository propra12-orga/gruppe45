package control;

import game.Player;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import render.Window;

/**
 * Die Klasse Control_Keyboard enthaelt Steuerungsbefehle für den Spieler und
 * verändert dessen Position und Blickwinkel. Ausserdem enthalten sind Befehle
 * für das Legen von Bomben und kurze Tastenbefehle, mit denen der
 * Programmablauf gesteuert werden kann (Beenden, Neustart)
 * 
 * 
 * @author chlov101
 * 
 */

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
			System.out.println("Position:  X: " + player.getX() + "  Y: "
					+ player.getY() + " Z: " + player.getZ());
		}
		// rueckwaerts:
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			player.moveBackward();
			System.out.println("Position:  X: " + player.getX() + "  Y: "
					+ player.getY() + " Z: " + player.getZ());
		}
		// rechts:
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			player.moveRight();
			System.out.println("Position:  X: " + player.getX() + "  Y: "
					+ player.getY() + " Z: " + player.getZ());
		}
		// vorwaerts:
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			player.moveForward();
			System.out.println("Position:  X: " + player.getX() + "  Y: "
					+ player.getY() + " Z: " + player.getZ());
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
			System.out.println("Bombe gelegt an Position:  X: " + player.getX()
					+ "  Y: " + player.getY() + " Z: " + player.getZ());
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
		// Durchlauf neustarten:
		if (Keyboard.isKeyDown(Keyboard.KEY_N)) {
			Display.destroy();
		}
	}

}
