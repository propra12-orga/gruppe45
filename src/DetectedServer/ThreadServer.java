package DetectedServer;

import game.Game;
import game.ThreadBomb;

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
	final private static int LEVEL_X = 13;
	final private static int LEVEL_Y = 13;
	final private static int LEVEL_Z = 14;

	final public static int SERVER_MAX_PLAYER = 10;

	private List<NetPlayer> listNetPlayer;
	private List<Thread> listThread;
	private NetLevel netLevel;
	private ServerSocket server;
	private NetPlayer netPlayer;
	private Thread thread;
	private Thread threadThis;
	private int number;
	private int random;

	private ThreadBomb threadBomb;

	public ThreadServer() {
		listNetPlayer = new ArrayList<NetPlayer>();
		listThread = new ArrayList<Thread>();
		netLevel = new NetLevel(LEVEL_X, LEVEL_Y, LEVEL_Z, listNetPlayer);
		Game.setThreadBomb(new ThreadBomb(netLevel, null, listNetPlayer));
		// threadBomb = new ThreadBomb(netLevel, null, listNetPlayer);
		// Game.setThreadBomb(threadBomb);
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
				netPlayer = new NetPlayer(netLevel, listNetPlayer, number++, client, listNetPlayer);
				listNetPlayer.add(netPlayer);
				thread = new Thread(new ThreadClient(netLevel, netPlayer, listNetPlayer));
				thread.start();
				listThread.add(thread);
				if (listNetPlayer.size() > SERVER_MAX_PLAYER) {
					netPlayer.write(NetPlayer.MSG_SERVER_FULL + ":");
				} else {
					netPlayer.setBombs(1);
					for (int i = 0; i < listNetPlayer.size(); i++) {
						listNetPlayer.get(i).msgSendPlayerList();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void kickAllPlayers() {
		for (int i = 0; i < listNetPlayer.size(); i++) {
			listNetPlayer.get(i).msgSendExit();
		}
	}

	public void delete() {
		kickAllPlayers();
		try {
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// FIXME Warum funktioniert dieser scheiss Destruktor nicht???
	// public void finalize() {
	// System.out.println("Serverthread getötet");
	//
	// kickAllPlayers();
	//
	// try {
	// server.close();
	// System.out.println("Server geschlossen");
	// } catch (IOException e) {
	// System.out.println("Server versucht zu schliessen");
	// e.printStackTrace();
	// }
	// }
}
