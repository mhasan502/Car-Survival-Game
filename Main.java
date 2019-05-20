import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import javax.swing.*;
import java.io.*;

public class Main extends JFrame {
	public Main() throws IOException {

		File a = new File("src\\files\\fling.wav");
		FileInputStream is = new FileInputStream(a);
		AudioStream as = new AudioStream(is);

		JFrame frame = new JFrame("Car Survival");
		GameDesign gameDesign = new GameDesign();
		frame.add(gameDesign);
		AudioPlayer.player.start(as);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600,700);
		frame.setVisible(true);
		frame.setResizable(true);
	}

}
