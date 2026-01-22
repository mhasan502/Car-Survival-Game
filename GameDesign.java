import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.sound.sampled.*;

public class GameDesign extends JPanel implements ActionListener, KeyListener {

	private static final Color TEAL = new Color(0, 128, 128);
	private static final Color GREY = new Color(105, 105, 105);
	private static final Color ROAD_LINE_COLOR = Color.WHITE;
	private static final Color MIDDLE_LINE_COLOR = Color.YELLOW;

	private static final int FRAME_WIDTH = 600;
	private static final int FRAME_HEIGHT = 700;
	private static final int CAR_WIDTH = 50;
	private static final int CAR_HEIGHT = 120;
	private static final int MOVE_STEP = 10;
	private static final int TIMER_DELAY_MS = 15;
	private static final int OPPOSITE_CAR_GAP = 350;
	private static final int INITIAL_OPPOSITE_CAR_COUNT = 4;
	private static final int SPEED_INCREASE_INTERVAL = 2000;
	private static final double SPEED_INCREASE_STEP = 0.5;
	private static final int OFFSCREEN_PADDING = 100;

	private static final int ROAD_CENTER_X = FRAME_WIDTH / 2;
	private static final int ROAD_HALF_WIDTH = 120;
	private static final int LEFT_ROAD_EDGE_X = ROAD_CENTER_X - ROAD_HALF_WIDTH;
	private static final int RIGHT_ROAD_EDGE_X = ROAD_CENTER_X + ROAD_HALF_WIDTH;
	private static final int LEFT_LANE_X = ROAD_CENTER_X - 90;
	private static final int RIGHT_LANE_X = ROAD_CENTER_X + 10;
	private static final int PLAYER_START_X = ROAD_CENTER_X - 95;
	private static final int PLAYER_START_Y = FRAME_HEIGHT - 100;
	private static final int PLAYER_LEFT_BOUND = ROAD_CENTER_X - 140;
	private static final int PLAYER_RIGHT_BOUND = ROAD_CENTER_X + 20;
	private static final int PLAYER_IMAGE_Y_OFFSET = 60;
	private static final int OPPOSITE_IMAGE_Y_OFFSET = 55;

	private static final String PLAYER_CAR_PATH = "/src/sprites/car.png";
	private static final String OPPOSITE_CAR_PATH = "/src/sprites/revcar.png";
	private static final String MUSIC_PATH = "/src/music/fling.wav";

	private double speed = 2.0;
	private int count = 0;

	private final Rectangle car;
	private final Random random;
	private final BufferedImage carMain;
	private final BufferedImage carOther;
	private final Timer timer;
	private final List<Rectangle> oppositeCars;
	private final Clip clip;

	public GameDesign() throws Exception {
		carMain = ImageIO.read(resolveResource(PLAYER_CAR_PATH));
		carOther = ImageIO.read(resolveResource(OPPOSITE_CAR_PATH));
		clip = initAndStartMusic();

		timer = new Timer(TIMER_DELAY_MS, this);
		random = new Random();
		oppositeCars = new ArrayList<>();
		car = new Rectangle(PLAYER_START_X, PLAYER_START_Y, CAR_WIDTH, CAR_HEIGHT);

		addKeyListener(this);
		setFocusable(true);

		for (int i = 0; i < INITIAL_OPPOSITE_CAR_COUNT; i++) {
			addOppositeCars(true);
		}

		timer.start();
	}

	private Clip initAndStartMusic() throws Exception {
		AudioInputStream audioIn = AudioSystem.getAudioInputStream(resolveResource(MUSIC_PATH));
		Clip gameClip = AudioSystem.getClip();
		gameClip.open(audioIn);
		gameClip.start();
		return gameClip;
	}

	public void addOppositeCars(boolean exist) {
		addOppositeCar(exist);
	}

