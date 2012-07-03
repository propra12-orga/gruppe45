package DetectedServer;

import java.util.List;

/**
 * diese Klasse wird von dem Server fuer jeden Klienten erzeugt der sich
 * verbindet
 */
public class ThreadClient implements Runnable {

	NetPlayer netPlayer;
	NetLevel netLevel;
	List<NetPlayer> listNetPlayer;

	public ThreadClient(NetLevel netLevel, NetPlayer netPlayer, List<NetPlayer> listNetPlayer) {
		this.netPlayer = netPlayer;
		this.netLevel = netLevel;
		this.listNetPlayer = listNetPlayer;
		netPlayer.msgSendLevel();
		netPlayer.msgSendPosition();
		netPlayer.msgSendPlayerList();
	}

	public void run() {
		String strIn;
		String[] strSplit;
		while (true) {
			if (null != (strIn = netPlayer.read())) {
				strSplit = strIn.split(":");
				if (strSplit[0].equals(NetPlayer.MSG_POSITION)) {
					netPlayer.msgReceivePosition(strSplit);
					for (int i = 0; i < listNetPlayer.size(); i++) {
						listNetPlayer.get(i).write(strIn);
					}
				} else if (strSplit[0].equals(NetPlayer.MSG_BOMB)) {
					netPlayer.msgReceiveBomb(strSplit);
				} else if (strSplit[0].equals(NetPlayer.MSG_EXIT)) {
					if (Integer.valueOf(strSplit[1]) == netPlayer.getNumber()) {
						netPlayer.msgSendExit();
					}
					for (int i = 0; i < listNetPlayer.size(); i++) {
						if (listNetPlayer.get(i).getNumber() == Integer.valueOf(strSplit[1])) {
							listNetPlayer.remove(i);
							break;
						}
					}
					for (int i = 0; i < listNetPlayer.size(); i++) {
						listNetPlayer.get(i).msgSendPlayerList();
					}
					netPlayer.close();
				} else if (strSplit[0].equals(NetPlayer.MSG_SERVER_MAX_PLAYER)) {
					// TODO es wird nicht die richtige maximale Spielerzahl
					// zurueckgegeben
					netPlayer.write(NetPlayer.MSG_SERVER_MAX_PLAYER + ":0");
				} else if (strSplit[0].equals(NetPlayer.MSG_SERVER_COUNT_PLAYER)) {
					netPlayer.write(NetPlayer.MSG_SERVER_COUNT_PLAYER + ":" + listNetPlayer.size());
				}
			}
		}
	}

}
