import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Watcher {
    static JFrame frame = new JFrame("Car Survival");

    public Watcher() throws IOException {
        GameDesign gameDesign = new GameDesign();
        frame.add(gameDesign);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,700);
        frame.setVisible(true);
        frame.setResizable(true);
    }
    public static void closing(int count) {

        frame.dispose();
        JFrame frame1 = new JFrame("Game Over");
        JButton scoreButton = new JButton("Score is: "+count);
        scoreButton.setBounds(0,100,500, 160);
        scoreButton.setBackground(new Color(255, 255, 255));
        scoreButton.setFont(new Font("New", Font.BOLD, 25));
        scoreButton.setForeground(Color.black);

        JButton button1 = new JButton("Play Again");
        JButton button2 = new JButton("Exit");
        setButtonProperty(button1,button2);

        button1.addActionListener(e -> {
            try {
                new Watcher();
            } catch (IOException ignored) {}
            frame1.dispose();
        });
        button2.addActionListener(e -> System.exit(0));

        frame1.add(button1);
        frame1.add(button2);
        frame1.add(scoreButton);
        frame1.getContentPane().setBackground(new Color(71, 91, 122));
        frame1.setSize(500, 700);
        frame1.setLayout(null);
        frame1.setVisible(true);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    static void setButtonProperty(JButton button1,JButton button2){
        button1.setBounds(130,360,230, 70);
        button1.setBackground(new Color(176, 0, 80));
        button1.setFont(new Font("Monospaced", Font.BOLD, 21));
        button1.setForeground(Color.white);

        button2.setBounds(130,440,230, 70);
        button2.setBackground(new Color(176, 0, 80));
        button2.setFont(new Font("Monospaced", Font.BOLD, 21));
        button2.setForeground(Color.white);
    }
}
