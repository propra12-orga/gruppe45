import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	static class HandleThread implements Runnable {
		Socket client;
		DataOutputStream out;
		Scanner in;
		String strIn;
		String strOut;

		public HandleThread(Socket client) {
			this.client = client;
			try {
				this.out = new DataOutputStream(this.client.getOutputStream());
				this.in = new Scanner(this.client.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				strOut = "+OK\n";
				out.writeChars(strOut);
				System.out.println("Server:" + strOut);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void run() {
			boolean exit = false;

			try {
				while (true) {
					// Wenn der Klient die Verbindung beendet
					if (client.isClosed()) {
						exit = true;
					}
					// Wenn der Klient eine Nachricht gesendet hat
					if (in.hasNext()) {
						strOut = "";
						strIn = in.nextLine();
						System.out.println("Klient:" + strIn);

						// Nachrichten auswerten
						if (strIn.equals("QUIT")) {
							strOut = "+OK\n";
							exit = true;
						} else if (strIn.startsWith("USER ")) {
							strOut = "+OK\n";
						} else if (strIn.startsWith("PASS ")) {
							strOut = "+OK\n";
						} else if (strIn.equals("STAT")) {
							strOut = "+OK 1 236\n";
						} else if (strIn.equals("LIST")) {
							strOut = "+OK\n";
							strOut += "1 236\n";
							strOut += ".\n";
						} else if (strIn.startsWith("RETR ")) {
							strOut = "+OK\n";
							strOut += "Date: Mon, 18 Oct 2004 04:11:45 +0200\n";
							strOut += "From: Someone <someone@example.com>\n";
							strOut += "To: wiki@example.com\n";
							strOut += "Subject: Test-E-Mail\n";
							strOut += "Content-Type: text/plain; charset=us-ascii; format=flowed\n";
							strOut += "Content-Transfer-Encoding: 7bit\n";
							strOut += "\n";
							strOut += "Dies ist eine Test-E-Mail\n";
							strOut += ".\n";
						} else if (strIn.startsWith("DELE ")) {
							strOut = "+OK\n";
						} else if (strIn.equals("NOOP")) {
							strOut = "+OK\n";
						} else {
							strOut = "-ERR\n";
						}
						if (strOut != "") {
							out.writeChars(strOut);
						}
						// Antwort des Servers ausgeben
						System.out.println("Server:" + strOut);
						// Programm beenden wenn exit=true ist
						if (exit) {
							client.close();
							break;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} catch (Throwable e) {
				e.printStackTrace();
			}

		}

	}

	public static void main(String[] args) {
		ServerSocket server;
		try {
			server = new ServerSocket(12345);
			while (true) {
				Socket client = server.accept();
				System.out.println("Neuer Klient");
				new Thread(new HandleThread(client)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
