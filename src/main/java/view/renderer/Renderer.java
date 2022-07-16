package view.renderer;


import controller.GameObjController;

import java.awt.*;

import static states.GameStateManager.gp;

public abstract class Renderer {

    GameObjController controller;

    public Renderer(GameObjController controller) {
        this.controller = controller;
    }

    public void draw (Graphics2D g2) {
//        this.controller.getBounds().render(g2);
        g2.drawRect(
                (int) this.controller.getPos().getScreenX(),
                (int) this.controller.getPos().getScreenY(),
                gp.titleSize,
                gp.titleSize
        );

        this.controller.getFch().draw(
                g2,
                this.controller.getBounds(),
                this.controller.name
        );
    }

    public abstract void update ();
}
