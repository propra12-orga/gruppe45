package control;

import game.Game;
import game.Level;
import game.Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import org.lwjgl.input.Keyboard;

import render.Menu;
import DetectedServer.NetPlayer;

/**
 * Enthaelt Steuerungsbefehle fuer den Spieler und veraendert dessen Position
 * und Blickwinkel. Ausserdem enthalten sind Befehle fuer das Legen von Bomben
 * und kurze Tastenbefehle, mit denen der Programmablauf gesteuert werden kann
 * (Beenden, Neustart...)
 */

public class Control_Keyboard extends Control {

	final int MILLISECS_PER_STEP = 10;
	public Timer timer;
	Level level;
	int chatMode = 0;
	char letter;

	public Control_Keyboard(Player player, Level level) {
		super(player);
		this.level = level;
		timer = new Timer(MILLISECS_PER_STEP, new TimerKeyboard());
		timer.start();
	}

	public void keyboardDestroy() {
		Keyboard.destroy();
	}

	public void timeout(boolean a) {
		if (a) {
			try {
				timer.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			timer.notify();
		}
	}

	class TimerKeyboard implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (player.getGravity()) {
				player.sinkDown();
			}
			move_Control1();
		}

		public void move_Control1() {
			if (!Menu.menuOffen) {
				if (chatMode == 0) {
					// TAB
					if (Keyboard.isKeyDown(Keyboard.KEY_TAB)) {
						Game.getHUD().setShowStats(true);
					} else {
						// TODO Sollte nur bei Keyrelease aufgerufen werden
						Game.getHUD().setShowStats(false);
					}
					// RETURN
					if (Keyboard.isKeyDown(Keyboard.KEY_RETURN) && !Level.inMenu) {
						chatMode = 1;
					}
					// links:
					if (Keyboard.isKeyDown(Keyboard.KEY_O)) {
						Game.getThreadBomb().prologTime = 15000;
					}
					if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
						player.moveLeft();
					}
					// rueckwaerts:
					if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
						player.moveBackward();
					}
					// rechts:
					if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
						player.moveRight();
					}
					// vorwaerts:
					if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
						player.moveForward();
					}
					// hoch:
					if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
						if (!(player.getGravity())) {
							player.moveUp();
						}
					}
					// runter:
					if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
						if (!(player.getGravity())) {
							player.moveDown();
						}
					}
					// Zurück zum Menü:
					if (Keyboard.isKeyDown(Keyboard.KEY_T)) {
						Game.disconnect();
					}
					// Programm beenden
					if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
						Game.disconnect();
						System.exit(0);
					}
					// Bombe legen:
					if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
						player.setBomb();
					}
					// nach rechts drehen:
					if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
						player.turnRight(0.012f);
					}
					// nach links drehen:
					if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
						player.turnLeft(0.012f);
					}
					// nach unten neigen:
					if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
						player.turnDown(0.009f);
					}
					// nach oben neigen:
					if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
						player.turnUp(0.009f);
					}
					// Quicksave
					if (Keyboard.isKeyDown(Keyboard.KEY_O)) {
						level.save();
					}
					// Quickload
					if (Keyboard.isKeyDown(Keyboard.KEY_L)) {
						level.load();
					}
				} else if (chatMode == 1) {// Enter muss losgelassen werden
					Game.getHUD().setShowChatInput(true);
					if (!Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
						chatMode = 2;
					}
				} else if (chatMode == 2) {// Jetzt kann geschrieben werden
					if (Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
						chatMode = 5;
					} else if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
						letter = 'a';
						chatMode = 3;
					} else if (Keyboard.isKeyDown(Keyboard.KEY_B)) {
						letter = 'b';
						chatMode = 3;
					} else if (Keyboard.isKeyDown(Keyboard.KEY_C)) {
						letter = 'c';
						chatMode = 3;
					} else if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
						letter = 'd';
						chatMode = 3;
					} else if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
						letter = 'e';
						chatMode = 3;
					} else if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
						letter = 'f';
						chatMode = 3;
					} else if (Keyboard.isKeyDown(Keyboard.KEY_G)) {
						letter = 'g';
						chatMode = 3;
					} else if (Keyboard.isKeyDown(Keyboard.KEY_H)) {
						letter = 'h';
						chatMode = 3;
					} else if (Keyboard.isKeyDown(Keyboard.KEY_I)) {
						letter = 'i';
						chatMode = 3;
					} else if (Keyboard.isKeyDown(Keyboard.KEY_J)) {
						letter = 'j';
						chatMode = 3;
					} else if (Keyboard.isKeyDown(Keyboard.KEY_K)) {
						letter = 'k';
						chatMode = 3;
					} else if (Keyboard.isKeyDown(Keyboard.KEY_L)) {
						letter = 'l';
						chatMode = 3;
					} else if (Keyboard.isKeyDown(Keyboard.KEY_M)) {
						letter = 'm';
						chatMode = 3;
					} else if (Keyboard.isKeyDown(Keyboard.KEY_N)) {
						letter = 'n';
						chatMode = 3;
					} else if (Keyboard.isKeyDown(Keyboard.KEY_O)) {
						letter = 'o';
						chatMode = 3;
					} else if (Keyboard.isKeyDown(Keyboard.KEY_P)) {
						letter = 'p';
						chatMode = 3;
					} else if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
						letter = 'q';
						chatMode = 3;
					} else if (Keyboard.isKeyDown(Keyboard.KEY_R)) {
						letter = 'r';
						chatMode = 3;
					} else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
						letter = 's';
						chatMode = 3;
					} else if (Keyboard.isKeyDown(Keyboard.KEY_T)) {
						letter = 't';
						chatMode = 3;
					} else if (Keyboard.isKeyDown(Keyboard.KEY_U)) {
						letter = 'u';
						chatMode = 3;
					} else if (Keyboard.isKeyDown(Keyboard.KEY_V)) {
						letter = 'v';
						chatMode = 3;
					} else if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
						letter = 'w';
						chatMode = 3;
					} else if (Keyboard.isKeyDown(Keyboard.KEY_X)) {
						letter = 'x';
						chatMode = 3;
					} else if (Keyboard.isKeyDown(Keyboard.KEY_Y)) {
						letter = 'y';
						chatMode = 3;
					} else if (Keyboard.isKeyDown(Keyboard.KEY_Z)) {
						letter = 'z';
						chatMode = 3;
					} else if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
						letter = ' ';
						chatMode = 3;
					} else if (Keyboard.isKeyDown(Keyboard.KEY_BACK)) {
						Game.getHUD().clearChatInput();
					}
				} else if (chatMode == 3) {
					Game.getHUD().addToChatInput(letter);
					chatMode = 4;
				} else if (chatMode == 4) {
					if (letter == 'a' && !Keyboard.isKeyDown(Keyboard.KEY_A)) {
						chatMode = 2;
					} else if (letter == 'b' && !Keyboard.isKeyDown(Keyboard.KEY_B)) {
						chatMode = 2;
					} else if (letter == 'c' && !Keyboard.isKeyDown(Keyboard.KEY_C)) {
						chatMode = 2;
					} else if (letter == 'd' && !Keyboard.isKeyDown(Keyboard.KEY_D)) {
						chatMode = 2;
					} else if (letter == 'e' && !Keyboard.isKeyDown(Keyboard.KEY_E)) {
						chatMode = 2;
					} else if (letter == 'f' && !Keyboard.isKeyDown(Keyboard.KEY_F)) {
						chatMode = 2;
					} else if (letter == 'g' && !Keyboard.isKeyDown(Keyboard.KEY_G)) {
						chatMode = 2;
					} else if (letter == 'h' && !Keyboard.isKeyDown(Keyboard.KEY_H)) {
						chatMode = 2;
					} else if (letter == 'i' && !Keyboard.isKeyDown(Keyboard.KEY_I)) {
						chatMode = 2;
					} else if (letter == 'j' && !Keyboard.isKeyDown(Keyboard.KEY_J)) {
						chatMode = 2;
					} else if (letter == 'k' && !Keyboard.isKeyDown(Keyboard.KEY_K)) {
						chatMode = 2;
					} else if (letter == 'l' && !Keyboard.isKeyDown(Keyboard.KEY_L)) {
						chatMode = 2;
					} else if (letter == 'm' && !Keyboard.isKeyDown(Keyboard.KEY_M)) {
						chatMode = 2;
					} else if (letter == 'n' && !Keyboard.isKeyDown(Keyboard.KEY_N)) {
						chatMode = 2;
					} else if (letter == 'o' && !Keyboard.isKeyDown(Keyboard.KEY_O)) {
						chatMode = 2;
					} else if (letter == 'p' && !Keyboard.isKeyDown(Keyboard.KEY_P)) {
						chatMode = 2;
					} else if (letter == 'q' && !Keyboard.isKeyDown(Keyboard.KEY_Q)) {
						chatMode = 2;
					} else if (letter == 'r' && !Keyboard.isKeyDown(Keyboard.KEY_R)) {
						chatMode = 2;
					} else if (letter == 's' && !Keyboard.isKeyDown(Keyboard.KEY_S)) {
						chatMode = 2;
					} else if (letter == 't' && !Keyboard.isKeyDown(Keyboard.KEY_T)) {
						chatMode = 2;
					} else if (letter == 'u' && !Keyboard.isKeyDown(Keyboard.KEY_U)) {
						chatMode = 2;
					} else if (letter == 'v' && !Keyboard.isKeyDown(Keyboard.KEY_V)) {
						chatMode = 2;
					} else if (letter == 'w' && !Keyboard.isKeyDown(Keyboard.KEY_W)) {
						chatMode = 2;
					} else if (letter == 'x' && !Keyboard.isKeyDown(Keyboard.KEY_X)) {
						chatMode = 2;
					} else if (letter == 'y' && !Keyboard.isKeyDown(Keyboard.KEY_Y)) {
						chatMode = 2;
					} else if (letter == 'z' && !Keyboard.isKeyDown(Keyboard.KEY_Z)) {
						chatMode = 2;
					} else if (letter == ' ' && !Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
						chatMode = 2;
					}
				} else if (chatMode == 5) {// Enter wurde gedrueckt und muss
											// noch losgelassen werden
					if (!Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
						chatMode = 0;
						if (player.getType().equals("NetPlayer")) {
							NetPlayer netPlayer = (NetPlayer) player;
							netPlayer.msgSendChat(Game.getHUD().getChatInput());
						} else {
							Game.getHUD().addToChatLog();
						}
						Game.getHUD().setShowChatInput(false);
					}
				}
			}
		}
	}
}
