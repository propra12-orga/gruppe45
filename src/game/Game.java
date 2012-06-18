package game;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;

import render.OpenGL;
import render.Window;
import DetectedServer.ThreadServer;
import control.Control_Keyboard;
import control.Control_Mouse;

public class Game {
	final static public int LEVEL_SIZE_X = 13; // X-Ausdehnung der Spielwelt
	final static public int LEVEL_SIZE_Y = 13; // Y-Ausdehnung der Spielwelt
	final static public int LEVEL_SIZE_Z = 14; // Z-Ausdehnung der Spielwelt

	static private List<Player> listPlayer;
	static private Level level;
	static private Player player;
	static private Window window;
	static private OpenGL openGl;
	static private ThreadBomb threadBomb;
	static private Thread threadServer;

	static private Control_Keyboard controlKeyboard;
	static private Control_Mouse controlMouse;

	static private GameMulti gameMulti;

	public static void main(String[] argv) {
		// Die Liste der Mitspieler
		listPlayer = new ArrayList<Player>();
		// Das Spielfenster erzeugen
		window = new Window(player, level);
		// Das Level das gezeichnet wird
		level = new Level(LEVEL_SIZE_X, LEVEL_SIZE_Y, LEVEL_SIZE_Z);
		threadBomb = new ThreadBomb(level, listPlayer, null);
		level.showMenu();
		// Eigener Spieler
		player = new Player(level, (level.getSizeX() / 2) * 10 + 5, (level.getSizeY() / 2) * 10 + 5, 15, listPlayer);
		player.setBombs(0);
		listPlayer.add(player);
		openGl = new OpenGL(window.getWidth(), window.getHeight(), player, level);
		controlKeyboard = new Control_Keyboard(player, level);
		controlMouse = new Control_Mouse(player);
		while (!Display.isCloseRequested()) {
			openGl.display();
			Display.update();
			controlMouse.mouse_Move();
		}
		window.close();
	}

	public static ThreadBomb getThreadBomb() {
		return threadBomb;
	}

	public static void host() {
		threadServer = new Thread(new ThreadServer());
		threadServer.start();
		connect();
	}

	public static void connect() {
		threadBomb.stop();
		gameMulti = new GameMulti();
		gameMulti.connect(openGl, controlKeyboard, controlMouse);
		System.out.println("CONNECT");
	}

	public static void disconnect() {
		threadBomb.start();
		openGl.setLevel(level);
		openGl.setPlayer(player);
		openGl.setPlayerList(listPlayer);
		controlKeyboard.setPlayer(player);
		controlMouse.setPlayer(player);

		level.showMenu();
		player.setPosition((level.getSizeX() / 2) * 10 + 5, (level.getSizeY() / 2) * 10 + 5, 15);
		player.setBombs(0);
		player.setAngleX(0);
		player.setAngleY(0);

		gameMulti = null;
		threadServer = null;
	}
}
