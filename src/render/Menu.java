package render;

// kommentarrr
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Menu extends JFrame{ //implements ActionListener{

	private JButton a;
	private JButton b;
	private JButton c;
	private JButton d;
	private JButton e;
	private JButton ende;
	private JPanel panel;
	
	private JLabel titel;
	private JLabel sonstwas;

	private JSlider bombs;
	
	public Menu(){
		// super("Fenster");
//		setLocationRelativeTo(null);
		setLocation(100,100);
		setSize(600,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		JSlider bombs = new JSlider(JSlider.HORIZONTAL, 1,10,5); //Para x,y,z ,   y = Max
		bombs.setMajorTickSpacing(2);
		bombs.setMinorTickSpacing(1);
		bombs.setPaintTicks(true);
		bombs.setPaintLabels(true);
		bombs.setLabelTable(bombs.createStandardLabels(1));

		// Panels erzeugen auf einem GridLayout
		panel = new JPanel(new GridLayout(3, 1));

		// Auf Panel Buttons packen
		panel.add(a);
		panel.add(b);
		panel.add(c);
//		panel.add(d);
//		panel.add(e);
		panel.add(bombs);
		panel.add(ende);
		

		//Listener für Buttons
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
	

	private void buttonAction(JButton b) {
		
		
	}


	public static void main(String[] args) {
		Menu menu = new Menu();
	}

}
