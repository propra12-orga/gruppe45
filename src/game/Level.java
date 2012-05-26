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
	
	final static public int OBSTACLE_PROBABILITY = 30;    // 0-100 Prozent
	final static public boolean EXIT_CAN_HIDE_BEHIND_CUBES = true;
			// Wenn "true", dann kann sich der Ausgang auch hinter
			// Blöcken verbergen, sodass dieser erst freibomt werdeb
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
						
						if ((rnd <= OBSTACLE_PROBABILITY) && ((i<7) || (j<7) || (k>3))) {
							level[i][j][k] = new CubeObstacle();
						}
						else {
							level[i][j][k] = new CubeEmpty();
						}
					}
					// Aussenseite des Levels
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
				
		// legt den Exit in eine zufällige Ecke
		switch (rnd) {
			case 1:  //level[8][1][1] = new CubeExit(); 
					 if (EXIT_CAN_HIDE_BEHIND_CUBES) {
						 if (level[8][1][1].getCubename() == "CubeObstacle") {
							 	level[8][1][1].sethidesExit(true);
						 }
						 else {
							 	System.out.println("Ist kein Hinder");
							 	level[8][1][1] = new CubeExit();
						 }
					 }
					 else {
						 		level[8][1][1] = new CubeExit();
					 }
					 break;
			case 2:  //level[2][1][1] = new CubeExit();
					 if (EXIT_CAN_HIDE_BEHIND_CUBES) {
						 if (level[2][1][1].getCubename() == "CubeObstacle") {
							 	level[2][1][1].sethidesExit(true);
						 }
						 else {
							 	System.out.println("Ist kein Hinder");
							 	level[2][1][1] = new CubeExit();
						 }
					 }
					 else {
						 		level[2][1][1] = new CubeExit();
					 }
					 break;
			case 3:  //level[1][8][1] = new CubeExit();
					 if (EXIT_CAN_HIDE_BEHIND_CUBES) {
						 if (level[1][8][1].getCubename() == "CubeObstacle") {
							 	level[1][8][1].sethidesExit(true);
						 }
						 else {
							 	System.out.println("Ist kein Hinder");
							 	level[1][8][1] = new CubeExit();
						 }
					 }
					 else {
						 		level[8][1][1] = new CubeExit();
					 }
					 break;
			case 4:  //level[8][8][8] = new CubeExit();
					 if (EXIT_CAN_HIDE_BEHIND_CUBES) {
						 if (level[8][8][8].getCubename() == "CubeObstacle") {
							 	level[8][8][8].sethidesExit(true);
						 }
						 else {
							 	System.out.println("Ist kein Hinder");
							 	level[8][8][8] = new CubeExit();
						 }
					 }
					 else {
						 		level[8][8][8] = new CubeExit();
					 }
					 break;
			case 5:  //level[1][8][8] = new CubeExit();
					 if (EXIT_CAN_HIDE_BEHIND_CUBES) {
						 if (level[1][8][8].getCubename() == "CubeObstacle") {
							 	level[1][8][8].sethidesExit(true);
						 }
						 else {
							 	System.out.println("Ist kein Hinder");
							 	level[1][8][8] = new CubeExit();
						 }
					 }
					 else {
						 		level[8][1][1] = new CubeExit();
					 }
					 break;
			default: //level[1][1][8] = new CubeExit();
					 if (EXIT_CAN_HIDE_BEHIND_CUBES) {
						 if (level[1][1][8].getCubename() == "CubeObstacle") {
							 	level[1][1][8].sethidesExit(true);
						 }
						 else {
							 	System.out.println("Ist kein Hinder");
							 	level[1][1][8] = new CubeExit();
						 }
					 }
					 else {
						 		level[1][1][8] = new CubeExit();
					 }
					 break;
		}

	}
}
