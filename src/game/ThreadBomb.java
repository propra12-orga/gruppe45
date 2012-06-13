package game;

import game.cube.Cube;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;

/**
 * Laesst Bomben ticken und explodieren Laesst sich die Explosionen ausbreiten
 * und kann andere Bomben zuenden
 */
public class ThreadBomb {

	final float PROBABILITY_HEALTH = 0.01f;
	final float PROBABILITY_XTRA_BOMB = 0.01f;
	final float PROBABILITY_PORTAL = 0.01f;
	// Rest ist CUBE_EMPTY

	final int MILLISECS_PER_TICK = 10;
	final int FUSE_TIME = 300; // 3 Sekunden
	final int EXPLOSION_SPEED = 40; // 0,1 Sekunden
	final int EXPLOSION_TIME = 50; // 0,2 Sekunden
	final int BOMB_STRENGTH = 1;
	Timer timer;
	Level level;
	List<Bomb> listBomb;
	List<Explosion> listExplosion;

	public ThreadBomb(Level level) {
		this.level = level;
		this.listBomb = new ArrayList<Bomb>();
		this.listExplosion = new ArrayList<Explosion>();
		timer = new Timer(MILLISECS_PER_TICK, new TimerKeyboard());
		timer.start();
	}

	public void setBomb(int x, int y, int z, int radius, Player player) {
		if (level.getCubeName(x, y, z).equals(Cube.CUBE_EMPTY)) {
			player.decreaseBombs();
			listBomb.add(new Bomb(x, y, z, radius, player));
		}
	}

	class TimerKeyboard implements ActionListener {
		public void actionPerformed(ActionEvent e) {
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
			new Explosion(x, y, z, x, y, z, radius, player, BOMB_STRENGTH);
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
				if (level.getCubeName(x, y, z).equals(Cube.CUBE_OBSTACLE_HIDE_EXIT)) {
					level.setCube(Cube.getCubeByName(Cube.CUBE_EXPLOSION_HIDE_EXIT), x, y, z);
				} else {
					level.setCube(Cube.getCubeByName(Cube.CUBE_EXPLOSION), x, y, z);
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
				if (level.getCubeName(x, y, z).equals(Cube.CUBE_EXPLOSION_HIDE_EXIT)) {
					level.setCube(Cube.getCubeByName(Cube.CUBE_EXIT), x, y, z);
				} else {
					float random = new Random().nextFloat();
					if (random < PROBABILITY_HEALTH) {
						level.setCube(Cube.getCubeByName(Cube.CUBE_ITEM_HEALTH), x, y, z);
					} else if (random < (PROBABILITY_HEALTH + PROBABILITY_XTRA_BOMB)) {
						level.setCube(Cube.getCubeByName(Cube.CUBE_ITEM_XTRA_BOMB), x, y, z);
					} else if (random < (PROBABILITY_HEALTH + PROBABILITY_XTRA_BOMB + PROBABILITY_PORTAL)) {
						level.setCube(Cube.getCubeByName(Cube.CUBE_ITEM_PORTAL), x, y, z);
					} else {
						level.setCube(Cube.getCubeByName(Cube.CUBE_EMPTY), x, y, z);
					}
				}
			}
		}
	}
}
