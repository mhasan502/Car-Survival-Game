import javax.swing.*;
import java.awt.*;

public class Menu {
	private static final String TITLE = "Car Survival";
	private static final Dimension MENU_SIZE = new Dimension(500, 700);
	private static final Color MENU_BACKGROUND = new Color(67, 26, 85);
	private static final Color BUTTON_BACKGROUND = new Color(52, 168, 64);
	private static final Color BUTTON_FOREGROUND = Color.WHITE;
	private static final Font BUTTON_FONT = new Font("Monospaced", Font.BOLD, 21);

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> showMenu(Watcher.frame));
	}

	private static void showMenu(JFrame frame) {
		JButton playButton = createMenuButton("Play Game", 150);
		JButton exitButton = createMenuButton("Exit", 260);

		playButton.addActionListener(e -> {
			try {
				new Watcher();
			} catch (Exception ignored) {}
		});
		exitButton.addActionListener(e -> System.exit(0));

		frame.getContentPane().removeAll();
		frame.setTitle(TITLE);
		frame.add(playButton);
		frame.add(exitButton);
		frame.getContentPane().setBackground(MENU_BACKGROUND);
		frame.setSize(MENU_SIZE);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.revalidate();
		frame.repaint();
	}

	private static JButton createMenuButton(String text, int y) {
		JButton button = new JButton(text);
		button.setBounds(130, y, 230, 70);
		button.setBackground(BUTTON_BACKGROUND);
		button.setFont(BUTTON_FONT);
		button.setForeground(BUTTON_FOREGROUND);
		return button;
	}
}
