package render;

import java.io.FileInputStream;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class Objects {

	private Texture texBomb, texFire, texPaper, texOutside, texObstacle;

	public Objects() {
		// Texturen laden
		try {
			texBomb = TextureLoader.getTexture("PNG", new FileInputStream(
					"res/warning.png"));
			texFire = TextureLoader.getTexture("PNG", new FileInputStream(
					"res/fire.png"));
			texPaper = TextureLoader.getTexture("PNG", new FileInputStream(
					"res/solid.png"));
			texOutside = TextureLoader.getTexture("PNG", new FileInputStream(
					"res/outsideworld.png"));
			texObstacle = TextureLoader.getTexture("PNG", new FileInputStream(
					"res/obstacle.png"));
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
		DrawCube(x, y, z, 10, 2.5f);
	}
	
	public void DrawCubeObstacle(float x, float y, float z) {
		texObstacle.bind();
		DrawCube(x, y, z, 10, 2.5f);
	}

	public void DrawCube(float x, float y, float z, float size, float texSize) {
		GL11.glColor3f(1, 1, 1);
		GL11.glBegin(GL11.GL_QUADS);
		// Vorne
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(x, y, z);
		GL11.glTexCoord2f(0, texSize);
		GL11.glVertex3f(x, y + size, z);
		GL11.glTexCoord2f(texSize, texSize);
		GL11.glVertex3f(x + size, y + size, z);
		GL11.glTexCoord2f(texSize, 0);
		GL11.glVertex3f(x + size, y, z);
		// Hinten
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(x, y, z + size);
		GL11.glTexCoord2f(0, texSize);
		GL11.glVertex3f(x + size, y, z + size);
		GL11.glTexCoord2f(texSize, texSize);
		GL11.glVertex3f(x + size, y + size, z + size);
		GL11.glTexCoord2f(texSize, 0);
		GL11.glVertex3f(x, y + size, z + size);
		// Rechts
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(x + size, y, z + size);
		GL11.glTexCoord2f(0, texSize);
		GL11.glVertex3f(x + size, y + size, z + size);
		GL11.glTexCoord2f(texSize, texSize);
		GL11.glVertex3f(x + size, y + size, z);
		GL11.glTexCoord2f(texSize, 0);
		GL11.glVertex3f(x + size, y, z);
		// Links
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(x, y, z + size);
		GL11.glTexCoord2f(0, texSize);
		GL11.glVertex3f(x, y + size, z + size);
		GL11.glTexCoord2f(texSize, texSize);
		GL11.glVertex3f(x, y + size, z);
		GL11.glTexCoord2f(texSize, 0);
		GL11.glVertex3f(x, y, z);
		// Oben
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(x, y + size, z + size);
		GL11.glTexCoord2f(0, texSize);
		GL11.glVertex3f(x + size, y + size, z + size);
		GL11.glTexCoord2f(texSize, texSize);
		GL11.glVertex3f(x + size, y + size, z);
		GL11.glTexCoord2f(texSize, 0);
		GL11.glVertex3f(x, y + size, z);
		// Unten
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(x, y, z + size);
		GL11.glTexCoord2f(0, texSize);
		GL11.glVertex3f(x + size, y, z + size);
		GL11.glTexCoord2f(texSize, texSize);
		GL11.glVertex3f(x + size, y, z);
		GL11.glTexCoord2f(texSize, 0);
		GL11.glVertex3f(x, y, z);
		GL11.glEnd();
	}

}
