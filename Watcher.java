import javax.swing.*;
import java.awt.*;

public class Watcher {
    private static final String TITLE_GAME = "Car Survival";
    private static final String TITLE_GAME_OVER = "Game Over";

    private static final Dimension GAME_SIZE = new Dimension(600, 700);
    private static final Dimension GAME_OVER_SIZE = new Dimension(500, 700);

    private static final Color GAME_OVER_BG = new Color(71, 91, 122);
    private static final Color SCORE_BG = Color.WHITE;
    private static final Color SCORE_FG = Color.BLACK;
    private static final Font SCORE_FONT = new Font("New", Font.BOLD, 25);

    static final JFrame frame = new JFrame(TITLE_GAME);

    public Watcher() {
        startGame();
    }

    private static void startGame() {
        try {
            GameDesign gameDesign = new GameDesign();
            resetFrame(TITLE_GAME, GAME_SIZE, new BorderLayout(), null);
            frame.add(gameDesign, BorderLayout.CENTER);
            renderFrame();
            SwingUtilities.invokeLater(gameDesign::requestFocusInWindow);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closing(int count) {
        JButton scoreButton = createScoreButton(count);
        JButton playAgainButton = new JButton("Play Again");
        JButton exitButton = new JButton("Exit");

        setButtonProperty(playAgainButton, exitButton);
        playAgainButton.addActionListener(e -> startGame());
        exitButton.addActionListener(e -> System.exit(0));

        resetFrame(TITLE_GAME_OVER, GAME_OVER_SIZE, null, GAME_OVER_BG);
        frame.add(playAgainButton);
        frame.add(exitButton);
        frame.add(scoreButton);
        renderFrame();
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

    private static JButton createScoreButton(int count) {
        JButton scoreButton = new JButton("Score is: " + count);
        scoreButton.setBounds(0, 100, 500, 160);
        scoreButton.setBackground(SCORE_BG);
        scoreButton.setFont(SCORE_FONT);
        scoreButton.setForeground(SCORE_FG);
        return scoreButton;
    }

    private static void resetFrame(String title, Dimension size, LayoutManager layout, Color background) {
        frame.getContentPane().removeAll();
        frame.setTitle(title);
        frame.setLayout(layout);
        frame.setSize(size);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        if (background != null) {
            frame.getContentPane().setBackground(background);
        }
    }

    private static void renderFrame() {
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }
}
