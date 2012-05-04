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
	final private double PI_DIV_2 = (Math.PI / 2);
	private float x, y, z;
	private float stepSize = 1f;
	private float[] color;
	private float angleY = 0;
	private float angleX = 0;

	/**
	 * Der Konstruktor verlangt die Anfangsposition
	 */
	public Player(float x, float y, float z) {
		setPosition(x, y, z);
	}
	
	public void setColor(float[] color) {
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
	 * @return X-Position des Camerasichtpunktes
	 */
	public float getCamX() {
		return this.x + (float) Math.sin(angleY);
	}

	/**
	 * @return Y-Position des Camerasichtpunktes
	 */
	// FIXME Ueberlegen ob die ganze Rechnung sein muss
	public float getCamY() {
		return this.y
				+ (float) (Math.sin(angleX)
				* Math.sqrt(Math.sin(angleY) * Math.sin(angleY)
 + Math.cos(angleY)
						* Math.cos(angleY)));
	}

	/**
	 * @return Z-Position des Camerasichtpunktes
	 */
	public float getCamZ() {
		return this.z + (float) Math.cos(angleY);
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

	public void turnUp() {
		if (this.angleX < PI_DIV_2) {
			this.angleX += 0.1;
		}
	}

	public void turnDown() {
		if (this.angleX > -PI_DIV_2) {
			this.angleX -= 0.1;
		}
	}

	public void turnRight() {
		this.angleY -= 0.1f;
	}

	public void turnLeft() {
		this.angleY += 0.1f;
	}

	public void moveForward() {
		move((float) Math.sin(angleY) * stepSize,
				(float) (Math.sin(angleX) * Math.sqrt(Math.sin(angleY)
						* Math.sin(angleY) + Math.cos(angleY)
						* Math.cos(angleY)))
						* stepSize, (float) Math.cos(angleY)
				* stepSize);
	}

	public void moveBackward() {
		move((float) Math.sin(angleY) * -stepSize,
				(float) (Math.sin(angleX) * Math.sqrt(Math.sin(angleY)
						* Math.sin(angleY) + Math.cos(angleY)
						* Math.cos(angleY)))
						* -stepSize, (float) Math.cos(angleY) * -stepSize);
	}

	public void moveLeft() {
		move((float) Math.sin(angleY + PI_DIV_2) * stepSize, 0,
				(float) Math.cos(angleY + PI_DIV_2)
				* stepSize);
	}

	public void moveRight() {
		move((float) Math.sin(angleY - PI_DIV_2) * stepSize, 0,
				(float) Math.cos(angleY - PI_DIV_2)
				* stepSize);
	}

	// FIXME PlayerMove() Prüfen ob Platz an der Stelle ist
	private void move(float x, float y, float z) {
		this.x += x;
		this.y += y;
		this.z += z;
	}
	 
}
