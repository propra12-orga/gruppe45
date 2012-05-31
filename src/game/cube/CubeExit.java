package game.cube;

public class CubeExit extends Cube {

	public CubeExit() {
		super(Cube.IS_WALKABLE, Cube.IS_COLLECTABLE, Cube.IS_NOT_DESTROYABLE);
		this.hidesExit = true;
	}

	@Override
	public void change() {
		// TODO Println-Zeile löschen! Ausgabe nur zu Probezwecken!
		System.out.println("Du hast den Ausgang erreicht - Glückwunsch!");
		System.exit(1);
	}

}
