import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    public static void closing(int count) throws IOException{

        frame.setVisible(false);
        JFrame frame1 = new JFrame("Game Over");
        JButton scoreButton = new JButton("Score is: "+count);
        scoreButton.setBounds(0,100,500, 160);
        scoreButton.setBackground(new Color(255, 255, 255));
        scoreButton.setFont(new Font("New", Font.BOLD, 25));
        scoreButton.setForeground(Color.black);

        JButton button1 = new JButton("Play Again");
        button1.setBounds(130,360,230, 70);
        button1.setBackground(new Color(176, 0, 80));
        button1.setFont(new Font("Monospaced", Font.BOLD, 21));
        button1.setForeground(Color.white);

        JButton button2 = new JButton("Exit");
        button2.setBounds(130,440,230, 70);
        button2.setBackground(new Color(176, 0, 80));
        button2.setFont(new Font("Monospaced", Font.BOLD, 21));
        button2.setForeground(Color.white);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Main();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                frame1.setVisible(false);
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame1.add(button1);
        frame1.add(button2);
        frame1.add(scoreButton);
        frame1.getContentPane().setBackground(new Color(71, 91, 122));
        frame1.setSize(500, 700);
        frame1.setLayout(null);
        frame1.setVisible(true);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
