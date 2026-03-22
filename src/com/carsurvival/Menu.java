package com.carsurvival;

import javax.swing.*;
import java.awt.*;

public class Menu {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = Watcher.frame;
			frame.getContentPane().removeAll();
			frame.setTitle("Car Survival");
			frame.setLayout(null);
			frame.setSize(500, 700);
			frame.getContentPane().setBackground(new Color(67, 26, 85));
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setResizable(true);

			JButton play = button("Play Game", 150);
			JButton exit = button("Exit", 260);
			play.addActionListener(e -> Watcher.startGame());
			exit.addActionListener(e -> System.exit(0));

			frame.add(play);
			frame.add(exit);
			frame.setVisible(true);
			frame.revalidate();
			frame.repaint();
		});
	}

	private static JButton button(String text, int y) {
		JButton b = new JButton(text);
		b.setBounds(130, y, 230, 70);
		b.setBackground(new Color(52, 168, 64));
		b.setForeground(Color.WHITE);
		b.setFont(new Font("Monospaced", Font.BOLD, 21));
		return b;
	}
}
