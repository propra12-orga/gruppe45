package control;

import game.Level;
import game.Player;
import game.cube.Cube;
import render.Menu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Scanner;

import javax.swing.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

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

	public Control_Keyboard(Player player, Level level) {
		super(player);
		this.level = level;
		timer = new Timer(MILLISECS_PER_STEP, new TimerKeyboard());
		timer.start();
	}
	public void keyboardDestroy(){
		Keyboard.destroy();
	}
	public void timeout(boolean a){
		if (a){
		try {
			timer.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}else{
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

				// links:
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
					level.showMenu();
					// FIXME Netzwerkfähig machen
					player.reinit((level.getSizeX() / 2) * 10 + 5,
							(level.getSizeY() / 2) * 10 + 5, 15, 0, 0, 100, 0,
							1, 1, false);
				}
				// Menuetest:
				if (Keyboard.isKeyDown(Keyboard.KEY_M)) {
					Menu.menuOffen = true;
			    	System.out.println("Menü geöffnet durch Taste m");
			    	try {
						Robot rob = new Robot();
						rob.keyRelease(KeyEvent.VK_M);
			    	} catch (AWTException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    	
			        /*
			         * Set the Nimbus look and feel
			         */
			        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
			        /*
			         * If Nimbus (introduced in Java SE 6) is not available, stay with the
			         * default look and feel. For details see
			         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
			         */
			        try {
			            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
			                if ("Nimbus".equals(info.getName())) {
			                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
			                    break;
			                }
			            }
			        } catch (ClassNotFoundException ex) {
			            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
			        } catch (InstantiationException ex) {
			            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
			        } catch (IllegalAccessException ex) {
			            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
			        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
			            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
			        }
			        //</editor-fold>

			        /*
			         * Create and display the form
			         */
			        java.awt.EventQueue.invokeLater(new Runnable() {

			            public void run() {
			                new Menu().setVisible(true);
			            }
			        });

				}
					

				// Programm beenden
				if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
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
				// Durchlauf neustarten:
				/*
				 * TODO: Funktionsfähig machen nach dem Vorbild des Menüs: if
				 * (Keyboard.isKeyDown(Keyboard.KEY_N)) { Display.destroy(); }
				 */
				// if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
				// float y = player.getY();
				// while(y < y + 4.0f){
				// player.moveUp();
				// if (level.getCubeName((int)player.getX(), (int)y+ 4,
				// (int)player.getZ()) ){
				//
				//
				// }
				// }
				// }
				if (Keyboard.isKeyDown(Keyboard.KEY_O)) {
					level.save();
				}
				if (Keyboard.isKeyDown(Keyboard.KEY_H)) {
					Level level = new Level(10,10,10);
				}
				
				if (Keyboard.isKeyDown(Keyboard.KEY_L)) {
					level.load();
				}
				// TODO Testtasten, um verschiedene Level zu testen
				// -> entfernen!
				if (Keyboard.isKeyDown(Keyboard.KEY_1)) {
					level.buildDefaultLevel();
					// Ermittle Startposition
					int player1_start_x = 0;
					if (level.getSizeX() % 2 == 0) { // Größe in X gerade
						player1_start_x = level.getSizeX() * 10 - 15;
					} else { // Größe in X ungerade
						if (level.getSizeY() % 2 == 0) { // Größe in Y gerade
							player1_start_x = level.getSizeX() * 10 - 15;
						} else { // Größe in Y ungerade
							player1_start_x = level.getSizeX() * 10 - 25;
						}
					}
					player.reinit(player1_start_x, level.getSizeY() * 10 - 15,
							15, 0, 0, 100, 1, 1, 1, false);
				}
				if (Keyboard.isKeyDown(Keyboard.KEY_2)) {
					level.buildGravityLevel();

					// TODO An skalierbares Level anpassen
					player.reinit(level.getSizeX() * 10 - 15, 15, 15, 0, 0,
							100, 1, 1, 1, true);
					// FIXME Nur testing - Startplatzfreiräumen
					level.setCube(Cube.getCubeByName(Cube.CUBE_EMPTY),
							(level.getSizeX() - 2), 1, 1);
					level.setCube(Cube.getCubeByName(Cube.CUBE_EMPTY),
							(level.getSizeX() - 2), 1, 2);
					level.setCube(Cube.getCubeByName(Cube.CUBE_EMPTY),
							(level.getSizeX() - 2), 1, 3);
				}
			}
		}
	}
}
