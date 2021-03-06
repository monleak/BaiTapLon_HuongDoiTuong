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

    public UI(GamePanel gp) {
        messageUI = new MessageUI(gp);
        animalStatusUI = new AnimalStatusUI(gp);
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
    }

}
