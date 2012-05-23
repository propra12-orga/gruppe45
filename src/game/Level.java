package game;

import game.cube.Cube;
import game.cube.CubeEmpty;
import game.cube.CubeSolid;
import game.cube.CubeOutside;
import game.cube.CubeObstacle;

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

	public void setCube(Cube cube, int x, int y, int z) {
		if (x >= 0 && y >= 0 && z >= 0 && x < getSizeX() && y < getSizeY()
				&& z < getSizeZ()) {
			level[x][y][z] = cube;
		}
	}

	/**
	 * Der Standardkonstruktor erzeugt ein Level der Groesse 10x10x10
	 */
	public Level() {
		level = new Cube[10][10][10];
		clear();
	}

	/**
	 * Mit diesem Kontroktor kann die Groesse des Levels variiert werden
	 * 
	 * @param x
	 *            Breite des Levels
	 * @param y
	 *            Hoehe des Levels
	 * @param z
	 *            Tiefe des Levels
	 */
	public Level(byte x, byte y, byte z) {
		level = new Cube[x][y][z];
		clear();
	}

	/**
	 * Gibt die Art eines Wuerfels an einer bestimmten Position aus
	 * 
	 * @param x
	 *            Horizontale Wuerfelnummer
	 * @param y
	 *            Senkrechte Wuerfelnummer
	 * @param z
	 *            Tiefenwuerfelnummer
	 * @return Gibt die Art eines Wuerfels an einer bestimmten Position aus
	 */
	public Cube getCube(int x, int y, int z) {
		if ((x >= 0) && (y >= 0) && (z >= 0) && (x < this.getSizeX())
				&& (y < this.getSizeY()) && (z < this.getSizeZ())) {
			return level[x][y][z];
		} else
			return new CubeEmpty();
	}

	/**
	 * Setzt das Levelarray auf Anfang
	 */
	public void clear() {
		for (byte i = 0; i < getSizeX(); i++) {
			for (byte j = 0; j < getSizeY(); j++) {
				for (byte k = 0; k < getSizeZ(); k++) {
					// Festes Blockmuster
					if (!(i % 2 == 0 || j % 2 == 0 || k % 2 == 0)) {
						level[i][j][k] = new CubeSolid();
					} else {
						level[i][j][k] = new CubeEmpty();
					}
					// Aussenseite des Levels
					if (i == 0 || j == 0 || k == 0 || i == getSizeX() - 1
							|| j == getSizeY() - 1 || k == getSizeZ() - 1) {
						level[i][j][k] = new CubeOutside();
					}
				}
			}
		}
		//FIXME nur zu Testzwecken/Vorführung: zerstörbare Würfel
		level[2][2][6] = new CubeObstacle();
		level[4][4][4] = new CubeObstacle();
		level[6][6][6] = new CubeObstacle();
		level[4][8][6] = new CubeObstacle();
		level[8][2][6] = new CubeObstacle();
	}
}
