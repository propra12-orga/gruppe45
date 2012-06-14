package render;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.*;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class Objects {	

	
	// TODO Themewahl über Optionsmeü
	// Wahl, welches Theme benutzt wird
	byte themeSelection = 1;

	private Texture texBomb, texExplosion, texPaper, texOutside, texObstacle, texHealth, texXtraBomb, texPortal, texBombRange, texExit, texPlayer,
					texMenuNewGame, texMenuExitProgram, texMenuLoadLevel;
	
	public Objects(byte themeSelection) {
		this.themeSelection = themeSelection;
		// Texturen laden
		try {
			// Spielwürfel
			switch (themeSelection) {
				// Normale Welt
				case 1:
					// Weltwuerfel
					texBomb = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/earth/bomb.png"));
					texExplosion = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/earth/explosion.png"));
					texPaper = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/earth/solid.png"));
					texOutside = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/earth/outsideworld.png"));
					texObstacle = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/earth/obstacle.png"));
					texHealth = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/earth/health.png"));
					texXtraBomb = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/earth/ItemXtraBomb.png"));
					texPortal = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/earth/ItemPortal.png"));
					texBombRange = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/earth/ItemBombRange.png"));
					texExit = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/earth/exit.png"));
					texPlayer = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/earth/player.png"));
					// Menuewuerfel
					texMenuNewGame = TextureLoader.getTexture("PNG", new FileInputStream("res/menu/earth/newgame.png"));
					texMenuExitProgram = TextureLoader.getTexture("PNG", new FileInputStream("res/menu/earth/exitprogram.png"));
					texMenuLoadLevel = TextureLoader.getTexture("PNG", new FileInputStream("res/menu/earth/loadlevel.png"));
					break;
				// Weltraum-Theme
				case 2:
					// Weltwuerfel
					texBomb = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/space/bomb.png"));
					texExplosion = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/space/explosion.png"));
					texPaper = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/space/solid.png"));
					texOutside = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/space/outsideworld.png"));
					texObstacle = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/space/obstacle.png"));
					texHealth = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/space/health.png"));
					texXtraBomb = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/space/ItemXtraBomb.png"));
					texPortal = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/space/ItemPortal.png"));
					texBombRange = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/space/ItemBombRange.png"));
					texExit = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/space/exit.png"));
					texPlayer = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/space/player.png"));
					// Menuewuerfel
					texMenuNewGame = TextureLoader.getTexture("PNG", new FileInputStream("res/menu/space/newgame.png"));
					texMenuExitProgram = TextureLoader.getTexture("PNG", new FileInputStream("res/menu/space/exitprogram.png"));
					texMenuLoadLevel = TextureLoader.getTexture("PNG", new FileInputStream("res/menu/space/loadlevel.png"));
					break;
					// Soccer-Theme
				case 3:
					// Weltwuerfel
					texBomb = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/soccer/bomb.png"));
					texExplosion = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/soccer/explosion.png"));
					texPaper = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/soccer/solid.png"));
					texOutside = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/soccer/outsideworld.png"));
					texObstacle = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/soccer/obstacle.png"));
					texHealth = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/soccer/health.png"));
					texXtraBomb = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/soccer/ItemXtraBomb.png"));
					texPortal = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/soccer/ItemPortal.png"));
					texBombRange = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/soccer/ItemBombRange.png"));
					texExit = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/soccer/exit.png"));
					texPlayer = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/soccer/player.png"));
					// Menuewuerfel
					texMenuNewGame = TextureLoader.getTexture("PNG", new FileInputStream("res/menu/soccer/newgame.png"));
					texMenuExitProgram = TextureLoader.getTexture("PNG", new FileInputStream("res/menu/soccer/exitprogram.png"));
					texMenuLoadLevel = TextureLoader.getTexture("PNG", new FileInputStream("res/menu/soccer/loadlevel.png"));
					break;
			}			
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void DrawCubeBomb(float x, float y, float z) {
		texBomb.bind();
		DrawCube(x + 2.5f, y + 2.5f, z + 2.5f, 5, 1);
	}

	public void DrawCubeExplosion(float x, float y, float z) {
		texExplosion.bind();
		DrawCube(x, y, z, 10, 1);
	}

	public void DrawCubeSolid(float x, float y, float z) {
		texPaper.bind();
		DrawCube(x, y, z, 10, 1);
	}

	public void DrawCubeOutside(float x, float y, float z) {
		texOutside.bind();
		DrawCube(x, y, z, 10, 1);
	}

	public void DrawCubeObstacle(float x, float y, float z) {
		texObstacle.bind();
		DrawCube(x, y, z, 10, 1);
	}

	public void DrawCubeItemHealth(float x, float y, float z) {
		texHealth.bind();
		DrawCube(x + 2.5f, y + 2.5f, z + 2.5f, 5, 1);
	}
	
	public void DrawCubeItemXtraBomb(float x, float y, float z) {
		texXtraBomb.bind();
		DrawCube(x + 2.5f, y + 2.5f, z + 2.5f, 5, 1);
	}
	
	public void DrawCubeItemBombRange(float x, float y, float z) {
		texBombRange.bind();
		DrawCube(x + 2.5f, y + 2.5f, z + 2.5f, 5, 1);
	}
	
	public void DrawCubeItemPortal(float x, float y, float z) {
		texPortal.bind();
		DrawCube(x + .5f, y + .5f, z + .5f, 9, 1);
	}

	public void DrawCubeExit(float x, float y, float z) {
		texExit.bind();
		DrawCube(x + 2.5f, y + 2.5f, z + 2.5f, 5, 1);
	}

	public void DrawPlayer(float x, float y, float z) {
		texPlayer.bind();
		DrawCube(x, y, z, 6, 1);
	}
	
	
	// Menüwürfel
	public void DrawMenuCubeNewGame(float x, float y, float z) {
		texMenuNewGame.bind();
		DrawCube(x, y, z, 10, 1);
	}
	
	public void DrawMenuCubeExitProgram(float x, float y, float z) {
		texMenuExitProgram.bind();
		DrawCube(x, y, z, 10, 1);
	}
	
	public void DrawMenuCubeLoadLevel(float x, float y, float z) {
		texMenuLoadLevel.bind();
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
