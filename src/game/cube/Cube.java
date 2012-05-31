package game.cube;

import game.Player;

/**
 * Abstrakte Klasse von der alle anderen Würfel abgeleitet werden
 */
public abstract class Cube {
	final static public boolean IS_COLLECTABLE = true;
	final static public boolean IS_NOT_COLLECTABLE = false;
	final static public boolean IS_WALKABLE = true;
	final static public boolean IS_NOT_WALKABLE = false;
	final static public boolean IS_DESTROYABLE = true;
	final static public boolean IS_NOT_DESTROYABLE = false;
	final static public boolean HIDES_EXIT = true;
	final static public boolean DOES_NOT_HIDE_EXIT = false;

	final static public String CUBE_BOMB = "CubeBomb";
	final static public String CUBE_EMPTY = "CubeEmpty";
	final static public String CUBE_EXIT = "CubeExit";
	final static public String CUBE_EXPLOSION = "CubeExplosion";
	final static public String CUBE_EXPLOSION_HIDE_EXIT = "CubeExplosionHideExit";
	final static public String CUBE_ITEM_HEALTH = "CubeItemHealth";
	final static public String CUBE_OBSTACLE = "CubeObstacle";
	final static public String CUBE_OUTSIDE = "CubeOutside";
	final static public String CUBE_SOLID = "CubeSolid";

	final static public CubeData[] cubeData = { new CubeData(new CubeBomb(), CUBE_BOMB),
			new CubeData(new CubeEmpty(), CUBE_EMPTY), new CubeData(new CubeExit(), CUBE_EXIT),
			new CubeData(new CubeExplosion(), CUBE_EXPLOSION),
			new CubeData(new CubeExplosionHideExit(), CUBE_EXPLOSION_HIDE_EXIT),
			new CubeData(new CubeItemHealth(), CUBE_ITEM_HEALTH), new CubeData(new CubeObstacle(), CUBE_OBSTACLE),
			new CubeData(new CubeOutside(), CUBE_OUTSIDE), new CubeData(new CubeSolid(), CUBE_SOLID) };

	boolean walkable;
	boolean collectable;
	boolean destroyable;
	boolean hidesExit;
	String name;

	/**
	 * @param walkable
	 *            Ist der Wuerfel begehbar(true) oder nicht(false)
	 */
	Cube(boolean walkable, boolean collectable, boolean destroyable) {
		this.walkable = walkable;
		this.collectable = collectable;
		this.destroyable = destroyable;
		this.hidesExit = false;
	}

	public void setCubeName(String name) {
		this.name = name;
	}

	public Cube getCube() {
		return this;
	}

	static public Cube getCubeByNumber(int number) {
		if (number >= 0 && number < cubeData.length) {
			return cubeData[number].getCubeObject();
		} else {
			return null;
		}
	}

	static public Cube getCubeByName(String name) {
		for (int i = 0; i < cubeData.length; i++) {
			if (cubeData[i].getCubeName().equals(name)) {
				return cubeData[i].getCubeObject();
			}
		}
		return null;
	}

	static public int getNumberByCube(Cube cube) {
		for (int i = 0; i < cubeData.length; i++) {
			if (cubeData[i].getCubeObject().equals(cube)) {
				return cubeData[i].getNumber();
			}
		}
		return -1;
	}

	static public String getNameByCube(Cube cube) {
		for (int i = 0; i < cubeData.length; i++) {
			if (cubeData[i].getCubeObject().equals(cube)) {
				return cubeData[i].getCubeName();
			}
		}
		return null;
	}

	/**
	 * @return Gibt aus ob der Wuerfel begehbar ist
	 */
	public boolean isWalkable() {
		return this.walkable;
	}

	public boolean isCollectable() {
		return this.collectable;
	}

	public boolean isDestroyable() {
		return this.destroyable;
	}

	public boolean hidesExit() {
		return this.hidesExit;
	}

	public String getCubeName() {
		return this.name;
	}

	public void sethidesExit(boolean hidesexit) {
		this.hidesExit = hidesexit;
	}

	public void change(Player player) {
		player.healPlayer(25);
	}

	public void change() {

	}

}
