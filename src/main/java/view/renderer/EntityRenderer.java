package view.renderer;

import controller.EntityController;

import java.awt.*;
import static states.GameStateManager.gp;

public abstract class EntityRenderer extends Renderer{

    private final EntityController controller;


    public EntityRenderer(EntityController controller) {
        super(controller);

        this.controller = controller;

    }

    public void drawWithoutImage (Graphics2D g2) {
        super.drawWithoutImage(g2);

//        g2.drawString(
//                String.valueOf(this.controller.getDirection()),
//                this.controller.getPos().getScreenX(),
//                this.controller.getPos().getScreenY() + gp.titleSize
//        );
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);

        this.drawWithoutImage(g2);

    }
}
