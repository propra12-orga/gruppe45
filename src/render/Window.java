package render;

import game.Player;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Window {
	final static public int width = 800;
	final static public int height = 600;

	public void start() {
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		Player player = new Player(50, 50, -150);
		OpenGL openGl = new OpenGL(player, width, height);

		while (!Display.isCloseRequested()) {

			openGl.display();

			Display.update();
		}

		Display.destroy();
	}

	public static void main(String[] argv) {
		Window window = new Window();
		window.start();
	}
}