package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import render.OpenGL;
import DetectedServer.NetLevel;
import DetectedServer.NetPlayer;
import DetectedServer.ThreadWindowNetwork;
import control.Control_Keyboard;
import control.Control_Mouse;

//TODO Bei Fehler nicht EXIT sondern zurueck ins Menu

public class GameMulti {

	// final static private String SERVER_IP = "192.168.2.100";
	static private String SERVER_IP = "localhost";
	final static private int SERVER_PORT = 12345;

	private Socket server;
	private Level level;
	private NetPlayer myPlayer;
	private List<Player> listPlayer;
	private Thread thread;

	public GameMulti(String ip) {
		SERVER_IP = ip;
	}

	public void connect(OpenGL openGl, Control_Keyboard controlKeyboard, Control_Mouse controlMouse) {
		int number;
		float posX = 0, posY = 0, posZ = 0;
		listPlayer = new ArrayList<Player>();

		PrintWriter out;
		BufferedReader in;
		String strIn;
		String strSplit[];
		try {
			server = new Socket(SERVER_IP, SERVER_PORT);
			out = new PrintWriter(server.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(server.getInputStream()));
			System.out.println("Spieler ist verbunden");
			strIn = in.readLine();
			System.out.println(strIn);
			strSplit = strIn.split(":");

			// TODO durch spawn wird zuerst MSG_PLAYERLIST gesendet
			// ######################################################
			if (strSplit[0].equals(NetPlayer.MSG_POSITION) || strSplit[0].equals(NetPlayer.MSG_PLAYERLIST)) {
				strIn = in.readLine();
				System.out.println(strIn);
				strSplit = strIn.split(":");
			}
			if (strSplit[0].equals(NetPlayer.MSG_POSITION) || strSplit[0].equals(NetPlayer.MSG_PLAYERLIST)) {
				strIn = in.readLine();
				System.out.println(strIn);
				strSplit = strIn.split(":");
			}
			// ######################################################
			// Oben wird einfach nur die Nachricht verworfen bis MSG_LEVEL kommt

			if (strSplit[0].equals(NetPlayer.MSG_LEVEL)) {
				level = new NetLevel(Integer.valueOf(strSplit[1]), Integer.valueOf(strSplit[2]), Integer.valueOf(strSplit[3]),
						null);
				level.setLevel(strSplit);
				openGl.setLevel(level);
			} else {
				System.out.println("Sever hat nicht wie erwartet geantwortet, konnte Level nicht empfangen");
				Game.disconnect();
				return;
			}
			strIn = in.readLine();
			strSplit = strIn.split(":");
			if (strSplit[0].equals(NetPlayer.MSG_POSITION)) {
				number = Integer.valueOf(strSplit[1]);
				posX = Float.valueOf(strSplit[2]);
				posY = Float.valueOf(strSplit[3]);
				posZ = Float.valueOf(strSplit[4]);
				myPlayer = new NetPlayer(level, posX, posY, posZ, listPlayer, number, server);
				openGl.setPlayer(myPlayer);
			} else {
				System.out.println("Sever hat nicht wie erwartet geantwortet, konnte Spieler nicht empfangen");
				Game.disconnect();
				return;
			}
			strIn = in.readLine();
			strSplit = strIn.split(":");
			if (strSplit[0].equals(NetPlayer.MSG_PLAYERLIST)) {
				// anzahl der Spieler, [Spielernummer, X, Y, Z, angleX, angleY]
				myPlayer.msgReceivePlayerList(strSplit, listPlayer);
			} else {
				System.out.println("Sever hat nicht wie erwartet geantwortet, konnte Spielerliste nicht empfangen");
				Game.disconnect();
				return;
			}
			openGl.setPlayerList(listPlayer);

		} catch (UnknownHostException e1) {
			System.out.println("Fehler");
			Game.disconnect();
			return;
		} catch (IOException e1) {
			System.out.println("Fehler");
			Game.disconnect();
			return;
		}

		// TODO Keyboard muss noch Level uebergeben werden
		controlKeyboard.setPlayer(myPlayer);
		controlMouse.setPlayer(myPlayer);

		thread = new Thread(new ThreadWindowNetwork(level, myPlayer, listPlayer));
		thread.start();

		System.out.println("Netzwerkspiel erfolgreich gestartet");

	}

	protected void finalize() {
		myPlayer.msgSendExit();
		// thread.stop();
		thread = null;
	}

}
