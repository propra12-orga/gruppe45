package game;

import java.util.TimerTask;
import game.Player;

public class BombCount extends TimerTask{
	Player player;
	int maxBombs;	
	
	
	@Override
	public void run() {
		player.maxBombs ++;
	}

	public BombCount(Player player, int maxBombs){
		this.player = player;
		this.maxBombs = maxBombs;
	}
	
}
