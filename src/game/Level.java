package game;

/**
 * Speichert und verwaltet ein abstraktes Level
 * 
 * @author felidosz
 */
public class Level {

	/**
	 * Kein Würfel an dieser Stelle
	 */
	final static public int CUBE_NR_EMTPY = 0;
	/**
	 * Unzerstörbarer Würfel an dieser Stelle
	 */
	final static public int CUBE_NR_SOLID = 1000;
	/**
	 * Bome
	 */
	final static public int CUBE_NR_BOMB = 2000;
	/**
	 * Item
	 */
	final static public int CUBE_NR_ITEM = 3000;

	int[][][] level;

	/**
	 * Der Standardkonstruktor erzeugt ein Level der Größe 10x10x10
	 */
	public Level() {
		level = new int[10][10][10];
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
		level = new int[x][y][z];
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
	public int getCubeNumber(int x, int y, int z) {
		return level[x][y][z];
	}

	/**
	 * Setzt das Levelarray auf Anfang
	 */
	public void clear() {
		for (byte i = 0; i < level.length; i++) {
			for (byte j = 0; j < level[0].length; j++) {
				for (byte k = 0; k < level[0][0].length; k++) {
					if (/*
						 * i == 9 || j == 9 || k == 9 || i == 0 || j == 0 || k
						 * == 0 ||
						 */!(i % 2 == 0 || j % 2 == 0 || k % 2 == 0)) {
						level[i][j][k] = CUBE_NR_SOLID;
					} else {
						level[i][j][k] = CUBE_NR_EMTPY;
					}
				}
			}
		}
	}
}
