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
	// Der Server oder der Client bekommt eine neue Chatnachricht
	// #CHAT:number:
	final static public String MSG_CHAT = "12";

	private BufferedReader in = null;
	private PrintWriter out = null;
	private Socket client = null;
	private NetLevel netLevel;
	private List<NetPlayer> listNetPlayer;

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
		setBombs(Player.START_BOMBS);
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

	/**
	 * Gibt den Typ der Playerklasse aus
	 */
	public String getType() {
		return "NetPlayer";
	}

	/**
	 * schreibt dem Server/Clienten eine Nachricht
	 * 
	 * @param msg
	 */
	public void write(String msg) {
		out.print(msg + "\n");
		out.flush();
	}

	/**
	 * liesst die Nachricht des Server/Clienten
	 * 
	 * @return
	 */
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

	/**
	 * Empfaengt eine neue Position
	 * 
	 * @param splitMsg
	 */
	public void msgReceivePosition(String[] splitMsg) {
		setPosition(Float.valueOf(splitMsg[2]), Float.valueOf(splitMsg[3]), Float.valueOf(splitMsg[4]));
		setAngleX(Float.valueOf(splitMsg[5]));
		setAngleY(Float.valueOf(splitMsg[6]));
	}

	/**
	 * Der Client teilt dem Server mit das er eine Bombe legen will
	 * 
	 * @param splitMsg
	 */
	public void msgReceiveBomb(String[] splitMsg) {
		super.setBomb(Integer.valueOf(splitMsg[1]), Integer.valueOf(splitMsg[2]), Integer.valueOf(splitMsg[3]));
	}

	/**
	 * Der Client geht/wird rausgeschmissen weil der Server ausgeschaltet wurde
	 */
	public void msgReceiveExit() {
		Game.disconnect();
	}

	/**
	 * Der Client empfaengt das Level des Servers
	 * 
	 * @param splitMsg
	 * @return
	 */
	public Level msgReceiveLevel(String[] splitMsg) {
		Level level = new Level(Integer.valueOf(splitMsg[1]), Integer.valueOf(splitMsg[2]), Integer.valueOf(splitMsg[3]));
		level.setLevel(splitMsg);
		return level;
	}

	/**
	 * Der Client empfaengt die Spielerliste des Servers
	 * 
	 * @param splitMsg
	 * @param listPlayer
	 */
	public void msgReceivePlayerList(String[] splitMsg, List<Player> listPlayer) {
		Player tmpPlayer;
		listPlayer.clear();
		// TODO tmp vor die Var schreiben
		float x, y, z, angleX, angleY;
		int number, healthPoints, hits, deaths;
		String name;
		// ###############

		String stats = "Spieler\tTreffer\tTode\n";

		for (int i = 0; i < Integer.valueOf(splitMsg[1]); i++) {
			x = Float.valueOf(splitMsg[3 + (i * 10)]);
			y = Float.valueOf(splitMsg[4 + (i * 10)]);
			z = Float.valueOf(splitMsg[5 + (i * 10)]);
			number = Integer.valueOf(splitMsg[2 + (i * 10)]);
			angleX = Float.valueOf(splitMsg[6 + (i * 10)]);
			angleY = Float.valueOf(splitMsg[7 + (i * 10)]);
			healthPoints = Integer.valueOf(splitMsg[8 + (i * 10)]);
			hits = Integer.valueOf(splitMsg[9 + (i * 10)]);
			deaths = Integer.valueOf(splitMsg[10 + (i * 10)]);
			name = splitMsg[11 + (i * 10)];
			tmpPlayer = new Player(netLevel, x, y, z, listPlayer, number);
			tmpPlayer.setAngleX(angleX);
			tmpPlayer.setAngleY(angleY);
			tmpPlayer.setHealthPoints(healthPoints);
			tmpPlayer.setHits(hits);
			tmpPlayer.setDeaths(deaths);
			tmpPlayer.setName(name);
			listPlayer.add(tmpPlayer);
			if (number == getNumber()) {
				setHits(hits);
				setDeaths(deaths);
				stats += "ICH\t" + hits + "\t" + deaths + "\n";
			} else {
				stats += number + ":" + name + "\t" + hits + "\t" + deaths + "\n";
			}
		}
		Game.getHUD().setStats(stats);
	}

	/**
	 * Der Client sendet seine aktuelle Position/ bekommt die Position eines
	 * anderen Spielers
	 */
	public void msgSendPosition() {
		write(MSG_POSITION + ":" + getNumber() + ":" + getX() + ":" + getY() + ":" + getZ() + ":" + getAngleX() + ":"
				+ getAngleY());
	}

	/**
	 * Frage den Server eine Bombe legen zu dürfen
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public void msgSendBomb(int x, int y, int z) {
		write(MSG_BOMB + ":" + x + ":" + y + ":" + z);
	}

	/**
	 * Die abgewandelte setBomb setzt keine Bombe sondern teil es dem Server mit
	 */
	public void setBomb(int x, int y, int z) {
		msgSendBomb(x, y, z);
	}

	/**
	 * Der Client/Server beendet sein Spiel
	 */
	public void msgSendExit() {
		write(MSG_EXIT + ":" + getNumber());
	}

	/**
	 * Der Server sendet sein Level an den Clienten der Klasse
	 */
	public void msgSendLevel() {
		if (netLevel != null) {
			write(MSG_LEVEL + ":" + netLevel.getSizeX() + ":" + netLevel.getSizeY() + ":" + netLevel.getSizeZ()
					+ netLevel.getLevel());
		}
	}

	/**
	 * Der Server sendet die Spielerliste an den Clienten der Klasse
	 */
	public void msgSendPlayerList() {
		write(MSG_PLAYERLIST + ":" + netLevel.getPlayerList());
	}

	/**
	 * Der Server schickt dem Clienten der Klasse Cube XxYxZ im Level
	 * 
	 * @param posX
	 * @param posY
	 * @param posZ
	 */
	public void msgSendCube(int posX, int posY, int posZ) {
		write(MSG_CUBE + ":" + Cube.getNumberByCube(netLevel.getCube(posX, posY, posZ)) + ":" + posX + ":" + posY + ":" + posZ);
	}

	/**
	 * Sendet eine Chatnachricht an den Server
	 * 
	 * @param chat
	 */
	public void msgSendChat(String chat) {
		write(MSG_CHAT + ":" + getNumber() + ":" + chat);
	}

	/**
	 * Leitet eine Chatnachricht an alle weiter
	 * 
	 * @param chat
	 * @param number
	 */
	public void msgSendChat(String chat, int number) {
		write(MSG_CHAT + ":" + number + ":" + chat);
	}

	/**
	 * Abgewandelte move() sendet dem Server bei jedem Aufruf die neue Position
	 */
	protected void move(float x, float y, float z) {
		super.move(x, y, z);
		msgSendPosition();
	}

	/**
	 * Abgewandelte dies() zaehlt Tode mit und laesst den Spieler neu spawnen
	 */
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

	/**
	 * Schliesst die Verbindung
	 */
	public void close() {
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Macht die setScore von Player unschaedlich
	 */
	public void setScore(int score) {
	}

}
