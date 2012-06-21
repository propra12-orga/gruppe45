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
	/**
	 * Obergrenze für die Lebenspunkte eines Spielers
	 */
	private static int MAX_HEALTH_POINTS = 150;
	/**
	 * Obergrenze für die Anzahl an Bomben, die ein Spieler zur
	 * gleichen Zeit legen darf. 
	 */
	public static int MAX_SIMULTAN_BOMBS = 3;
	/**
	 * Maximale Reichweite von Bomben (ausgehend vom Ursprungspunkt der Bombe)
	 */
	private static int MAX_BOMB_RADIUS = 5;
	/**
	 * Legt fest, ob ein Spieler schweben/fliegen kann oder ob er zu Boden
	 * gezogen wird.
	 */
	private static int MAX_BOMB_STRENGTH_MULTIPLIER = 3;	
	
	final static public float INERTIA = .1f;
	//final static public float MAX_ACCELERATION = 10;
	


	boolean gravity = false;

	private int number = 0;

	final private double PI_DIV_2 = (Math.PI / 2);
	protected float x = 0, y = 0, z = 0;
	private float stepSize = 1f;
	private float[] color;
	protected float angleY = 0;
	protected float angleX = 0;

	private float accelerationX = 0;
	private float accelerationY = 0;
	private float accelerationZ = 0;

	protected Level level;

	private int healthPoints = 100;
	int radius = 1;
	int bombStrengthMultiplier = 1;
	static public int bombs = 1; // Anzahl der gleichzeitig legbaren Bomben
	int fuseTime = 3000;
	int explosionTime = 1000;
	List listPlayer;

	/**
	 * Konstruktor erzeugt einen Spieler
	 * 
	 * @param level
	 *            Die Spielwelt - also das Level - wird übergeben.
	 * @param x
	 *            Startposition in x-Richtung (nicht Würfelkoordinate)
	 * @param y
	 *            Startposition in y-Richtung (nicht Würfelkoordinate)
	 * @param z
	 *            Startposition in z-Richtung (nicht Würfelkoordinate)
	 * @param listPlayer
	 *            EINTRAGEN 
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
	/**
	 * Gibt die maximal möglichen Lebenspunkte aller Spieler zurück
	 * @return maximal mögliche Lebenspunkte
	 */
	public int getMaxHealthPoints() {
		return MAX_HEALTH_POINTS;
	}
	/**
	 * Erlaubt es, über das Menü die für alle Spieler maximal möglichen Lebenspunkte
	 * festzulegen.
	 * @param MaxHealthPoints Obergrenze für Lebenspunkte aller Spieler
	 */
	public void setMaxHealthPoints(int MaxHealthPoints) {
		MAX_HEALTH_POINTS = MaxHealthPoints;
	}
	/**
	 * Gibt die Anzahl der maximal zur gleichen Zeit platzierbaren Bomben eines Spieler zurück.
	 * Die Obergrenze ist für alle Spieler gleich.
	 * @return Anzahl der maximal zur gleichen Zeit platzierbaren Bomben eines Spielers
	 */
	public int getMaxSimultanBombs() {
		return MAX_SIMULTAN_BOMBS;
	}
	/**
	 * Erlaubt es, über das Menü die für Spieler maximale Anzahl gleichzeitig platzierbarer Bomben festzulegen.
	 * Die Obergrenze ist für Spieler gleich.
	 * @param MaxSimultanBombs Anzahl der maximal zur gleichen Zeit platzierbaren Bomben eines Spielers
	 */
	public void setMaxSimultanBombs(int MaxSimultanBombs) {
		MAX_SIMULTAN_BOMBS = MaxSimultanBombs;
	}
	/**
	 * Gibt die maximal mögliche Bombenreichweite für Spieler zurück.
	 * Die Obergrenze ist für alle Spieler gleich.
	 * @return maximaler Bombenradius
	 */
	public int getMaxBombRadius() {
		return MAX_BOMB_RADIUS;
	}
	/** 
	 * Erlaubt es, über das Menü die maximale Bombenreichweite festzulegen.
	 * Die Obergrenze ist für alle Spieler gleich. 
	 * @param MaxBombRadius maximaler Bombenradius
	 */
	public void setMaxBombRadius(int MaxBombRadius) {
		MAX_BOMB_RADIUS = MaxBombRadius;
	}
	/**
	 * Gibt den maximalen Energiemultiplikator für Bomben zurück, d. h. die
	 * maximale Energie/Durchschlagkraft von Bomben.
	 * Die Obergrenze ist für alle Spieler gleich.
	 * @return maximaler Energiemultiplikator
	 */
	public int getMaxBombStrengthMultiplier() {
		return MAX_BOMB_STRENGTH_MULTIPLIER;
	}
	/** 
	 * Erlaubt es, über das Menü den maximalen Energiemultiplikator für Bomben festzulegen.
	 * Die Obergrenze ist für alle Spieler gleich.
	 * @param MaxBombStrengthMultiplier maximaler Energiemultiplikator für Bomben.
	 */
	public void setMaxBombStrengthMultiplier(int MaxBombStrengthMultiplier) {
		MAX_BOMB_STRENGTH_MULTIPLIER = MaxBombStrengthMultiplier;
	}
	/**
	 * Reinitialisieren eines Spielers
	 * @param startpositionX x-Position, auf die der Spieler gesetzt wird (nicht Würfelnummer)
	 * @param startpositionY y-Position, auf die der Spieler gesetzt wird (nicht Würfelnummer)
	 * @param startpositionZ z-Position, auf die der Spieler gesetzt wird (nicht Würfelnummer)
	 * @param angleX Drehung des Spielers um die x-Achse
	 * @param angleY Drehung des Spielers um die y-Achse
	 * @param healthPoints Anzahl der Lebenspunkte, auf die der Spieler gesetzt wird
	 * @param bombs Anzahl der durch den Spieler gleichzeitig legbaren Bomben
	 * @param bombradius Bombenreichweite des Spielers
	 * @param bombStrengthMultiplier Bombenenergiemultiplikator des Spielers
	 * @param gravity boolean gibt an, ob der Spieler von der Erdanziehung beeinflusst wird
	 */
	public void reinit(int startpositionX, int startpositionY, int startpositionZ, float angleX, float angleY, int healthPoints, int bombs, int bombradius, int bombStrengthMultiplier, boolean gravity){
		setPosition(startpositionX,startpositionY,startpositionZ);
		setHealthPoints(healthPoints);
		setBombs(bombs);
		setAngleX(angleX);
		setAngleY(angleY);
		setbombStrengthMultiplier(bombStrengthMultiplier);
		setGravity(gravity);
		setRadius(bombradius);
	}

	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
	}

	private float getAccX() {
		return accelerationX;
	}

	private float getAccY() {
		return accelerationY;
	}

	private float getAccZ() {
		return accelerationZ;
	}

	public void accerlate() {
		move(accelerationX, accelerationY, accelerationZ);
		accelerationX = getNewAcceleration(accelerationX);
		accelerationY = getNewAcceleration(accelerationY);
		accelerationZ = getNewAcceleration(accelerationZ);
	}

	public void addAcceleration(float accelerationX, float accelerationY, float accelerationZ) {
		this.accelerationX += accelerationX;
		this.accelerationY += accelerationY;
		this.accelerationZ += accelerationZ;
	}

	/**
	 * laesst die Beschleunigung langsam weniger werden
	 * 
	 * @param acceleration
	 * @return
	 */
	private float getNewAcceleration(float acceleration) {
		if (acceleration > INERTIA) {
			acceleration -= INERTIA;
		} else if (acceleration < -INERTIA) {
			acceleration += INERTIA;
		} else {
			acceleration = 0;
		}
		return acceleration;
	}

	/**
	 * Erhöht die Anzahl der gleichzeitig platzierbaren Bomben durch den
	 * Spieler um 1.
	 */
	public void increaseBombs() {
		this.bombs += 1;
	}

	/**
	 * Verringert die Anzahl der gleichzeitig platzierbaren Bomben durch den
	 * Spieler um 1.
	 */
	public void decreaseBombs() {
		this.bombs -= 1;
	}

	/**
	 * Erhöht den Bombenenergiemultiplikator um 1.
	 */
	public void increaseBombStrengthMultiplier() {
		this.bombStrengthMultiplier += 1;
	}

	/** 
	 * Verringert den Bombenenergiemultiplikator um 1.
	 */
	public void decreaseBombStrengthMultiplier() {
		this.bombStrengthMultiplier -= 1;
	}
	
	/**
	 * Gibt den Bombenenergiemultiplikator des Spielers zurück
	 * @return Energiemultiplikator des Spielers
	 */
	public int getbombStrengthMultiplier() {
		return this.bombStrengthMultiplier;
	}

	/**
	 * Setzt den Bombenenergiemultiplikator auf den übergebenen Wert, sofern dieser
	 * kleiner als die für alle geltende Obergrenze ist; sonst wird der Multiplikator
	 * auf maximal gesetzt.
	 * @param bombStrengthMultiplier neuer Bombenenergiemultiplikator
	 */
	public void setbombStrengthMultiplier(int bombStrengthMultiplier) {
		if (bombStrengthMultiplier <= getMaxBombStrengthMultiplier()) {
			this.bombStrengthMultiplier = bombStrengthMultiplier;
		} else {
			this.bombStrengthMultiplier = getMaxBombStrengthMultiplier();
		}
	}

	//TODO Löschen?!?
