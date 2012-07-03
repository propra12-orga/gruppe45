package game.cube;

import game.Level;
import game.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Der Ausgang muss erreicht werden (nachdem alle Gegner vernichtet wurden), um
 * das Spiel zu gewinnen.
 */
public class CubeExit extends Cube {

	final static public int SCORE = 1000;

	/**
	 * Erzeugt eine Instanz des Würfels "Exit"
	 * 
	 * Ein Ausgang kann nicht zerstört werden. Er ist aber begehbar und wie ein
	 * Item aufnehmbar (durch Aufnahme hat der aufnehmende Spieler gewonnen)
	 */
	public CubeExit() {
		super(Cube.IS_WALKABLE, Cube.IS_COLLECTABLE, Cube.IS_NOT_DESTROYABLE);
	}

	@Override
	public void change(Player player, Level level) {
		// TODO Println-Zeile löschen! Ausgabe nur zu Probezwecken!
		System.out.println("");
		System.out.println("Du hast den Ausgang erreicht - Glückwunsch!");

		// Das erreichen des Ausgangs (Spielgewinn) bringt Punkte
		player.addScore(SCORE);
		System.out.println("Der Sieg bringt dir " + SCORE + " Punkte!");
		System.out.println("Du hast damit " + player.getScore() + " Punkte - ganz toll!");

		// Überprüft, ob die erzielten Punkte den bisher gültigen Highscore
		// brechen
		// und speichert bei Bedarf den neuen Highscore
		System.out.println("");
		if (player.getScore() >= checkHighscore()) {
			System.out.println("Der alte Highscore lag bei " + checkHighscore() + " Punkten.");
			System.out.println("Du hast also mit " + player.getScore() + " Punkten einen neuen Rekord!");
			saveHighscore(player.getScore());
		} else {
			System.out.println("Der alte Highscore lag bei " + checkHighscore() + " Punkten.");
			System.out.println("Mit deinen schwachen " + player.getScore() + " Punkten hast Du hier nichts zu feiern!");
		}
		System.out.println("");

		level.showMenu();
		// FIXME Netzwerkfähigkeit
		// FIXME + Abfrage, ob noch mehr als ein Spieler lebt
		// FIXME + Aufbau des Menüs im Netzwerk
		player.reinit((level.getSizeX() / 2) * 10 + 5, (level.getSizeY() / 2) * 10 + 5, 15, 0, 0, 100, 0, 1, 1, false);
	}

	/**
	 * Speichert den Highscore in einer Textdatei
	 * 
	 * @param theScore
	 *            Erzielter Highscore
	 */
	public void saveHighscore(long theScore) {
		File file;
		FileWriter writer;
		file = new File("save/highscore.txt");
		try {
			writer = new FileWriter(file);
			writer.write(theScore + "");
			writer.write("\t");
			writer.write(System.getProperty("line.separator"));
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Überprüft den aktuell gespeicherten Highscore
	 * 
	 * @return gültiger Highscore
	 */
	public long checkHighscore() {
		try {
			Scanner scanner = new Scanner(new File("save/highscore.txt"));
			return scanner.nextLong();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return 0;
		}
	}

}
