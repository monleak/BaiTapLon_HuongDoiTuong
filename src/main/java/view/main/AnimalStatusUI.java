package view.main;

import model.Activities.Activity;
import model.Animals.Animal;
import view.utils.Animation;

import java.awt.*;

public class AnimalStatusUI {

    private GamePanel gp;
    private String[] messages;
    private boolean isShown;
    private final Font msgFont;
    private Animal animal;
    private final Animation animation;

    public AnimalStatusUI(GamePanel gp) {
        this.gp = gp;
        this.msgFont = new Font("Arial", Font.BOLD, 20);
        animation = new Animation()
                .setDelay(10)
                .setForm(0)
                .setTo(100)
                .setNumFrames(30);
    }

    private void getAnimalStatus(Animal animal) {
        messages = new String[10];
        messages[0] = "Age:   " + animal.getAge();
        messages[1] = "HP:    " + animal.getHP();
        messages[2] = "Calo:  " + animal.getCalo();
        messages[3] = "Water: " + animal.getWater();
        messages[4] = "Sleep: " + animal.getSleep();
        messages[5] = "Food:  " + animal.getNeededFood().getFood().getName();
        Activity activity = animal.getActivity();
        if (activity != null) {
            messages[6] = "Action: " + activity.getClass().getSimpleName();
        } else {
            messages[6] = "Action: None";
        }
    }

    public boolean isShown() {
        return isShown;
    }

    public void showAnimalStatus(Animal animal) {
        this.isShown = true;
        this.animal = animal;
        animation.start();
    }

    public void hideAnimalStatus () {
        this.isShown = false;
        messages = null;
        this.animal = null;
    }

    public void update() {
        if (isShown) {
            getAnimalStatus(this.animal);
            animation.update();
        }

        // do sth
    }

    public void draw (Graphics2D g2) {
        if (isShown && messages != null) {

            g2.setColor(Color.WHITE);
            g2.setFont(msgFont);
            for (int i = 0; i < messages.length; i++) {
                if (messages[i] != null)
                    g2.drawString(messages[i], 24 - 100 + animation.getValue(), 48 + i * 24);
            }

        }
    }

}
