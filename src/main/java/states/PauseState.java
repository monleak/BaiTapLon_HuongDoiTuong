package states;

import view.main.GamePanel;
import view.main.KeyHandler;
import view.main.MouseHandler;

import java.awt.*;

public class PauseState extends GameState{
    private final Font font;
    private final String[] options;
    private final int RESUME = 0;
    private final int EXIT = 1;
    private boolean resumePressed;
    private boolean exitPressed;
    private int shadowCounter = 0;

    public PauseState (GameStateManager gsm) {
        super(gsm);

        font            = new Font("MeatMadness", Font.PLAIN, 48);
        options         = new String[2];
        options[RESUME] = "(Space) Resume";
        options[EXIT]   = "(X) Exit";
        this.resumePressed = false;
    }

    @Override
    public void update(double time) {

    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        if (key.spacePressed) {
            resumePressed = true;
        } else if (key.xPressed) {
            exitPressed = true;
        } else if (resumePressed) {
            gsm.pop(GameStateManager.PAUSE);
        } else if (exitPressed) {
            System.exit(0);
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        GamePanel gp = GameStateManager.gp;

        if (shadowCounter < 100)
            shadowCounter+=10 ;
        g2.setColor(new Color(3, 3, 3, shadowCounter));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.setColor(Color.WHITE);
        g2.setFont(font);
        g2.drawString("Pause", 100, 100);
        g2.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 24));
        for (int i = 0; i < options.length; i++) {
            FontMetrics metrics = g2.getFontMetrics(font);
            int x = gp.screenWidth - metrics.stringWidth(options[i]);
            int y = gp.screenHeight - metrics.getHeight();
            if(resumePressed && i == RESUME) {
                g2.fillRect(x - 100, y - 30, metrics.stringWidth(options[i]) - 120, metrics.getHeight());
                g2.setColor(Color.BLACK);
            } else if (exitPressed && i == EXIT) {
                g2.fillRect(x , y - 30, metrics.stringWidth(options[i]) - 20, metrics.getHeight());
                g2.setColor(Color.BLACK);
            } else {
                g2.setColor(Color.WHITE);
            }
            g2.drawString(options[i], x - 100 + 100 * i, y);
        }
    }
}
