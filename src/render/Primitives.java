package render;
/**
 * Diese Klasse stellt OpenGLObjekte die für das Spiel benötigt werden zur Verfügung.
 * -Würfel(Fest,Zerstörbar,...)
 * -Spielfiguren
 * -Bomben
 * -Items(Bonusgegenstände)
 */
import javax.media.opengl.GL;

public class Primitives {
	/**
	 * Zeichnet einen Würfel dessen Mittelpunkt die übergebene Position ist. Die
	 * aktuelle glColor wird verwendet.
	 * 
	 * @param gl
	 *            GL-Drawable
	 * @param x
	 *            horizontale Position
	 * @param y
	 *            vertikale Position
	 * @param z
	 *            Tiefenposition
	 * @param size
	 *            Kantenlänge des Würfels
	 */
	static public void DrawCube(GL gl, float x, float y, float z, float size) {
		size /= 2;
		gl.glBegin(GL.GL_QUADS);
		// Vorne
		gl.glVertex3f(x - size, y - size, z - size);
		gl.glVertex3f(x - size, y + size, z - size);
		gl.glVertex3f(x + size, y + size, z - size);
		gl.glVertex3f(x + size, y - size, z - size);
		// Hinten
		gl.glVertex3f(x - size, y - size, z + size);
		gl.glVertex3f(x + size, y - size, z + size);
		gl.glVertex3f(x + size, y + size, z + size);
		gl.glVertex3f(x - size, y + size, z + size);
		// Rechts
		gl.glVertex3f(x + size, y - size, z + size);
		gl.glVertex3f(x + size, y + size, z + size);
		gl.glVertex3f(x + size, y + size, z - size);
		gl.glVertex3f(x + size, y - size, z - size);
		// Links
		gl.glVertex3f(x - size, y - size, z + size);
		gl.glVertex3f(x - size, y + size, z + size);
		gl.glVertex3f(x - size, y + size, z - size);
		gl.glVertex3f(x - size, y - size, z - size);
		// Oben
		gl.glVertex3f(x - size, y + size, z + size);
		gl.glVertex3f(x + size, y + size, z + size);
		gl.glVertex3f(x + size, y + size, z - size);
		gl.glVertex3f(x - size, y + size, z - size);
		// Unten
		gl.glVertex3f(x - size, y - size, z + size);
		gl.glVertex3f(x + size, y - size, z + size);
		gl.glVertex3f(x + size, y - size, z - size);
		gl.glVertex3f(x - size, y - size, z - size);
		gl.glEnd();
		gl.glColor3f(0, 0, 0);
		gl.glBegin(GL.GL_LINE_LOOP);
		// Vorne
		gl.glVertex3f(x - size, y - size, z - size);
		gl.glVertex3f(x - size, y + size, z - size);
		gl.glVertex3f(x + size, y + size, z - size);
		gl.glVertex3f(x + size, y - size, z - size);
		gl.glEnd();
		gl.glBegin(GL.GL_LINE_LOOP);
		// Hinten
		gl.glVertex3f(x - size, y - size, z + size);
		gl.glVertex3f(x + size, y - size, z + size);
		gl.glVertex3f(x + size, y + size, z + size);
		gl.glVertex3f(x - size, y + size, z + size);
		gl.glEnd();
		gl.glBegin(GL.GL_LINE_LOOP);
		// Rechts
		gl.glVertex3f(x + size, y - size, z + size);
		gl.glVertex3f(x + size, y + size, z + size);
		gl.glVertex3f(x + size, y + size, z - size);
		gl.glVertex3f(x + size, y - size, z - size);
		gl.glEnd();
		gl.glBegin(GL.GL_LINE_LOOP);
		// Links
		gl.glVertex3f(x - size, y - size, z + size);
		gl.glVertex3f(x - size, y + size, z + size);
		gl.glVertex3f(x - size, y + size, z - size);
		gl.glVertex3f(x - size, y - size, z - size);
		gl.glEnd();
		gl.glBegin(GL.GL_LINE_LOOP);
		// Oben
		gl.glVertex3f(x - size, y + size, z + size);
		gl.glVertex3f(x + size, y + size, z + size);
		gl.glVertex3f(x + size, y + size, z - size);
		gl.glVertex3f(x - size, y + size, z - size);
		gl.glEnd();
		gl.glBegin(GL.GL_LINE_LOOP);
		// Unten
		gl.glVertex3f(x - size, y - size, z + size);
		gl.glVertex3f(x + size, y - size, z + size);
		gl.glVertex3f(x + size, y - size, z - size);
		gl.glVertex3f(x - size, y - size, z - size);
		gl.glEnd();
	}

	/**
	 * Zeichnet einen Würfel dessen Mittelpunkt die übergebene Position ist. Die
	 * aktuelle glColor wird verwendet.
	 * 
	 * @param gl
	 *            GL-Drawable
	 * @param x
	 *            horizontale Position
	 * @param y
	 *            vertikale Position
	 * @param z
	 *            Tiefenposition
	 * @param sizeX
	 *            Breite
	 * @param sizeY
	 *            Höhe
	 * @param sizeZ
	 *            Tiefe
	 */
	static public void DrawCube(GL gl, float x, float y, float z, float sizeX,
			float sizeY, float sizeZ) {
		sizeX /= 2;
		sizeY /= 2;
		sizeZ /= 2;
		gl.glBegin(GL.GL_QUADS);
		// Vorne
		gl.glVertex3f(x - sizeX, y - sizeY, z - sizeZ);
		gl.glVertex3f(x - sizeX, y + sizeY, z - sizeZ);
		gl.glVertex3f(x + sizeX, y + sizeY, z - sizeZ);
		gl.glVertex3f(x + sizeX, y - sizeY, z - sizeZ);
		// Hinten
		gl.glVertex3f(x - sizeX, y - sizeY, z + sizeZ);
		gl.glVertex3f(x + sizeX, y - sizeY, z + sizeZ);
		gl.glVertex3f(x + sizeX, y + sizeY, z + sizeZ);
		gl.glVertex3f(x - sizeX, y + sizeY, z + sizeZ);
		// Rechts
		gl.glVertex3f(x + sizeX, y - sizeY, z + sizeZ);
		gl.glVertex3f(x + sizeX, y + sizeY, z + sizeZ);
		gl.glVertex3f(x + sizeX, y + sizeY, z - sizeZ);
		gl.glVertex3f(x + sizeX, y - sizeY, z - sizeZ);
		// Links
		gl.glVertex3f(x - sizeX, y - sizeY, z + sizeZ);
		gl.glVertex3f(x - sizeX, y + sizeY, z + sizeZ);
		gl.glVertex3f(x - sizeX, y + sizeY, z - sizeZ);
		gl.glVertex3f(x - sizeX, y - sizeY, z - sizeZ);
		// Oben
		gl.glVertex3f(x - sizeX, y + sizeY, z + sizeZ);
		gl.glVertex3f(x + sizeX, y + sizeY, z + sizeZ);
		gl.glVertex3f(x + sizeX, y + sizeY, z - sizeZ);
		gl.glVertex3f(x - sizeX, y + sizeY, z - sizeZ);
		// Unten
		gl.glVertex3f(x - sizeX, y - sizeY, z + sizeZ);
		gl.glVertex3f(x + sizeX, y - sizeY, z + sizeZ);
		gl.glVertex3f(x + sizeX, y - sizeY, z - sizeZ);
		gl.glVertex3f(x - sizeX, y - sizeY, z - sizeZ);
		gl.glEnd();
	}
}
