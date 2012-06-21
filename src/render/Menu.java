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


public class Menu extends JFrame implements ActionListener{
	
	public static boolean menuOffen = false;

	Level level;
	Player player;
	


	//Buttons:
	private JButton bMaxBombs= new JButton("Text a");
	private JButton b= new JButton("Text b");
	private JButton c= new JButton("Text c");
	private JButton d= new JButton("Text d");
	private JButton e= new JButton("Text e");
	private JButton f= new JButton("Text f");
	private JButton g= new JButton("Text g");
	private JButton h= new JButton("Text h");
	private JButton i= new JButton("Text i");
	private JButton j= new JButton("Text j");
	private JButton bEnde= new JButton("Schliessen");
//	public JSlider sMaxBombs = new JSlider(JSlider.HORIZONTAL, 1,10,player.getMaxSimultanBombs()); //Para x,y,z ,   y = Max
	public JSlider sMaxBombs = new JSlider(JSlider.HORIZONTAL, 1,player.MAX_SIMULTAN_BOMBS,1); //Para x,y,z ,   y = Max
	
	//NULLPOINTER WARUM AUCH IMMER??
	
	
	//Panel:
/*	private JPanel panelNorth;
	private JPanel panelEast;
	private JPanel panelSouth;
	private JPanel panelWest;
	private JPanel panelCenter;
	*/
	
	
	private JLabel titel;
	private JLabel sonstwas;

	
	
	public Menu(Level level, Player player){
		this.toFront();
		this.level = level;
		this.player = player;
		this.setTitle("Optionen");
		this.setLayout(new GridLayout(3,3,10,10));
		setLocation(400,100);
		setSize(600,600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// Das BorderLayout ist mal das erste - später fügen wir noch ein
		// GridLayout im Westen hinzu
//		getContentPane().setLayout(new BorderLayout(0xcafe, 5));
		this.add(sMaxBombs);
		sMaxBombs.setMajorTickSpacing(2);
		sMaxBombs.setMinorTickSpacing(1);
		sMaxBombs.setPaintTicks(true);
		sMaxBombs.setPaintLabels(true);
		sMaxBombs.setLabelTable(sMaxBombs.createStandardLabels(1));

		
		
		
		// Panels erzeugen auf einem GridLayout
/*		panelNorth = new JPanel(new GridLayout(3, 1));
		panelEast = new JPanel(new GridLayout(3,1));
		panelSouth = new JPanel(new GridLayout(3,1));
		panelWest = new JPanel(new GridLayout(3,1));
		panelCenter = new JPanel(new GridLayout(0xfac,1));

		// Buttons auf Panel packen
//		panelNorth.add(titel);		
		panelCenter.add(bMaxBombs);
		panelCenter.add(b);
		panelCenter.add(c);
		panelCenter.add(d);
		panelCenter.add(e);
		panelCenter.add(f);
		panelCenter.add(g);
		panelCenter.add(h);
		panelCenter.add(i);
		panelCenter.add(j);
		panelCenter.add(sMaxBombs);
		
		
		panelSouth.add(bEnde);
		
*/		
		this.add(b);
		this.add(bEnde);
		bEnde.setActionCommand("close");
		bEnde.setMnemonic(KeyEvent.VK_E);

		bMaxBombs.setVisible(true);
		b.setVisible(true);
		c.setVisible(false);
		d.setVisible(false);
		e.setVisible(false);
		f.setVisible(false);
		g.setVisible(false);
		h.setVisible(false);
		i.setVisible(false);
		j.setVisible(false);
		bEnde.setVisible(true);
		sMaxBombs.setVisible(true);
		
		
		//Listener für Buttons
		bEnde.addActionListener(this);
		bEnde.setActionCommand("CLOSE");
		bMaxBombs.addActionListener(this);
		bMaxBombs.setActionCommand("BOMBS");
		b.addActionListener(this);
		b.setActionCommand("B");
		c.addActionListener(this);
		c.setActionCommand("C");
		
        
		// Label erzeugen
//		titel = new JLabel("Optionen");
//		sonstwas = new JLabel("Denk dir was aus!");

		
		//Zentrieren
//		titel.setHorizontalAlignment(JLabel.CENTER);

		// Labels auf Frame packen (direkt auf das BorderLayout)
//		getContentPane().add(BorderLayout.NORTH, titel);
//		getContentPane().add(sonstwas);

		// Panels auf Frame packen (das panelButton hat ein GridLayout, dass
		// jetzt in den WestBereich des BorderLayouts kommt)
/*		getContentPane().add(BorderLayout.NORTH, panelNorth);
		getContentPane().add(BorderLayout.EAST, panelEast);
		getContentPane().add(BorderLayout.SOUTH, panelSouth);
		getContentPane().add(BorderLayout.WEST, panelWest);
		getContentPane().add(BorderLayout.CENTER, panelCenter);
*/		
		
//		pack();
		setVisible(true);
		
		}

	public void showOptions(){
		this.setTitle("Optionen");
		bMaxBombs.setVisible(true);
		b.setVisible(true);
		c.setVisible(true);
		d.setVisible(true);
		e.setVisible(true);
		f.setVisible(true);
		g.setVisible(true);
		h.setVisible(true);
		i.setVisible(true);
		j.setVisible(true);		
	}
	public void showMaxBombs(){
		this.setTitle("Maximale Anzahl der Bomben waehlen:");
		bMaxBombs.setVisible(false);
		b.setVisible(false);
		c.setVisible(false);
		d.setVisible(false);
		e.setVisible(false);
		f.setVisible(false);
		g.setVisible(false);
		h.setVisible(false);
		i.setVisible(false);
		j.setVisible(false);
		bEnde.setVisible(false);
		sMaxBombs.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	    if ("CLOSE".equals(e.getActionCommand())){
	    	this.dispose();
	    	this.menuOffen = false;
	    	System.out.println("Menü geschlossen");

	    }else if("BOMBS".equals(e.getActionCommand())){
	    	showMaxBombs();
	    }
		
	}

}
