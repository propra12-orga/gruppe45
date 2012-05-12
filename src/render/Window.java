package render;

import game.Player;
import control.Control_Keyboard;

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

		Player player = new Player(50, 50, -150);
		OpenGL openGl = new OpenGL(player, width, height);

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
		// TODO Auto-generated method stub

//		while (Keyboard.next()) {
			while (Keyboard.getEventKeyState()) {
				if (Keyboard.getEventKey() == Keyboard.KEY_A) {
//					do {
						player.moveLeft();
//					} while (Keyboard.getEventKeyState());
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_S) {
					player.moveBackward();
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_D) {
					player.moveRight();
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_W) {
					player.moveForward();
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_T) {
					Display.destroy();
					System.exit(0);
				}
			}
//		}
	}
}