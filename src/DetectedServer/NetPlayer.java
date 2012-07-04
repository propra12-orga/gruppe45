package DetectedServer;

import game.Game;
import game.Level;
import game.Player;
import game.cube.Cube;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class NetPlayer extends Player {
	// SendX()
	// ReceiveX(String strIn)
	//
	// ThreadWindowNetwork
	// Client an Server
	// #POSITION:number:x:y:z:angleX:angleY:health
	// move()
	final static public String MSG_POSITION = "1";
	// #BOMB:x:y:z
	// setBomb()
	final static public String MSG_BOMB = "2";
	// #EXIT:number
	// System.exit()
	final static public String MSG_EXIT = "3";

	// Server an Client(individuelle Nachrichten)
	// #SERVER_MAX_PLAYER
	final static public String MSG_SERVER_MAX_PLAYER = "4";
	// #SERVER_COUNT_PLAYER
	final static public String MSG_SERVER_COUNT_PLAYER = "5";
	// #SERVERFULL
	final static public String MSG_SERVER_FULL = "6";
	// #LEVEL:x:y:z:[cubenumber]
	final static public String MSG_LEVEL = "7";
	// #POSITION:number:x:y:z:angleX:angleY:health
	// final static public int MSG_POSITION = 8;
	// #PLAYERLIST:count:[number:x:y:z:angleX:angleY]
	final static public String MSG_PLAYERLIST = "9";

	// ThreadClient
	// Server an alle Clienten(globale Spielnachrichten)
	// #CUBE:x:y:z:cubenumber
	final static public String MSG_CUBE = "10";
	// setCube()
	// #POSITION:number:x:y:z:angleX:angleY:health
	// final static public int MSG_POSITION = 11;
	// wenn Spielerposition empfangen wird
	// #EXIT:number
	// final static public int MSG_EXIT = 11;
	// wenn Spielerexit empfangen wird
	// #SERVERDOWN
	final static public String MSG_SERVERDOWN = "12";
	// Wenn Server heruntergefahren wird

	BufferedReader in = null;
	PrintWriter out = null;
	Socket client = null;
	NetLevel netLevel;
	List<NetPlayer> listNetPlayer;

	/**
	 * Dieser NetPlayer wird vom Server fuer jeden Clienten erzeugt, Aenderungen
	 * im NetLevel werden direkt an alle Spieler uebertragen
	 * 
	 * @param netLevel
	 * @param listPlayer
	 * @param number
	 * @param client
	 */
	public NetPlayer(NetLevel netLevel, List listPlayer, int number, Socket client, List listNetPlayer) {
		super(netLevel, 0, 0, 0, listPlayer, number);
		setBombs(1);
		this.netLevel = netLevel;
		this.listNetPlayer = listNetPlayer;
		if (client != null) {
			this.client = client;
			try {
				out = new PrintWriter(client.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		spawn();
	}

	/**
	 * Dieser NetPlayer wird vom Client erzeugt, das Level wird bei Aenderungen
	 * nicht versendet
	 * 
	 * @param level
	 * @param x
	 * @param y
	 * @param z
	 * @param listPlayer
	 * @param number
	 * @param client
	 */
	public NetPlayer(Level level, float x, float y, float z, List listPlayer, int number, Socket client) {
		super(level, x, y, z, listPlayer, number);
		this.client = client;
		try {
			out = new PrintWriter(client.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.netLevel = null;
	}

	public void write(String msg) {
		out.print(msg + "\n");
		out.flush();
	}

	public String read() {
		String strIn = null;
		try {
			if (in.ready()) {
				strIn = in.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return strIn;
	}

	public void msgReceivePosition(String[] splitMsg) {
		setPosition(Float.valueOf(splitMsg[2]), Float.valueOf(splitMsg[3]), Float.valueOf(splitMsg[4]));
		setAngleX(Float.valueOf(splitMsg[5]));
		setAngleY(Float.valueOf(splitMsg[6]));
	}

	public void msgReceiveBomb(String[] splitMsg) {
		super.setBomb(Integer.valueOf(splitMsg[1]), Integer.valueOf(splitMsg[2]), Integer.valueOf(splitMsg[3]));
	}

	public void msgReceiveExit() {
		Game.disconnect();
	}

	public Level msgReceiveLevel(String[] splitMsg) {
		Level level = new Level(Integer.valueOf(splitMsg[1]), Integer.valueOf(splitMsg[2]), Integer.valueOf(splitMsg[3]));
		level.setLevel(splitMsg);
		return level;
	}

	public void msgReceivePlayerList(String[] splitMsg, List<Player> listPlayer) {
		Player tmpPlayer;
		listPlayer.clear();
		// TODO tmp vor die Var schreiben
		float x, y, z, angleX, angleY;
		int number, healthPoints, hits, deaths;
		// ###############

		String stats = "Spieler\tTreffer\tTode\n";

		for (int i = 0; i < Integer.valueOf(splitMsg[1]); i++) {
			x = Float.valueOf(splitMsg[3 + (i * 9)]);
			y = Float.valueOf(splitMsg[4 + (i * 9)]);
			z = Float.valueOf(splitMsg[5 + (i * 9)]);
			number = Integer.valueOf(splitMsg[2 + (i * 9)]);
			angleX = Float.valueOf(splitMsg[6 + (i * 9)]);
			angleY = Float.valueOf(splitMsg[7 + (i * 9)]);
			healthPoints = Integer.valueOf(splitMsg[8 + (i * 9)]);
			hits = Integer.valueOf(splitMsg[9 + (i * 9)]);
			deaths = Integer.valueOf(splitMsg[10 + (i * 9)]);
			tmpPlayer = new Player(netLevel, x, y, z, listPlayer, number);
			tmpPlayer.setAngleX(angleX);
			tmpPlayer.setAngleY(angleY);
			tmpPlayer.setHealthPoints(healthPoints);
			tmpPlayer.setHits(hits);
			tmpPlayer.setDeaths(deaths);
			listPlayer.add(tmpPlayer);
			if (number == getNumber()) {
				setHits(hits);
				setDeaths(deaths);
				stats += "DU\t" + hits + "\t" + deaths + "\n";
			} else {
				stats += number + "\t" + hits + "\t" + deaths + "\n";
			}
		}
		Game.getHUD().setStats(stats);
	}

	public void msgSendPosition() {
		write(MSG_POSITION + ":" + getNumber() + ":" + getX() + ":" + getY() + ":" + getZ() + ":" + getAngleX() + ":"
				+ getAngleY());
	}

	public void msgSendBomb(int x, int y, int z) {
		write(MSG_BOMB + ":" + x + ":" + y + ":" + z);
	}

	public void setBomb(int x, int y, int z) {
		msgSendBomb(x, y, z);
	}

	public void msgSendExit() {
		write(MSG_EXIT + ":" + getNumber());
	}

	public void msgSendLevel() {
		if (netLevel != null) {
			write(MSG_LEVEL + ":" + netLevel.getSizeX() + ":" + netLevel.getSizeY() + ":" + netLevel.getSizeZ()
					+ netLevel.getLevel());
		}
	}

	public void msgSendPlayerList() {
		write(MSG_PLAYERLIST + ":" + netLevel.getPlayerList());
	}

	public void msgSendCube(int posX, int posY, int posZ) {
		write(MSG_CUBE + ":" + Cube.getNumberByCube(netLevel.getCube(posX, posY, posZ)) + ":" + posX + ":" + posY + ":" + posZ);
	}

	public void msgSendServerDown() {
		write(MSG_SERVERDOWN + ":");
	}

	protected void move(float x, float y, float z) {
		super.move(x, y, z);
		msgSendPosition();
	}

	public void printScore() {
		// for (int i = 0; i < listPlayer.size(); i++) {
		// System.out.println("Nr " + listPlayer.get(i).getNumber() +
		// " Trefferpunkte: " + listPlayer.get(i).getHits()
		// + ", Todesanzahl: " + listPlayer.get(i).getDeaths());
		// }
		System.out.println("Trefferpunkte: " + getHits() + ", Todesanzahl: " + getDeaths());
	}

	public void dies() {
		super.increaseDeaths();
		spawn();
	}

	/**
	 * Diese Methode laessten den Spieler am Spawnpoint neu ins Spiel eintreten
	 */
	public void spawn() {
		float[] spawnPoint = netLevel.getSpawnPoint();
		setPosition(spawnPoint[0], spawnPoint[1], spawnPoint[2]);
		setAngleY(spawnPoint[3]);
		setHealthPoints(MAX_HEALTH_POINTS);
		// Spieler verliert seine Extrabomben
		setBombs(1);
		// TODO Was muss noch alles zurueckgesetzt werden?
		for (int i = 0; i < listNetPlayer.size(); i++) {
			listNetPlayer.get(i).msgSendPlayerList();
		}
	}

	public void close() {
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
