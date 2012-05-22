package render;

import game.Level;
import game.Player;
import game.cube.CubeExit;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import control.Control_Keyboard;

public class Window {// implements Runnable {

	final static public int width = 800;
	final static public int height = 600;

	// Thread t = new Thread();

	public void start() {

		try {

			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		Level level = new Level();
		level.setCube(new CubeExit(), 1, 1, 1);
		Player player = new Player(level, 50, 50, -150); // 40,50,4
		OpenGL openGl = new OpenGL(level, player, width, height);
		Control_Keyboard keyboard = new Control_Keyboard(player);

		// Programmschleife:
		while (!Display.isCloseRequested()) {
			openGl.display();
			Display.update();

			keyboard.move_Control1(player);
		}
		Display.destroy();

	}

	public static void main(String[] argv) {
		Window window = new Window();
		window.start();
	}
}