package render;

import game.Level;
import game.Player;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Window {

	final static public int width = 800; // Größe des Darstellungsfensters
	final static public int height = 600;
	final static public DisplayMode dispmod = new DisplayMode(width, height);

	public Window(Player player, Level level) {
		try {
			Display.setDisplayMode(dispmod);
			Display.setFullscreen(true);
			Display.create();
			Display.setTitle("Bombardiman ücbinikiyüzellibes 3D");
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