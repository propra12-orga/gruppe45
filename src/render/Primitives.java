package render;

import org.lwjgl.opengl.GL11;

/**
 * Diese Klasse stellt OpenGLObjekte die fuer das Spiel benoetigt werden zur
 * Verfuegung. -Wuerfel(Fest,Zerstoerbar,...) -Spielfiguren -Bomben
 * -Items(Bonusgegenstaende)
 */

public class Primitives {
	/**
	 * Zeichnet einen Wuerfel dessen Mittelpunkt die uebergebene Position ist.
	 * Die aktuelle glColor wird verwendet.
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
	static public void DrawCube(float x, float y, float z, float size) {
		GL11.glBegin(GL11.GL_QUADS);
		// Vorne
		GL11.glVertex3f(x, y, z);
		GL11.glVertex3f(x, y + size, z);
		GL11.glVertex3f(x + size, y + size, z);
		GL11.glVertex3f(x + size, y, z);
		// Hinten
		GL11.glVertex3f(x, y, z + size);
		GL11.glVertex3f(x + size, y, z + size);
		GL11.glVertex3f(x + size, y + size, z + size);
		GL11.glVertex3f(x, y + size, z + size);
		// Rechts
		GL11.glVertex3f(x + size, y, z + size);
		GL11.glVertex3f(x + size, y + size, z + size);
		GL11.glVertex3f(x + size, y + size, z);
		GL11.glVertex3f(x + size, y, z);
		// Links
		GL11.glVertex3f(x, y, z + size);
		GL11.glVertex3f(x, y + size, z + size);
		GL11.glVertex3f(x, y + size, z);
		GL11.glVertex3f(x, y, z);
		// Oben
		GL11.glVertex3f(x, y + size, z + size);
		GL11.glVertex3f(x, y + size, z);
		GL11.glVertex3f(x + size, y + size, z);
		GL11.glVertex3f(x + size, y + size, z + size);
		// Unten
		GL11.glVertex3f(x, y, z + size);
		GL11.glVertex3f(x + size, y, z + size);
		GL11.glVertex3f(x + size, y, z);
		GL11.glVertex3f(x, y, z);
		GL11.glEnd();
		/*
		 * Rand am Wuerfel GL11.glColor3f(0, 0, 0);
		 * GL11.glBegin(GL11.GL_LINE_LOOP); // Vorne GL11.glVertex3f(x, y, z);
		 * GL11.glVertex3f(x, y + size, z); GL11.glVertex3f(x + size, y + size,
		 * z); GL11.glVertex3f(x + size, y, z); GL11.glEnd();
		 * GL11.glBegin(GL11.GL_LINE_LOOP); // Hinten GL11.glVertex3f(x, y, z +
		 * size); GL11.glVertex3f(x + size, y, z + size); GL11.glVertex3f(x +
		 * size, y + size, z + size); GL11.glVertex3f(x, y + size, z + size);
		 * GL11.glEnd(); GL11.glBegin(GL11.GL_LINE_LOOP); // Rechts
		 * GL11.glVertex3f(x + size, y, z + size); GL11.glVertex3f(x + size, y +
		 * size, z + size); GL11.glVertex3f(x + size, y + size, z);
		 * GL11.glVertex3f(x + size, y, z); GL11.glEnd();
		 * GL11.glBegin(GL11.GL_LINE_LOOP); // Links GL11.glVertex3f(x, y, z +
		 * size); GL11.glVertex3f(x, y + size, z + size); GL11.glVertex3f(x, y +
		 * size, z); GL11.glVertex3f(x, y, z); GL11.glEnd();
		 * GL11.glBegin(GL11.GL_LINE_LOOP); // Oben GL11.glVertex3f(x, y + size,
		 * z + size); GL11.glVertex3f(x + size, y + size, z + size);
		 * GL11.glVertex3f(x + size, y + size, z); GL11.glVertex3f(x, y + size,
		 * z); GL11.glEnd(); GL11.glBegin(GL11.GL_LINE_LOOP); // Unten
		 * GL11.glVertex3f(x, y, z + size); GL11.glVertex3f(x + size, y, z +
		 * size); GL11.glVertex3f(x + size, y, z); GL11.glVertex3f(x, y, z);
		 * GL11.glEnd();
		 */
	}

	/**
	 * Zeichnet einen Wuerfel dessen Mittelpunkt die uebergebene Position ist.
	 * Die aktuelle glColor wird verwendet.
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
	 *            Hoehe
	 * @param sizeZ
	 *            Tiefe
	 */
	static public void DrawCube(float x, float y, float z, float sizeX, float sizeY, float sizeZ) {
		GL11.glBegin(GL11.GL_QUADS);
		// Vorne
		GL11.glVertex3f(x, y, z);
		GL11.glVertex3f(x, y + sizeY, z);
		GL11.glVertex3f(x + sizeX, y + sizeY, z);
		GL11.glVertex3f(x + sizeX, y, z);
		// Hinten
		GL11.glVertex3f(x, y, z + sizeZ);
		GL11.glVertex3f(x + sizeX, y, z + sizeZ);
		GL11.glVertex3f(x + sizeX, y + sizeY, z + sizeZ);
		GL11.glVertex3f(x, y + sizeY, z + sizeZ);
		// Rechts
		GL11.glVertex3f(x + sizeX, y, z + sizeZ);
		GL11.glVertex3f(x + sizeX, y + sizeY, z + sizeZ);
		GL11.glVertex3f(x + sizeX, y + sizeY, z);
		GL11.glVertex3f(x + sizeX, y, z);
		// Links
		GL11.glVertex3f(x, y, z + sizeZ);
		GL11.glVertex3f(x, y + sizeY, z + sizeZ);
		GL11.glVertex3f(x, y + sizeY, z);
		GL11.glVertex3f(x, y, z);
		// Oben
		GL11.glVertex3f(x, y + sizeY, z + sizeZ);
		GL11.glVertex3f(x, y + sizeY, z);
		GL11.glVertex3f(x + sizeX, y + sizeY, z);
		GL11.glVertex3f(x + sizeX, y + sizeY, z + sizeZ);
		// Unten
		GL11.glVertex3f(x, y, z + sizeZ);
		GL11.glVertex3f(x + sizeX, y, z + sizeZ);
		GL11.glVertex3f(x + sizeX, y, z);
		GL11.glVertex3f(x, y, z);
		GL11.glEnd();
	}
}
