package game;

import game.cube.Cube;

import java.util.Timer;

/**
 * Die Klasse Player enthaelt die Position des Spielers die von Control-Klassen
 * veraendert und von der OpenGL-Klasse aufgerufen wird um die Spieler an der
 * richtigen Position darstellen zu koennen. Ausserdem wird Farbe und Art der
 * eingesammelten Items hier gespeichert.
 * 
 * @author felidosz
 * 
 */
public class Player {
	
	final static public int MAX_HEALTH_POINTS = 100;
	
	private int number = 0;

	final private double PI_DIV_2 = (Math.PI / 2);
	protected float x = 0, y = 0, z = 0;
	private float stepSize = 1f;
	private float[] color;
	private float angleY = 0;
	private float angleX = 0;

	private Level level;

	private int healthPoints = 100;
	int radius = 3;
	int maxBombs = 1;
	int fuseTime = 3000;
	int explosionTime = 1000;

	/**
	 * Der Konstruktor verlangt die Anfangsposition
	 */
	public Player(Level level, float x, float y, float z) {
		setPosition(x, y, z);
		this.level = level;
	}

	public Player(Level level, float x, float y, float z, int number) {
		setPosition(x, y, z);
		this.level = level;
	}

	public int getNumber() {
		return number;
	}

	/**
	 * Nur zum Speichern der Spielerposition im Server
	 */

	public void setBomb() {
		setBomb((int) (x / 10), (int) (y / 10), (int) (z / 10));
	}

	public void setBomb(int x, int y, int z) {
		if (maxBombs > 0) {
			maxBombs--;
			ArrayPosition[] posExp = { new ArrayPosition((int) x, (int) y, (int) z),
					new ArrayPosition((int) x - 1, (int) y, (int) z), new ArrayPosition((int) x + 1, (int) y, (int) z),
					new ArrayPosition((int) x, (int) y - 1, (int) z), new ArrayPosition((int) x, (int) y + 1, (int) z),
					new ArrayPosition((int) x, (int) y, (int) z - 1), new ArrayPosition((int) x, (int) y, (int) z + 1) };
			Timer timer = new Timer();
			level.setCube(Cube.getCubeByName(Cube.CUBE_BOMB), (int) x, (int) y, (int) z);

			// Explosion
			timer.schedule(new TimeCube(level, Cube.getCubeByName(Cube.CUBE_EXPLOSION), posExp, this), fuseTime);
			// Leerer Block
			timer.schedule(new TimeCube(level, Cube.getCubeByName(Cube.CUBE_EMPTY), posExp, this), fuseTime + explosionTime);
			// Verhindern, dass mehr Bomben gelegt werden als maxBombs erlaubt.
			timer.schedule(new BombCount(this, maxBombs), fuseTime + explosionTime + 10);
		}
	}

	public void setColor(float[] color) {
		this.color = color;
	}

	public float[] getColor() {
		return this.color;
	}

	public void healPlayer(int healPoints) {
		healthPoints += healPoints;
	}
	
	public void setMaxHealth(){
		healthPoints = MAX_HEALTH_POINTS;
	}
	

	public void hitPlayer(int hitPoints) {
		healthPoints -= hitPoints;
	}

