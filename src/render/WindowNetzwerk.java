package render;

import game.Level;
import game.Player;
import game.cube.Cube;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import DetectedServer.NetLevel;
import DetectedServer.NetPlayer;
import control.Control_Keyboard;
import control.Control_Mouse;

public class WindowNetzwerk {// implements Runnable {

	// Vorgehensweise:
	// 1.Mit Server verbinden
	// 2.Levelgroesse ermitteln & -level- erzeugen
	// 3.-level- einlesen
	// 4.Spielerposition ermitteln & -player- erzeugen
	// 5.Andere Spieler ermitteln und in die playerList

	final static private int width = 800, height = 600;

	final static private String SERVER_IP = "localhost";
	final static private int SERVER_PORT = 12345;

	Socket server;

	private void start() {
		int number;
		float posX = 0, posY = 0, posZ = 0;
		Level level = null;
		NetPlayer myPlayer = null;
		List<Player> listPlayer = new ArrayList<Player>();

		PrintWriter out;
		BufferedReader in = null;
		String strIn;
		String strSplit[];
		try {
			server = new Socket(SERVER_IP, SERVER_PORT);
			out = new PrintWriter(server.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(server.getInputStream()));
			System.out.println("Spieler ist verbunden");
			strIn = in.readLine();
			strSplit = strIn.split(":");
			if (strSplit[0].equals(NetPlayer.MSG_LEVEL)) {
				level = new NetLevel(Integer.valueOf(strSplit[1]), Integer.valueOf(strSplit[2]), Integer.valueOf(strSplit[3]),
						null);
				level.setLevel(strSplit);
			} else {
				System.out.println("Sever hat nicht wie erwartet geantwortet");
				System.exit(1);
			}
			strIn = in.readLine();
			strSplit = strIn.split(":");
			if (strSplit[0].equals(NetPlayer.MSG_POSITION)) {
				number = Integer.valueOf(strSplit[1]);
				posX = Float.valueOf(strSplit[2]);
				posY = Float.valueOf(strSplit[3]);
				posZ = Float.valueOf(strSplit[4]);
				myPlayer = new NetPlayer(level, posX, posY, posZ, listPlayer, number, server);
			} else {
				System.out.println("Sever hat nicht wie erwartet geantwortet");
				System.exit(1);
			}
			strIn = in.readLine();
			strSplit = strIn.split(":");
			if (strSplit[0].equals(NetPlayer.MSG_PLAYERLIST)) {
				// anzahl der Spieler, [Spielernummer, X, Y, Z, angleX, angleY]
				myPlayer.msgReceivePlayerList(strSplit, listPlayer);
			} else {
				System.out.println("Sever hat nicht wie erwartet geantwortet");
				System.exit(1);
			}

		} catch (UnknownHostException e1) {
			System.out.println("Fehler");
			e1.printStackTrace();
			System.exit(1);
		} catch (IOException e1) {
			System.out.println("Fehler");
			e1.printStackTrace();
			System.exit(1);
		}
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(1);
		}

		OpenGL openGl = new OpenGL(level, myPlayer, width, height, listPlayer);
		Control_Keyboard controlKeyboard = new Control_Keyboard(myPlayer);
		Control_Mouse controlMouse = new Control_Mouse(myPlayer);
		// TODO Netzwerk im Thread laufen lassen funzt nicht
		// ThreadWindowNetwork threadWindoeNetwork = new
		// ThreadWindowNetwork(listPlayer, server, myPlayer, level);

		while (!Display.isCloseRequested()) {
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
					level.setCubeSilent(Cube.getCubeByNumber(Integer.valueOf(strSplit[1])), Integer.valueOf(strSplit[2]),
							Integer.valueOf(strSplit[3]), Integer.valueOf(strSplit[4]));
				} else if (strSplit[0].equals(NetPlayer.MSG_PLAYERLIST)) {
					myPlayer.msgReceivePlayerList(strSplit, listPlayer);
				} else if (strSplit[0].equals(NetPlayer.MSG_EXIT)) {
					myPlayer.dies();
					System.exit(0);
				}
			}

			// ALLEN ANDERN DIE NEUE POS MITTEILEN
			openGl.display();
			Display.update();
			controlMouse.mouse_Move(myPlayer);
		}
		Display.destroy();
	}

	public static void main(String[] argv) {
		WindowNetzwerk windowNetzwerk = new WindowNetzwerk();
		windowNetzwerk.start();
	}
}