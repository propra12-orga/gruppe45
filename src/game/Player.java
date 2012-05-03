package game;

/**
 * Die Klasse Player enthält die Position des Spielers die von Control-Klassen
 * verändert und von der OpenGL-Klasse aufgerufen wird um die Spieler an der
 * richtigen Position darstellen zu können. Außerdem wird Farbe und Art der
 * eingesammelten Items hier gespeichert.
 * 
 * @author felidosz
 * 
 */
public class Player {
	private float x, y, z;
	private float stepSize = 0.1f;
	private float[] color;

	/**
	 * Konstruktor, setzt die Spielerposition auf (0,0,0)
	 */
	public Player(float[] color) {
		this.x = 0;
		this.y = 0;
		this.z = 0;
		this.color = color;
	}

	/**
	 * @return X-Postion des Spielers
	 */
	public float getX() {
		return this.x;
	}

	/**
	 * @return Y-Postion des Spielers
	 */
	public float getY() {
		return this.y;
	}

	/**
	 * @return Z-Postion des Spielers
	 */
	public float getZ() {
		return this.z;
	}

	/**
	 * Setzt den Spieler an eine bestimmt Position
	 * 
	 * @param x
	 *            Horizontale Position
	 * @param y
	 *            Vertikale Position
	 * @param z
	 *            Tiefenposition
	 */
	// FIXME PlayerSetPosition() Prüfen ob Platz an der Stelle ist
	public void setPosition(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void moveUp() {
		move(0, stepSize, 0);
	}

	public void moveDown() {
		move(0, -stepSize, 0);
	}

	public void moveForward() {
		move(0, 0, stepSize);
	}

	public void moveBackward() {
		move(0, 0, -stepSize);
	}

	public void moveLeft() {
		move(stepSize, 0, 0);
	}

	public void moveRight() {
		move(-stepSize, 0, 0);
	}

	// FIXME PlayerMove() Prüfen ob Platz an der Stelle ist
	private void move(float x, float y, float z) {
		this.x += x;
		this.y += y;
		this.z += z;
	}
	 
}
