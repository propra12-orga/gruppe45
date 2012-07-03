package game.cube;

import game.Level;
import game.Player;

public class MenuCubeNewGameGravity extends Cube {

	public MenuCubeNewGameGravity() {
		super(Cube.IS_WALKABLE, Cube.IS_COLLECTABLE, Cube.IS_NOT_DESTROYABLE);
	}

	@Override
	public void change(Player player, Level level) {
		// TODO Println-Zeile löschen! Ausgabe nur zu Probezwecken!
		System.out.println("");
		System.out.println("Neues Spiel gestartet!");
		level.buildGravityLevel();

		// FIXME Netzwerkfähigkeit
		// TODO An skalierbares Level anpassen
		level.setInMenu(false);
		player.reinit(level.getSizeX() * 10 - 15, 15, 15, 0, 0, 100, 1, 1, 1, true);
		// TODO Nur testing - Startplatzfreiräumen
		level.setCube(Cube.getCubeByName(Cube.CUBE_EMPTY), (level.getSizeX() - 2), 1, 1);
		level.setCube(Cube.getCubeByName(Cube.CUBE_EMPTY), (level.getSizeX() - 2), 1, 2);
		level.setCube(Cube.getCubeByName(Cube.CUBE_EMPTY), (level.getSizeX() - 2), 1, 3);

		System.out.println("Du startest mit " + player.getScore() + " Punkten!");
		System.out.println("");
	}

}
