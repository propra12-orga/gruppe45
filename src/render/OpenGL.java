package render;

/**
 * Hier werden Level- und Playerinformationen zusammengeführt und in OpenGL ausgegeben.
 */
import game.Level;
import game.Player;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

public class OpenGL implements GLEventListener {

	final int width = 400;
	final int height = 400;
	float x = 0.0f;
	float y = 0.0f;
	float z = 0.0f;
	Level level = new Level();
	Player player;

	public OpenGL(Player player) {
		this.player = player;
	}

	/**
	 * Wird ausgeführt solange das Fenster sichtbar ist
	 */
	@Override
	public void display(GLAutoDrawable drawable) {
		GL gl = drawable.getGL();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

		GLU glu = new GLU();
		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();
		float widthHeightRatio = width / height;
		glu.gluPerspective(45, widthHeightRatio, 1, 1000);
		glu.gluLookAt(player.getX(), player.getY(), player.getZ(),
				player.getCamX(), player.getCamY(),
				player.getCamZ(), 0,
				1, 0);
		gl.glMatrixMode(GL.GL_MODELVIEW);
		gl.glLoadIdentity();
		gl.glBegin(GL.GL_QUADS);
		for (byte i = 0; i < 10; i += 1) {
			for (byte j = 0; j < 10; j += 1) {
				for (byte k = 0; k < 10; k += 1) {
					if (level.getCubeNumber(i, j, k) == Level.CUBE_NR_SOLID) {
						gl.glColor3f(j / 20f + 0.2f, j / 20f + 0.2f, 1f);
						Primitives.DrawCube(gl, i * 10, j * 10, k * 10, 10f);
					}
				}
			}
		}
		gl.glColor3f(1, 0, 0);
		Primitives.DrawCube(gl, 9, 0, 0, 10, 1, 1);
		gl.glColor3f(0, 1, 0);
		Primitives.DrawCube(gl, 0, 9, 0, 1, 10, 1);
		gl.glColor3f(0, 0, 1);
		Primitives.DrawCube(gl, 0, 0, 9, 1, 1, 10);
		gl.glColor3f(0.8f, 0.8f, 0.8f);
		Primitives.DrawCube(gl, 0, 0, 0, 3);
		gl.glEnd();

		gl.glFlush();
	}

	@Override
	public void displayChanged(GLAutoDrawable drawable, boolean modeChanged,
			boolean deviceChanged) {
	}

	/**
	 * Initialisiert OpenGL
	 */
	@Override
	public void init(GLAutoDrawable drawable) {
		float clipsize = 0.8f;

		GL gl = drawable.getGL();
		gl.glEnable(GL.GL_DEPTH_TEST);
		// Alpha-Farbkanal, Transparenz, einschalten
		// gl.glEnable(GL.GL_BLEND);
		// gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);

		gl.glEnable(GL.GL_LINE_SMOOTH); // Antialiasing für Linien einschalten

		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrtho(-clipsize, +clipsize, -clipsize, +clipsize,
				-clipsize * 100.0f, +clipsize * 100.0f);
		gl.glViewport(0, 0, width, height);
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
			int height) {
	}

}
