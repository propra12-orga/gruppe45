package game;

import java.util.TimerTask;
import game.Player;

public class BombCount extends TimerTask{
	Player player;
	int bombenzahl;
	int maxBombs;	
	
	
	@Override
	public void run() {
		player.maxBombs ++;
//		System.out.println(player.bombenzahl);
		
	}

	public BombCount(Player player, int bombenzahl,int maxBombs){
		this.player = player;
		this.bombenzahl = bombenzahl;
		this.maxBombs = maxBombs;
	}
	
}
