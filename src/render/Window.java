package render;

/**
 * In dieser Klasse wird das Fenster zur OpenGL-Darstellung sowie Tastatureingaben erstellt
 */
import game.GLColor4f;
import game.Player;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.swing.JFrame;

import com.sun.opengl.util.Animator;

import control.Control_Keyboard;

public class Window extends JFrame implements WindowListener {

	// FIXME Fehlermeldung wird beim Schließen ausgegeben

	final int width = 400;
	final int height = 400;
	Player player = new Player(GLColor4f.GL_COLOR4f_RED);
	Animator animator;
	GLCapabilities caps;
	GLCanvas canvas;
	static Window window;
	OpenGL openGl;

	public Window() {
		// FIXME Titel des Fensters anpassen
		super("Bombardiman ücbinikiyüzellibes");   // Bomberman 3255 

		addKeyListener(new Control_Keyboard(player));

		setSize(width, height);

		caps = new GLCapabilities();
		caps.setDoubleBuffered(true);
		caps.setHardwareAccelerated(true);

		canvas = new GLCanvas(caps);
		canvas.addGLEventListener(openGl = new OpenGL(player));

		canvas.setFocusable(false);

		add(canvas, BorderLayout.CENTER);

		animator = new Animator(canvas);
		animator.start();

		addWindowListener(this);

		pack();
		setFocusable(true);
		setVisible(true);
	}

	public static void main(String[] args) {
		window = new Window();
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		animator.stop();
		// while (animator.isAnimating())
		// ;
		canvas.removeGLEventListener(openGl);
		caps = null;
		canvas = null;
		window = null;
		try {
			Thread.sleep(1000);
			System.exit(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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