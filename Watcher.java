import javax.swing.*;
import java.awt.*;

public class Watcher {
    static JFrame frame = new JFrame("Car Survival");

    public Watcher() {
        startGame();
    }

    private static void startGame() {
        try {
            GameDesign gameDesign = new GameDesign();
            frame.getContentPane().removeAll();
            frame.setLayout(new BorderLayout());
            frame.add(gameDesign, BorderLayout.CENTER);

            frame.setTitle("Car Survival");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600,700);
            frame.setResizable(true);
            frame.setVisible(true);
            frame.revalidate();
            frame.repaint();
            SwingUtilities.invokeLater(gameDesign::requestFocusInWindow);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closing(int count) {
        JButton scoreButton = new JButton("Score is: "+count);
        scoreButton.setBounds(0,100,500, 160);
        scoreButton.setBackground(new Color(255, 255, 255));
        scoreButton.setFont(new Font("New", Font.BOLD, 25));
        scoreButton.setForeground(Color.black);

        JButton button1 = new JButton("Play Again");
        JButton button2 = new JButton("Exit");
        setButtonProperty(button1,button2);

        button1.addActionListener(e -> {
            startGame();
        });
        button2.addActionListener(e -> System.exit(0));

        frame.getContentPane().removeAll();
        frame.setTitle("Game Over");
        frame.setLayout(null);
        frame.add(button1);
        frame.add(button2);
        frame.add(scoreButton);
        frame.getContentPane().setBackground(new Color(71, 91, 122));
        frame.setSize(500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
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
