package view.main;

import model.TimeManager;
import states.GameStateManager;
import view.utils.Animation;

import java.awt.*;
import java.util.Arrays;

/**
 * In thông tin ở phía bên trái màn hình khi nhấn enter vào con vật.
 */
public class UI {
    private final AnimalStatusUI animalStatusUI;
    private final MessageUI messageUI;
    private Font font;

    public UI(GamePanel gp) {
        messageUI = new MessageUI(gp);
        animalStatusUI = new AnimalStatusUI(gp);
        this.font = new Font(Font.MONOSPACED, Font.BOLD, 12);
    }

    public AnimalStatusUI getAnimalStatusUI() {
        return animalStatusUI;
    }

    public MessageUI getMessageUI() {
        return messageUI;
    }

    public void update() {
        messageUI.update();
        animalStatusUI.update();
    }

    public void draw(Graphics2D g2) {
        messageUI.draw(g2);
        animalStatusUI.draw(g2);

        // draw time
        if (GameStateManager.modelState != null) {
            int screenHeight = GameStateManager.gp.screenHeight;
            int screenWidth = GameStateManager.gp.screenWidth;
            int speed = GameStateManager.modelState.getSimulationSpeed();
            // calc time
            TimeManager timeManager = GameStateManager.modelState.getTimeManager();
            String time = null;
            int opacity = 0;
            if (timeManager.getHours() >= 21 || timeManager.getHours() < 5) {
                time = "[Night]: " + timeManager + " (" + GameStateManager.modelState.getSimulationSpeed() + "x)";
                opacity = 100;
            } else if (timeManager.getHours() < 12) {
                time = "[Morning]: " + timeManager + " (" + GameStateManager.modelState.getSimulationSpeed() + "x)";
            } else {
                time = "[Afternoon]: " + timeManager + " (" + GameStateManager.modelState.getSimulationSpeed() + "x)";
            }

            if (timeManager.getHours() >= 5 && timeManager.getHours() < 8) {
                int offset = (timeManager.getHours() - 5 ) * 60 + timeManager.getMinutes();
                int max = 3 * 60;
                opacity = (int) ((1 - 1.0 * offset / max) * 99) + 1;
            } else if (timeManager.getHours() >= 17 && timeManager.getHours() < 21) {
                int offset = (timeManager.getHours() - 5 ) * 60 + timeManager.getMinutes();
                int max = 3 * 60;
                opacity = (int) (1.0 * offset / max * 99) + 1;
            }

            // draw string
            g2.setFont(this.font);
            g2.setColor(Color.WHITE);
            g2.drawString(
                time, screenWidth - 200,  screenHeight - 48
            );

            // mau toi khi troi dem
            g2.setColor(new Color(3, 3, 3, opacity));
            g2.fillRect(0, 0, screenWidth, screenHeight);
            g2.setColor(Color.WHITE);
        }
    }
}
