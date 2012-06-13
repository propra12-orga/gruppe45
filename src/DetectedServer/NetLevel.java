package DetectedServer;

import game.Level;
import game.cube.Cube;

import java.util.ArrayList;
import java.util.List;

public class NetLevel extends Level {

	private List<NetPlayer> listNetPlayer = new ArrayList<NetPlayer>();

	public NetLevel(int x, int y, int z, List<NetPlayer> listNetPlayer) {
		super(x, y, z, null);
		this.listNetPlayer = listNetPlayer;
	}

	public void clear() {
		super.clear();
		// TODO 1/3 Stuerzt ohne diese Einschraenkung ab...
		if (listNetPlayer != null) {
			for (int i = 0; i < listNetPlayer.size(); i++) {
				listNetPlayer.get(i).msgSendLevel();
			}
		}
	}

	public String getPlayerList() {
		String playerList = "";
		int i = 0;
		// TODO 2/3 Stuerzt ohne diese Einschraenkung ab...
		if (listNetPlayer != null) {
			for (i = 0; i < listNetPlayer.size(); i++) {
				playerList += ":" + listNetPlayer.get(i).getNumber();
				playerList += ":" + listNetPlayer.get(i).getX();
				playerList += ":" + listNetPlayer.get(i).getY();
				playerList += ":" + listNetPlayer.get(i).getZ();
				playerList += ":" + listNetPlayer.get(i).getAngleX();
				playerList += ":" + listNetPlayer.get(i).getAngleY();
				playerList += ":" + listNetPlayer.get(i).getHealthPoints();
			}
		}
		return i + playerList;
	}

	public void setCube(Cube cube, int x, int y, int z) {
		super.setCube(cube, x, y, z);
		// TODO 3/3 Stuerzt ohne diese Einschraenkung ab...
		if (listNetPlayer != null) {
			for (int i = 0; i < listNetPlayer.size(); i++) {
				listNetPlayer.get(i).msgSendCube(x, y, z);
			}
		}
	}

	public void deletePlayer(int number) {
		if (listNetPlayer != null) {
			for (int i = 0; i < listNetPlayer.size(); i++) {
				if (listNetPlayer.get(i).getNumber() == number) {
					listNetPlayer.remove(i);
					break;
				}
			}
		}
	}
}
