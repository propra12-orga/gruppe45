package DetectedServer;

import game.Level;
import game.Player;
import game.cube.Cube;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ThreadWindowNetwork implements Runnable {

	List<Player> listPlayer;
	Socket server;
	PrintWriter out;
	BufferedReader in;
	NetPlayer myPlayer;
	Level level;

	public ThreadWindowNetwork(List<Player> listPlayer, Socket server, NetPlayer myPlayer, Level level) {
		this.listPlayer = listPlayer;
		this.server = server;
		this.myPlayer = myPlayer;
		this.level = level;
		try {
			out = new PrintWriter(server.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(server.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		String strIn;
		String[] splitMsg;
		while (true) {
			if (null != (strIn = myPlayer.read())) {
				splitMsg = strIn.split(":");
				if (splitMsg[0].equals(NetPlayer.MSG_POSITION)) {
					for (int i = 0; i < listPlayer.size(); i++) {
						if (listPlayer.get(i).getNumber() == Integer.valueOf(splitMsg[1])) {
							listPlayer.get(i).setPosition(Float.valueOf(splitMsg[2]), Float.valueOf(splitMsg[3]),
									Float.valueOf(splitMsg[4]));
							break;
						}
					}
				} else if (splitMsg[0].equals(NetPlayer.MSG_CUBE)) {
					level.setCube(Cube.getCubeByNumber(Integer.valueOf(splitMsg[1])), Integer.valueOf(splitMsg[2]),
							Integer.valueOf(splitMsg[3]), Integer.valueOf(splitMsg[4]));
				} else if (splitMsg[0].equals(NetPlayer.MSG_PLAYERLIST)) {
					myPlayer.msgReceivePlayerList(splitMsg, listPlayer);
				} else if (splitMsg[0].equals(NetPlayer.MSG_EXIT)) {
					myPlayer.dies();
					System.exit(0);
				}

			}
		}
	}

}
