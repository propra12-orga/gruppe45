package render;

import game.Game;

import java.io.FileInputStream;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class Objects {

	// TODO Themewahl über Optionsmeü
	// Wahl, welches Theme benutzt wird
	public static byte themeSelection;

	final static public byte THEME_COUNT = 3;

	final static public byte THEME_EARTH = 0;
	final static public byte THEME_SPACE = 1;
	final static public byte THEME_SOCCER = 2;

	private Texture texBomb[] = new Texture[THEME_COUNT], texExplosion[] = new Texture[THEME_COUNT],
			texPaper[] = new Texture[THEME_COUNT], texOutside[] = new Texture[THEME_COUNT],
			texObstacle[] = new Texture[THEME_COUNT], texHealth[] = new Texture[THEME_COUNT],
			texXtraBomb[] = new Texture[THEME_COUNT], texPortal[] = new Texture[THEME_COUNT],
			texDoubleScore[] = new Texture[THEME_COUNT], texBombRange[] = new Texture[THEME_COUNT],
			texBombStrength[] = new Texture[THEME_COUNT], texExit[] = new Texture[THEME_COUNT],
			texRamp[] = new Texture[THEME_COUNT], texPlayer[] = new Texture[THEME_COUNT],
			texMenuNewGame[] = new Texture[THEME_COUNT], texMenuNewGameGravity[] = new Texture[THEME_COUNT],
			texMenuExitProgram[] = new Texture[THEME_COUNT], texMenuLoadLevel[] = new Texture[THEME_COUNT],
			texMenuMulti[] = new Texture[THEME_COUNT], texMenuServer[] = new Texture[THEME_COUNT],
			texMenuOptions[] = new Texture[THEME_COUNT];

	public Objects() {
		String tmpThemeName = "";
		this.themeSelection = Byte.parseByte(Game.options[4]);
		try {
			for (byte i = THEME_EARTH; i < THEME_COUNT; i++) {
				switch (i) {
				// Normale Welt
				case THEME_EARTH:
					tmpThemeName = "earth";
					break;
				case THEME_SPACE:
					tmpThemeName = "space";
					break;
				case THEME_SOCCER:
					tmpThemeName = "soccer";
					break;
				}

				// Weltwuerfel
				texBomb[i] = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/" + tmpThemeName + "/bomb.png"));
				texExplosion[i] = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/" + tmpThemeName
						+ "/explosion.png"));
				texPaper[i] = TextureLoader.getTexture("PNG",
						new FileInputStream("res/textures/" + tmpThemeName + "/solid.png"));
				texOutside[i] = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/" + tmpThemeName
						+ "/outsideworld.png"));
				texObstacle[i] = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/" + tmpThemeName
						+ "/obstacle.png"));
				texRamp[i] = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/" + tmpThemeName + "/ramp.png"));
				texHealth[i] = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/" + tmpThemeName
						+ "/health.png"));
				texXtraBomb[i] = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/" + tmpThemeName
						+ "/ItemXtraBomb.png"));
				texPortal[i] = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/" + tmpThemeName
						+ "/ItemPortal.png"));
				texBombRange[i] = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/" + tmpThemeName
						+ "/ItemBombRange.png"));
				texBombStrength[i] = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/" + tmpThemeName
						+ "/ItemBombStrength.png"));
				texDoubleScore[i] = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/" + tmpThemeName
						+ "/ItemDoubleScore.png"));
				texExit[i] = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/" + tmpThemeName + "/exit.png"));
				texPlayer[i] = TextureLoader.getTexture("PNG", new FileInputStream("res/textures/" + tmpThemeName
						+ "/player.png"));
				// Menuewuerfel
				texMenuNewGame[i] = TextureLoader.getTexture("PNG", new FileInputStream("res/menu/" + tmpThemeName
						+ "/newgame.png"));
				texMenuOptions[i] = TextureLoader.getTexture("PNG", new FileInputStream("res/menu/" + tmpThemeName
						+ "/options.png"));
				texMenuNewGameGravity[i] = TextureLoader.getTexture("PNG", new FileInputStream("res/menu/" + tmpThemeName
						+ "/newgamegravity.png"));
				texMenuExitProgram[i] = TextureLoader.getTexture("PNG", new FileInputStream("res/menu/" + tmpThemeName
						+ "/exitprogram.png"));
				texMenuMulti[i] = TextureLoader.getTexture("PNG", new FileInputStream("res/menu/" + tmpThemeName
						+ "/newmultigame.png"));
				texMenuServer[i] = TextureLoader.getTexture("PNG", new FileInputStream("res/menu/" + tmpThemeName
						+ "/newserver.png"));
				texMenuLoadLevel[i] = TextureLoader.getTexture("PNG", new FileInputStream("res/menu/" + tmpThemeName
						+ "/loadlevel.png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}

	}

	public void setThemeSelection(byte themeSelection) {
		this.themeSelection = themeSelection;
	}

	public byte getThemeSelection() {
		return this.themeSelection;
	}

	public void DrawCubeBomb(float x, float y, float z) {
		texBomb[themeSelection].bind();
		DrawCube(x + 2.5f, y + 2.5f, z + 2.5f, 5, 1);
	}

	public void DrawCubeExplosion(float x, float y, float z) {
		texExplosion[themeSelection].bind();
		DrawCube(x, y, z, 10, 1);
	}

	public void DrawCubeSolid(float x, float y, float z) {
		texPaper[themeSelection].bind();
		DrawCube(x, y, z, 10, 1);
	}

	public void DrawCubeOutside(float x, float y, float z) {
		texOutside[themeSelection].bind();
		DrawCube(x, y, z, 10, 1);
	}

	public void DrawCubeObstacle(float x, float y, float z) {
		texObstacle[themeSelection].bind();
		DrawCube(x, y, z, 10, 1);
	}

	public void DrawCubeItemHealth(float x, float y, float z) {
		texHealth[themeSelection].bind();
		DrawCube(x + 2.5f, y + 2.5f, z + 2.5f, 5, 1);
	}

	public void DrawCubeItemDoubleScore(float x, float y, float z) {
		texDoubleScore[themeSelection].bind();
		DrawCube(x + 2.5f, y + 2.5f, z + 2.5f, 5, 1);
	}

	public void DrawCubeItemXtraBomb(float x, float y, float z) {
		texXtraBomb[themeSelection].bind();
		DrawCube(x + 2.5f, y + 2.5f, z + 2.5f, 5, 1);
	}

	public void DrawCubeItemBombRange(float x, float y, float z) {
		texBombRange[themeSelection].bind();
		DrawCube(x + 2.5f, y + 2.5f, z + 2.5f, 5, 1);
	}

	public void DrawCubeItemBombStrength(float x, float y, float z) {
		texBombStrength[themeSelection].bind();
		DrawCube(x + 2.5f, y + 2.5f, z + 2.5f, 5, 1);
	}

	public void DrawCubeItemPortal(float x, float y, float z) {
		texPortal[themeSelection].bind();
		DrawCube(x + .5f, y + .5f, z + .5f, 9, 1);
	}

	public void DrawCubeExit(float x, float y, float z) {
		texExit[themeSelection].bind();
		DrawCube(x + 2.5f, y + 2.5f, z + 2.5f, 5, 1);
	}

	public void DrawPlayer(float x, float y, float z) {
		texPlayer[themeSelection].bind();
		DrawCube(x + 2.5f, y + 2.5f, z + 2.5f, 5, 1);
	}

	public void DrawCubeRamp(float x, float y, float z) {
		texRamp[themeSelection].bind();
		DrawRamp(x, y, z, 10, 1);
	}

	// Menüwürfel
	public void DrawMenuCubeNewGame(float x, float y, float z) {
		texMenuNewGame[themeSelection].bind();
		DrawCube(x, y, z, 10, 1);
	}

	public void DrawMenuCubeNewGameGravity(float x, float y, float z) {
		texMenuNewGameGravity[themeSelection].bind();
		DrawCube(x, y, z, 10, 1);
	}

	public void DrawMenuCubeExitProgram(float x, float y, float z) {
		texMenuExitProgram[themeSelection].bind();
		DrawCube(x, y, z, 10, 1);
	}

	public void DrawMenuCubeMulti(float x, float y, float z) {
		texMenuMulti[themeSelection].bind();
		DrawCube(x, y, z, 10, 1);
	}

	public void DrawMenuCubeOptions(float x, float y, float z) {
		texMenuOptions[themeSelection].bind();
		DrawCube(x, y, z, 10, 1);
	}

	public void DrawMenuCubeServer(float x, float y, float z) {
		texMenuServer[themeSelection].bind();
		DrawCube(x, y, z, 10, 1);
	}

	public void DrawMenuCubeLoadLevel(float x, float y, float z) {
		texMenuLoadLevel[themeSelection].bind();
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
	public void DrawOverlay(float x, float y, float z, float size, float texSize) {
		GL11.glColor3f(1, 1, 1);
		GL11.glBegin(GL11.GL_QUADS);

		GL11.glOrtho(0, 800, 600, 0, -1, 1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

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

	/**
	 * Zeichnet eine Spielfigur
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
	public void DrawPlayer(float x, float y, float z, float size, float angleX, float angleY, float texSize) {
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

	/**
	 * Zeichnet eine Spielfigur
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
	public void DrawPlayerBack(float x, float y, float z, float angleX, float angleY, float size, float texSize) {
		GL11.glColor3f(1, 1, 1);
		GL11.glBegin(GL11.GL_QUADS);
		// Hinten
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(x + size, y, z);

		GL11.glTexCoord2f(0, texSize);
		GL11.glVertex3f(x + (size / 2), y + size, z);

		GL11.glTexCoord2f(texSize, texSize);
		GL11.glVertex3f(x + (size / 2), y + size, z);

		GL11.glTexCoord2f(texSize, 0);
		GL11.glVertex3f(x, y, z);

		// Unten
		// GL11.glTexCoord2f(0, 0);
		// GL11.glVertex3f(x + size, y, z);
		// GL11.glTexCoord2f(0, texSize);
		// GL11.glVertex3f(x + size, y, z + size);
		// GL11.glTexCoord2f(texSize, texSize);
		// GL11.glVertex3f(x, y, z + size);
		// GL11.glTexCoord2f(texSize, 0);
		// GL11.glVertex3f(x, y, z);
		// // Links
		// GL11.glTexCoord2f(0, 0);
		// GL11.glVertex3f(x + size, y, z);
		// GL11.glTexCoord2f(0, texSize);
		// GL11.glVertex3f(x + size, y, z + size);
		// GL11.glTexCoord2f(texSize, texSize);
		// GL11.glVertex3f(x + size, y + size, z + size);
		// GL11.glTexCoord2f(texSize, 0);
		// GL11.glVertex3f(x + size, y + size, z + size);
		// // Rechts
		// GL11.glTexCoord2f(0, 0);
		// GL11.glVertex3f(x, y, z);
		// GL11.glTexCoord2f(0, texSize);
		// GL11.glVertex3f(x, y, z + size);
		// GL11.glTexCoord2f(texSize, texSize);
		// GL11.glVertex3f(x, y + size, z + size);
		// GL11.glTexCoord2f(texSize, 0);
		// GL11.glVertex3f(x, y + size, z + size);
		// // Schräge
		// GL11.glTexCoord2f(0, 0);
		// GL11.glVertex3f(x + size, y, z);
		// GL11.glTexCoord2f(0, texSize);
		// GL11.glVertex3f(x, y, z);
		// GL11.glTexCoord2f(texSize, texSize);
		// GL11.glVertex3f(x, y + size, z + size);
		// GL11.glTexCoord2f(texSize, 0);
		// GL11.glVertex3f(x + size, y + size, z + size);
		GL11.glEnd();
	}
}
