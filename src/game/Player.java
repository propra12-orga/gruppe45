package game;

import game.cube.Cube;
import game.cube.CubeExplosion;

import java.util.List;

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
	// TODO Menüoptionen
	// Obergrenzen für Playervariablen
	final static public int MAX_HEALTH_POINTS = 150;
	final static public int MAX_SIMULTAN_BOMBS = 5;
	final static public int MAX_BOMB_RADIUS = 5;

	private int number = 0;

	final private double PI_DIV_2 = (Math.PI / 2);
	protected float x = 0, y = 0, z = 0;
	private float stepSize = 1f;
	private float[] color;
	protected float angleY = 0;
	protected float angleX = 0;

	protected Level level;

	private int healthPoints = 100;
	int radius = 1;
	// maxBombs in bombs geaendert weil die Variable nicht die maximale Anzahl
	// Bomben darstellt sondern die Bomben die man zu Verfgung hat
	int bombs = 1;
	int fuseTime = 3000;
	int explosionTime = 1000;
	List listPlayer;

	/**
	 * Der Konstruktor verlangt die Anfangsposition
	 */
	public Player(Level level, float x, float y, float z, List listPlayer) {
		setPosition(x, y, z);
		this.level = level;
		this.listPlayer = listPlayer;
	}

	public Player(Level level, float x, float y, float z, List listPlayer, int number) {
		setPosition(x, y, z);
		this.level = level;
		this.number = number;
		this.listPlayer = listPlayer;
	}

	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
	}

	public void increaseBombs() {
		this.bombs += 1;
	}

	public void decreaseBombs() {
		this.bombs -= 1;
	}

	public int getBombs() {
		return this.bombs;
	}

	public void setBombs(int bombs) {
		this.bombs = bombs;
	}

	public void setPlayerPosition(float newX, float newY, float newZ) {
		this.x = newX * 10 + 5;
		this.y = newY * 10 + 5;
		this.z = newZ * 10 + 5;
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
		if (getBombs() > 0) {
			level.setBomb(x, y, z, this);
		}
	}

	public int getRadius() {
		return radius;
	}

	public void increaseRadius() {
		this.radius += 1;
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

	public void setMaxHealth() {
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

	public int getHealthPoints() {
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

	public float getAngleX() {
		return angleX;
	}

	public void setAngleX(float angleX) {
		this.angleX = angleX;
	}

	public float getAngleY() {
		return angleY;
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
	protected void move(float x, float y, float z) {
		final int radius = 2;

		int tmpX = (int) (Math.signum(x) * (Math.abs(x) + radius));
		int tmpY = (int) (Math.signum(y) * (Math.abs(y) + radius));
		int tmpZ = (int) (Math.signum(z) * (Math.abs(z) + radius));

		int oldCubeX = (int) this.x / 10;
		int oldCubeY = (int) this.y / 10;
		int oldCubeZ = (int) this.z / 10;

		int newCubeX = (int) (this.x + tmpX) / 10;
		int newCubeY = (int) (this.y + tmpY) / 10;
		int newCubeZ = (int) (this.z + tmpZ) / 10;

		if ((newCubeX == oldCubeX) && (newCubeY == oldCubeY) && (newCubeZ == oldCubeZ)) {
			this.x += x;
			this.y += y;
			this.z += z;
		} else {
			if (level.getCube(newCubeX, oldCubeY, oldCubeZ).isWalkable()) {
				this.x += x;
			}
			if (level.getCube(oldCubeX, newCubeY, oldCubeZ).isWalkable()) {
				this.y += y;
			}
			if (level.getCube(oldCubeX, oldCubeY, newCubeZ).isWalkable()) {
				this.z += z;
			}
		}

		// Wenn ein Spieler in die Flammen einer Explosion hineinläuft (die
		// eigentliche
		// Explosionswirkung aber nicht mitbekommen hat), so verliert er durch
		// die
		// Einwirkung der Flammen nur einen Teil (ein Fünftel) der Punkte, die
		// er durch
		// die Explosion verloren hätte.
		if (((!(level.getCube(oldCubeX, oldCubeY, oldCubeZ).getCubeName() == Cube.CUBE_EXPLOSION))
				&& (!(level.getCube(oldCubeX, oldCubeY, oldCubeZ).getCubeName() == Cube.CUBE_EXPLOSION_HIDE_EXIT)) && (!(level
				.getCube(oldCubeX, oldCubeY, oldCubeZ).getCubeName() == Cube.CUBE_EXPLOSION_HIDE_ITEM)))
				&& ((level.getCube((int) this.x / 10, (int) this.y / 10, (int) this.z / 10).getCubeName() == Cube.CUBE_EXPLOSION)
						|| (level.getCube((int) this.x / 10, (int) this.y / 10, (int) this.z / 10).getCubeName() == Cube.CUBE_EXPLOSION_HIDE_EXIT) || (level
						.getCube((int) this.x / 10, (int) this.y / 10, (int) this.z / 10).getCubeName() == Cube.CUBE_EXPLOSION_HIDE_ITEM))) {
			this.hitPlayer(CubeExplosion.DAMAGE_POINTS / 5);

			// TODO Testausgabe entfernen
			System.out.println("Du bist in eine Explosion gelaufen! -" + (CubeExplosion.DAMAGE_POINTS / 5)
					+ "   Healthpoints: " + this.getHealthPoints());
		}

		// Überprüfe, ob ein Item eingesammelt werden kann
		if (level.getCube((int) this.x / 10, (int) this.y / 10, (int) this.z / 10).isCollectable()) {
			level.getCube((int) this.x / 10, (int) this.y / 10, (int) this.z / 10).change(this, level);
		}
	}
}
