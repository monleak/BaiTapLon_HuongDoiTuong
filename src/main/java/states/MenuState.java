package states;

import view.main.GamePanel;
import view.main.KeyHandler;
import view.main.MouseHandler;

import java.awt.*;

public class MenuState extends GameState{
    private final Font font;
    private int option;
    private final String[] options;
    private final int NEW_GAME = 0;
    private final int LOAD_GAME = 1;
    private final int EXIT = 2;
    private boolean upMark;
    private boolean downMark;

    public MenuState (GameStateManager gsm) {
        super(gsm);

        font            = new Font("MeatMadness", Font.PLAIN, 48);
        option          = 0;

        options         = new String[3];
        options[NEW_GAME]   = "New game";
        options[LOAD_GAME]  = "Load game";
        options[EXIT]       = "Exit";

        this.upMark = false;
        this.downMark = false;
    }

    @Override
    public void update(double time) {

    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        if (key.enterPressed) {
            if(option == NEW_GAME) {
                gsm.addAndpop(GameStateManager.PLAY, GameStateManager.MENU);
            }
            if(option == LOAD_GAME) {
                loadGame();
            }
            if(option == EXIT) System.exit(0);
        }

        if (option < options.length - 1)
            if (key.downPressed) {
                if (!downMark) {
                    downMark = true;
                    option += 1;
                }
            }

        if (option > 0)
            if (key.upPressed) {
                if (!upMark) {
                    upMark = true;
                    option -= 1;
                }
            }

        if (!key.downPressed) {
            downMark = false;
        }
        if (!key.upPressed) {
            upMark = false;
        }
    }

    // ???
    private void newGame() {
//        gsm.addAndpop(GameStateManager.PLAY, GameStateManager.MENU);
//        gsm.pop(0);
//        System.out.println("Is active: " + gsm.isStateActive(0));
//        System.out.println(gsm.getState(0) == null);
//        gsm.add(GameStateManager.PLAY);
//        gsm.setup();
    }

    private void loadGame() {

    }

    @Override
    public void draw(Graphics2D g2) {
        GamePanel gp = GameStateManager.gp;

        g2.setColor(Color.WHITE);
        g2.setFont(font);
        FontMetrics fm = g2.getFontMetrics(font);
        int th = fm.getHeight();
        int tw = fm.stringWidth("MENU");
        g2.drawString("MENU", (gp.screenWidth - tw) / 2, (gp.screenHeight - th) / 2 - 200);

        for (int i = 0; i < options.length; i++) {
            Font optionFont = new Font(Font.MONOSPACED, Font.PLAIN, 24);
            g2.setFont(optionFont);
            fm = g2.getFontMetrics(optionFont);
            th = fm.getHeight();
            tw = fm.stringWidth(options[i]);
            g2.drawString(options[i], (gp.screenWidth - tw) / 2, (gp.screenHeight - th) / 2 - 100 + 50 * i);

            if (option == i) {
                g2.fillPolygon(
                        new int[]{(gp.screenWidth ) / 2 - 100 - 10, (gp.screenWidth) / 2 - 100 - 10, (gp.screenWidth) / 2 - 100},
                        new int[]{(gp.screenHeight - th) / 2 - 110 + 50 * i + 10, (gp.screenHeight - th) / 2 - 110 + 50 * i - 10, (gp.screenHeight - th) / 2 - 110 + 50 * i},
                        3);
            }
        }
    }
}
