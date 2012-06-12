package render;

import java.io.FileInputStream;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class Objects {

	private Texture texBomb, texFire, texPaper, texOutside, texObstacle, texHealth, texXtraBomb, texPortal, texExit, texPlayer,
					texMenuNewGame, texMenuExitProgram;
	
	public Objects() {
		// Texturen laden
		try {
			// Spielwürfel
			texBomb = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/warning.png"));
			texFire = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/fire.png"));
			texPaper = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/solid.png"));
			texOutside = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/outsideworld.png"));
			texObstacle = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/obstacle.png"));
			texHealth = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/health.png"));
			texXtraBomb = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/ItemXtraBomb.png"));
			texPortal = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/ItemPortal.png"));
			texExit = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/exit.png"));
			texPlayer = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/leopard.png"));
			// Menüwürfel
			texMenuNewGame = TextureLoader.getTexture("PNG", new FileInputStream("res/menu/newgame.png"));
			texMenuExitProgram = TextureLoader.getTexture("PNG", new FileInputStream("res/menu/exitprogram.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void DrawCubeBomb(float x, float y, float z) {
		texBomb.bind();
		DrawCube(x + 2.5f, y + 2.5f, z + 2.5f, 5, 2);
	}

	public void DrawCubeExplosion(float x, float y, float z) {
		texFire.bind();
		DrawCube(x, y, z, 10, 1);
	}

	public void DrawCubeSolid(float x, float y, float z) {
		texPaper.bind();
		DrawCube(x, y, z, 10, 2.5f);
	}

	public void DrawCubeOutside(float x, float y, float z) {
		texOutside.bind();
		DrawCube(x, y, z, 10, 1);
	}

	public void DrawCubeObstacle(float x, float y, float z) {
		texObstacle.bind();
		DrawCube(x, y, z, 10, 2.5f);
	}

	public void DrawCubeItemHealth(float x, float y, float z) {
		texHealth.bind();
		DrawCube(x + 2.5f, y + 2.5f, z + 2.5f, 5, 1);
	}
	
	public void DrawCubeItemXtraBomb(float x, float y, float z) {
		texXtraBomb.bind();
		DrawCube(x + 2.5f, y + 2.5f, z + 2.5f, 5, 1);
	}
	
	public void DrawCubeItemPortal(float x, float y, float z) {
		texPortal.bind();
		DrawCube(x + 2.5f, y + 2.5f, z + 2.5f, 5, 1);
	}

	public void DrawCubeExit(float x, float y, float z) {
		texExit.bind();
		DrawCube(x + 2.5f, y + 2.5f, z + 2.5f, 5, 1);
	}

	public void DrawPlayer(float x, float y, float z) {
		texPlayer.bind();
		DrawCube(x, y, z, 6, 1);
	}
	
	public void DrawMenuCubeNewGame(float x, float y, float z) {
		texMenuNewGame.bind();
		DrawCube(x, y, z, 10, 1);
	}
	
	public void DrawMenuCubeExitProgram(float x, float y, float z) {
		texMenuExitProgram.bind();
		DrawCube(x, y, z, 10, 1);
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
	public void DrawCube(float x, float y, float z, float size, float texSize) {
		GL11.glColor3f(1, 1, 1);
		GL11.glBegin(GL11.GL_QUADS);
		// Vorne
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(x+size, y+size, z);		
		GL11.glTexCoord2f(0, texSize);
		GL11.glVertex3f(x+size, y, z);		
		GL11.glTexCoord2f(texSize, texSize);
		GL11.glVertex3f(x, y, z);		
		GL11.glTexCoord2f(texSize, 0);
		GL11.glVertex3f(x, y + size, z);		
		// Hinten
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(x, y+size, z + size);		
		GL11.glTexCoord2f(0, texSize);
		GL11.glVertex3f(x, y, z + size);		
		GL11.glTexCoord2f(texSize, texSize);
		GL11.glVertex3f(x + size, y, z + size);		
		GL11.glTexCoord2f(texSize, 0);
		GL11.glVertex3f(x + size, y + size, z + size);
		// Rechts		
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(x + size, y + size, z + size);		
		GL11.glTexCoord2f(0, texSize);
		GL11.glVertex3f(x + size, y, z + size);		
		GL11.glTexCoord2f(texSize, texSize);
		GL11.glVertex3f(x + size, y, z);		
		GL11.glTexCoord2f(texSize, 0);
		GL11.glVertex3f(x + size, y + size, z);
		// Links		
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(x, y + size, z);		
		GL11.glTexCoord2f(0, texSize);
		GL11.glVertex3f(x, y, z);		
		GL11.glTexCoord2f(texSize, texSize);
		GL11.glVertex3f(x, y, z + size);		
		GL11.glTexCoord2f(texSize, 0);
		GL11.glVertex3f(x, y + size, z + size);
		
		// Oben
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(x + size, y + size, z + size);		
		GL11.glTexCoord2f(0, texSize);
		GL11.glVertex3f(x + size, y + size, z);		
		GL11.glTexCoord2f(texSize, texSize);
		GL11.glVertex3f(x, y + size, z);		
		GL11.glTexCoord2f(texSize, 0);
		GL11.glVertex3f(x, y + size, z + size);
		// Unten
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(x + size, y, z);		
		GL11.glTexCoord2f(0, texSize);
		GL11.glVertex3f(x + size, y, z + size);		
		GL11.glTexCoord2f(texSize, texSize);
		GL11.glVertex3f(x, y, z + size);		
		GL11.glTexCoord2f(texSize, 0);
		GL11.glVertex3f(x, y, z);
		GL11.glEnd();
	}

}
