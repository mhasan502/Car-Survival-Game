import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Menu {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Car Survival");
		JButton button1 = new JButton("Play Game");
		button1.setBounds(130,150,230, 70);
		button1.setBackground(new Color(52, 168, 64));
		button1.setFont(new Font("Monospaced", Font.BOLD, 21));
		button1.setForeground(Color.white);


		JButton button2 = new JButton("Exit");
		button2.setBounds(130,260,230, 70);
		button2.setBackground(new Color(52, 168, 64));
		button2.setFont(new Font("Monospaced", Font.BOLD, 21));
		button2.setForeground(Color.white);

		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new Main();
				} catch (IOException ex) {}
				frame.setVisible(false);
			}
		});
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		frame.add(button1);
		frame.add(button2);
		frame.getContentPane().setBackground(new Color(67, 26, 85));
		frame.setSize(500,700);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
	}
}
