package game;

import game.cube.Cube;

import java.util.TimerTask;

public class TimeCube extends TimerTask {

	Cube cube;
	ArrayPosition[] positions;
	Level level;
	Player player;

	public TimeCube(Level level, Cube cube, ArrayPosition[] positions, Player player) {
		this.positions = positions;
		this.cube = cube;
		this.level = level;
		this.player = player;
	}

	@Override
	public void run() {

		for (int i = 0; i < positions.length; i++) {
			boolean transportExit = false;

			Cube tmpcube = level.getCube(positions[i].getX(), positions[i].getY(), positions[i].getZ());
			if (tmpcube.hidesExit())
				transportExit = true; // Merker, ob sich hier der Exit verbirgt

			if (tmpcube.isDestroyable()) {
				level.setCube(cube, positions[i].getX(), positions[i].getY(), positions[i].getZ());

				// Kollisionsabfrage mit Spieler
				// FIXME Für mehrere Spieler ermöglichen
//<<<<<<< HEAD
//				if (cube.getCubename() == "CubeExplosion") {
//					if ((positions[i].getX() == player.getCubeX()) && (positions[i].getY() == player.getCubeY()) && (positions[i].getZ() == player.getCubeZ())){
//						cube.change(player, level);
//							
//							// TODO Testausgabe entfernen!
//							System.out.println("Player getroffen!     -25    HealthPoints: " + player.getHealthPoints());
//						
//=======
				if (cube.getCubeName().equals(Cube.CUBE_EXPLOSION) || cube.getCubeName().equals(Cube.CUBE_EXPLOSION_HIDE_EXIT)) {
					if ((positions[i].getX() == player.getCubeX()) && (positions[i].getY() == player.getCubeY())
							&& (positions[i].getZ() == player.getCubeZ())) {
						cube.change(player, level);

						// TODO Testausgabe entfernen!
						System.out.println("Player getroffen! -25  HealthPoints: " + player.getHealthPoints());

//>>>>>>> 4a2c8bed93ae88ad1e79b299c0cb5d788520ef46
						// Abfrage, ob Player noch lebt oder getötet wurde
						if (player.getHealthPoints() <= 0) {
							player.dies();
						}
					}
				}
			}
//<<<<<<< HEAD
//			
//			if (transportExit) {	// Wenn sich hinter dem Würfel der Exit verborgen hat,
//									// so wird dieser nun freigelegt bzw. weitergegeben!
//				if (cube.getCubename() == "CubeEmpty") {
//					level.setCube(new CubeExit(), positions[i].getX(), positions[i].getY(),
//								positions[i].getZ());
//					System.out.println("TimeCube Zeile 57");					
//				}
//				else {
//					level.setCube(new CubeExplosion(true),positions[i].getX(),positions[i].getX(),positions[i].getX());
//=======

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
//>>>>>>> 4a2c8bed93ae88ad1e79b299c0cb5d788520ef46
				}
			}
		}
	}
}