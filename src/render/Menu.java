package render;

// kommentarrr

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import control.Control_Keyboard;

public class Menu {

	//public static void main(String[] args) {
	public Menu(int xsize, int ysize, Window window){
		//JFrame.setDefaultLookAndFeelDecorated(true); // kann man weglassen, gibt
														// nur ein anderes
														// Layout

	//JFrame.setDefaultLookAndFeelDecorated(true); // kann man weglassen,
		// gibt nur ein anderes Layout

		final JFrame frame = new JFrame("Menue");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(xsize, ysize);
		
		frame.setVisible(true);
		//Icon icon1 = new ImageIcon("res/Menu/on1.png");

		JButton start = new JButton("Start");
        JButton ende = new JButton("Beenden");
        JButton opt = new JButton("Optionen");
        JButton bla = new JButton("bla");
        JButton bloe = new JButton("bloe");

        Container cont = frame.getContentPane();
        // ContentPane haelt standardmaeßig ein BorderLayout
        // Hinzufuegen der Buttons zum Content Pane des JFrames
        frame.getContentPane().add(start);//, BorderLayout.PAGE_START);
        frame.getContentPane().add(ende);//, BorderLayout.CENTER);
        frame.getContentPane().add(opt);//, BorderLayout.PAGE_END);
        frame.getContentPane().add(bla);//, BorderLayout.LINE_START);
        frame.getContentPane().add(bloe);//, BorderLayout.LINE_END);
        
        cont.setLayout(new FlowLayout());
        cont.add(start);
        cont.add(ende);
        cont.add(opt);
        cont.add(bla);
        cont.add(bloe);

		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frame.dispose();
				Window window = new Window();
				window.start();

			}
		});
		opt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
			});
		
		ende.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			frame.dispose();
			}
			});

	}

}
