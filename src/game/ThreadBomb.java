package game;

import game.cube.Cube;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;

import DetectedServer.NetPlayer;

/**
 * Laesst Bomben ticken und explodieren Laesst sich die Explosionen ausbreiten
 * und kann andere Bomben zuenden
 */
public class ThreadBomb {

	public static float HealthOn = 1;
	public static float XtraBombOn = 1;
	public static float PortalOn = 1;
	public static float BombRangeOn = 1;
	public static float BombStrangeOn = 1;
	public static float DoubleScoreOn = 1;
	public static float ITEM_PROBABLY = 0.03f;
	final float PROBABILITY_HEALTH = ITEM_PROBABLY; // 0.02f (TESTEINSTELLUNGEN)
	final float PROBABILITY_XTRA_BOMB = ITEM_PROBABLY;
	final float PROBABILITY_PORTAL = ITEM_PROBABLY;
	final float PROBABILITY_BOMB_RANGE = ITEM_PROBABLY;
	final float PROBABILITY_BOMB_STRENGTH = ITEM_PROBABLY;
	final float PROBABILITY_DOUBLE_SCORE = ITEM_PROBABLY;
	// Rest ist CUBE_EMPTY

	final static public int SCORE_OBSTACLE = 50;
	final static public int SCORE_HIT_PLAYER = 150;
	final static public int SCORE_KILL = 500;

	final int MILLISECS_PER_TICK = 10;
	final int FUSE_TIME = 300; // (in Hundertstelsekunden) 3 Sekunden
	final int EXPLOSION_SPEED = 40; // 0,1 Sekunden 40
	final int EXPLOSION_TIME = 50; // 0,2 Sekunden 50
	final int BOMB_STRENGTH = 1;
	Timer timer;
	Level level;
	// FIXME Netzwerkfähig machen
	List<Player> listPlayer;
	List<NetPlayer> listNetPlayer;
	List<Bomb> listBomb;
	List<Explosion> listExplosion;

	int strengthMultiplier;

	// zeigt an ob es sich um eine Player- oder und eine NetPlayer-List handelt
	private boolean net;
	private Player tmpPlayer;

	final int MILLISECS_PER_CHAT = 300;
	static int chatTime = 0;

	// TODO down/upcasten muss IRGENDWIE gehen!! Um nicht zwei Playerlisten
	// uebergeben zu muessen
	public ThreadBomb(Level level, List<Player> listPlayer, List<NetPlayer> listNetPlayer) {
		if (listPlayer != null) {
			this.listPlayer = listPlayer;
			net = false;
		} else if (listNetPlayer != null) {
			this.listNetPlayer = listNetPlayer;
			net = true;
		} else {
			System.exit(-1);
		}
		this.level = level;
		this.listBomb = new ArrayList<Bomb>();
		this.listExplosion = new ArrayList<Explosion>();
		timer = new Timer(MILLISECS_PER_TICK, new TimerBombs());
		timer.start();
	}

