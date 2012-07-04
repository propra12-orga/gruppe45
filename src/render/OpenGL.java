package render;

//Hier werden Level- und Playerinformationen zusammengeführt und in OpenGL ausgegeben.

import game.Level;
import game.Player;
import game.cube.Cube;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class OpenGL {

	final static public byte EFFECT_OFF = 0x00; // 0000 0000
	final static public byte EFFECT_RED = 0x01; // 0000 0001
	final static public byte EFFECT_TRANSPARENT = 0x02; // 0000 0010
	final static public float SIZE_OF_CUBE = 10;
	final static public float CLIPSIZE = 1f;
	int width, height;
	Level level;
	Player player;
	Objects objects;
	HUD hud;

	// Netzwerk spieler
	private static List<Player> listPlayer;

	/**
	 * 
	 * @param level
	 *            dieses Level wird gezeichnet
	 * @param player
	 *            von diesem Player aus wird die OpenGL-Szene gezeichnet
	 * @param width
	 *            die Breite der Aufloesung
	 * @param height
	 *            die Hoehe der Aufloesung
	 * @param listPlayer
	 *            diese Spielerliste wird als Mitspieler gezeichnet
	 */
	public OpenGL(int width, int height, Player player, Level level) {
		this.width = width;
		this.height = height;
		this.player = player;
		this.level = level;
		objects = new Objects();
		hud = new HUD();
		init();
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void setPlayerList(List<Player> listPlayer) {
		this.listPlayer = listPlayer;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public void display() {
		GL11.glViewport(0, 0, width, height);
		GL11.glClearColor(0.5f, 0.5f, 0.5f, 0.1f);

		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		float widthHeightRatio = width / height;
		GLU.gluPerspective(45, widthHeightRatio, 1, 1000);
		GLU.gluLookAt(player.getX(), player.getY(), player.getZ(), player.getCamX(), player.getCamY(), player.getCamZ(), 0, 1,
				0);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		// Level

		GL11.glDepthFunc(GL11.GL_LEQUAL);
		GL11.glEnable(GL11.GL_DEPTH_TEST);

		for (byte i = 0; i < level.getSizeX(); i += 1) {
			for (byte j = 0; j < level.getSizeY(); j += 1) {
				for (byte k = 0; k < level.getSizeZ(); k += 1) {
					if (level.getCubeName(i, j, k).equals(Cube.CUBE_BOMB)) {
						objects.DrawCubeBomb(i * SIZE_OF_CUBE, j * SIZE_OF_CUBE, k * SIZE_OF_CUBE);
					} else if (level.getCubeName(i, j, k).equals(Cube.CUBE_EXPLOSION)) {
						objects.DrawCubeExplosion(i * SIZE_OF_CUBE, j * SIZE_OF_CUBE, k * SIZE_OF_CUBE);
					} else if (level.getCubeName(i, j, k).equals(Cube.CUBE_EXPLOSION_HIDE_EXIT)) {
						objects.DrawCubeExplosion(i * SIZE_OF_CUBE, j * SIZE_OF_CUBE, k * SIZE_OF_CUBE);
					} else if (level.getCubeName(i, j, k).equals(Cube.CUBE_EXPLOSION_HIDE_ITEM)) {
						objects.DrawCubeExplosion(i * SIZE_OF_CUBE, j * SIZE_OF_CUBE, k * SIZE_OF_CUBE);
					} else if (level.getCubeName(i, j, k).equals(Cube.CUBE_ITEM_HEALTH)) {
						objects.DrawCubeItemHealth(i * SIZE_OF_CUBE, j * SIZE_OF_CUBE, k * SIZE_OF_CUBE);
					} else if (level.getCubeName(i, j, k).equals(Cube.CUBE_ITEM_XTRA_BOMB)) {
						objects.DrawCubeItemXtraBomb(i * SIZE_OF_CUBE, j * SIZE_OF_CUBE, k * SIZE_OF_CUBE);
					} else if (level.getCubeName(i, j, k).equals(Cube.CUBE_ITEM_BOMB_RANGE)) {
						objects.DrawCubeItemBombRange(i * SIZE_OF_CUBE, j * SIZE_OF_CUBE, k * SIZE_OF_CUBE);
					} else if (level.getCubeName(i, j, k).equals(Cube.CUBE_ITEM_BOMB_STRENGTH)) {
						objects.DrawCubeItemBombStrength(i * SIZE_OF_CUBE, j * SIZE_OF_CUBE, k * SIZE_OF_CUBE);
					} else if (level.getCubeName(i, j, k).equals(Cube.CUBE_ITEM_PORTAL)) {
						objects.DrawCubeItemPortal(i * SIZE_OF_CUBE, j * SIZE_OF_CUBE, k * SIZE_OF_CUBE);
					} else if (level.getCubeName(i, j, k).equals(Cube.CUBE_ITEM_DOUBLE_SCORE)) {
						objects.DrawCubeItemDoubleScore(i * SIZE_OF_CUBE, j * SIZE_OF_CUBE, k * SIZE_OF_CUBE);
					} else if (level.getCubeName(i, j, k).equals(Cube.CUBE_SOLID)) {
						objects.DrawCubeSolid(i * SIZE_OF_CUBE, j * SIZE_OF_CUBE, k * SIZE_OF_CUBE);
					} else if (level.getCubeName(i, j, k).equals(Cube.CUBE_OUTSIDE)) {
						objects.DrawCubeOutside(i * SIZE_OF_CUBE, j * SIZE_OF_CUBE, k * SIZE_OF_CUBE);
					} else if (level.getCubeName(i, j, k).equals(Cube.CUBE_OBSTACLE)) {
						objects.DrawCubeObstacle(i * SIZE_OF_CUBE, j * SIZE_OF_CUBE, k * SIZE_OF_CUBE);
					} else if (level.getCubeName(i, j, k).equals(Cube.CUBE_OBSTACLE_HIDE_EXIT)) {
						objects.DrawCubeObstacle(i * SIZE_OF_CUBE, j * SIZE_OF_CUBE, k * SIZE_OF_CUBE);
					} else if (level.getCubeName(i, j, k).equals(Cube.CUBE_EXIT)) {
						objects.DrawCubeExit(i * SIZE_OF_CUBE, j * SIZE_OF_CUBE, k * SIZE_OF_CUBE);
					} else if (level.getCubeName(i, j, k).equals(Cube.CUBE_SOLID_RAMP)) {
						objects.DrawCubeRamp(i * SIZE_OF_CUBE, j * SIZE_OF_CUBE, k * SIZE_OF_CUBE);
						// Menüwürfel
					} else if (level.getCubeName(i, j, k).equals(Cube.MENU_CUBE_NEW_GAME)) {
						objects.DrawMenuCubeNewGame(i * SIZE_OF_CUBE, j * SIZE_OF_CUBE, k * SIZE_OF_CUBE);
					} else if (level.getCubeName(i, j, k).equals(Cube.MENU_CUBE_NEW_GAME_GRAVITY)) {
						objects.DrawMenuCubeNewGameGravity(i * SIZE_OF_CUBE, j * SIZE_OF_CUBE, k * SIZE_OF_CUBE);
					} else if (level.getCubeName(i, j, k).equals(Cube.MENU_CUBE_EXIT_PROGRAM)) {
						objects.DrawMenuCubeExitProgram(i * SIZE_OF_CUBE, j * SIZE_OF_CUBE, k * SIZE_OF_CUBE);
					} else if (level.getCubeName(i, j, k).equals(Cube.MENU_CUBE_MULTI)) {
						objects.DrawMenuCubeMulti(i * SIZE_OF_CUBE, j * SIZE_OF_CUBE, k * SIZE_OF_CUBE);
					} else if (level.getCubeName(i, j, k).equals(Cube.MENU_CUBE_SERVER)) {
						objects.DrawMenuCubeServer(i * SIZE_OF_CUBE, j * SIZE_OF_CUBE, k * SIZE_OF_CUBE);
					} else if (level.getCubeName(i, j, k).equals(Cube.MENU_CUBE_OPTIONS)) {
						objects.DrawMenuCubeOptions(i * SIZE_OF_CUBE, j * SIZE_OF_CUBE, k * SIZE_OF_CUBE);
					} else if (level.getCubeName(i, j, k).equals(Cube.MENU_CUBE_LOAD_LEVEL)) {
						objects.DrawMenuCubeLoadLevel(i * SIZE_OF_CUBE, j * SIZE_OF_CUBE, k * SIZE_OF_CUBE);
					}
				}
			}
		}
		if (listPlayer != null) {
			for (int i = 0; i < listPlayer.size(); i++) {
				if (listPlayer.get(i).getNumber() != player.getNumber()) {
					objects.DrawPlayer(listPlayer.get(i).getX() - (SIZE_OF_CUBE / 2), listPlayer.get(i).getY()
							- (SIZE_OF_CUBE / 2), listPlayer.get(i).getZ() - (SIZE_OF_CUBE / 2));
				}
			}
		}

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();

		// GL11.glOrtho(-CLIPSIZE, +CLIPSIZE, -CLIPSIZE, +CLIPSIZE, -CLIPSIZE *
		// 100.0f, +CLIPSIZE * 100.0f);
		// GL11.glOrtho(-CLIPSIZE, +CLIPSIZE, -CLIPSIZE, +CLIPSIZE, -CLIPSIZE,
		// +CLIPSIZE);
		GL11.glOrtho(-Window.width / 2, +Window.width / 2, -Window.height / 2, +Window.height / 2, -CLIPSIZE, +CLIPSIZE);

		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		// GL11.glTranslatef(1f, 1f, 0.0f);

		GL11.glDisable(GL11.GL_DEPTH_TEST);

		hud.renderHUD();

		GL11.glFlush();
	}

	public void init() {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_LIGHT0);

		// Textur

		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		GL11.glEnable(GL11.GL_TEXTURE_2D);

		// Alpha-Farbkanal, Transparenz, einschalten
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glEnable(GL11.GL_LINE_SMOOTH); // Antialiasing fuer Linien
											// einschalten

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
	}

}
