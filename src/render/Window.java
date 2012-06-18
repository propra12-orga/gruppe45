package render;

import game.Level;
import game.Player;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Window {

	final static public String FENSTERNAME = "Bomberdiman ikibinoniki 3D";

	final static public int width = 800; // Größe des Darstellungsfensters
	final static public int height = 600;
	// TODO Menüoption
	final static public int levelSizeX = 11; // X-Ausdehnung der Spielwelt
	final static public int levelSizeY = 11; // Y-Ausdehnung der Spielwelt
	final static public int levelSizeZ = 11; // Z-Ausdehnung der Spielwelt
	final static public DisplayMode dispmod = new DisplayMode(width, height);

	public Window(Player player, Level level) {
		try {
			Display.setDisplayMode(dispmod);
			Display.setFullscreen(true);
			Display.create();
			Display.setTitle(FENSTERNAME);
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void close() {
		Display.destroy();
	}
}