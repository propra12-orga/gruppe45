package game;

/**
 * Diese Klasse speichert eine GL-Farbe in einem byte-Array und enthält
 * Standardfarben Mit Alpha-Kanal
 * 
 * @author felidosz
 * 
 */
public class GLColor4f {
	private float[] glColor = { 0, 0, 0, 0 };
	final public static float[] GL_COLOR4f_BLACK = { 0, 0, 0, 0 };
	final public static float[] GL_COLOR4f_RED = { 1, 0, 0, 0 };
	final public static float[] GL_COLOR4f_GREEN = { 0, 1, 0, 0 };
	final public static float[] GL_COLOR4f_BLUE = { 0, 0, 1, 0 };
	final public static float[] GL_COLOR4f_WHITE = { 1, 1, 1, 0 };

	/**
	 * 
	 * @param r
	 *            Rot
	 * @param g
	 *            Grün
	 * @param b
	 *            Blau
	 * @param a
	 *            Alpha-Transparenz
	 */
	public GLColor4f(float r, float g, float b, float a) {
		glColor[0] = r;
		glColor[1] = g;
		glColor[2] = b;
		glColor[3] = a;
	}

	public float[] getGLColor4f() {
		return glColor;
	}
}
