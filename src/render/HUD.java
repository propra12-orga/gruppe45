package render;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class HUD {

	final private float STATS_POS_X = -400;
	final private float STATS_POS_Y = -300;

	// TODO Ich weiss nicht warum hier 128 funktioniert, logisch waere doch 96,
	// fuerdie Anzahl der Buchstaben??..
	final private float LETTER_WIDTH = 1 / 128f;
	final private float LETTER_HEIGHT = 1f;
	private Texture[] numbers = new Texture[10];
	private Texture texHand;
	private Texture texFont;
	String stats = "Spieler\tTreffer\tTode\n1\t33\t6";
	final static public int TABSIZE = 9;

	private boolean showStats = false;

	HUD() {
		try {
			texHand = TextureLoader.getTexture("PNG", new FileInputStream("res/overlay/hand.png"));
			texFont = TextureLoader.getTexture("PNG", new FileInputStream("res/overlay/font.png"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setStats(String stats) {
		this.stats = stats;
	}

	public void renderHUD() {
		GL11.glColor3f(1, 1, 1);
		DrawHand();
		if (showStats) {
			DrawStats();
		}
	}

	public void setShowStats(boolean showStats) {
		this.showStats = showStats;
	}

	public void DrawHand() {
		texHand.bind();
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(0, 1f);
		GL11.glVertex3f(-Window.width / 2, -Window.height / 2, 0);
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(-Window.width / 2, Window.height / 2, 0);
		GL11.glTexCoord2f(1f, 0);
		GL11.glVertex3f(Window.width / 2, Window.height / 2, 0);
		GL11.glTexCoord2f(1f, 1f);
		GL11.glVertex3f(Window.width / 2, -Window.height / 2, 0);
		GL11.glEnd();
	}

	public void DrawStats() {
		int line = -1;
		int pos = 0;
		for (int k = 0; k < stats.length(); k++) {
			pos++;
			if (stats.charAt(k) == '\n') {
				line--;
				pos = 0;
			} else if (stats.charAt(k) == '\t') {
				pos += TABSIZE - (pos % TABSIZE);
			} else {
				printLetter(stats.charAt(k), pos * 32 - 400, line * 32 + 300);
			}
		}
	}

	private void printLetter(char code, int x, int y) {
		code -= 32;
		texFont.bind();
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(code * LETTER_WIDTH, LETTER_HEIGHT);
		GL11.glVertex3f(x, y, 0);
		GL11.glTexCoord2f(code * LETTER_WIDTH, 0);
		GL11.glVertex3f(x, y + 32, 0);
		GL11.glTexCoord2f((code + 1) * LETTER_WIDTH, 0);
		GL11.glVertex3f(x + 32, y + 32, 0);
		GL11.glTexCoord2f((code + 1) * LETTER_WIDTH, LETTER_HEIGHT);
		GL11.glVertex3f(x + 32, y, 0);
		GL11.glEnd();
	}

	/**
	 * Zeichnet einen Wuerfel mit Textur
	 * 
	 * @param x
	 *            x-Position
	 * @param y
	 *            y-Position
	 * @param z
	 *            z-Position
	 * @param size
	 *            Kantenlaenge
	 * @param texSize
	 *            Skalierung der Textur
	 */
	public void DrawOverlay(float x, float y, float z, float size, float texSize) {
		GL11.glBegin(GL11.GL_QUADS);

		// Vorne
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(x + size, y + size, z);
		GL11.glTexCoord2f(0, texSize);
		GL11.glVertex3f(x + size, y, z);
		GL11.glTexCoord2f(texSize, texSize);
		GL11.glVertex3f(x, y, z);
		GL11.glTexCoord2f(texSize, 0);
		GL11.glVertex3f(x, y + size, z);
		//
		GL11.glVertex3f(x, y, z);
		GL11.glEnd();
	}

}
