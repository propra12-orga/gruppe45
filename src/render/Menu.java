package render;

// kommentarrr
import game.Level;
import game.Player;
import game.Game;

import java.awt.*;
import java.awt.event.*;

import javax.sound.sampled.Control;
import javax.swing.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import control.Control_Keyboard;

public class Menu implements ActionListener {

	
	public JFrame frame = new JFrame("Optionen");
	public static boolean menuOffen = false;
	public static Dimension dim = (new Dimension(100,60));

	Level level;
	Player player;

	// Buttons:
	private JButton bMaxBombs = new JButton("Maximale Bombenzahl");
	private JButton bHMenu = new JButton("Zurück zum Menue");
	private JButton bLevel = new JButton("Levelgröße festlegen");
	private JButton d = new JButton("Text d");
	private JButton e = new JButton("Text e");
	private JButton f = new JButton("Text f");
	private JButton g = new JButton("Text g");
	private JButton h = new JButton("Text h");
	private JButton i = new JButton("Text i");
	private JButton j = new JButton("Text j");
	private JButton bEnde = new JButton("Schliessen");
	// public JSlider sMaxBombs = new JSlider(JSlider.HORIZONTAL,
	// 1,10,player.getMaxSimultanBombs()); //Para x,y,z , y = Max
	public JSlider sMaxBombs = new JSlider(JSlider.HORIZONTAL, 1, 10,
			player.MAX_SIMULTAN_BOMBS); // Para x,y,z , y = Max
	public JSlider sLevelX = new JSlider(JSlider.HORIZONTAL, 1, 30,
			10); // Para x,y,z , y = Max
	public JSlider sLevelY = new JSlider(JSlider.HORIZONTAL, 1, 30,
			10); // Para x,y,z , y = Max
	public JSlider sLevelZ = new JSlider(JSlider.HORIZONTAL, 1, 30,
			10); // Para x,y,z , y = Max
	

	// Panel:
	/*
	 * private JPanel panelNorth; private JPanel panelEast; private JPanel
	 * panelSouth; private JPanel panelWest; private JPanel panelCenter;
	 */

	private JLabel lBombs;
	private JLabel lOptions;
	private JLabel lEmpty;

	public Menu(Level level, Player player) {
		frame.toFront();
		this.level = level;
		this.player = player;
		frame.setTitle("Optionen");
		frame.setLayout(new GridLayout(3, 1, 10, 10));
		//NULL
//		frame.setLayout(new BorderLayout());
//		frame.setLayout(new BoxLayout(dim));
		frame.setLocation(400, 100);
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// Listener für Buttons
		bEnde.addActionListener(this);
		bEnde.setActionCommand("CLOSE");
		bMaxBombs.addActionListener(this);
		bMaxBombs.setActionCommand("BOMBS");
		bHMenu.addActionListener(this);
		bHMenu.setActionCommand("BACK");
		bLevel.addActionListener(this);
		bLevel.setActionCommand("LEVEL");
		d.addActionListener(this);
		d.setActionCommand("D");
		e.addActionListener(this);
		e.setActionCommand("E");

		// Label erzeugen
		 lBombs = new JLabel("Maximale Anzahl der Bomben wählen");
		 lOptions = new JLabel("Optionen");
		 lEmpty = new JLabel("");

		// Zentrieren
		// titel.setHorizontalAlignment(JLabel.CENTER);

		// Labels auf Frame packen (direkt auf das BorderLayout)
		// getContentPane().add(BorderLayout.NORTH, titel);
		// getContentPane().add(sonstwas);

		// Panels auf Frame packen (das panelButton hat ein GridLayout, dass
		// jetzt in den WestBereich des BorderLayouts kommt)
		/*
		 * getContentPane().add(BorderLayout.NORTH, panelNorth);
		 * getContentPane().add(BorderLayout.EAST, panelEast);
		 * getContentPane().add(BorderLayout.SOUTH, panelSouth);
		 * getContentPane().add(BorderLayout.WEST, panelWest);
		 * getContentPane().add(BorderLayout.CENTER, panelCenter);
		 */

		frame.setVisible(true);
		showOptions();
//		frame.pack();
	}

	public void showOptions() {
		removeEverything();
		frame.setTitle("Optionen");
		lOptions.setVerticalTextPosition(frame.WIDTH/2);		
		lOptions.setVerticalAlignment(frame.WIDTH/2);
		frame.add(lOptions);
		frame.add(lEmpty);
		frame.add(bMaxBombs);
		frame.add(bLevel);
		frame.add(bEnde);
		frame.repaint();
		frame.validate();
//		frame.pack();
	}
	public void removeEverything(){
		frame.remove(bHMenu);
		frame.remove(bEnde);
		frame.remove(bMaxBombs);
		frame.remove(bLevel);
		frame.remove(lOptions);
		frame.remove(lBombs);
		frame.remove(sMaxBombs);
		frame.remove(sLevelX);
		frame.remove(sLevelY);
		frame.remove(sLevelZ);
		frame.remove(lEmpty);
		
	}
	
	public void showLevel(){
		removeEverything();
		frame.setTitle("Levelgröße waehlen:");
		frame.add(sLevelX);
		frame.add(sLevelY);
		frame.add(sLevelZ);
		sLevelX.setMajorTickSpacing(5);
		sLevelX.setPaintTicks(true);
		sLevelX.setPaintLabels(true);
		sLevelX.setLabelTable(sLevelX.createStandardLabels(5));
		sLevelY.setMajorTickSpacing(5);
		sLevelY.setPaintTicks(true);
		sLevelY.setPaintLabels(true);
		sLevelY.setLabelTable(sLevelY.createStandardLabels(5));
		sLevelZ.setMajorTickSpacing(5);
		sLevelZ.setPaintTicks(true);
		sLevelZ.setPaintLabels(true);
		sLevelZ.setLabelTable(sLevelZ.createStandardLabels(5));
		frame.add(lEmpty);
		frame.add(bHMenu);
		frame.add(bEnde);		
	}

	public void showMaxBombs() {
		frame.setTitle("Maximale Anzahl der Bomben waehlen:");
		removeEverything();
		frame.add(lBombs);
		frame.add(lEmpty);
		frame.add(sMaxBombs);
		frame.add(bHMenu);
		frame.add(bEnde);
		sMaxBombs.setMajorTickSpacing(1);
		// sMaxBombs.setMinorTickSpacing(1);
		sMaxBombs.setPaintTicks(true);
		sMaxBombs.setPaintLabels(true);
		sMaxBombs.setLabelTable(sMaxBombs.createStandardLabels(1));
//		frame.pack();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("CLOSE".equals(e.getActionCommand())) {
			frame.dispose();
			this.menuOffen = false;
			System.out.println("Menü geschlossen");

		} else if ("BOMBS".equals(e.getActionCommand())) {
			showMaxBombs();
		} else if ("BACK".equals(e.getActionCommand())) {
			showOptions();
		}else if ("LEVEL".equals(e.getActionCommand())) {
			showLevel();
		}

	}

}
