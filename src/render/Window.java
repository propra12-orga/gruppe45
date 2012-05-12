package render;

import game.Level;
import game.Player;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

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
		Player player = new Player(level, 50, 50, -150);
		OpenGL openGl = new OpenGL(level, player, width, height);

		while (!Display.isCloseRequested()) {

			openGl.display();

			Display.update();
			move_Control1(player);

		}

		Display.destroy();
	}

	public static void main(String[] argv) {
		Window window = new Window();
		window.start();
	}

	public void move_Control1(Player player) {

		// while (Keyboard.next()) {
		// if (Keyboard.getEventKeyState()) {
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			player.moveLeft();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			player.moveBackward();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			player.moveRight();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			player.moveForward();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_T)
				|| Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			Display.destroy();
			System.exit(0);
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			player.setBomb();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			player.turnRight();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			player.turnLeft();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			player.turnDown();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			player.turnUp();
		}

		// }
		// }
	}
}