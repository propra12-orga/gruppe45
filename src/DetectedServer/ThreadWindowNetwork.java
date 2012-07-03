package DetectedServer;

import game.Game;
import game.Level;
import game.Player;
import game.cube.Cube;

import java.util.List;

/**
 * diese Klasse wird von einem Klienten erzeugt um mit dem Server zu
 * kommunizieren
 */
public class ThreadWindowNetwork implements Runnable {

	private Level level;
	private NetPlayer myPlayer;
	private List<Player> listPlayer;

	public ThreadWindowNetwork(Level level, NetPlayer myPlayer, List<Player> listPlayer) {
		this.level = level;
		this.myPlayer = myPlayer;
		this.listPlayer = listPlayer;
	}

	public void run() {
		String strIn;
		String[] strSplit;
		while (true) {
			if (null != (strIn = myPlayer.read())) {
				strSplit = strIn.split(":");
				if (strSplit[0].equals(NetPlayer.MSG_POSITION)) {
					for (int i = 0; i < listPlayer.size(); i++) {
						if (listPlayer.get(i).getNumber() == Integer.valueOf(strSplit[1])) {
							listPlayer.get(i).setPosition(Float.valueOf(strSplit[2]), Float.valueOf(strSplit[3]),
									Float.valueOf(strSplit[4]));
							break;
						}
					}
				} else if (strSplit[0].equals(NetPlayer.MSG_CUBE)) {
					level.setCube(Cube.getCubeByNumber(Integer.valueOf(strSplit[1])), Integer.valueOf(strSplit[2]),
							Integer.valueOf(strSplit[3]), Integer.valueOf(strSplit[4]));
				} else if (strSplit[0].equals(NetPlayer.MSG_PLAYERLIST)) {
					myPlayer.msgReceivePlayerList(strSplit, listPlayer);
				} else if (strSplit[0].equals(NetPlayer.MSG_EXIT)) {
					System.out.println("Client hat EXIT erhalten");
					Game.disconnect();
				}
			}
		}
	}

}
