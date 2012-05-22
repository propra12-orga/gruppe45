package game;

import game.cube.CubeBomb;
import game.cube.CubeEmpty;
import game.cube.CubeExplosion;

import java.util.Timer;

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
		ArrayPosition[] posExp = {
				new ArrayPosition((int) x / 10, (int) y / 10, (int) z / 10),
				new ArrayPosition((int) x / 10 - 1, (int) y / 10, (int) z / 10),
				new ArrayPosition((int) x / 10 + 1, (int) y / 10, (int) z / 10),
				new ArrayPosition((int) x / 10, (int) y / 10 - 1, (int) z / 10),
				new ArrayPosition((int) x / 10, (int) y / 10 + 1, (int) z / 10),
				new ArrayPosition((int) x / 10, (int) y / 10, (int) z / 10 - 1),
				new ArrayPosition((int) x / 10, (int) y / 10, (int) z / 10 + 1) };
		Timer timer = new Timer();
		// TODO Hier wird die Position nur durch abrunden ermittelt,
		// Druchschnitt waere wohl besser
		level.setCube(new CubeBomb(), (int) x / 10, (int) y / 10, (int) z / 10);

		timer.schedule(new TimeCube(level, new CubeExplosion(), posExp),
				fuseTime);
		timer.schedule(new TimeCube(level, new CubeEmpty(), posExp), fuseTime
				+ explosionTime);

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
		return this.x + (float) Math.sin(angleY);
	}

	public float getDirectionX() {
		return (float) Math.sin(angleY);
	}

	/**
	 * @return Y-Position des Camerasichtpunktes
	 */
	// FIXME Ueberlegen ob die ganze Rechnung sein muss
	public float getCamY() {
		return this.y
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
		return this.z + (float) Math.cos(angleY);
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
	// FIXME PlayerSetPosition() Prüfen ob Platz an der Stelle ist
	public void setPosition(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void turnUp() {
		if (this.angleX < PI_DIV_2) {
			this.angleX += 0.006;
		}
		
	}

	public void turnDown() {
		if (this.angleX > -PI_DIV_2) {
			this.angleX -= 0.006;
		}		
	}

	public void turnRight() {
		this.angleY -= 0.006f;
	}

	public void turnLeft() {
		this.angleY += 0.006f;
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

	// FIXME PlayerMove() Prüfen ob Platz an der Stelle ist
	private void move(float x, float y, float z) {
		/*
		 * Wie waere es mal mit TESTEN??? int tmpCubeX = (int) (this.x + x) /
		 * 10; int tmpCubeY = (int) (this.y + y) / 10; int tmpCubeZ = (int)
		 * (this.z + z) / 10; if (level.getCube(tmpCubeX, tmpCubeY,
		 * tmpCubeZ).isWalkable()) {
		 */
		this.x += x;
		this.y += y;
		this.z += z;
		/* } */
	}
}
