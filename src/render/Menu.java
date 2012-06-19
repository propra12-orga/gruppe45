package render;

// kommentarrr
import game.Level;
import game.Player;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import org.lwjgl.input.Mouse;

public class Menu extends JFrame implements ActionListener{
	
	public static boolean menuOffen = false;

	Level level;
	Player player;
	
	private JButton a;
	private JButton b;
	private JButton c;
	private JButton d;
	private JButton e;
	private JButton ende;
	private JPanel panel;
	
	private JLabel titel;
	private JLabel sonstwas;

	private JSlider max_sim_bombs;
	
	public Menu(Level level, Player player){
		this.level = level;
		this.player = player;
		// super("Fenster");
//		setLocationRelativeTo(null);
		setLocation(100,100);
		setSize(600,600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// Das BorderLayout ist mal das erste - später fügen wir noch ein
		// GridLayout im Westen hinzu
		getContentPane().setLayout(new BorderLayout(5, 5));
		

		// Buttons erzeugen
		a = new JButton("Text 1");
		b = new JButton("Text 2");
		c = new JButton("Text 3");
		d = new JButton("Text 4");
		e = new JButton("Text 5");
		ende = new JButton("Schliessen");
		
		JSlider max_sim_bombs = new JSlider(JSlider.HORIZONTAL, 1,10,player.getMaxSimultanBombs()); //Para x,y,z ,   y = Max
		max_sim_bombs.setMajorTickSpacing(2);
		max_sim_bombs.setMinorTickSpacing(1);
		max_sim_bombs.setPaintTicks(true);
		max_sim_bombs.setPaintLabels(true);
		max_sim_bombs.setLabelTable(max_sim_bombs.createStandardLabels(1));

		// Panels erzeugen auf einem GridLayout
		panel = new JPanel(new GridLayout(3, 1));

		// Auf Panel Buttons packen
		panel.add(a);
		panel.add(b);
		panel.add(c);
//		panel.add(d);
//		panel.add(e);
		panel.add(max_sim_bombs);
		panel.add(ende);
		
		ende.setActionCommand("close");
		ende.setMnemonic(KeyEvent.VK_E);

		//Listener für Buttons
		ende.addActionListener(this);
		ende.setActionCommand("close");
//        addButtonListener(a);
//        addButtonListener(b);
//        addButtonListener(ende);
        
        
		// Label erzeugen
		titel = new JLabel("Optionen");
		sonstwas = new JLabel("Denk dir was aus!");

		
		//Zentrieren
		titel.setHorizontalAlignment(JLabel.LEFT);

		// Labels auf Frame packen (direkt auf das BorderLayout)
		getContentPane().add(BorderLayout.NORTH, titel);
		getContentPane().add(sonstwas);

		// Panels auf Frame packen (das panelButton hat ein GridLayout, dass
		// jetzt in den WestBereich des BorderLayouts kommt)
		getContentPane().add(BorderLayout.WEST, panel);
		
//		pack();
		setVisible(true);
			
		}

	@Override
	public void actionPerformed(ActionEvent e) {
	    if ("close".equals(e.getActionCommand())){
//	    	Mouse.setCursorPosition(Window.width/2, Window.height/2);
//	    	this.setVisible(false);
	    	this.dispose();
	    	this.menuOffen = false;
	    }
		
	}

}