	private void addOppositeCar(boolean exist) {
		int x = random.nextBoolean() ? LEFT_LANE_X : RIGHT_LANE_X;
		int y;

		if (exist) {
			y = -(oppositeCars.size() * OPPOSITE_CAR_GAP);
		} else {
			y = oppositeCars.get(oppositeCars.size() - 1).y - OPPOSITE_CAR_GAP;
		}

		oppositeCars.add(new Rectangle(x, y, CAR_WIDTH, CAR_HEIGHT));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		count++;

		for (Rectangle oppositeCar : oppositeCars) {
			oppositeCar.y += (int) speed;
		}
		if (count % SPEED_INCREASE_INTERVAL == 0) {
			speed += SPEED_INCREASE_STEP * oppositeCars.size();
		}

		for (Rectangle r : oppositeCars) {
			if (r.intersects(car)) {
				if (clip != null && clip.isRunning()) {
					clip.stop();
				}
				Watcher.closing(count);
				timer.stop();
			}
		}

		repaint();

		for (int i = 0; i < oppositeCars.size(); i++) {
			Rectangle rect = oppositeCars.get(i);
			if (rect.y + rect.height - OFFSCREEN_PADDING > FRAME_HEIGHT) {
				oppositeCars.remove(rect);
				addOppositeCar(false);
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(TEAL);
		g.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

		g.setColor(GREY);
		g.fillRect(LEFT_ROAD_EDGE_X, 0, ROAD_HALF_WIDTH * 2, FRAME_HEIGHT);

		g.setColor(ROAD_LINE_COLOR);
		g.drawLine(LEFT_ROAD_EDGE_X, 0, LEFT_ROAD_EDGE_X, FRAME_HEIGHT);
		g.drawLine(LEFT_ROAD_EDGE_X + 1, 0, LEFT_ROAD_EDGE_X + 1, FRAME_HEIGHT);
		g.drawLine(RIGHT_ROAD_EDGE_X, 0, RIGHT_ROAD_EDGE_X, FRAME_HEIGHT);
		g.drawLine(RIGHT_ROAD_EDGE_X - 1, 0, RIGHT_ROAD_EDGE_X - 1, FRAME_HEIGHT);

		g.setColor(MIDDLE_LINE_COLOR);
		g.drawLine(ROAD_CENTER_X, 0, ROAD_CENTER_X, FRAME_HEIGHT);
		g.drawLine(ROAD_CENTER_X - 1, 0, ROAD_CENTER_X - 1, FRAME_HEIGHT);
		g.drawLine(ROAD_CENTER_X - 2, 0, ROAD_CENTER_X - 2, FRAME_HEIGHT);
		g.drawLine(ROAD_CENTER_X + 1, 0, ROAD_CENTER_X + 1, FRAME_HEIGHT);
		g.drawLine(ROAD_CENTER_X + 2, 0, ROAD_CENTER_X + 2, FRAME_HEIGHT);

		g.drawImage(carMain, car.x, car.y - PLAYER_IMAGE_Y_OFFSET, null);
		for (Rectangle rect : oppositeCars) {
			g.drawImage(carOther, rect.x, rect.y - OPPOSITE_IMAGE_Y_OFFSET, null);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
			case KeyEvent.VK_UP:
				move_up();
				break;
			case KeyEvent.VK_DOWN:
				move_down();
				break;
			case KeyEvent.VK_LEFT:
				move_left();
				break;
			case KeyEvent.VK_RIGHT:
				move_right();
				break;
			default:
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) { }

	@Override
	public void keyTyped(KeyEvent e) { }

	private void move_up(){
		if (car.y - MOVE_STEP >= 0) {
			car.y -= MOVE_STEP;
		}
	}

	private void move_down(){
		if (car.y + car.height + MOVE_STEP <= FRAME_HEIGHT + 1) {
			car.y += MOVE_STEP;
		}
	}

	private void move_left(){
		if (car.x - MOVE_STEP >= PLAYER_LEFT_BOUND) {
			car.x -= MOVE_STEP;
		}
	}

	private void move_right(){
		if (car.x - MOVE_STEP <= PLAYER_RIGHT_BOUND) {
			car.x += MOVE_STEP;
		}
	}

	private URL resolveResource(String path) throws IOException {
		URL resource = getClass().getResource(path);
		if (resource != null) {
			return resource;
		}

		String relativePath = path.startsWith("/") ? path.substring(1) : path;
		File fallback = new File(relativePath);
		if (fallback.exists()) {
			return fallback.toURI().toURL();
		}

		throw new FileNotFoundException("Could not load resource: " + path);
	}
}
