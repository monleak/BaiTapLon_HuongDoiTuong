package view.renderer.objectRenderer;

import controller.gameObject.FoodTrayController;
import view.renderer.Renderer;
import view.utils.ImageSplitter;

import java.awt.*;
import java.awt.image.BufferedImage;
import static states.GameStateManager.gp;

public class FoodTrayRenderer extends Renderer {

    private FoodTrayController controller;
    private BufferedImage image;

    public FoodTrayRenderer(FoodTrayController controller) {
        super(controller);

        this.controller = controller;

        this.setImage();
    }

    private void setImage () {
        String path = "/food/stardew-valley-food.png";
        System.out.println("Load image: " + path);
        ImageSplitter is = new ImageSplitter(gp, path, 16 ,16
                , 0);
        this.image = is.getSubImage(0, 0);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);

        g2.drawImage(
                image,
                (int) this.controller.getPos().getScreenX(),
                (int) this.controller.getPos().getScreenY(),
                null
        );
    }
}