	public void resetChatTime() {
		chatTime = 0;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public void stop() {
		timer.stop();
	}

	public void start() {
		timer.start();
	}

	public void setBomb(int x, int y, int z, int radius, Player player) {
		if (level.getCubeName(x, y, z).equals(Cube.CUBE_EMPTY)) {
			player.decreaseBombs();
			listBomb.add(new Bomb(x, y, z, radius, player));
		}
		// System.out.println("BOMBE IN DEN THREAD");
	}

	class TimerBombs implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (!Level.inMenu) {
				chatTime++;
				if (chatTime == MILLISECS_PER_CHAT) {
					Game.getHUD().addToChatLog("");
					chatTime = 0;
				}
			}
			if (net) {
				for (int i = 0; i < listNetPlayer.size(); i++) {
					listNetPlayer.get(i).accerlate();
				}
			} else {
				for (int i = 0; i < listPlayer.size(); i++) {
					listPlayer.get(i).accerlate();
				}
			}
			for (int i = 0; i < listBomb.size(); i++) {
				listBomb.get(i).tick();
			}
			for (int i = 0; i < listExplosion.size(); i++) {
				listExplosion.get(i).tick();
			}
		}
	}

	class Bomb {
		private int x, y, z;
		private int fuseTime;
		private int radius;
		private Player player;

		public Bomb(int x, int y, int z, int radius, Player player) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.fuseTime = FUSE_TIME;
			this.radius = radius;
			this.player = player;
			level.setCube(Cube.getCubeByName(Cube.CUBE_BOMB), x, y, z);
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public int getZ() {
			return z;
		}

		public void tick() {
			fuseTime--;
			if (fuseTime == 0) {
				explode();
			}
		}

		public int getBombStartEnergy() {
			return BOMB_STRENGTH * player.getbombStrengthMultiplier();
		}

		public void explode() {
			level.setCube(Cube.getCubeByName(Cube.CUBE_EMPTY), x, y, z);// sonst
																		// brauchst
																		// die
																		// Bombe
																		// schon
																		// einen
																		// strength-Punkt
																		// um
																		// die
																		// Bombe
																		// selbst
																		// wegzusprengen
			listBomb.remove(this);
			new Explosion(x, y, z, x, y, z, radius, player, this.getBombStartEnergy());
			player.increaseBombs();
		}
	}

	class Explosion {
		int x, y, z;
		int startX, startY, startZ;
		int timeRest;
		int radius;
		int strength;
		Player player;

		public Explosion(int startX, int startY, int startZ, int x, int y, int z, int radius, Player player, int strength) {
			if (level.getCube(x, y, z).isDestroyable()) {
				this.x = x;
				this.y = y;
				this.z = z;
				// Bomben die in Explosionen geraten explodieren lassen
				if (level.getCubeName(x, y, z).equals(Cube.CUBE_BOMB)) {
					Bomb tmpBomb;
					for (int i = 0; i < listBomb.size(); i++) {
						tmpBomb = listBomb.get(i);
						if (x == tmpBomb.getX() && y == tmpBomb.getY() && z == tmpBomb.getZ()) {
							tmpBomb.explode();
							break;
						}
					}
					// Immer nur eine Kiste wegsprengen können (oder je nach
					// Sprengkraft)
				} else if (!level.getCubeName(x, y, z).equals(Cube.CUBE_EMPTY)) {
					strength--;
				}
				this.startX = startX;
				this.startY = startY;
				this.startZ = startZ;
				this.radius = radius;
				this.strength = strength;
				this.timeRest = EXPLOSION_TIME;
				this.player = player;
				listExplosion.add(this);

				// Würfel, der den Ausgang verbirgt transportiert den Ausgang
				// hinter die Explosion
				if (level.getCubeName(x, y, z).equals(Cube.CUBE_OBSTACLE_HIDE_EXIT)) {
					level.setCube(Cube.getCubeByName(Cube.CUBE_EXPLOSION_HIDE_EXIT), x, y, z);
					// Ein Hinderniswürfel bringt Punkte
					player.addScore(SCORE_OBSTACLE);
					System.out.println("");
					System.out.println("Die zerstörte Wand bringt dir " + SCORE_OBSTACLE + " Punkte.");
					System.out.println("Du hast jetzt " + player.getScore() + " Punkte!");
					System.out.println("");

					// Ein Hinderniswürfel kann später zu einem Item werden
				} else if (level.getCubeName(x, y, z).equals(Cube.CUBE_OBSTACLE)) {
					level.setCube(Cube.getCubeByName(Cube.CUBE_EXPLOSION_HIDE_ITEM), x, y, z);
					// Ein Hinderniswürfel bringt Punkte
					player.addScore(SCORE_OBSTACLE);
					System.out.println("");
					System.out.println("Das zerstörte Wand bringt dir " + SCORE_OBSTACLE + " Punkte.");
					System.out.println("Du hast jetzt " + player.getScore() + " Punkte!");
					System.out.println("");

					// alle anderen Würfel (leere, Items, ...) verschwinden
				} else {
					level.setCube(Cube.getCubeByName(Cube.CUBE_EXPLOSION), x, y, z);
				}

				// // Überprüfe, ob Spieler von Explosion getroffen wird
				if (net) {
					for (int c = 0; c < listNetPlayer.size(); c++) {
						if ((level.getCubeName(x, y, z).equals(Cube.CUBE_EXPLOSION))
								|| (level.getCubeName(x, y, z).equals(Cube.CUBE_EXPLOSION_HIDE_EXIT))
								|| (level.getCubeName(x, y, z).equals(Cube.CUBE_EXPLOSION_HIDE_ITEM))) {

							// Wenn sich an der Stelle der Explosion ein
							// Spieler befindet, wird dieser getroffen
							if ((x == listNetPlayer.get(c).getCubeX()) && (y == listNetPlayer.get(c).getCubeY())
									&& (z == listNetPlayer.get(c).getCubeZ())) {
								// Spieler werden u. a. Lebenspunkte abgezogen
								level.getCube(x, y, z).change(listNetPlayer.get(c), level);

								// Ein Spielertreffer bringt Punkte
								if (listNetPlayer.get(c).getNumber() != player.getNumber()) {
									player.addScore(SCORE_HIT_PLAYER);
									System.out.println("");
									System.out.println("Der Treffer bringt dir " + SCORE_HIT_PLAYER + " Punkte.");
									System.out.println("Du hast jetzt " + player.getScore() + " Punkte!");
									System.out.println("");
								}

								listNetPlayer.get(c).addAcceleration(Math.signum(x - startX) * 2, Math.signum(y - startY) * 2,
										Math.signum(z - startZ) * 2);

								// Abfrage, ob Player noch lebt oder getötet
								// wurde
								if (listNetPlayer.get(c).getHealthPoints() <= 0) {
									// Ein Kill bringt Punkte
									// TODO Aber nur wenn man sich nicht selbst
									// umgebracht hat...
									System.out.println("");
									player.addScore(SCORE_KILL);
									System.out.println("Der Kill bringt dir " + SCORE_KILL + " Punkte.");
									System.out.println("Du hast nun " + player.getScore() + " Punkte!");
									player.increaseHits();
									listNetPlayer.get(c).dies();
								}
							}
						}
					}
				} else {
					for (int c = 0; c < listPlayer.size(); c++) {
						if ((level.getCubeName(x, y, z).equals(Cube.CUBE_EXPLOSION))
								|| (level.getCubeName(x, y, z).equals(Cube.CUBE_EXPLOSION_HIDE_EXIT))
								|| (level.getCubeName(x, y, z).equals(Cube.CUBE_EXPLOSION_HIDE_ITEM))) {

							// Wenn sich an der Stelle der Explosion ein
							// Spieler befindet, wird dieser getroffen
							if ((x == listPlayer.get(c).getCubeX()) && (y == listPlayer.get(c).getCubeY())
									&& (z == listPlayer.get(c).getCubeZ())) {
								// Spieler werden u. a. Lebenspunkte abgezogen
								level.getCube(x, y, z).change(listPlayer.get(c), level);

								// Ein Spielertreffer bringt Punkte
								if (listPlayer.get(c).getNumber() != player.getNumber()) {
									player.addScore(SCORE_HIT_PLAYER);
									System.out.println("");
									System.out.println("Der Treffer bringt dir " + SCORE_HIT_PLAYER + " Punkte.");
									System.out.println("Du hast jetzt " + player.getScore() + " Punkte!");
									System.out.println("");
								}

								listPlayer.get(c).addAcceleration(Math.signum(x - startX) * 2, Math.signum(y - startY) * 2,
										Math.signum(z - startZ) * 2);

								// Abfrage, ob Player noch lebt oder getötet
								// wurde
								if (listPlayer.get(c).getHealthPoints() <= 0) {
									// Ein Kill bringt Punkte
									System.out.println("");
									player.addScore(SCORE_KILL);
									System.out.println("Der Kill bringt dir " + SCORE_KILL + " Punkte.");
									System.out.println("Du hast nun " + player.getScore() + " Punkte!");
									listPlayer.get(c).dies();
								}
							}
						}
					}
				}
			}
		}

		public void tick() {
			timeRest--;
			if (timeRest == EXPLOSION_SPEED) {
				if (radius > 0 && strength > 0) {
					if (startX == x && startY == y && startZ == z) {
						new Explosion(startX, startY, startZ, x + 1, y, z, radius - 1, player, strength);
						new Explosion(startX, startY, startZ, x - 1, y, z, radius - 1, player, strength);
						new Explosion(startX, startY, startZ, x, y + 1, z, radius - 1, player, strength);
						new Explosion(startX, startY, startZ, x, y - 1, z, radius - 1, player, strength);
						new Explosion(startX, startY, startZ, x, y, z + 1, radius - 1, player, strength);
						new Explosion(startX, startY, startZ, x, y, z - 1, radius - 1, player, strength);
					} else if (startX < x) {
						new Explosion(startX, startY, startZ, x + 1, y, z, radius - 1, player, strength);
					} else if (startX > x) {
						new Explosion(startX, startY, startZ, x - 1, y, z, radius - 1, player, strength);
					} else if (startY < y) {
						new Explosion(startX, startY, startZ, x, y + 1, z, radius - 1, player, strength);
					} else if (startY > y) {
						new Explosion(startX, startY, startZ, x, y - 1, z, radius - 1, player, strength);
					} else if (startZ < z) {
						new Explosion(startX, startY, startZ, x, y, z + 1, radius - 1, player, strength);
					} else if (startZ > z) {
						new Explosion(startX, startY, startZ, x, y, z - 1, radius - 1, player, strength);
					}
				}
			}
			if (timeRest == 0) {
				listExplosion.remove(this);
				// Verbirgt sich der Ausgang hinter der Explosion, wird dieser
				// nun freigelegt
				if (level.getCubeName(x, y, z).equals(Cube.CUBE_EXPLOSION_HIDE_EXIT)) {
					level.setCube(Cube.getCubeByName(Cube.CUBE_EXIT), x, y, z);
					// Hinter Explosionen, die Hindernisse zerstört haben,
					// können
					// nun Items entstehen...
				} else if (level.getCubeName(x, y, z).equals(Cube.CUBE_EXPLOSION_HIDE_ITEM)) {
					// Erzeuge Zufallszahl, um zu entscheiden, was an dieser
					// Stelle
					// erscheinen soll
					float random = new Random().nextFloat();
					// ITEM: Health (Medipack)
					if (random < PROBABILITY_HEALTH) {
						level.setCube(Cube.getCubeByName(Cube.CUBE_ITEM_HEALTH), x, y, z);
						// ITEM: Extra Bombe (erhöht maximale Anzahl
						// gleichzeitiger Bomben
					} else if (random < (PROBABILITY_HEALTH + PROBABILITY_XTRA_BOMB)) {
						level.setCube(Cube.getCubeByName(Cube.CUBE_ITEM_XTRA_BOMB), x, y, z);
						// ITEM: Portal (transportiert einen zu neuer Position)
					} else if (random < (PROBABILITY_HEALTH + PROBABILITY_XTRA_BOMB + PROBABILITY_PORTAL)) {
						level.setCube(Cube.getCubeByName(Cube.CUBE_ITEM_PORTAL), x, y, z);
						// ITEM: Bomb Range (Erhöht den Radius von Bomben)
					} else if (random < (PROBABILITY_HEALTH + PROBABILITY_XTRA_BOMB + PROBABILITY_PORTAL + PROBABILITY_BOMB_RANGE)) {
						level.setCube(Cube.getCubeByName(Cube.CUBE_ITEM_BOMB_RANGE), x, y, z);
						// ITEM: Bomb Strength (Erhöht die Durchschlagskraft von
						// Bomben)
					} else if (random < (PROBABILITY_HEALTH + PROBABILITY_XTRA_BOMB + PROBABILITY_PORTAL
							+ PROBABILITY_BOMB_RANGE + PROBABILITY_BOMB_STRENGTH)) {
						level.setCube(Cube.getCubeByName(Cube.CUBE_ITEM_BOMB_STRENGTH), x, y, z);
						// ITEM: Double Score (verdoppelt den Punktestand des
						// Spielers)
					} else if (random < (PROBABILITY_HEALTH + PROBABILITY_XTRA_BOMB + PROBABILITY_PORTAL
							+ PROBABILITY_BOMB_RANGE + PROBABILITY_BOMB_STRENGTH + PROBABILITY_DOUBLE_SCORE)) {
						level.setCube(Cube.getCubeByName(Cube.CUBE_ITEM_DOUBLE_SCORE), x, y, z);
						// ...oder es können leere Würfel entstehen
					} else {
						level.setCube(Cube.getCubeByName(Cube.CUBE_EMPTY), x, y, z);
					}
				} else {
					level.setCube(Cube.getCubeByName(Cube.CUBE_EMPTY), x, y, z);
				}
			}
		}
	}
}
