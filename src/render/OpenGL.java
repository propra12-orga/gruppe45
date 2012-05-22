package render;

//Hier werden Level- und Playerinformationen zusammengeführt und in OpenGL ausgegeben.

import game.Level;

import game.Player;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class OpenGL {

	int width, height;
	final static public float sizeOfCube = 10;
	Level level;

	// Level level = new Level();
	Player player;

	public OpenGL(Level level, Player player, int width, int height) {
		this.level = level;
		this.player = player;
		player.setBomb();
		this.width = width;
		this.height = height;
		this.level = level;
		init();
	}

	public void display() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		float widthHeightRatio = width / height;
		GLU.gluPerspective(45, widthHeightRatio, 1, 1000);
		GLU.gluLookAt(player.getX(), player.getY(), player.getZ(),
				player.getCamX(), player.getCamY(), player.getCamZ(), 0, 1, 0);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		// Level
		for (byte i = 0; i < level.getSizeX(); i += 1) {
			for (byte j = 0; j < level.getSizeY(); j += 1) {
				for (byte k = 0; k < level.getSizeZ(); k += 1) {
					if (level.getCube(i, j, k).getClass().getName()
							.equals("game.cube.CubeBomb")) {
						GL11.glColor3f(0.5f, 0.5f, 0.5f);
						Primitives.DrawCube(i * sizeOfCube, j * sizeOfCube, k
								* sizeOfCube, sizeOfCube * 0.5f);
					} else if (level.getCube(i, j, k).getClass().getName()
							.equals("game.cube.CubeExplosion")) {
						GL11.glColor3f(1f, 0f, 0f);
						Primitives.DrawCube(i * sizeOfCube, j * sizeOfCube, k
								* sizeOfCube, sizeOfCube * 0.8f);
					} else if (level.getCube(i, j, k).getClass().getName()
							.equals("game.cube.CubeItemHealth")) {
						GL11.glColor3f(1f, 0f, 0f);
						Primitives.DrawCube(i * sizeOfCube, j * sizeOfCube, k
								* sizeOfCube, sizeOfCube * 0.1f);
					} else if (level.getCube(i, j, k).getClass().getName()
							.equals("game.cube.CubeSolid")) {
						GL11.glColor3f(j / 20f + 0.2f, j / 20f + 0.2f, 1f);
						Primitives.DrawCube(i * sizeOfCube, j * sizeOfCube, k
								* sizeOfCube, sizeOfCube);
					}
				}
			}
		}
		// Koordinatenachsen
		GL11.glColor3f(1, 0, 0);// Farbe rot auswählen
		Primitives.DrawCube(9, 0, 0, 10, 1, 1);
		GL11.glColor3f(0, 1, 0);// Farbe gruen auswählen
		Primitives.DrawCube(0, 9, 0, 1, 10, 1);
		GL11.glColor3f(0, 0, 1);// Farbe blau auswählen
		Primitives.DrawCube(0, 0, 9, 1, 1, 10);
		GL11.glColor3f(0.8f, 0.8f, 0.8f);// Farbe weiß auswählen
		Primitives.DrawCube(0, 0, 0, 3);

		GL11.glFlush();
	}

	public void init() {
		float clipsize = 0.8f;

		GL11.glEnable(GL11.GL_DEPTH_TEST);
		// Alpha-Farbkanal, Transparenz, einschalten
		// GL11.glEnable(GL11.GL_BLEND);
		// GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glEnable(GL11.GL_LINE_SMOOTH); // Antialiasing für Linien
											// einschalten

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(-clipsize, +clipsize, -clipsize, +clipsize,
				-clipsize * 100.0f, +clipsize * 100.0f);
		GL11.glViewport(0, 0, width, height);
	}

}
