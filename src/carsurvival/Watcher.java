package carsurvival;

import javax.swing.*;
import java.awt.*;

public class Watcher {
	static final JFrame frame = new JFrame("Car Survival");

	public Watcher() {
		startGame();
	}

	private static void startGame() {
		GameDesign game = new GameDesign();
		frame.getContentPane().removeAll();
		frame.setTitle("Car Survival");
		frame.setLayout(new BorderLayout());
		frame.setSize(600, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.add(game, BorderLayout.CENTER);
		frame.setVisible(true);
		frame.revalidate();
		frame.repaint();
		SwingUtilities.invokeLater(game::requestFocusInWindow);
	}

	public static void closing(int score) {
		frame.getContentPane().removeAll();
		frame.setTitle("Game Over");
		frame.setLayout(null);
		frame.setSize(500, 700);
		frame.getContentPane().setBackground(new Color(71, 91, 122));

		JButton scoreBtn = new JButton("Score is: " + score);
		scoreBtn.setBounds(0, 100, 500, 160);
		scoreBtn.setBackground(Color.WHITE);
		scoreBtn.setForeground(Color.BLACK);
		scoreBtn.setFont(new Font("New", Font.BOLD, 25));

		JButton playAgain = button("Play Again", 360);
		JButton exit = button("Exit", 440);
		playAgain.addActionListener(e -> startGame());
		exit.addActionListener(e -> System.exit(0));

		frame.add(scoreBtn);
		frame.add(playAgain);
		frame.add(exit);
		frame.setVisible(true);
		frame.revalidate();
		frame.repaint();
	}

	private static JButton button(String text, int y) {
		JButton b = new JButton(text);
		b.setBounds(130, y, 230, 70);
		b.setBackground(new Color(176, 0, 80));
		b.setForeground(Color.WHITE);
		b.setFont(new Font("Monospaced", Font.BOLD, 21));
		return b;
	}
}
