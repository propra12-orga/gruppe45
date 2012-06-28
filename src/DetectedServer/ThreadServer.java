package DetectedServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Der Detected Server wird zusaetzlich neben dem Spiel gestartet
 */

public class ThreadServer implements Runnable {

	final private static int SERVER_PORT = 12345;
	final private static int LEVEL_X = 20;
	final private static int LEVEL_Y = 20;
	final private static int LEVEL_Z = 20;

	final public static int SERVER_MAX_PLAYER = 10;

	// Zufaellige SpawnPoints
	// TODO Muss abhaenig von der Levelgroesse sein
	final private static float[][] spawnPoint = { { 85, 85, 15 }, { 25, 15, 85 } };

	private List<NetPlayer> listNetPlayer;
	private NetLevel netLevel;
	private ServerSocket server;
	private NetPlayer netPlayer;
	private int number;
	private int random;

	// private ThreadBomb threadBomb;

	public ThreadServer() {
		listNetPlayer = new ArrayList<NetPlayer>();
		netLevel = new NetLevel(LEVEL_X, LEVEL_Y, LEVEL_Z, listNetPlayer);
		// threadBomb = new ThreadBomb(netLevel, null, listNetPlayer);
		random = 0; // TODO Muss noch zufaellig werden
		number = 1;
		try {
			server = new ServerSocket(SERVER_PORT);
			System.out.println("Server ist gestartet");
			netLevel.clear();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			while (true) {
				Socket client = server.accept();
				System.out.println("Server:Neuer Spieler");
				netPlayer = new NetPlayer(netLevel, spawnPoint[random][0], spawnPoint[random][1], spawnPoint[random][2],
						listNetPlayer, number++, client);
				netPlayer.setBombs(1);
				new Thread(new ThreadClient(netLevel, netPlayer, listNetPlayer)).start();
				listNetPlayer.add(netPlayer);
				if (listNetPlayer.size() > SERVER_MAX_PLAYER) {
					netPlayer.write(NetPlayer.MSG_SERVER_FULL + ":");
				} else {
					netPlayer.setBombs(1);

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
