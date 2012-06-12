package game.cube;
import game.Level;
import game.Player;

public class CubeExit extends Cube {

	public CubeExit() {
		super(Cube.IS_WALKABLE, Cube.IS_COLLECTABLE, Cube.IS_NOT_DESTROYABLE);
	}

	@Override
	public void change(Player player, Level level) {
		// TODO Println-Zeile löschen! Ausgabe nur zu Probezwecken!
		System.out.println("Du hast den Ausgang erreicht - Glückwunsch!");
		level.showMenu();
		player.setPosition((level.getSizeX() / 2) * 10 + 5  , (level.getSizeY() / 2) * 10 + 5, 15);
		//		System.exit(1);
	}

}
