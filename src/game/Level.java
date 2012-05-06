package game;

/**
 * Speichert und verwaltet ein abstraktes Level
 * 
 * @author felidosz
 */
public class Level {

	Cube[][][] level;

	public int getSizeX() {
		return level.length;
	}

	public int getSizeY() {
		return level[0].length;
	}

	public int getSizeZ() {
		return level[0][0].length;
	}

	/**
	 * Der Standardkonstruktor erzeugt ein Level der Größe 10x10x10
	 */
	public Level() {
		level = new Cube[10][10][10];
		clear();
	}

	/**
	 * Mit diesem Kontroktor kann die Größe des Levels variiert werden
	 * 
	 * @param x
	 *            Breite des Levels
	 * @param y
	 *            Höhe des Levels
	 * @param z
	 *            Tiefe des Levels
	 */
	public Level(byte x, byte y, byte z) {
		level = new Cube[x][y][z];
		clear();
	}

	/**
	 * Gibt die Art eines Würfels an einer bestimmten Position aus
	 * 
	 * @param x
	 *            Horizontale Würfelnummer
	 * @param y
	 *            Senkrechte Würfelnummer
	 * @param z
	 *            Tiefenwürfelnummer
	 * @return Gibt die Art eines Würfels an einer bestimmten Position aus
	 */
	public Cube getCube(int x, int y, int z) {
		return level[x][y][z];
	}

	/**
	 * Setzt das Levelarray auf Anfang
	 */
	public void clear() {
		for (byte i = 0; i < getSizeX(); i++) {
			for (byte j = 0; j < getSizeY(); j++) {
				for (byte k = 0; k < getSizeZ(); k++) {
					if (!(i % 2 == 0 || j % 2 == 0 || k % 2 == 0)) {
						level[i][j][k] = new CubeSolid();
					} else {
						level[i][j][k] = new CubeEmpty();
					}
				}
			}
		}
	}
}
