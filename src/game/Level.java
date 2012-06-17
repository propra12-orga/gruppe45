package game;

import game.cube.Cube;
import render.Objects;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * Speichert und verwaltet ein abstraktes Level
 * 
 * @author felidosz
 */
public class Level {

	// TODO Menüoption für ObstacleProbability

	/**
	 * Wahrscheinlichkeit, dass bei zufälliger Levelfüllung ein Hindernis an
	 * eine freie Stelle gesetzt wird
	 */
	final static public float OBSTACLE_PROBABILITY = 0.3f; // Wahrscheinlichkeit
															// eines
															// Hindernisses
															// an leerer Stelle
															// des
															// Levels (0..1)

	// Themenauswahl
	// TODO Menüintegration
	final static public byte THEME_EARTH = 1;
	final static public byte THEME_SPACE = 2;
	final static public byte THEME_SOCCER = 3;

	/**
	 * Umschalten zwischen Darstellungsthemen
	 */
	byte themeSelection = THEME_EARTH;
	// byte themeSelection = THEME_SPACE;
	// byte themeSelection = THEME_SOCCER;

	/**
	 * Ermöglicht das Verstecken des Ausgangs in einem zerstörbaren Block
	 */
	final static public boolean EXIT_CAN_HIDE_BEHIND_CUBES = true;
	// Wenn "true", dann kann sich der Ausgang auch hinter
	// Blöcken verbergen, sodass dieser erst freigebomt werden
	// muss! (vgl. 2. Meilenstein Anforderung #5)

	Cube[][][] level;

	/**
	 * Mit diesem Konstruktor kann die Groesse des Levels variiert werden
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
		buildDefaultLevel();
	}

	public void setBomb(int x, int y, int z, Player player) {
		Game.getThreadBomb().setBomb(x, y, z, player.getRadius(), player, player.getbombStrengthMultiplier());
	}

	public int getSizeX() {
		return level.length;
	}

	public int getSizeY() {
		return level[0].length;
	}

	public int getSizeZ() {
		return level[0][0].length;
	}

	public byte getthemeSelection() {
		return this.themeSelection;
	}

	public void setthemeSelection(byte themeSelection) {
		this.themeSelection = themeSelection;
	}

	public Cube getCube(int x, int y, int z) {
		if (x >= 0 && x < getSizeX() && y >= 0 && x < getSizeY() && x >= 0 && z < getSizeZ()) {
			return level[x][y][z];
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @param cube
	 * @param x
	 * @param y
	 * @param z
	 */
	public void setCube(Cube cube, int x, int y, int z) {
		if (x >= 0 && y >= 0 && z >= 0 && x < getSizeX() && y < getSizeY() && z < getSizeZ()) {
			level[x][y][z] = cube;
		}
	}

