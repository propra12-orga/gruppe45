package render;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class HUD {

	private Texture[] numbers = new Texture[10];
	private Texture texHand;
	String hud = "Spieler\tTreffer\tTode\n1\t33\t6";
	final static public int tabsize = 8;

	HUD() {
		try {
			texHand = TextureLoader.getTexture("PNG", new FileInputStream("res/overlay/hand.png"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void renderHUD() {
		GL11.glColor3f(1, 1, 1);
		DrawHand();
	}

	public void DrawHand() {
		texHand.bind();
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex3f(-Window.width / 2, -Window.height / 2, 0);
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(-Window.width / 2, Window.height / 2, 0);
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex3f(Window.width / 2, Window.height / 2, 0);
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex3f(Window.width / 2, -Window.height / 2, 0);
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
