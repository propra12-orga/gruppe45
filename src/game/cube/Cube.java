package game.cube;

import game.Level;
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

	final static public String CUBE_BOMB = "CubeBomb";
	final static public String CUBE_EMPTY = "CubeEmpty";
	final static public String CUBE_EXIT = "CubeExit";
	final static public String CUBE_EXPLOSION = "CubeExplosion";
	final static public String CUBE_EXPLOSION_HIDE_EXIT = "CubeExplosionHideExit";
	final static public String CUBE_EXPLOSION_HIDE_ITEM = "CubeExplosionHideItem";
	final static public String CUBE_OBSTACLE_HIDE_EXIT = "CubeObstacleHideExit";
	final static public String CUBE_OBSTACLE = "CubeObstacle";
	final static public String CUBE_OUTSIDE = "CubeOutside";
	final static public String CUBE_SOLID = "CubeSolid";
	final static public String CUBE_SOLID_RAMP = "CubeSolidRamp";
	// Items
	final static public String CUBE_ITEM_HEALTH = "CubeItemHealth";
	final static public String CUBE_ITEM_XTRA_BOMB = "CubeItemXtraBomb";
	final static public String CUBE_ITEM_PORTAL = "CubeItemPortal";
	final static public String CUBE_ITEM_BOMB_RANGE = "CubeItemBombRange";
	final static public String CUBE_ITEM_BOMB_STRENGTH = "CubeItemBombStrength";
	// Menüwürfel
	final static public String MENU_CUBE_NEW_GAME = "MenuCubeNewGame";
	final static public String MENU_CUBE_EXIT_PROGRAM = "MenuCubeExitProgram";
	final static public String MENU_CUBE_LOAD_LEVEL = "MenuCubeLoadLevel";
	final static public String MENU_CUBE_MULTI = "MenuCubeMulti";
	final static public String MENU_CUBE_SERVER = "MenuCubeServer";

	final static public CubeData[] cubeData = { new CubeData(new CubeBomb(), CUBE_BOMB),
			new CubeData(new CubeEmpty(), CUBE_EMPTY), new CubeData(new CubeExit(), CUBE_EXIT),
			new CubeData(new CubeExplosion(), CUBE_EXPLOSION),
			new CubeData(new CubeExplosionHideExit(), CUBE_EXPLOSION_HIDE_EXIT),
			new CubeData(new CubeExplosionHideItem(), CUBE_EXPLOSION_HIDE_ITEM),
			new CubeData(new CubeItemHealth(), CUBE_ITEM_HEALTH), new CubeData(new CubeItemXtraBomb(), CUBE_ITEM_XTRA_BOMB),
			new CubeData(new CubeItemBombRange(), CUBE_ITEM_BOMB_RANGE), new CubeData(new CubeItemPortal(), CUBE_ITEM_PORTAL),
			new CubeData(new CubeObstacle(), CUBE_OBSTACLE),
			new CubeData(new CubeOutside(), CUBE_OUTSIDE),
			new CubeData(new CubeSolid(), CUBE_SOLID),
			new CubeData(new CubeSolidRamp(), CUBE_SOLID_RAMP),
			new CubeData(new CubeObstacleHideExit(), CUBE_OBSTACLE_HIDE_EXIT),
			new CubeData(new CubeItemBombStrength(), CUBE_ITEM_BOMB_STRENGTH),
			// Menüwürfel
			new CubeData(new MenuCubeNewGame(), MENU_CUBE_NEW_GAME),
			new CubeData(new MenuCubeExitProgram(), MENU_CUBE_EXIT_PROGRAM),
			new CubeData(new MenuCubeMulti(), MENU_CUBE_MULTI), new CubeData(new MenuCubeServer(), MENU_CUBE_SERVER),
			new CubeData(new MenuCubeLoadLevel(), MENU_CUBE_LOAD_LEVEL) };

	boolean walkable;
	boolean collectable;
	boolean destroyable;
	String name;

	/**
	 * @param walkable
	 *            Ist der Wuerfel begehbar(true) oder nicht(false)
	 */
	Cube(boolean walkable, boolean collectable, boolean destroyable) {
		this.walkable = walkable;
		this.collectable = collectable;
		this.destroyable = destroyable;
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

	public String getCubeName() {
		return this.name;
	}

	public void change() {

	}

	public void change(Player player, Level level) {

	}

}
