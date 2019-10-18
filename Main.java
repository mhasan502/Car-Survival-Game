import javax.swing.*;
import java.io.*;

public class Main extends JFrame {
	public Main() throws IOException {
		JFrame frame = new JFrame("Car Survival");
		GameDesign gameDesign = new GameDesign();
		frame.add(gameDesign);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,700);
		frame.setVisible(true);
		frame.setResizable(true);
	}
}
