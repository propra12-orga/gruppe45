package game;

import game.cube.Cube;
import game.cube.CubeExit;
import game.Player;
import java.util.TimerTask;
import game.Level;

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
			boolean transportExit = false;  
			
			Cube tmpcube = level.getCube(positions[i].getX(), positions[i].getY(), positions[i].getZ());
			if (tmpcube.hidesExit()) transportExit = true;	//Merker, ob sich hier der Exit verbirgt
			
			if (tmpcube.isDestroyable()) {	
				level.setCube(cube, positions[i].getX(), positions[i].getY(),
						positions[i].getZ());
			}
			
			if (transportExit) {	// Wenn sich hinter dem Würfel der Exit verborgen hat,
									// so wird dieser nun freigelegt!
				level.setCube(new CubeExit(), positions[i].getX(), positions[i].getY(),
								positions[i].getZ());
			}
		}
	}
}