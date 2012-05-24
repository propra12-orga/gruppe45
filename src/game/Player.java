package game;

import game.cube.Cube;
import game.cube.CubeBomb;
import game.cube.CubeEmpty;
import game.cube.CubeExplosion;
import game.BombCount;

import java.util.Timer;
import java.util.TimerTask;

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
	final private double PI_DIV_2 = (Math.PI / 2);
	private float x, y, z;
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

	public void setBomb() {
		if (maxBombs > 0) {
			maxBombs --;
			ArrayPosition[] posExp = {
					new ArrayPosition((int) x / 10, (int) y / 10, (int) z / 10),
					new ArrayPosition((int) x / 10 - 1, (int) y / 10,
							(int) z / 10),
					new ArrayPosition((int) x / 10 + 1, (int) y / 10,
							(int) z / 10),
					new ArrayPosition((int) x / 10, (int) y / 10 - 1,
							(int) z / 10),
					new ArrayPosition((int) x / 10, (int) y / 10 + 1,
							(int) z / 10),
					new ArrayPosition((int) x / 10, (int) y / 10,
							(int) z / 10 - 1),
					new ArrayPosition((int) x / 10, (int) y / 10,
							(int) z / 10 + 1) };
			Timer timer = new Timer();
			// TODO Hier wird die Position nur durch abrunden ermittelt,
			// Druchschnitt waere wohl besser
			level.setCube(new CubeBomb(), (int) x / 10, (int) y / 10,
					(int) z / 10);
			
			//Explosion
			timer.schedule(new TimeCube(level, new CubeExplosion(), posExp),
					fuseTime);
			//Leerer Block
			timer.schedule(new TimeCube(level, new CubeEmpty(), posExp),
					fuseTime + explosionTime);
			//Verhindern, dass mehr Bomben gelegt werden als maxBombs erlaubt.
			timer.schedule(new BombCount(this, maxBombs) , fuseTime + explosionTime + 10);
		}
	}

	public void setColor(float[] color) {
		this.color = color;
	}

	public float[] getColor() {
		return this.color;
	}

	public void healPlayer(int healPoints) {
		healPoints += healPoints;
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
				+ (float) (Math.sin(angleX) * Math.sqrt(Math.sin(angleY)
						* Math.sin(angleY) + Math.cos(angleY)
						* Math.cos(angleY)));
	}

	public float getDirectionY() {
		return (float) (Math.sin(angleX) * Math.sqrt(Math.sin(angleY)
				* Math.sin(angleY) + Math.cos(angleY) * Math.cos(angleY)));
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
	 * Setzt den Spieler an eine bestimmt Position
	 * 
	 * @param x
	 *            Horizontale Position
	 * @param y
	 *            Vertikale Position
	 * @param z
	 *            Tiefenposition
	 */
	// FIXME PlayerSetPosition() Pruefen ob Platz an der Stelle ist
	public void setPosition(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
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
				(float) (Math.sin(angleX) * Math.sqrt(Math.sin(angleY)
						* Math.sin(angleY) + Math.cos(angleY)
						* Math.cos(angleY)))
						* stepSize, (float) Math.cos(angleY) * stepSize);
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
				(float) Math.cos(angleY + PI_DIV_2) * stepSize);
	}

	public void moveRight() {
		move((float) Math.sin(angleY - PI_DIV_2) * stepSize, 0,
				(float) Math.cos(angleY - PI_DIV_2) * stepSize);
	}

	public void moveDown() {
		move(0, -1, 0);
	}

	public void moveUp() {
		move(0, 1, 0);
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
				((tmpCubeX == (int) this.x / 10)
						&& (tmpCubeY == (int) this.y / 10) && (tmpCubeZ == (int) this.z / 10))) {
			this.x += x;
			this.y += y;
			this.z += z;
			if (cube.isCollectable()) {
				cube.change();
			}
		}
	}
}
