package adapter;

import model.ModelState;
import states.*;
import view.main.Camera;
import view.main.GamePanel;

import java.awt.*;

/**
 * Adapter pattern
 *
 * @see <a href="https://refactoring.guru/design-patterns/adapter">Adapter</a>
 */
public class Model2GSMAdapter extends GameStateManager {
    public ModelState modelState;

    public Model2GSMAdapter(Graphics2D g2, GamePanel gp, ModelState modelState) {
        super(g2, gp);

        this.modelState = modelState;
    }

    @Override
    public void createAndSetupPlayState(Camera camera) {
        states[PLAY] = new Model2PlayStateAdapter(camera, modelState);
        this.setup();
    }

}
