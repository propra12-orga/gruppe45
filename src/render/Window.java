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
	
	final static public String FENSTERNAME = "Bomberdiman ikibinoniki 3D";
	
	final static public int width = 800; // Größe des Darstellungsfensters
	final static public int height = 600;

	// TODO Menüoption
	final static public int levelSizeX = 11; // X-Ausdehnung der Spielwelt
	final static public int levelSizeY = 11; // Y-Ausdehnung der Spielwelt
	final static public int levelSizeZ = 11; // Z-Ausdehnung der Spielwelt

	public void start() {
		int player1_start_x;

		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
			Display.setTitle(FENSTERNAME);
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		List<Player> listPlayer = new ArrayList<Player>();
		Level level = new Level(levelSizeX, levelSizeY, levelSizeZ, listPlayer);
		level.showMenu();

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

		Player player = new Player(level, (level.getSizeX() / 2) * 10 + 5, (level.getSizeY() / 2) * 10 + 5, 15, listPlayer); // STARTPOSITION
		// SPIELER
		listPlayer.add(player);
		player.setBombs(0);

		// TODO

		OpenGL openGl = new OpenGL(level, player, width, height, null);
		Control_Keyboard controlKeyboard = new Control_Keyboard(player, level);
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
		// Menu menue = new Menu(600,500, window);
	}
}