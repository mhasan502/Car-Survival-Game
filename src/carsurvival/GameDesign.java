package carsurvival;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameDesign extends JPanel implements ActionListener, KeyListener {
	private static final int W = 600, H = 700, CAR_W = 50, CAR_H = 120, STEP = 10, GAP = 350;
	private static final int LEFT_X = W / 2 - 90, RIGHT_X = W / 2 + 10;
	private static final int LEFT_BOUND = W / 2 - 140, RIGHT_BOUND = W / 2 + 20;

	private final Rectangle player = new Rectangle(W / 2 - 95, H - 100, CAR_W, CAR_H);
	private final List<Rectangle> enemies = new ArrayList<>();
	private final Random random = new Random();
	private final Timer timer = new Timer(15, this);
	private final BufferedImage playerImg = loadImage("/sprites/car.png");
	private final BufferedImage enemyImg = loadImage("/sprites/revcar.png");
	private final Clip clip = loadBackgroundClip();

	private double speed = 2;
	private int score = 0;

	public GameDesign() {
		setFocusable(true);
		addKeyListener(this);
		for (int i = 0; i < 4; i++) spawnEnemy(true);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		score++;
		for (Rectangle enemy : enemies) enemy.y += (int) speed;
		if (score % 2000 == 0) speed += 0.5 * enemies.size();

		for (Rectangle enemy : enemies) {
			if (!enemy.intersects(player)) continue;
			if (clip != null && clip.isRunning()) clip.stop();
			timer.stop();
			Watcher.closing(score);
			return;
		}

		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i).y + CAR_H - 100 <= H) continue;
			enemies.remove(i--);
			spawnEnemy(false);
		}
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int roadLeft = W / 2 - 120, roadRight = W / 2 + 120, center = W / 2;

		g.setColor(new Color(0, 128, 128));
		g.fillRect(0, 0, W, H);
		g.setColor(new Color(105, 105, 105));
		g.fillRect(roadLeft, 0, 240, H);

		g.setColor(Color.WHITE);
		g.drawLine(roadLeft, 0, roadLeft, H);
		g.drawLine(roadLeft + 1, 0, roadLeft + 1, H);
		g.drawLine(roadRight, 0, roadRight, H);
		g.drawLine(roadRight - 1, 0, roadRight - 1, H);

		g.setColor(Color.YELLOW);
		for (int x = center - 2; x <= center + 2; x++) g.drawLine(x, 0, x, H);

		g.drawImage(playerImg, player.x, player.y - 60, null);
		for (Rectangle enemy : enemies) g.drawImage(enemyImg, enemy.x, enemy.y - 55, null);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP -> player.y = Math.max(0, player.y - STEP);
			case KeyEvent.VK_DOWN -> player.y = Math.min(H - player.height + 1, player.y + STEP);
			case KeyEvent.VK_LEFT -> player.x = Math.max(LEFT_BOUND, player.x - STEP);
			case KeyEvent.VK_RIGHT -> player.x = Math.min(RIGHT_BOUND, player.x + STEP);
			default -> { }
		}
	}

	@Override
	public void keyReleased(KeyEvent e) { }

	@Override
	public void keyTyped(KeyEvent e) { }

	private void spawnEnemy(boolean initial) {
		int y = initial ? -(enemies.size() * GAP) : enemies.getLast().y - GAP;
		int x = random.nextBoolean() ? LEFT_X : RIGHT_X;
		enemies.add(new Rectangle(x, y, CAR_W, CAR_H));
	}

	private BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(resource(path));
		} catch (Exception e) {
			throw new IllegalStateException("Cannot load image " + path, e);
		}
	}

	private Clip loadBackgroundClip() {
		try (AudioInputStream in = AudioSystem.getAudioInputStream(resource("/music/fling.wav"))) {
			Clip c = AudioSystem.getClip();
			c.open(in);
			c.start();
			return c;
		} catch (Exception e) {
			return null;
		}
	}

	private URL resource(String path) throws Exception {
		URL classpath = getClass().getResource(path);
		if (classpath != null) return classpath;

		File fallback = new File("src/resources" + path);
		if (fallback.exists()) return fallback.toURI().toURL();

		throw new IllegalStateException("Missing resource: " + path);
	}
}