	// Meilenstein:
	// Levelzustand speichern
	public void save() {
		File file;
		FileWriter writer;
		file = new File("quicksave.txt");
		try {
			writer = new FileWriter(file);

			for (byte i = 0; i < getSizeX(); i++) {
				for (byte j = 0; j < getSizeY(); j++) {
					for (byte k = 0; k < getSizeZ(); k++) {
						writer.write(Cube.getNumberByCube(level[i][j][k]) + "");
						writer.write("\t");
					}
					writer.write(System.getProperty("line.separator"));
				}
				writer.write(System.getProperty("line.separator"));
				writer.write(System.getProperty("line.separator"));
				writer.write(System.getProperty("line.separator"));
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// letzten gespeicherten Levelzustand laden
	// TODO: Für beliebe Levelgröße einrichten (kann nur die Größe laden, in der
	// Gespeichert wurde)
	public void load() {
		try {
			Scanner scanner = new Scanner(new File("quicksave.txt"));
			for (byte i = 0; i < getSizeX(); i++) {
				for (byte j = 0; j < getSizeY(); j++) {
					for (byte k = 0; k < getSizeZ(); k++) {

						level[i][j][k] = Cube.getCubeByNumber(scanner.nextInt());
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
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
	 * @return Gibt den Namen eines Wuerfels an einer bestimmten Position aus
	 */
	public String getCubeName(int x, int y, int z) {
		if ((x >= 0) && (y >= 0) && (z >= 0) && (x < this.getSizeX()) && (y < this.getSizeY()) && (z < this.getSizeZ())) {
			return level[x][y][z].getCubeName();
		} else
			return null;
	}

	// TODO String nur aendern wenn sich auch das Level aendert, neuberechnen
	// ist ineffizient
	public String getLevel() {
		String out = "";
		for (byte i = 0; i < getSizeX(); i++) {
			for (byte j = 0; j < getSizeY(); j++) {
				for (byte k = 0; k < getSizeZ(); k++) {
					out += ":" + Cube.getNumberByCube(level[i][j][k]);
				}
			}
		}
		return out;
	}

	public void setLevel(String[] in) {
		int posArray = 4;
		for (byte i = 0; i < getSizeX(); i++) {
			for (byte j = 0; j < getSizeY(); j++) {
				for (byte k = 0; k < getSizeZ(); k++) {
					setCube(Cube.getCubeByNumber(Integer.valueOf(in[posArray++])), i, j, k);
				}
			}
		}
	}

	public void showMenu() {
		final int Z_VERSCHIEBUNG = 2; // gibt an, wie weit die Menüwand von der
										// Rückwand entfernt ist
		// leere das Levelinnere
		clear();
		// Baue das Hauptmenü auf
		level[getSizeX() / 2 + 2][getSizeY() / 2 + 1][getSizeZ() - Z_VERSCHIEBUNG] = Cube.getCubeByName(Cube.MENU_CUBE_NEW_GAME);
		level[getSizeX() / 2][getSizeY() / 2 + 1][getSizeZ() - Z_VERSCHIEBUNG] = Cube.getCubeByName(Cube.MENU_CUBE_NEW_GAME_GRAVITY);
		level[getSizeX() / 2 - 2][getSizeY() / 2 + 1][getSizeZ() - Z_VERSCHIEBUNG] = Cube.getCubeByName(Cube.MENU_CUBE_LOAD_LEVEL);
		
		level[getSizeX() / 2 + 2][getSizeY() / 2 - 1][getSizeZ() - Z_VERSCHIEBUNG] = Cube.getCubeByName(Cube.MENU_CUBE_MULTI);	
		level[getSizeX() / 2][getSizeY() / 2 - 1][getSizeZ() - Z_VERSCHIEBUNG] = Cube.getCubeByName(Cube.MENU_CUBE_SERVER);
		level[getSizeX() / 2 - 2][getSizeY() / 2-1][getSizeZ() - Z_VERSCHIEBUNG] = Cube.getCubeByName(Cube.MENU_CUBE_EXIT_PROGRAM);
	}

	public void clear() {
		for (byte i = 0; i < getSizeX(); i++) {
			for (byte j = 0; j < getSizeY(); j++) {
				for (byte k = 0; k < getSizeZ(); k++) {
					if (i == 0 || j == 0 || k == 0 || i == getSizeX() - 1 || j == getSizeY() - 1 || k == getSizeZ() - 1) {
						level[i][j][k] = Cube.getCubeByName(Cube.CUBE_OUTSIDE);
					} else
						level[i][j][k] = Cube.getCubeByName(Cube.CUBE_EMPTY);
				}
			}
		}
	}

	public void fillWithObstacles() {
		for (byte i = 1; i < getSizeX() - 1; i++) {
			for (byte j = 1; j < getSizeY() - 1; j++) {
				for (byte k = 1; k < getSizeZ() - 1; k++) {

					if (!(level[i][j][k].getCubeName() == Cube.CUBE_SOLID)) {
						float rnd = new Random().nextFloat();

						// Setze zufällig Hindernisse; lasse dabei die
						// Startpositionen der Spieler frei
						if ((rnd <= OBSTACLE_PROBABILITY)
								&& ((i < this.getSizeX() - 3) || (j < this.getSizeY() - 3) || (k > 3))
								&& ((i > 2) || (j > 2) || (k < this.getSizeZ() - 4))) {
							level[i][j][k] = Cube.getCubeByName(Cube.CUBE_OBSTACLE);
						}
					}
				}
			}
		}
		// Baue das Hauptmenü auf
	}
	
	/**
	 * Setzt eine Rampe, die eine Verbindung zweier getrennter Ebenen
	 * herstellt. Dabei wird der Eingang und der Ausgang der Rampe
	 * frei gehalten. Zudem wird ein Geländer, sodass eine Art
	 * Rampentunnel entsteht.
	 * Es wird die Position der unteren Rampe übergeben.
	 * @param x x-Position der unteren Rampe im Levelarray 
	 * @param y y-Position der unteren Rampe im Levelarray 
	 * @param z z-Position der unteren Rampe im Levelarray 
	 */
	public void setRamp(int x, int y, int z){
		// Lege die Rampen
		level[x][y][z] = Cube.getCubeByName(Cube.CUBE_SOLID_RAMP);
		level[x][y+1][z+1] = Cube.getCubeByName(Cube.CUBE_SOLID_RAMP);
		// Öffne Zugänge und Durchgänge
		level[x][y+1][z] = Cube.getCubeByName(Cube.CUBE_EMPTY);
		level[x][y][z-1] = Cube.getCubeByName(Cube.CUBE_EMPTY);
		level[x][y+2][z] = Cube.getCubeByName(Cube.CUBE_EMPTY);
		level[x][y+2][z+1] = Cube.getCubeByName(Cube.CUBE_EMPTY);
		level[x][y+2][z+2] = Cube.getCubeByName(Cube.CUBE_EMPTY);
		level[x-1][y+2][z+2] = Cube.getCubeByName(Cube.CUBE_EMPTY);
		level[x+1][y+2][z+2] = Cube.getCubeByName(Cube.CUBE_EMPTY);
		level[x-1][y+2][z+2] = Cube.getCubeByName(Cube.CUBE_EMPTY);
		level[x+1][y+2][z+2] = Cube.getCubeByName(Cube.CUBE_EMPTY);
		// Setze Fundament
		level[x][y][z+1] = Cube.getCubeByName(Cube.CUBE_SOLID);
		level[x][y][z+2] = Cube.getCubeByName(Cube.CUBE_SOLID);
		level[x][y+1][z+2] = Cube.getCubeByName(Cube.CUBE_SOLID);
		// Setze Umrandung, um Reinlaufen von der Seite zu verhindern
		// Rechte Seite
		level[x-1][y][z] = Cube.getCubeByName(Cube.CUBE_SOLID);
		level[x-1][y][z+1] = Cube.getCubeByName(Cube.CUBE_SOLID);
		level[x-1][y][z+2] = Cube.getCubeByName(Cube.CUBE_SOLID);
		level[x-1][y+1][z] = Cube.getCubeByName(Cube.CUBE_SOLID);
		level[x-1][y+1][z+1] = Cube.getCubeByName(Cube.CUBE_SOLID);
		level[x-1][y+1][z+2] = Cube.getCubeByName(Cube.CUBE_SOLID);
		level[x-1][y+2][z] = Cube.getCubeByName(Cube.CUBE_SOLID);
		level[x-1][y+2][z+1] = Cube.getCubeByName(Cube.CUBE_SOLID);
		// Linke Seite
		level[x+1][y][z] = Cube.getCubeByName(Cube.CUBE_SOLID);
		level[x+1][y][z+1] = Cube.getCubeByName(Cube.CUBE_SOLID);
		level[x+1][y][z+2] = Cube.getCubeByName(Cube.CUBE_SOLID);
		level[x+1][y+1][z] = Cube.getCubeByName(Cube.CUBE_SOLID);
		level[x+1][y+1][z+1] = Cube.getCubeByName(Cube.CUBE_SOLID);
		level[x+1][y+1][z+2] = Cube.getCubeByName(Cube.CUBE_SOLID);
		level[x+1][y+2][z] = Cube.getCubeByName(Cube.CUBE_SOLID);
		level[x+1][y+2][z+1] = Cube.getCubeByName(Cube.CUBE_SOLID);
		// Vorderseite, oben
		level[x-1][y+2][z-1] = Cube.getCubeByName(Cube.CUBE_SOLID);
		level[x][y+2][z-1] = Cube.getCubeByName(Cube.CUBE_SOLID);
		level[x+1][y+2][z-1] = Cube.getCubeByName(Cube.CUBE_SOLID);
		level[x-1][y+1][z-1] = Cube.getCubeByName(Cube.CUBE_SOLID);
		level[x][y+1][z-1] = Cube.getCubeByName(Cube.CUBE_SOLID);
		level[x+1][y+1][z-1] = Cube.getCubeByName(Cube.CUBE_SOLID);
	}
	
	
	public void buildGravityLevel() {
		// Baue leeres Level
		clear();

		// Setze die unzerstörbaren Würfel
		for (byte i = 1; i < getSizeX()-1; i++) {
			for (byte j = 1; j < getSizeY()-1; j++) {
				for (byte k = 1; k < getSizeZ()-1; k++) {
					// Jede gerade Ebene wird komplett mit Solids gefüllt
					if (j % 2 == 0) {
						level[i][j][k] = Cube.getCubeByName(Cube.CUBE_SOLID);
					// Muster der ungeraden Ebenen nach Bomberman-Vorbild
					} else if ((i % 2 == 0 && k % 2 == 0)) {
						level[i][j][k] = Cube.getCubeByName(Cube.CUBE_SOLID);
					}
				}
			}
		}

		// Fülle die Welt mit Hindernissen
//		fillWithObstacles();
		
		// Setze Rampen (manuell)
		for (byte j = 1; j < getSizeY() - 2; j++) {
			if (j % 2 == 0) {
				if (j % 4 == 0) {
					setRamp(getSizeX() - 4, j-1, getSizeZ() - 5);
				} else {
					setRamp(3, j-1, 2);
				}
			}					
		}

		// Setze Ausgang manuell
//		Random random = new Random();
//		int exit_y = 1 + Math.abs(random.nextInt()) % (this.getSizeY()- 2);
//
//		if (exit_y % 2 == 0) {
//			exit_y -= 1;
//		}
//		
//		if (level[this.getSizeX()/2][exit_y][this.getSizeZ()/2].getCubeName() == Cube.CUBE_SOLID) {
//			if (level[this.getSizeX()/2-1][exit_y][this.getSizeZ()/2].getCubeName() == Cube.CUBE_OBSTACLE) {
//				level[this.getSizeX()/2-1][exit_y][this.getSizeZ()/2] = Cube.getCubeByName(Cube.CUBE_OBSTACLE_HIDE_EXIT);
//			} else {
//				level[this.getSizeX()/2-1][exit_y][this.getSizeZ()/2] = Cube.getCubeByName(Cube.CUBE_EXIT);
//			}
//		} else {
//			if (level[this.getSizeX()/2][exit_y][this.getSizeZ()/2].getCubeName() == Cube.CUBE_OBSTACLE) {
//				level[this.getSizeX()/2][exit_y][this.getSizeZ()/2] = Cube.getCubeByName(Cube.CUBE_OBSTACLE_HIDE_EXIT);
//			} else {
//				level[this.getSizeX()/2][exit_y][this.getSizeZ()/2] = Cube.getCubeByName(Cube.CUBE_EXIT);
//			}
//		}
		
//		setRamp(3,1,3);
	}

	/**
	 * ======= // Setze Ausgang manuell level[5][9][5] =
	 * Cube.getCubeByName(Cube.CUBE_EXIT); }
	 * 
	 * 
	 * 
	 * 
	 * /** >>>>>>> 7bc20cee4251101595954ff78142ace3228418b7 Setzt das Levelarray
	 * auf Anfang nach dem Muster des einfachen Bomberman
	 */
	public void buildDefaultLevel() {
		int exit_x, exit_y, exit_z; // Hilfsvariablen für zufälligen Ausgang
		for (byte i = 0; i < getSizeX(); i++) {
			for (byte j = 0; j < getSizeY(); j++) {
				for (byte k = 0; k < getSizeZ(); k++) {
					// Festes Blockmuster: Solid auf (x,y,z) für alle x,y,z
					// ungerade
					if (!(i % 2 == 0 || j % 2 == 0 || k % 2 == 0)) {
						level[i][j][k] = Cube.getCubeByName(Cube.CUBE_SOLID);
						// Sonst zufällige Verteilung zerstörbarer Blöcke
					} else {
						// Erzeuge Zufallszahl zwischen 0..1
						float rnd = new Random().nextFloat();

						// Setze zufällig Hindernisse; lasse dabei die
						// Startpositionen der Spieler frei
						if ((rnd <= OBSTACLE_PROBABILITY)
								&& ((i < this.getSizeX() - 3) || (j < this.getSizeY() - 3) || (k > 3))
								&& ((i > 2) || (j > 2) || (k < this.getSizeZ() - 4))) {
							level[i][j][k] = Cube.getCubeByName(Cube.CUBE_OBSTACLE);
						} else {
							// Die raumteilenden Ebenden werden mit deutlich
							// erhöhter Wahrscheinlichkeit
							// mit Hindernissen gefüllt
							if ((rnd <= OBSTACLE_PROBABILITY * 2.0f)
									&& ((i == this.getSizeX() / 2) || (j == this.getSizeY() / 2) || (k == this.getSizeZ() / 2))) {
								level[i][j][k] = Cube.getCubeByName(Cube.CUBE_OBSTACLE);
							} else {
								// Wenn kein Hindernis gesetzt wird, so setze
								// einen leere Würfel
								level[i][j][k] = Cube.getCubeByName(Cube.CUBE_EMPTY);
							}
						}
					}
					// Aussenseite des Levels wird ausgefüllt
					if (i == 0 || j == 0 || k == 0 || i == getSizeX() - 1 || j == getSizeY() - 1 || k == getSizeZ() - 1) {
						level[i][j][k] = Cube.getCubeByName(Cube.CUBE_OUTSIDE);
					}
				}
			}
		}

		// TODO zum AUSPROBIEREN: Exit verborgen
		// level[this.getSizeX()-2][this.getSizeY()-2][this.getSizeZ()-2] =
		// Cube.getCubeByName(Cube.CUBE_OBSTACLE);

		// Setze den Ausgang in eine zufällige der sechs Ecken,
		// die nicht durch Spieler belegt ist!
		Random random = new Random();
		int rnd = 1 + Math.abs(random.nextInt()) % 6;

		// rnd = 4; //TODO zum AUSPROBIEREN: Exit verborgen

		// Skalierbares Level:
		// Der Ausgang wird bei freiwählbarer Levelausdehnungen in X,Y,Z
		// immer in die Ecken des Levels gelegt
		switch (rnd) {
		case 1:
			exit_x = this.getSizeX() - 2;
			exit_y = 1;
			exit_z = 1;
			break;
		case 2:
			exit_x = 2;
			exit_y = 1;
			exit_z = 1;
			break;
		case 3:
			exit_x = 1;
			exit_y = this.getSizeY() - 2;
			exit_z = 1;
			break;
		case 4:
			exit_x = this.getSizeX() - 2;
			exit_y = this.getSizeY() - 2;
			exit_z = this.getSizeZ() - 2;
			break;
		case 5:
			exit_x = 1;
			exit_y = this.getSizeY() - 2;
			exit_z = this.getSizeZ() - 2;
			break;
		default:
			exit_x = 1;
			exit_y = 1;
			exit_z = this.getSizeZ() - 2;
			break;
		}

		// Wenn der Exit in einem unzerstörbaren Würfel liegen, verschiebe den
		// Exit auf der x-Achse in einen leeren/zerstörbaren Würfel
		if (level[exit_x][exit_y][exit_z].getCubeName() == Cube.CUBE_SOLID) {
			if (exit_x == this.getSizeX() - 2) {
				exit_x -= 1;
			} else {
				exit_x = 2;
			}
		}

		// Setze den Ausgang
		if (EXIT_CAN_HIDE_BEHIND_CUBES) { // Option: Ausgang darf hinter
											// Hindernissen liegen
			if (level[exit_x][exit_y][exit_z].getCubeName() == Cube.CUBE_OBSTACLE) {
				// Wenn Cube ein Hindernisse, dann verberge den Ausgang hinter
				// diesem
				level[exit_x][exit_y][exit_z] = Cube.getCubeByName(Cube.CUBE_OBSTACLE_HIDE_EXIT);
			} else {
				// Wenn Cube leer ist, setze Ausgang direkt
				level[exit_x][exit_y][exit_z] = Cube.getCubeByName(Cube.CUBE_EXIT);
			}
		} else { // Option: Ausgang kann nicht hinter Hindernissen liegen
			level[exit_x][exit_y][exit_z] = Cube.getCubeByName(Cube.CUBE_EXIT);
		}
	}
}
