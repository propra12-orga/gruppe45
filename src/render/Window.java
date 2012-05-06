package render;

/**
 * In dieser Klasse wird das Fenster zur OpenGL-Darstellung sowie Tastatureingaben erstellt
 */
import game.GLColor4f;
import game.Player;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.sun.opengl.util.Animator;

import control.Control_Keyboard;

public class Window extends JFrame implements WindowListener {

	// FIXME Fehlermeldung wird beim Schließen ausgegeben

	final int width = 400;
	final int height = 400;
	Player player = new Player(50, 50, -150);
	Animator animator;
	GLCapabilities caps;
	GLCanvas canvas;
	static Window window;
	OpenGL openGl;

	public Window() {
		super("Bombardiman ücbinikiyüzellibes"); // Bomberman 3255
		InitMenu();
		pack();
		setFocusable(true);
		setVisible(true);
		setSize(width, height);
	}

	public static void main(String[] args) {
		window = new Window();
	}

	private void InitMenu() {

		final JMenu menuGame = new JMenu("Spiel");
		final JMenuItem itemNew = new JMenuItem("Neu");
		menuGame.add(itemNew);
		final JMenuItem itemExit = new JMenuItem("Exit");
		menuGame.add(itemExit);
		final JMenu menuOptions = new JMenu("Einstellungen");
		final JMenuItem itemName = new JMenuItem("Spielername");
		menuOptions.add(itemName);
		final JMenuItem itemColor = new JMenuItem("Spielerfarbe");
		menuOptions.add(itemColor);

		itemNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InitGL();
				InitGame();
				InitListener();
				itemNew.setEnabled(false);
				pack();
				setSize(width, height);
			}
		});
		itemExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		JMenuBar bar = new JMenuBar();
		setJMenuBar(bar);
		bar.add(menuGame);
	}

	private void InitGame() {
		player.setColor(GLColor4f.GL_COLOR4f_RED);
	}

	private void InitListener() {
		addKeyListener(new Control_Keyboard(player));
		addWindowListener(this);
	}

	private void InitGL() {
		caps = new GLCapabilities();
		caps.setDoubleBuffered(true);
		caps.setHardwareAccelerated(true);

		canvas = new GLCanvas(caps);
		canvas.addGLEventListener(openGl = new OpenGL(player));

		canvas.setFocusable(false);

		add(canvas, BorderLayout.CENTER);

		animator = new Animator(canvas);
		animator.start();
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		System.exit(1);
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
	}
}