package game.cube;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.lwjgl.input.Keyboard;

import render.Menu;
import game.Level;
import game.Player;
import control.Control_Keyboard;

public class MenuCubeOptions extends Cube{
	public MenuCubeOptions() {
		super(Cube.IS_WALKABLE, Cube.IS_COLLECTABLE, Cube.IS_NOT_DESTROYABLE);
	}

	@Override
	public void change(Player player, Level level) {
		if (!Menu.menuOffen) {
			Menu.menuOffen = true;
			try {
				Robot rob = new Robot();
				rob.delay(500);
				rob.keyRelease(KeyEvent.VK_W);
				rob.keyRelease(KeyEvent.VK_UP);
	    	} catch (AWTException e) {}
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

			player.setPosition((level.getSizeX() / 2) * 10 + 5,
					(level.getSizeY() / 2) * 10 + 5, 15);
				}

	}

}
