package game;

import game.cube.Cube;
import game.cube.CubeEmpty;
import game.cube.CubeExit;
import game.cube.CubeObstacle;
import game.cube.CubeOutside;
import game.cube.CubeSolid;

import java.util.Random;

/**
 * Speichert und verwaltet ein abstraktes Level
 * 
 * @author felidosz
 */
public class Level {

	final static public int OBSTACLE_PROBABILITY = 0;		// Wahrscheinlichkeit eines Hindernisses
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
	 * Mit diesem Kontroktor kann die Groesse des Levels variiert werden
	 * 
	 * @param x
	 *            Breite des Levels
	 * @param y
	 *            Hoehe des Levels
	 * @param z
	 *            Tiefe des Levels
	 */
	public Level(int x, int y, int z) {
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
		//FIXME Philipp: Levelverteilung an skalierbare Levelgröße anpassen
		int exit_x, exit_y, exit_z;
		for (byte i = 0; i < getSizeX(); i++) {
			for (byte j = 0; j < getSizeY(); j++) {
				for (byte k = 0; k < getSizeZ(); k++) {
					// Festes Blockmuster
					if (!(i % 2 == 0 || j % 2 == 0 || k % 2 == 0)) {
						level[i][j][k] = new CubeSolid();
					} else {

						//FIXME Bessere zufällige Hindernisverteilung einbauen
						Random random = new Random();
						int rnd = 1 + Math.abs(random.nextInt()) % 100;
						
						// Setze zufällig Hindernisse; lasse dabei die Startpositionen der Spieler frei
						if ((rnd <= OBSTACLE_PROBABILITY) && ((i<this.getSizeX()-3) || (j<this.getSizeY()-3) || (k>3)) && ((i>2) || (j>2) || (k<this.getSizeZ()-4))) {
							level[i][j][k] = new CubeObstacle();
						} else {
							// Die raumteilenden Ebenden werden mit deutlich
							// erhöhter Wahrscheinlichkeit
							// mit Hindernissen gefüllt
							if ((rnd <= OBSTACLE_PROBABILITY * 2.0f) && ((i==this.getSizeX()/2) || (j==this.getSizeY()/2) || (k==this.getSizeZ()/2))) {
								level[i][j][k] = new CubeObstacle();
							} else {
								// Wenn kein Hindernis gesetzt wird, so setze
								// einen leere Würfel
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

		// TODO zum AUSPROBIEREN: Exit verborgen 
		// level[this.getSizeX()-2][this.getSizeY()-2][this.getSizeZ()-2] = new CubeObstacle();
		// level[this.getSizeX()-2][this.getSizeY()-2][this.getSizeZ()-2].sethidesExit(true);
		
		// Setze den Ausgang in eine zufällige der sechs Ecken,
		// die nicht durch Spieler belegt ist!
		Random random = new Random();
		int rnd = 1 + Math.abs(random.nextInt()) % 6;
		
		// rnd = 4;	//TODO zum AUSPROBIEREN: Exit verborgen 

		// Skalierbares Level:
		// Der Ausgang wird bei freiwählbaren Levelausdehnungen in X,Y,Z 
		// immer in die Ecken des Levels gelegt
		switch (rnd) {
			case 1:  exit_x = this.getSizeX()-2;
					 exit_y = 1;
					 exit_z = 1;
					 break;
			case 2:  exit_x = 2;
					 exit_y = 1;
					 exit_z = 1;
					 break;
			case 3:  exit_x = 1;
					 exit_y = this.getSizeY()-2;
					 exit_z = 1;
					 break;
			case 4:  exit_x = this.getSizeX()-2;
					 exit_y = this.getSizeY()-2;
					 exit_z = this.getSizeZ()-2;
					 break;
			case 5:  exit_x = 1;
					 exit_y = this.getSizeY()-2;
					 exit_z = this.getSizeZ()-2;
					 break;
			default: exit_x = 1;
					 exit_y = 1;
					 exit_z = this.getSizeZ()-2;
					 break;
		}
		
		// Wenn der Exit in einem unzerstörbaren Würfel liegen, verschiebe den
		// Exit auf der x-Achse in einen leeren/zerstörbaren Würfel
		if (level[exit_x][exit_y][exit_z].getCubename() == "CubeSolid") {
			if (exit_x == this.getSizeX()-2) {
				exit_x -= 1;
			}
			else {
				exit_x = 2;
			}
		}		

		// Setze den Ausgang
		if (EXIT_CAN_HIDE_BEHIND_CUBES) { // Option: Ausgang darf hinter
											// Hindernissen liegen
			if (level[exit_x][exit_y][exit_z].getCubename() == "CubeObstacle") {
				// Wenn Cube ein Hindernisse, dann verberge den Ausgang hinter
				// diesem
				level[exit_x][exit_y][exit_z].sethidesExit(true);
			} else {
				// Wenn Cube leer ist, setze Ausgang direkt
				level[exit_x][exit_y][exit_z] = new CubeExit();
			}
		} else { // Option: Ausgang kann nicht hinter Hindernissen liegen
			level[exit_x][exit_y][exit_z] = new CubeExit();
		}
	}
}
