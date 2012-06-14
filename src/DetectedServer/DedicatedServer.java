package DetectedServer;

import game.ThreadBomb;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Der Detected Server wird zusaetzlich neben dem Spiel gestartet
 */

public class DedicatedServer {

	final private static int SERVER_PORT = 12345;
	final private static int LEVEL_X = 20;
	final private static int LEVEL_Y = 20;
	final private static int LEVEL_Z = 20;

	final public static int SERVER_MAX_PLAYER = 10;

	// Zufaellige SpawnPoints
	// TODO Muss abhaenig von der Levelgroesse sein
	final private static float[][] spawnPoint = { { 85, 85, 15 }, { 25, 15, 85 } };

	private static List<NetPlayer> listNetPlayer = new ArrayList<NetPlayer>();
	private static NetLevel netLevel = new NetLevel(LEVEL_X, LEVEL_Y, LEVEL_Z, listNetPlayer);
	private static ThreadBomb threadBomb = new ThreadBomb(netLevel, null,listNetPlayer);

	public static void main(String[] args) {
		ServerSocket server;
		NetPlayer netPlayer;
		int random = 0; // TODO Muss noch zufaellig werden
		try {
			server = new ServerSocket(SERVER_PORT);
			int number = 1;

			System.out.println("Server ist gestartet");

			netLevel.clear();
			while (true) {
				Socket client = server.accept();
				netPlayer = new NetPlayer(netLevel, spawnPoint[random][0], spawnPoint[random][1], spawnPoint[random][2],
						listNetPlayer, number++, client);
				if (listNetPlayer.size() > SERVER_MAX_PLAYER) {
					netPlayer.write(NetPlayer.MSG_SERVER_FULL + ":");
				} else {
					listNetPlayer.add(netPlayer);
					new Thread(new ThreadClient(netLevel, netPlayer, listNetPlayer)).start();
					for (int i = 0; i < (listNetPlayer.size() - 1); i++) {
						listNetPlayer.get(i).msgSendPlayerList();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
