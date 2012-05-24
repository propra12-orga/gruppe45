package game;

import game.cube.Cube;

import java.util.TimerTask;

public class TimeCube extends TimerTask {

	Cube cube;
	ArrayPosition[] positions;
	Level level;

	public TimeCube(Level level, Cube cube, ArrayPosition[] positions) {
		this.positions = positions;
		this.cube = cube;
		this.level = level;
	}

	@Override
	public void run() {
		for (int i = 0; i < positions.length; i++) {	
			Cube tmpcube = level.getCube(positions[i].getX(), positions[i].getY(), positions[i].getZ());
			if (tmpcube.isDestroyable()) {	
				level.setCube(cube, positions[i].getX(), positions[i].getY(),
						positions[i].getZ());
			}	
		}
	}
}