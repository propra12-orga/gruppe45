package game.cube;

public class CubeData {
	static private int counter = 0;

	private int number;
	private Cube cube;

	CubeData(Cube cube, String name) {
		this.number = counter++;
		this.cube = cube;
		cube.setCubeName(name);
	}

	public int getNumber() {
		return number;
	}

	public Cube getCubeObject() {
		return cube;
	}

	public String getCubeName() {
		return cube.getCubeName();
	}
}
