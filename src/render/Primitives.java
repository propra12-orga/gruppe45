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
	 * Zeichnet einen Würfel an die übergebene Stelle. Die aktuelle OpenGLColor
	 * wird verwendet
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
	}
}
