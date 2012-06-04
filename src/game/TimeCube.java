package game;

import game.cube.Cube;

import java.util.List;
import java.util.TimerTask;

public class TimeCube extends TimerTask {

	Cube cube;
	ArrayPosition[] positions;
	Level level;
	List<Player> listPlayer;

	/**
	 * Der TimeCube fungiert als Thread
	 * 
	 * @param level
	 *            Level wird übergeben
	 * @param cube
	 *            Der bestimmte Würfel wird übergeben
	 * @param positions
	 *            Ein Array wird übergeben, der bestimmte Positionen enthält
	 *            (z.B. jedes Feld, das von einer Bombenexplosion betroffen ist)
	 * @param player
	 *            Der aktuelle Spieler wird übergeben
	 */
	public TimeCube(Level level, Cube cube, ArrayPosition[] positions, List<Player> listPlayer) {
		this.positions = positions;
		this.cube = cube;
		this.level = level;
		this.listPlayer = listPlayer;
	}

	@Override
	public void run() {
		// Alle übergebenen Positionen werden angesteuert:
		for (int i = 0; i < positions.length; i++) {
			boolean transportExit = false;
			// Die Würfelart an der Position i wird erfragt:
			Cube tmpcube = level.getCube(positions[i].getX(), positions[i].getY(), positions[i].getZ());
			if (tmpcube.hidesExit())
				transportExit = true; // Merker, ob sich hier der Exit verbirgt

			if (tmpcube.isDestroyable()) {
				level.setCube(cube, positions[i].getX(), positions[i].getY(), positions[i].getZ());

				for (int j = 0; j < listPlayer.size(); j++) {
					if (cube.getCubeName().equals(Cube.CUBE_EXPLOSION)
							|| cube.getCubeName().equals(Cube.CUBE_EXPLOSION_HIDE_EXIT)) {
						if ((positions[i].getX() == listPlayer.get(j).getCubeX())
								&& (positions[i].getY() == listPlayer.get(j).getCubeY())
								&& (positions[i].getZ() == listPlayer.get(j).getCubeZ())) {
							cube.change(listPlayer.get(j), level);

							// Abfrage, ob Player noch lebt oder getötet wurde
							if (listPlayer.get(j).getHealthPoints() <= 0) {
								listPlayer.get(j).dies();
							}
						}
					}
				}
			}

			if (transportExit) { // Wenn sich hinter dem Würfel der Exit
									// verborgen hat,
									// so wird dieser nun freigelegt bzw.
									// weitergegeben!
				if (cube.getCubeName().equals(Cube.CUBE_EMPTY)) {
					level.setCube(Cube.getCubeByName(Cube.CUBE_EXIT), positions[i].getX(), positions[i].getY(),
							positions[i].getZ());
				} else {
					level.setCube(Cube.getCubeByName(Cube.CUBE_EXPLOSION_HIDE_EXIT), positions[i].getX(), positions[i].getX(),
							positions[i].getX());
				}
			}
		}
	}
}