//	public int getBombStrengthMultiplier(int bombStr) {
//		return this.bombStrengthMultiplier;
//	}

	/**
	 * Anzahl der (gleichzeitig) legbaren Bomben durch den Spieler
	 * @return Anzahl der verfügbaren Bomben
	 */
	public int getBombs() {
		return this.bombs;
	}

	/**
	 * Hier kann die Anzahl der gleichzeitig platzierbaren Bomben durch den
	 * Spieler direkt festgelegt werden.
	 * 
	 * @param bombs
	 *            Anzahl der gleichzeitig platzierbaren Bomben
	 */
	public void setBombs(int bombs) {
		this.bombs = bombs;
	}

	/**
	 * Bestimmt die dargestellte Spielerposition anhand der Würfelkoordinaten
	 * 
	 * @param newX
	 *            x-Position des Würfels im Levelarray
	 * @param newY
	 *            y-Position des Würfels im Levelarray
	 * @param newZ
	 *            z-Position des Würfels im Levelarray
	 */
	public void setPlayerPosition(float newX, float newY, float newZ) {
		this.x = newX * 10 + 5;
		this.y = newY * 10 + 5;
		this.z = newZ * 10 + 5;
	}

	public int getNumber() {
		return number;
	}

	/**
	 * Dieser Boolean gibt an, ob ein Spieler durch Erdanziehung zu Boden sinkt (true)
	 * oder ob er schweben/fliegen kann (false)
	 * @return true = Erdanziehung; false = keine Erdanziehung
	 */
	public boolean getGravity() {
		return this.gravity;
	}

	/**
	 * Hiermit kann festgelegt, ob die Erdanziehung auf einen Spieler wirkt;
	 * also ob er zu Boden sinkt (true) oder fliegen kann (false)
	 * @param gravity Status der Erdanziehung
	 */
	public void setGravity(boolean gravity) {
		this.gravity = gravity;
	}

	/**
	 * Nur zum Speichern der Spielerposition im Server
	 */
	// Bombe legen
	public void setBomb() {
		setBomb((int) (x / 10), (int) (y / 10), (int) (z / 10));
	}

	public void setBomb(int x, int y, int z) {
		if (getBombs() > 0) {
			// TODO der Klient weiss nicht wie viele er leget und muss noch
			// irgenwie selber mitzaehlen
			level.setBomb(x, y, z, this);
			System.out.println("bombe legen");
		}
	}

	public int getRadius() {
		return radius;
	}
	

	public void setRadius(int radius) {
		this.radius = radius;
	}

	/**
	 * Erhöht die Reichweite von Bomben des Spielers um 1.
	 */
	public void increaseRadius() {
		this.radius += 1;
	}

	public void setColor(float[] color) {
		this.color = color;
	}

	public float[] getColor() {
		return this.color;
	}

	/**
	 * Heilt den Spieler, indem die Anzahl der Lebenspunkte um den
	 * Wert der Heilungspunkte erhöht wird
	 * 
	 * @param healPoints
	 *            Anzahl der Heilungspunkte 
	 */
	public void healPlayer(int healPoints) {
		healthPoints += healPoints;
	}

	/**
	 * Setzt den Wert der Lebenspunkte des Spielers auf den durch die Obergrenze
	 * festgelegten Maximalwert.
	 */
	public void setMaxHealth() {
		healthPoints = MAX_HEALTH_POINTS;
	}

	/**
	 * Verringert die Anzahl der Lebenspunkte des Spielers um die
	 * Anzahl der Trefferpunkte
	 * 
	 * @param hitPoints
	 *            Anzahl der Trefferpunkte
	 */
	public void hitPlayer(int hitPoints) {
		healthPoints -= hitPoints;
	}

	/**
	 * Überprüft, ob der Spieler noch lebt - also ob er noch Lebenspunkte hat
	 * 
	 * @return true (wenn Spieler noch lebt), false (wenn Spieler gestorben ist)
	 */
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
	 * @return Anzahl der Lebenspunkte des Spielers
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

	public void sinkDown() {
		move(0, -0.3f, 0);
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

	public float zum_quadrat(float zahl) {
		return zahl * zahl;
	}

	// TODO Testen, ob Abfrage funktioniert
	protected void move(float x, float y, float z) {
		final int radius = 2;

		int tmpX = (int) (Math.signum(x) * (Math.abs(x) + radius));
		int tmpY = (int) (Math.signum(y) * (Math.abs(y) + radius+3));
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
			// Der Spieler kann durch move() in einem Block landen der nicht
			// mehr
			// im Level ist. Sollte er nach der Addition ausserhalt des Levels
			// landen, nicht Addieren und die Beschleunigung loeschen
			if (level.getCube(newCubeX, oldCubeY, oldCubeZ) != null) {
				if (level.getCube(newCubeX, oldCubeY, oldCubeZ).isWalkable()) {
					this.x += x;
				} else {
					accelerationX = 0; // Der Spieler muesste sich eigentlich
										// noch bewegen, wird jedoch von einem
										// Hindernis gestoppt, Impulsenergie
										// könnte in Schaden umgewandelt werden
										// ;)
				}
			} else {
				accelerationX = 0;
			}
			if (level.getCube(oldCubeX, newCubeY, oldCubeZ) != null) {
				if (level.getCube(oldCubeX, newCubeY, oldCubeZ).isWalkable()) {
					this.y += y;
				} else {
					accelerationY = 0;
				}
			} else {
				accelerationY = 0;
			}
			if (level.getCube(oldCubeX, oldCubeY, newCubeZ) != null) {
				if (level.getCube(oldCubeX, oldCubeY, newCubeZ).isWalkable()) {
					this.z += z;
				} else {
					accelerationZ = 0;
				}
			} else {
				accelerationZ = 0;
			}
		}
		//TEST Rampe laufen
		// Würfelwinkel 45° z -> y
		if (level.getCube(this.getCubeX(), this.getCubeY(), this.getCubeZ()).getCubeName() == Cube.CUBE_SOLID_RAMP) {
			float z_in_ramp = this.z - this.getCubeZ() * 10;
			float elevate = (float) Math.sqrt(2 * zum_quadrat(z_in_ramp));
			float tmp_y = this.getCubeY() * 10 + elevate;
			this.y = tmp_y + 0.2f;
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
