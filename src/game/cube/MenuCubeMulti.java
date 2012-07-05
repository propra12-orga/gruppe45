package game.cube;

import java.awt.BorderLayout;
import java.awt.LayoutManager;

import javax.swing.*;

import game.Game;
import game.Level;
import game.Player;

public class MenuCubeMulti extends Cube {
	public static String IP;
	public MenuCubeMulti() {

		super(Cube.IS_WALKABLE, Cube.IS_COLLECTABLE, Cube.IS_NOT_DESTROYABLE);
		
	}

	@Override
	public void change(Player player, Level level) {
		ipRequest(IP);
		level.setCube(Cube.getCubeByName(Cube.CUBE_EMPTY), player.getCubeX(), player.getCubeY(), player.getCubeZ());
		level.setInMenu(false);
		Game.connect();
	}
	public void ipRequest(String IP_ADRESSE){
		IP_ADRESSE = JOptionPane.showInputDialog("Bitte IP eingeben:");
		System.out.println("Die eingegebene IP-Adresse lautet: " + IP_ADRESSE);		
	}
}
