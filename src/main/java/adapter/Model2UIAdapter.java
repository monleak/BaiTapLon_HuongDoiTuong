package adapter;

import model.ModelState;
import model.TimeManager;
import states.GameStateManager;
import view.main.GamePanel;
import view.main.UI;

import java.awt.*;

public class Model2UIAdapter extends UI {

    TimeManager timeManager;
    ModelState modelState;
    Font font;


    public Model2UIAdapter(GamePanel gp, ModelState modelState) {
        super(gp);
        this.timeManager = modelState.getTimeManager();
        this.modelState = modelState;
        this.font = new Font(Font.MONOSPACED, Font.BOLD, 12);
    }

    @Override
    public void draw (Graphics2D g2) {
        super.draw(g2);

        // draw time
        if (timeManager != null) {
            int screenHeight = GameStateManager.gp.screenHeight;
            int screenWidth = GameStateManager.gp.screenWidth;
            int speed = modelState.getSimulationSpeed();
            // calc time
            String time = null;
            if (timeManager.getHours() >= 21 || timeManager.getHours() < 5) {
                time = "[Night]: " + timeManager + " (" + speed + "x)";
            } else if (timeManager.getHours() < 12) {
                time = "[Morning]: " + timeManager + " (" + speed + "x)";
            } else {
                time = "[Afternoon]: " + timeManager + " (" + speed + "x)";
            }

            // draw string
            g2.setFont(this.font);
            g2.setColor(Color.WHITE);
            g2.drawString(
                    time, screenWidth - 280,  screenHeight - 48
            );

        }
    }

}
