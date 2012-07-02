package game.cube;
import java.util.List;

import game.Level;
import game.Player;
import DetectedServer.NetPlayer;

/**
 * Der Ausgang muss erreicht werden (nachdem alle Gegner vernichtet wurden), um das Spiel zu gewinnen.
 */
public class CubeExit extends Cube {
	
	final static public int SCORE = 1000;

	/** 
	 * Erzeugt eine Instanz des Würfels "Exit"
	 * 
	 * Ein Ausgang kann nicht zerstört werden.
	 * Er ist aber begehbar und wie ein Item aufnehmbar
	 * (durch Aufnahme hat der aufnehmende Spieler gewonnen)
	 */
	public CubeExit() {
		super(Cube.IS_WALKABLE, Cube.IS_COLLECTABLE, Cube.IS_NOT_DESTROYABLE);
	}

	@Override
	public void change(Player player, Level level) {
		// TODO Println-Zeile löschen! Ausgabe nur zu Probezwecken!
		System.out.println("Du hast den Ausgang erreicht - Glückwunsch!");
		
		player.addScore(SCORE);
		System.out.println("Der Sieg bringt Dir " + SCORE + " Punkte!");
		System.out.println("Du hast damit " + player.getScore() + " Punkte - ganz toll!");
		
		level.showMenu();
		// FIXME Netzwerkfähigkeit
		// FIXME + Abfrage, ob noch mehr als ein Spieler lebt
		// FIXME + Aufbau des Menüs im Netzwerk
		player.reinit((level.getSizeX() / 2) * 10 + 5, (level.getSizeY() / 2) * 10 + 5, 15, 0, 0, 100, 0, 1, 1, false);
	}
}
