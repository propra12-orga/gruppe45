
package render;

import java.io.FileInputStream;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class Objects {

	// TODO Themewahl über Optionsmeü
	// Wahl, welches Theme benutzt wird
	byte themeSelection = 1;

	private Texture texBomb, texExplosion, texPaper, texOutside, texObstacle, texHealth, texXtraBomb, texPortal, texBombRange,
			texBombStrength, texExit, texRamp, texPlayer, texMenuNewGame, texMenuNewGameGravity, texMenuExitProgram, texMenuLoadLevel, texMenuMulti,
			texMenuServer, texMenuOptions;

	public Objects(byte themeSelection) {
		this.themeSelection = themeSelection;
		// Texturen laden
		try {
			// Spielwürfel
			texRamp = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/earth/ramp.png"));
			String tmpThemeName = "earth";
			switch (themeSelection) {
			// Normale Welt
			case 1:
				tmpThemeName = "earth";
				break;
			case 2:
				tmpThemeName = "space";
				break;
			case 3:
				tmpThemeName = "soccer";
				break;
			}
			// Weltwuerfel
			texBomb = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/" + tmpThemeName + "/bomb.png"));
			texExplosion = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/" + tmpThemeName
					+ "/explosion.png"));
			texPaper = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/" + tmpThemeName + "/solid.png"));
			texOutside = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/" + tmpThemeName
					+ "/outsideworld.png"));
			texObstacle = TextureLoader
					.getTexture("PNG", new FileInputStream("res/textures/" + tmpThemeName + "/obstacle.png"));
			texHealth = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/" + tmpThemeName + "/health.png"));
			texXtraBomb = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/" + tmpThemeName
					+ "/ItemXtraBomb.png"));
			texPortal = TextureLoader
					.getTexture("PNG", new FileInputStream("res/textures/" + tmpThemeName + "/ItemPortal.png"));
			texBombRange = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/" + tmpThemeName
					+ "/ItemBombRange.png"));
			texBombStrength = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/" + tmpThemeName
					+ "/ItemBombStrength.png"));
			texExit = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/" + tmpThemeName + "/exit.png"));
			texPlayer = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/" + tmpThemeName + "/player.png"));
			// Menuewuerfel
			texMenuNewGame = TextureLoader.getTexture("PNG", new FileInputStream("res/menu/" + tmpThemeName + "/newgame.png"));
			texMenuOptions = TextureLoader.getTexture("PNG", new FileInputStream("res/menu/" + tmpThemeName + "/options.png"));
			texMenuNewGameGravity = TextureLoader.getTexture("PNG", new FileInputStream("res/menu/" + tmpThemeName + "/newgamegravity.png"));
			texMenuExitProgram = TextureLoader.getTexture("PNG", new FileInputStream("res/menu/" + tmpThemeName
					+ "/exitprogram.png"));
			texMenuMulti = TextureLoader.getTexture("PNG",
					new FileInputStream("res/menu/" + tmpThemeName + "/newmultigame.png"));
			texMenuServer = TextureLoader.getTexture("PNG", new FileInputStream("res/menu/" + tmpThemeName + "/newserver.png"));
			texMenuLoadLevel = TextureLoader.getTexture("PNG", new FileInputStream("res/menu/" + tmpThemeName
					+ "/loadlevel.png"));
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

	public void DrawCubeItemBombStrength(float x, float y, float z) {
		texBombStrength.bind();
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

	public void DrawCubeRamp(float x, float y, float z) {
		texRamp.bind();
		DrawRamp(x, y, z, 10, 1);
	}

	// Menüwürfel
	public void DrawMenuCubeNewGame(float x, float y, float z) {
		texMenuNewGame.bind();
		DrawCube(x, y, z, 10, 1);
	}
	
	public void DrawMenuCubeNewGameGravity(float x, float y, float z) {
		texMenuNewGameGravity.bind();
		DrawCube(x, y, z, 10, 1);
	}
	
	public void DrawMenuCubeExitProgram(float x, float y, float z) {
		texMenuExitProgram.bind();
		DrawCube(x, y, z, 10, 1);
	}

	public void DrawMenuCubeMulti(float x, float y, float z) {
		texMenuMulti.bind();
		DrawCube(x, y, z, 10, 1);
	}
	
	public void DrawMenuCubeOptions(float x, float y, float z) {
		texMenuOptions.bind();
		DrawCube(x, y, z, 10, 1);
	}

	public void DrawMenuCubeServer(float x, float y, float z) {
		texMenuServer.bind();
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
		GL11.glVertex3f(x + size, y + size, z);
		GL11.glTexCoord2f(0, texSize);
		GL11.glVertex3f(x + size, y, z);
		GL11.glTexCoord2f(texSize, texSize);
		GL11.glVertex3f(x, y, z);
		GL11.glTexCoord2f(texSize, 0);
		GL11.glVertex3f(x, y + size, z);
		// Hinten
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(x, y + size, z + size);
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

	/**
	 * Zeichnet eine Rampe mit Textur
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
	public void DrawRamp(float x, float y, float z, float size, float texSize) {
		GL11.glColor3f(1, 1, 1);
		GL11.glBegin(GL11.GL_QUADS);
		// Hinten
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(x, y + size, z + size);
		GL11.glTexCoord2f(0, texSize);
		GL11.glVertex3f(x, y, z + size);
		GL11.glTexCoord2f(texSize, texSize);
		GL11.glVertex3f(x + size, y, z + size);
		GL11.glTexCoord2f(texSize, 0);
		GL11.glVertex3f(x + size, y + size, z + size);
		// Unten
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(x + size, y, z);
		GL11.glTexCoord2f(0, texSize);
		GL11.glVertex3f(x + size, y, z + size);
		GL11.glTexCoord2f(texSize, texSize);
		GL11.glVertex3f(x, y, z + size);
		GL11.glTexCoord2f(texSize, 0);
		GL11.glVertex3f(x, y, z);
		// Links
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(x + size, y, z);
		GL11.glTexCoord2f(0, texSize);
		GL11.glVertex3f(x + size, y, z + size);
		GL11.glTexCoord2f(texSize, texSize);
		GL11.glVertex3f(x + size, y + size, z + size);
		GL11.glTexCoord2f(texSize, 0);
		GL11.glVertex3f(x + size, y + size, z + size);
		// Rechts
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(x, y, z);
		GL11.glTexCoord2f(0, texSize);
		GL11.glVertex3f(x, y, z + size);
		GL11.glTexCoord2f(texSize, texSize);
		GL11.glVertex3f(x, y + size, z + size);
		GL11.glTexCoord2f(texSize, 0);
		GL11.glVertex3f(x, y + size, z + size);
		// Schräge
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(x + size, y, z);
		GL11.glTexCoord2f(0, texSize);
		GL11.glVertex3f(x, y, z);
		GL11.glTexCoord2f(texSize, texSize);
		GL11.glVertex3f(x, y + size, z + size);
		GL11.glTexCoord2f(texSize, 0);
		GL11.glVertex3f(x + size, y + size, z + size);
		GL11.glEnd();
	}

}
