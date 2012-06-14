package game;

import game.cube.Cube;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.Scanner;

import java.util.Random;

/**
 * Speichert und verwaltet ein abstraktes Level
 * 
 * @author felidosz
 */
public class Level {

	// TODO Menüoption für ObstacleProbability
	
	/**
	 * Wahrscheinlichkeit, dass bei zufälliger Levelfüllung ein Hindernis an eine freie Stelle gesetzt wird
	 */
	final static public int OBSTACLE_PROBABILITY = 32; // Wahrscheinlichkeit
														// eines Hindernisses
														// an leerer Stelle des
														// Levels (0..100 %)
	
	// Themenauswahl
	// TODO Menüintegration
	final static public byte THEME_EARTH = 1;
	final static public byte THEME_SPACE = 2;
	
	/**
	 * Umschalten zwischen Darstellungsthemen
	 */
	byte themeSelection = THEME_EARTH;
	//byte themeSelection = THEME_SPACE;


	/**
	 * Ermöglicht das Verstecken des Ausgangs in einem zerstörbaren Block
	 */
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
	
	public byte getthemeSelection() {
		return this.themeSelection;
	}
	
	public void setthemeSelection(byte themeSelection){
		this.themeSelection = themeSelection;
	}
	

	public Cube getCube(int x, int y, int z) {
		if (x >= 0 && x < getSizeX() && y >= 0 && x < getSizeY() && x >= 0
				&& z < getSizeZ()) {
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
		if (x >= 0 && y >= 0 && z >= 0 && x < getSizeX() && y < getSizeY()
				&& z < getSizeZ()) {
			level[x][y][z] = cube;
		}
	}

	public void setCubeSilent(Cube cube, int x, int y, int z) {
		if (x >= 0 && y >= 0 && z >= 0 && x < getSizeX() && y < getSizeY()
				&& z < getSizeZ()) {
			level[x][y][z] = cube;
		}
	}
	//Meilenstein:
	// Levelzustand speichern
	public void save() {
		File file;
		FileWriter writer;
		file = new File("TestFile.txt");
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
	//letzten gespeicherten Levelzustand laden
	//TODO: Für beliebe Levelgröße einrichten (kann nur die Größe laden, in der Gespeichert wurde)
	public void load() {
		try {
			Scanner scanner = new Scanner(new File("TestFile.txt"));
			for (byte i = 0; i < getSizeX(); i++) {
				for (byte j = 0; j < getSizeY(); j++) {
					for (byte k = 0; k < getSizeZ(); k++) {
					
						level[i][j][k] = Cube.getCubeByNumber(scanner.nextInt());
					}
				}
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}

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
	 * @return Gibt den Namen eines Wuerfels an einer bestimmten Position aus
	 */
	public String getCubeName(int x, int y, int z) {
		if ((x >= 0) && (y >= 0) && (z >= 0) && (x < this.getSizeX())
				&& (y < this.getSizeY()) && (z < this.getSizeZ())) {
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
					setCube(Cube.getCubeByNumber(Integer
							.valueOf(in[posArray++])), i, j, k);
				}
			}
		}
	}
	
	public void showMenu(){		
		final int Z_VERSCHIEBUNG = 2; // gibt an, wie weit die Menüwand von der Rückwand entfernt ist
		// leere das Levelinnere
		for (byte i = 1; i < getSizeX()-1; i++) {
			for (byte j = 1; j < getSizeY()-1; j++) {
				for (byte k = 1; k < getSizeZ()-1; k++) {					
					level[i][j][k] = Cube.getCubeByName(Cube.CUBE_EMPTY);						
				}
			}
		}
		// Baue das Hauptmenü auf
		level[getSizeX()/2 + 2][getSizeY()/2][getSizeZ()- Z_VERSCHIEBUNG] = Cube.getCubeByName(Cube.MENU_CUBE_NEW_GAME);	
		level[getSizeX()/2][getSizeY()/2][getSizeZ()- Z_VERSCHIEBUNG] = Cube.getCubeByName(Cube.MENU_CUBE_LOAD_LEVEL);		
		level[getSizeX()/2 - 2][getSizeY()/2][getSizeZ()- Z_VERSCHIEBUNG] = Cube.getCubeByName(Cube.MENU_CUBE_EXIT_PROGRAM);		
	}

	/**
	 * Setzt das Levelarray auf Anfang
	 */
	public void clear() {
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
						// Erzeuge Zufallszahl zwischen 0-100
						Random random = new Random();
						int rnd = 1 + Math.abs(random.nextInt()) % 100;

						// Setze zufällig Hindernisse; lasse dabei die
						// Startpositionen der Spieler frei
						if ((rnd <= OBSTACLE_PROBABILITY)
								&& ((i < this.getSizeX() - 3)
										|| (j < this.getSizeY() - 3) || (k > 3))
								&& ((i > 2) || (j > 2) || (k < this.getSizeZ() - 4))) {
							level[i][j][k] = Cube
									.getCubeByName(Cube.CUBE_OBSTACLE);
						} else {
							// Die raumteilenden Ebenden werden mit deutlich
							// erhöhter Wahrscheinlichkeit
							// mit Hindernissen gefüllt
							if ((rnd <= OBSTACLE_PROBABILITY * 2.0f)
									&& ((i == this.getSizeX() / 2)
											|| (j == this.getSizeY() / 2) || (k == this
											.getSizeZ() / 2))) {
								level[i][j][k] = Cube
										.getCubeByName(Cube.CUBE_OBSTACLE);
							} else {
								// Wenn kein Hindernis gesetzt wird, so setze
								// einen leere Würfel
								level[i][j][k] = Cube
										.getCubeByName(Cube.CUBE_EMPTY);
							}
						}
					}
					// Aussenseite des Levels wird ausgefüllt
					if (i == 0 || j == 0 || k == 0 || i == getSizeX() - 1
							|| j == getSizeY() - 1 || k == getSizeZ() - 1) {
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
				level[exit_x][exit_y][exit_z] = Cube
						.getCubeByName(Cube.CUBE_OBSTACLE_HIDE_EXIT);
			} else {
				// Wenn Cube leer ist, setze Ausgang direkt
				level[exit_x][exit_y][exit_z] = Cube
						.getCubeByName(Cube.CUBE_EXIT);
			}
		} else { // Option: Ausgang kann nicht hinter Hindernissen liegen
			level[exit_x][exit_y][exit_z] = Cube.getCubeByName(Cube.CUBE_EXIT);
		}
	}
}
