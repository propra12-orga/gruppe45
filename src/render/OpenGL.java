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
		glu.gluLookAt(0, 0, 100, 0, 0, 0, 0, 1, 0);
		gl.glRotated(0, 0.0, 1.0, 0.0);
		gl.glMatrixMode(GL.GL_MODELVIEW);
		gl.glBegin(GL.GL_QUADS);
		for (byte i = -5; i < 5; i += 1) {
			for (byte j = -5; j < 5; j += 1) {
				for (byte k = -5; k < 5; k += 1) {
					if (level.getCubeNumber(i + 5, j + 5, k + 5) == Level.CUBE_NR_SOLID) {
						gl.glColor4b((byte) (i * 10 + 50),
								(byte) (j * 10 + 50), (byte) (k * 10 + 50),
								(byte) 127);
						Primitives.DrawCube(gl, i * 10 + player.getX() * 10, j
								* 10 + player.getY() * 10,
								k * 10 + player.getZ() * 10, 10f);
					}
				}
			}
		}
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
		// Alpha-Farbkanal, Transparenz, einschalten
		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glEnable(GL.GL_BLEND);
		gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);

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
