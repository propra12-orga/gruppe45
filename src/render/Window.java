package render;

import game.Level;
import game.Player;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import control.Control_Keyboard;
import control.Control_Mouse;

public class Window {

	final static public int width = 800; // Größe des Darstellungsfensters
	final static public int height = 600;	

	// TODO Menüoption
	final static public int level_size_x = 10; // X-Ausdehnung der Spielwelt
	final static public int level_size_y = 10; // Y-Ausdehnung der Spielwelt
	final static public int level_size_z = 10; // Z-Ausdehnung der Spielwelt

	public void start() {

		int player1_start_x = 0;

		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		Level level = new Level(level_size_x, level_size_y, level_size_z);

		// Skalierbare Levelgröße:
		// überprüft, dass die Spieler aus freien Feldern starten
		if (level.getSizeX() % 2 == 0) { // Größe in X gerade
			player1_start_x = level.getSizeX() * 10 - 15;
		} else { // Größe in X ungerade
			if (level.getSizeY() % 2 == 0) { // Größe in Y gerade
				player1_start_x = level.getSizeX() * 10 - 15;
			} else { // Größe in Y ungerade
				player1_start_x = level.getSizeX() * 10 - 25;
			}
		}

		List<Player> listPlayer = new ArrayList<Player>();
		Player player = new Player(level, player1_start_x, level_size_y * 10 - 15, 15, listPlayer); // STARTPOSITION
		// SPIELER
		listPlayer.add(player);

		OpenGL openGl = new OpenGL(level, player, width, height, null);
		Control_Keyboard controlKeyboard = new Control_Keyboard(player);
		Control_Mouse controlMouse = new Control_Mouse(player);

		// Programmschleife:
		while (!Display.isCloseRequested()) {
			openGl.display();
			Display.update();

			controlMouse.mouse_Move(player);

		}
		Display.destroy();
	}

	public static void main(String[] argv) {
		Window window = new Window();
		window.start();
	}
}