package game;

import game.cube.Cube;
import game.cube.CubeEmpty;
import game.cube.CubeExit;
import game.cube.CubeSolid;
import game.cube.CubeOutside;
import game.cube.CubeObstacle;
import java.util.Random;

/**
 * Speichert und verwaltet ein abstraktes Level
 * 
 * @author felidosz
 */
public class Level {
	
	final static public int OBSTACLE_PROBABILITY = 28;		// Wahrscheinlichkeit eines Hindernisses
															// an leerer Stelle des Levels (0..100 %)
						
	final static public boolean EXIT_CAN_HIDE_BEHIND_CUBES = true;
			// Wenn "true", dann kann sich der Ausgang auch hinter
			// Blöcken verbergen, sodass dieser erst freigebomt werden
			// muss! (vgl. 2. Meilenstein Anforderung #5)
	
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
		int exit_x, exit_y, exit_z;
		for (byte i = 0; i < getSizeX(); i++) {
			for (byte j = 0; j < getSizeY(); j++) {
				for (byte k = 0; k < getSizeZ(); k++) {
					// Festes Blockmuster
					if (!(i % 2 == 0 || j % 2 == 0 || k % 2 == 0)) {
						level[i][j][k] = new CubeSolid();
					} else {
						//FIXME Bessere zufällige Hindernisverteilung einbauen
						Random r = new Random();
						int rnd = 1 + Math.abs(r.nextInt()) % 100;
						
						// Setze zufällig Hindernisse; lasse dabei die Startpositionen der Spieler frei
						if ((rnd <= OBSTACLE_PROBABILITY) && ((i<7) || (j<7) || (k>3)) && ((i>2) || (j>2) || (k<6))) {
							level[i][j][k] = new CubeObstacle();
						}
						else {
							// Die raumteilenden Ebenden werden mit deutlich erhöhter Wahrscheinlichkeit
							// mit Hindernissen gefüllt
							if ((rnd <= OBSTACLE_PROBABILITY * 2.0f) && ((i==5) || (j==5) || (k==5))) {
								level[i][j][k] = new CubeObstacle();
							}
							else {
								// Wenn kein Hindernis gesetzt wird, so setze einen leere Würfel
								level[i][j][k] = new CubeEmpty();
							}
						}
					}
					// Aussenseite des Levels wird ausgefüllt
					if (i == 0 || j == 0 || k == 0 || i == getSizeX() - 1
							|| j == getSizeY() - 1 || k == getSizeZ() - 1) {
						level[i][j][k] = new CubeOutside();
					}
				}
			}
		}
		// zum AUSPROBIEREN: Exit verborgen 
		// level[8][1][1] = new CubeObstacle();
		// level[8][1][1].sethidesExit(true);
		
		// Setze den Ausgang in eine zufällige der sechs Ecken,
		// die nicht durch Spieler belegt ist!
		Random r = new Random();
		int rnd = 1 + Math.abs(r.nextInt()) % 6;
		
		// rnd = 1;	//zum AUSPROBIEREN: Exit verborgen 
				
		switch (rnd) {
			case 1:  exit_x = 8;
					 exit_y = 1;
					 exit_z = 1;
					 break;
			case 2:  exit_x = 2;
					 exit_y = 1;
					 exit_z = 1;
					 break;
			case 3:  exit_x = 1;
					 exit_y = 8;
					 exit_z = 1;
					 break;
			case 4:  exit_x = 8;
					 exit_y = 8;
					 exit_z = 8;
					 break;
			case 5:  exit_x = 1;
					 exit_y = 8;
					 exit_z = 8;
					 break;
			default: exit_x = 1;
					 exit_y = 1;
					 exit_z = 8;
					 break;
		}
		
		// Setze den Ausgang
		if (EXIT_CAN_HIDE_BEHIND_CUBES) {	// Option: Ausgang darf hinter Hindernissen liegen
			if (level[exit_x][exit_y][exit_z].getCubename() == "CubeObstacle") {
				// Wenn Cube ein Hindernisse, dann verberge den Ausgang hinter diesem 	
				level[exit_x][exit_y][exit_z].sethidesExit(true);
			}
			else {
				// Wenn Cube leer ist, setze Ausgang direkt
				level[exit_x][exit_y][exit_z] = new CubeExit();
			}
		}
		else {								// Option: Ausgang kann nicht hinter Hindernissen liegen
			 	level[exit_x][exit_y][exit_z] = new CubeExit();
		}
	}
}
