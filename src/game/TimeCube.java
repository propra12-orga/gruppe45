//<<<<<<< HEAD
//package game;
//
//import game.cube.Cube;
//
//import java.util.List;
//import java.util.Random;
//import java.util.Timer;
//import java.util.TimerTask;
//
//
//public class TimeCube extends TimerTask {
//	
//	// Wahrscheinlichkeit, dass hinter einem Weggesprengten Obstacle
//	// ein Item erscheinen wird.
//	// Summe < 100 !
//	final static public int ITEM_HEALTH_PROBABILITY = 5;
//	final static public int ITEM_XTRA_BOMB_PROBABILITY = 5;
//	final static public int ITEM_PORTAL_PROBABILITY = 8;
//
//	Cube cube;
//	ArrayPosition[] positions;
//	Level level;
//	List<Player> listPlayer;
//	Player player;
//
//	/**
//	 * Der TimeCube fungiert als Thread
//	 * 
//	 * @param level
//	 *            Level wird übergeben
//	 * @param cube
//	 *            Der bestimmte Würfel wird übergeben
//	 * @param positions
//	 *            Ein Array wird übergeben, der bestimmte Positionen enthält
//	 *            (z.B. jedes Feld, das von einer Bombenexplosion betroffen ist)
//	 * @param player
//	 *            Der aktuelle Spieler wird übergeben
//	 */
//	public TimeCube(Level level, Cube cube, ArrayPosition[] positions, List<Player> listPlayer, Player player) {
//		this.positions = positions;
//		this.cube = cube;
//		this.level = level;
//		this.listPlayer = listPlayer;
//		this.player = player;
//	}
//
//	@Override
//	public void run() {
////		// Alle übergebenen Positionen werden angesteuert:
//		for (int i = 0; i < positions.length; i++) {
//
//			// Die Würfelart an der Position i wird erfragt:
//			Cube tmpcube = level.getCube(positions[i].getX(), positions[i].getY(), positions[i].getZ());
//
//			if (tmpcube.isDestroyable()) {
//			// Überprüfe, ob Ausgang oder Items transportiert werden!
//				
//				// Explosionen, die ein Item verbergen können (waren vor der
//				// Explosion Obstacles) können Item freilegen oder leere Würfel werden
//				if (tmpcube.getCubeName().equals(Cube.CUBE_EXPLOSION_HIDE_ITEM)) {
//					// FIXME zufälliges Erscheinen von Items anpassen, Verteilung prozentual anpassen
//					Random random = new Random();
//					int rnd = 1 + Math.abs(random.nextInt()) % 100;
//					// ITEM: HEALTH
//					if (rnd <= ITEM_HEALTH_PROBABILITY) {
//						level.setCube(Cube.getCubeByName(Cube.CUBE_ITEM_HEALTH), positions[i].getX(), positions[i].getY(), positions[i].getZ());
//					// ITEM: EXTRA BOMBE
//					} else if ((rnd > ITEM_HEALTH_PROBABILITY) && (rnd <= ITEM_HEALTH_PROBABILITY + ITEM_XTRA_BOMB_PROBABILITY)) {
//						level.setCube(Cube.getCubeByName(Cube.CUBE_ITEM_XTRA_BOMB), positions[i].getX(), positions[i].getY(), positions[i].getZ());
//					// ITEM: PORTAL	
//					} else if ((rnd > ITEM_HEALTH_PROBABILITY + ITEM_XTRA_BOMB_PROBABILITY) && (rnd <= ITEM_HEALTH_PROBABILITY + ITEM_XTRA_BOMB_PROBABILITY + ITEM_PORTAL_PROBABILITY)) {
//						level.setCube(Cube.getCubeByName(Cube.CUBE_ITEM_PORTAL), positions[i].getX(), positions[i].getY(), positions[i].getZ());
//					// SONSTIGER FALL: Cube wird leerer Würfel						
//					} else
//						level.setCube(Cube.getCubeByName(Cube.CUBE_EMPTY), positions[i].getX(), positions[i].getY(), positions[i].getZ());
//					
//				// Explosion, die den Ausgang verbirgt, wird zum Ausgang  
//				} else if (tmpcube.getCubeName().equals(Cube.CUBE_EXPLOSION_HIDE_EXIT)) {
//					level.setCube(Cube.getCubeByName(Cube.CUBE_EXIT), positions[i].getX(), positions[i].getY(), positions[i].getZ());
//				// Obstacle, das den Ausgang verbirgt, wird zur Explosion, die den
//				// Ausgang weiter transportiert
//				} else if (tmpcube.getCubeName().equals(Cube.CUBE_OBSTACLE_HIDE_EXIT)) {
//					level.setCube(Cube.getCubeByName(Cube.CUBE_EXPLOSION_HIDE_EXIT), positions[i].getX(), positions[i].getY(), positions[i].getZ());
//				// Einfache Hindernisse werden zu Explosionen, die potentiell 
//				// Items verbergen können!
//				} else if (tmpcube.getCubeName().equals(Cube.CUBE_OBSTACLE)) {
//					level.setCube(Cube.getCubeByName(Cube.CUBE_EXPLOSION_HIDE_ITEM), positions[i].getX(), positions[i].getY(), positions[i].getZ());
//				// Alle anderen Würfel werden zu einfachen Explosionen bzw. leeren Würfeln
////				}else if (tmpcube.getCubeName().equals(Cube.CUBE_BOMB)){
////					Timer t1 = new Timer();
////					ArrayPosition[] pos = {new ArrayPosition(positions[i].getX(), positions[i].getY(), positions[i].getZ()),
////							new ArrayPosition(positions[i].getX() - 1, positions[i].getY(), positions[i].getZ()), new ArrayPosition(positions[i].getX() + 1, positions[i].getY(), positions[i].getZ()),
////							new ArrayPosition(positions[i].getX(), positions[i].getY() - 1, positions[i].getZ()), new ArrayPosition(positions[i].getX(), positions[i].getY() + 1, positions[i].getZ()),
////							new ArrayPosition(positions[i].getX(), positions[i].getY(), positions[i].getZ()), new ArrayPosition(positions[i].getX(), positions[i].getY(), positions[i].getZ())};
////					t1.schedule(new TimeCube(level, Cube.getCubeByName(Cube.CUBE_EXPLOSION), pos, listPlayer, player), 1,1);
////					// Leerer Block
////					
////					t1.schedule(new TimeCube(level, Cube.getCubeByName(Cube.CUBE_EMPTY), pos, listPlayer, player), 1
////							+ 1);
////////					player.setBomb(positions[i].getX(), positions[i].getY(), positions[i].getZ(),0,0);
//////					this.scheduledExecutionTime(new TimeCube(level, Cube.getCubeByName(Cube.CUBE_EXPLOSION), positions, listPlayer, this), fuseTime)
//				} else
//					level.setCube(cube, positions[i].getX(), positions[i].getY(), positions[i].getZ());
//				
//				// Überprüfe, ob ein Player von der Explosion getroffen wird
//				for (int j = 0; j < listPlayer.size(); j++) {
//					if (cube.getCubeName().equals(Cube.CUBE_EXPLOSION)
//							|| cube.getCubeName().equals(Cube.CUBE_EXPLOSION_HIDE_EXIT)
//							|| cube.getCubeName().equals(Cube.CUBE_EXPLOSION_HIDE_ITEM)) {
//						if ((positions[i].getX() == listPlayer.get(j).getCubeX())
//								&& (positions[i].getY() == listPlayer.get(j).getCubeY())
//								&& (positions[i].getZ() == listPlayer.get(j).getCubeZ())) {
//							cube.change(listPlayer.get(j), level);
//
//							// Abfrage, ob Player noch lebt oder getötet wurde
//							if (listPlayer.get(j).getHealthPoints() <= 0) {
//								listPlayer.get(j).dies();
//							}
//						}
//					}
//				}				
//			}			
//		}
//	}
//}
//=======
//package game;
//
//import game.cube.Cube;
//
//import java.util.List;
//import java.util.Random;
//import java.util.TimerTask;
//
//public class TimeCube extends TimerTask {
//	
//	// Wahrscheinlichkeit, dass hinter einem Weggesprengten Obstacle
//	// ein Item erscheinen wird.
//	// Summe < 100 !
//	final static public int ITEM_HEALTH_PROBABILITY = 30;
//	final static public int ITEM_XTRA_BOMB_PROBABILITY = 30;
//	final static public int ITEM_PORTAL_PROBABILITY = 30;
//
//	Cube cube;
//	ArrayPosition[] positions;
//	Level level;
//	List<Player> listPlayer;
//
//	/**
//	 * Der TimeCube fungiert als Thread
//	 * 
//	 * @param level
//	 *            Level wird übergeben
//	 * @param cube
//	 *            Der bestimmte Würfel wird übergeben
//	 * @param positions
//	 *            Ein Array wird übergeben, der bestimmte Positionen enthält
//	 *            (z.B. jedes Feld, das von einer Bombenexplosion betroffen ist)
//	 * @param player
//	 *            Der aktuelle Spieler wird übergeben
//	 */
//	public TimeCube(Level level, Cube cube, ArrayPosition[] positions, List<Player> listPlayer) {
//		this.positions = positions;
//		this.cube = cube;
//		this.level = level;
//		this.listPlayer = listPlayer;
//	}
//
//	@Override
//	public void run() {
////		// Alle übergebenen Positionen werden angesteuert:
//		for (int i = 0; i < positions.length; i++) {
//
//			// Die Würfelart an der Position i wird erfragt:
//			Cube tmpcube = level.getCube(positions[i].getX(), positions[i].getY(), positions[i].getZ());
//
//			if (tmpcube.isDestroyable()) {
//			// Überprüfe, ob Ausgang oder Items transportiert werden!
//				
//				// Explosionen, die ein Item verbergen können (waren vor der
//				// Explosion Obstacles) können Item freilegen oder leere Würfel werden
//				if (tmpcube.getCubeName().equals(Cube.CUBE_EXPLOSION_HIDE_ITEM)) {
//					// FIXME zufälliges Erscheinen von Items anpassen, Verteilung prozentual anpassen
//					Random random = new Random();
//					int rnd = 1 + Math.abs(random.nextInt()) % 100;
//					// ITEM: HEALTH
//					if (rnd <= ITEM_HEALTH_PROBABILITY) {
//						level.setCube(Cube.getCubeByName(Cube.CUBE_ITEM_HEALTH), positions[i].getX(), positions[i].getY(), positions[i].getZ());
//					// ITEM: EXTRA BOMBE
//					} else if ((rnd > ITEM_HEALTH_PROBABILITY) && (rnd <= ITEM_HEALTH_PROBABILITY + ITEM_XTRA_BOMB_PROBABILITY)) {
//						level.setCube(Cube.getCubeByName(Cube.CUBE_ITEM_XTRA_BOMB), positions[i].getX(), positions[i].getY(), positions[i].getZ());
//					// ITEM: PORTAL	
//					} else if ((rnd > ITEM_HEALTH_PROBABILITY + ITEM_XTRA_BOMB_PROBABILITY) && (rnd <= ITEM_HEALTH_PROBABILITY + ITEM_XTRA_BOMB_PROBABILITY + ITEM_PORTAL_PROBABILITY)) {
//						level.setCube(Cube.getCubeByName(Cube.CUBE_ITEM_PORTAL), positions[i].getX(), positions[i].getY(), positions[i].getZ());
//					// SONSTIGER FALL: Cube wird leerer Würfel						
//					} else
//						level.setCube(Cube.getCubeByName(Cube.CUBE_EMPTY), positions[i].getX(), positions[i].getY(), positions[i].getZ());
//					
//				// Explosion, die den Ausgang verbirgt, wird zum Ausgang  
//				} else if (tmpcube.getCubeName().equals(Cube.CUBE_EXPLOSION_HIDE_EXIT)) {
//					level.setCube(Cube.getCubeByName(Cube.CUBE_EXIT), positions[i].getX(), positions[i].getY(), positions[i].getZ());
//				// Obstacle, das den Ausgang verbirgt, wird zur Explosion, die den
//				// Ausgang weiter transportiert
//				} else if (tmpcube.getCubeName().equals(Cube.CUBE_OBSTACLE_HIDE_EXIT)) {
//					level.setCube(Cube.getCubeByName(Cube.CUBE_EXPLOSION_HIDE_EXIT), positions[i].getX(), positions[i].getY(), positions[i].getZ());
//				// Einfache Hindernisse werden zu Explosionen, die potentiell 
//				// Items verbergen können!
//				} else if (tmpcube.getCubeName().equals(Cube.CUBE_OBSTACLE)) {
//					level.setCube(Cube.getCubeByName(Cube.CUBE_EXPLOSION_HIDE_ITEM), positions[i].getX(), positions[i].getY(), positions[i].getZ());
//				// Alle anderen Würfel werden zu einfachen Explosionen bzw. leeren Würfeln
//				} else
//					level.setCube(cube, positions[i].getX(), positions[i].getY(), positions[i].getZ());
//				
//				// Überprüfe, ob ein Player von der Explosion getroffen wird
//				for (int j = 0; j < listPlayer.size(); j++) {
//					if (cube.getCubeName().equals(Cube.CUBE_EXPLOSION)
//							|| cube.getCubeName().equals(Cube.CUBE_EXPLOSION_HIDE_EXIT)
//							|| cube.getCubeName().equals(Cube.CUBE_EXPLOSION_HIDE_ITEM)) {
//						if ((positions[i].getX() == listPlayer.get(j).getCubeX())
//								&& (positions[i].getY() == listPlayer.get(j).getCubeY())
//								&& (positions[i].getZ() == listPlayer.get(j).getCubeZ())) {
//							cube.change(listPlayer.get(j), level);
//
//							// Abfrage, ob Player noch lebt oder getötet wurde
//							if (listPlayer.get(j).getHealthPoints() <= 0) {
//								listPlayer.get(j).dies();
//							}
//						}
//					}
//				}				
//			}			
//		}
//	}
//}
//>>>>>>> 70ac9c707525d26db9c7dc84e919aa6e4c1b38c6
