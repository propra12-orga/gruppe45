package render;

import game.Game;
import game.Level;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class HUD {

	final private int STATS_POS_X = -400;
	final private int STATS_POS_Y = 300;

	final private int CHAT_INPUT_POS_X = -400;
	final private int CHAT_INPUT_POS_Y = -200;

	final private int CHAT_POS_X = -400;
	final private int CHAT_POS_Y = 300;

	// TODO Ich weiss nicht warum hier 128 funktioniert, logisch waere doch 96,
	// fuerdie Anzahl der Buchstaben??..
	final private float LETTER_WIDTH = 1 / 128f;
	final private float LETTER_HEIGHT = 1f;
	private Texture[] numbers = new Texture[10];
	private Texture texEgoview[] = new Texture[Objects.THEME_COUNT];
	private Texture texFont;
	private Texture texProlog;
	String stats = "";
	final static public int TABSIZE = 9;

	private boolean showStats = false;
	private boolean showChat = true;
	private boolean showChatInput = false;

	private String chatInput;
	private String chatLog[] = new String[10];

	HUD() {
		String tmpThemeName = "";
		for (int i = 0; i < chatLog.length; i++) {
			chatLog[i] = "";
		}
		// this.themeSelection = Byte.parseByte(Game.options[4]);
		try {
			for (byte i = Objects.THEME_EARTH; i < Objects.THEME_COUNT; i++) {
				switch (i) {
				// Normale Welt
				case Objects.THEME_EARTH:
					tmpThemeName = "earth";
					break;
				case Objects.THEME_SPACE:
					tmpThemeName = "space";
					break;
				case Objects.THEME_SOCCER:
					tmpThemeName = "soccer";
					break;
				}
				texEgoview[i] = TextureLoader.getTexture("PNG",
						new FileInputStream("res/overlay/" + tmpThemeName + "/self.png"));
			}
			texFont = TextureLoader.getTexture("PNG", new FileInputStream("res/overlay/font.png"));
			texProlog = TextureLoader.getTexture("PNG", new FileInputStream("res/overlay/prolog.png"));
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
		DrawProlog();
		if (!(Level.inMenu)) {
			DrawEgoview();
			DrawHealthPoints();
			if (showStats) {
				DrawStats();
			}
			if (showChat) {
				DrawChat();
			}
			if (showChatInput) {
				DrawChatInput();
			}
		}
	}

	public void setShowStats(boolean showStats) {
		this.showStats = showStats;
	}

	public void setShowChat(boolean showChat) {
		this.showChat = showChat;
	}

	public void setShowChatInput(boolean showChatInput) {
		this.showChatInput = showChatInput;
		chatInput = "";
	}

	private void DrawHealthPoints() {
		String healthPoints = Integer.toString(Game.getPlayer().getHealthPoints());
		GL11.glColor3f(1, 0, 0);
		for (int i = 0; i < healthPoints.length(); i++) {
			printLetter(healthPoints.charAt(i), -400 + 40 * i, -300, 40);
		}
	}

	private void DrawEgoview() {
		texEgoview[Objects.themeSelection].bind();
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

	private void DrawStats() {
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glColor4f(0.1f, 0.1f, 0.1f, 0.7f);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex3f(-Window.width / 2 + 10, -Window.height + 10 / 2, 0);
		GL11.glVertex3f(-Window.width / 2 + 10, Window.height - 10 / 2, 0);
		GL11.glVertex3f(Window.width / 2 - 10, Window.height - 10 / 2, 0);
		GL11.glVertex3f(Window.width / 2 - 10, -Window.height + 10 / 2, 0);
		GL11.glEnd();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glColor3f(1, 1, 1);
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
				printLetter(stats.charAt(k), pos * 32 + STATS_POS_X, line * 32 + STATS_POS_Y, 32);
			}
		}
	}

	private void DrawChatInput() {
		String input = ":" + chatInput + "|";
		GL11.glColor3f(1, 1, 1);
		for (int k = 0; k < input.length(); k++) {
			printLetter(input.charAt(k), k * 20 + CHAT_INPUT_POS_X, CHAT_INPUT_POS_Y, 20);
		}
	}

	private void DrawChat() {
		for (int i = 0; i < chatLog.length; i++) {
			for (int j = 0; j < chatLog[i].length(); j++) {
				GL11.glColor4f(1, 1, 1, (float) (chatLog.length - i) / (chatLog.length));
				printLetter(chatLog[i].charAt(j), j * 20 - 400, i * 20 - 170, 20);
			}
		}
	}

	private void DrawProlog() {
		int versch = Game.getThreadBomb().prologTime;
		versch /= 5;
		versch -= 200;
		texProlog.bind();
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(0, 1f);
		GL11.glVertex3f(-Window.width / 2, -Window.height / 2 + versch, 0);
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(-Window.width / 2, Window.height / 2 + versch, 0);
		GL11.glTexCoord2f(1f, 0);
		GL11.glVertex3f(Window.width / 2, Window.height / 2 + versch, 0);
		GL11.glTexCoord2f(1f, 1f);
		GL11.glVertex3f(Window.width / 2, -Window.height / 2 + versch, 0);
		GL11.glEnd();
	}

	public void addToChatInput(char letter) {
		chatInput += letter;
	}

	public void clearChatInput() {
		chatInput = "";
	}

	public void addToChatLog(String chat) {
		for (int i = (chatLog.length - 1); i > 0; i--) {
			chatLog[i] = chatLog[i - 1];
		}
		chatLog[0] = chat;
		Game.getThreadBomb().resetChatTime();
	}

	public void addToChatLog() {
		addToChatLog(chatInput);
	}

	public String getChatInput() {
		return chatInput;
	}

	private void printLetter(char code, int x, int y, int size) {
		code -= 32;
		texFont.bind();
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(code * LETTER_WIDTH, LETTER_HEIGHT);
		GL11.glVertex3f(x, y, 0);
		GL11.glTexCoord2f(code * LETTER_WIDTH, 0);
		GL11.glVertex3f(x, y + size, 0);
		GL11.glTexCoord2f((code + 1) * LETTER_WIDTH, 0);
		GL11.glVertex3f(x + size, y + size, 0);
		GL11.glTexCoord2f((code + 1) * LETTER_WIDTH, LETTER_HEIGHT);
		GL11.glVertex3f(x + size, y, 0);
		GL11.glEnd();
	}
}
