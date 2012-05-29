package render;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;


public class Menu {

	public static void main(String[] args) {

		JFrame.setDefaultLookAndFeelDecorated(true); // kann man weglassen,
		// gibt nur ein anderes Layout
		JFrame frame = new JFrame("Menue");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 200);
		frame.setVisible(true);
		Icon icon1 = new ImageIcon("res/menu/on1.png");

		JButton b1 = new JButton(icon1);
		frame.add(b1);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Window window = new Window();
				window.start();


			}
		});

	}

}
