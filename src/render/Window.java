package render;

import game.Level;
import game.Player;
import game.cube.CubeExit;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import control.Control_Keyboard;
import control.Control_Mouse;

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
		Player player = new Player(level, 85, 85, 15); // STARTPOSITION SPIELER
														//Zweiter Spieler (25,15,85)
		OpenGL openGl = new OpenGL(level, player, width, height);
		Control_Keyboard controlKeyboard = new Control_Keyboard(player);
		Control_Mouse controlMouse = new Control_Mouse(player);

		// Programmschleife:
		while (!Display.isCloseRequested()) {
			openGl.display();
			Display.update();

			// controlKeyboard.move_Control1(player);
			controlMouse.mouse_Move(player);

		}
		Display.destroy();
	}

	public static void main(String[] argv) {
		Window window = new Window();
		window.start();
	}
}