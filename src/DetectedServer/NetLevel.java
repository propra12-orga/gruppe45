package DetectedServer;

import game.Level;
import game.cube.Cube;

import java.util.ArrayList;
import java.util.List;

public class NetLevel extends Level {

	private List<NetPlayer> listNetPlayer = new ArrayList<NetPlayer>();
	// Zufaellige SpawnPoints
	// TODO Muss abhaenig von der Levelgroesse sein
	final private static float[][] spawnPoint = { { 85, 85, 15, 0 }, { 25, 15, 85, 0 } };

	public NetLevel(int x, int y, int z, List<NetPlayer> listNetPlayer) {
		super(x, y, z);
		this.listNetPlayer = listNetPlayer;
	}

	/**
	 * Gibt einen Spawnpoint als Array (x,y,z,angleY) aus
	 */
	public float[] getSpawnPoint() {
		return spawnPoint[0];
	}

	public void clear() {
		// FIXME GravityLevel als Standardlevel einrichten
		// FIXME + zunächst Spielerstartposition anpassen!!!
		super.buildDefaultLevel();
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
				playerList += ":" + listNetPlayer.get(i).getHits();
				playerList += ":" + listNetPlayer.get(i).getDeaths();
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