	public boolean isLiving() {
		if (healthPoints > 0) {
			return true;
		} else {
			return false;
		}
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
	 * @return X-Koordinate im Levelarray des Spielers
	 */
	public int getCubeX() {
		return (int) this.x / 10;
	}

	/**
	 * @return Y-Koordinate im Levelarray des Spielers
	 */
	public int getCubeY() {
		return (int) this.y / 10;
	}

	/**
	 * @return Z-Koordinate im Levelarray des Spielers
	 */
	public int getCubeZ() {
		return (int) this.z / 10;
	}

	/**
	 * @return X-Position des Camerasichtpunktes
	 */
	public float getCamX() {
		return getX() + (float) Math.sin(angleY);
	}

	public float getDirectionX() {
		return (float) Math.sin(angleY);
	}

	/**
	 * @return Y-Position des Camerasichtpunktes
	 */
	// FIXME Ueberlegen ob die ganze Rechnung sein muss
	public float getCamY() {
		return getY()
				+ (float) (Math.sin(angleX) * Math.sqrt(Math.sin(angleY) * Math.sin(angleY) + Math.cos(angleY)
						* Math.cos(angleY)));
	}

	public float getDirectionY() {
		return (float) (Math.sin(angleX) * Math.sqrt(Math.sin(angleY) * Math.sin(angleY) + Math.cos(angleY) * Math.cos(angleY)));
	}

	/**
	 * @return Z-Position des Camerasichtpunktes
	 */
	public float getCamZ() {
		return getZ() + (float) Math.cos(angleY);
	}

	public float getDirectionZ() {
		return (float) Math.cos(angleY);
	}

	/**
	 * @return Anzahl der Healthpoints
	 */

	public int getHealthPoints(){
		return this.healthPoints;
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
	public void setPosition(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void setAngleX(float angleX) {
		this.angleX = angleX;
	}

	public void setAngleY(float angleY) {
		this.angleY = angleY;
	}

	/**
	 * Sich um die X-Achse drehen
	 */
	public void pitch(float pitch) {
		angleX += pitch;
		if (angleX > PI_DIV_2) {
			angleX = (float) PI_DIV_2;
		} else if (angleX < -PI_DIV_2) {
			angleX = (float) -PI_DIV_2;
		}
	}

	/**
	 * Sich um die Y-Achse drehen
	 */
	public void yaw(float yaw) {
		this.angleY += yaw;
	}

	public void turnUp(float p) {
		if (this.angleX < PI_DIV_2) {
			this.angleX += p;// 0.009;
		}
	}

	public void turnDown(float q) {
		if (this.angleX > -PI_DIV_2) {
			this.angleX -= q;// 0.009;
		}
	}

	public void turnRight(float r) {
		this.angleY -= r;// 0.012f;
	}

	public void turnLeft(float s) {
		this.angleY += s;// 0.012f;
	}

	public void moveForward() {
		move((float) Math.sin(angleY) * stepSize,
				(float) (Math.sin(angleX) * Math.sqrt(Math.sin(angleY) * Math.sin(angleY) + Math.cos(angleY) * Math.cos(angleY)))
						* stepSize, (float) Math.cos(angleY) * stepSize);
	}

	public void moveBackward() {
		move((float) Math.sin(angleY) * -stepSize,
				(float) (Math.sin(angleX) * Math.sqrt(Math.sin(angleY) * Math.sin(angleY) + Math.cos(angleY) * Math.cos(angleY)))
						* -stepSize, (float) Math.cos(angleY) * -stepSize);
	}

	public void moveLeft() {
		move((float) Math.sin(angleY + PI_DIV_2) * stepSize, 0, (float) Math.cos(angleY + PI_DIV_2) * stepSize);
	}

	public void moveRight() {
		move((float) Math.sin(angleY - PI_DIV_2) * stepSize, 0, (float) Math.cos(angleY - PI_DIV_2) * stepSize);
	}

	public void moveDown() {
		move(0, -1, 0);
	}

	public void moveDown(float i) {
		move(0, -i, 0);
	}

	public void moveUp() {
		move(0, 1, 0);
	}

	// FIXME Player stirbt -> Programmende
	public void dies() {
		System.out.println("Du bist jetzt tot!");
		System.exit(0);
	}

	// TODO Testen, ob Abfrage funktioniert
	private void move(float x, float y, float z) {
		int tmpCubeX = (int) (this.x + x) / 10; // x-Position des ZielCubes im
												// Level
		int tmpCubeY = (int) (this.y + y) / 10; // y-Position des ZielCubes im
												// Level
		int tmpCubeZ = (int) (this.z + z) / 10; // z-Position des ZielCubes im
												// Level
		Cube cube = level.getCube(tmpCubeX, tmpCubeY, tmpCubeZ);
		if ((cube.isWalkable()) ||
		// oder naechster Schritt im gleichen Cube -> um geblockte Bloecke zu
		// verlassen
				((tmpCubeX == (int) this.x / 10) && (tmpCubeY == (int) this.y / 10) && (tmpCubeZ == (int) this.z / 10))) {
			this.x += x;
			this.y += y;
			this.z += z;
			if (cube.isCollectable()) {
				cube.change(this, level);
			}
		}
	}
}
