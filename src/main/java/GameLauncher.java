import model.ModelState;
import view.main.GamePanel;

import javax.swing.*;

public class GameLauncher {
    public static void main (String[] args) {
        // setup window
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Game");

        // setup game model
        ModelState modelState = new ModelState(1);
        // todo: connect model state and game panel

        // setup game panel
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        // ...
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}
