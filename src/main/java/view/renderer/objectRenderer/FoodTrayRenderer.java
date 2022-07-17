package view.renderer.objectRenderer;

import controller.gameObject.FoodTrayController;
import view.component.FlyUpNumber;
import view.component.FlyUpNumberPool;
import view.math.AABB;
import view.math.Vector2f;
import view.renderer.Renderer;
import view.utils.ImageSplitter;

import java.awt.*;
import java.awt.image.BufferedImage;
import static states.GameStateManager.gp;

public class FoodTrayRenderer extends Renderer {

    private FoodTrayController controller;
    private BufferedImage image;
    private int amount;

    private FlyUpNumber flyUpNumber;

    public FoodTrayRenderer(FoodTrayController controller) {
        super(controller);

        this.controller = controller;
//        this.flyUpNumber = FlyUpNumberPool.getInstance().checkOut();
//        this.amount = this.controller.getFoodInventory().getAmount();

        this.setImage();
    }

    private void setImage () {
        String path = "/food/stardew-valley-food.png";
        System.out.println("Load image: " + path);
        ImageSplitter is = new ImageSplitter(gp, path, 16 ,16
                , 0);
        switch (this.controller.name) {
            case "egg":
                this.image = is.getSubImage(1, 7);
                break;
            case "meat":
                this.image = is.getSubImage(1, 11);
                break;
            case "rice":
                this.image = is.getSubImage(0, 7);
                break;
            default:
                break;
        }
    }

    @Override
    public void update() {
        if (this.amount != this.controller.getFoodInventory().getAmount()) {
            int next = this.controller.getFoodInventory().getAmount();
            int prev = this.amount;

            int range = next - prev;
            if (range > 0) {
                this.flyUpNumber = FlyUpNumberPool.getInstance().checkOut();
                this.flyUpNumber.setPos((
                        new Vector2f(
                                this.controller.getPos().getX(),
                                this.controller.getPos().getY()
                        )
                ));
                this.flyUpNumber.dispatch();
            }

            this.amount = next;
        }

        if (this.flyUpNumber != null) {
            this.flyUpNumber.update();
        }

        if (this.controller.getFch().getIsFocused()) {
            this.controller.getFoodInventory().setAmount(
                    this.controller.getCapacity()
            );
        }
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

//        FontMetrics metrics = g2.getFontMetrics();
//        int stringWidth = metrics.stringWidth(this.controller.getFoodInventory().getFood().getName());
//        g2.drawString(
//                this.controller.getFoodInventory().getFood().getName(),
//                (int) this.controller.getPos().getScreenX() + stringWidth / 2,
//                (int) this.controller.getPos().getScreenY() - 12
//        );

        // bar
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(
                (int) this.controller.getPos().getScreenX(),
                (int) this.controller.getPos().getScreenY() - 12,
                gp.titleSize,
                8,
                0,
                10
        );
        g2.setColor(Color.RED);
        g2.fillRoundRect(
                (int) this.controller.getPos().getScreenX() +2,
                (int) this.controller.getPos().getScreenY() +2 - 12,
                (int) (1.0f * this.controller.getFoodInventory().getAmount() / this.controller.getCapacity() * (gp.titleSize - 4)),
                4,
                0,
                10
        );
        g2.setColor(Color.WHITE);

        String str = (this.controller.getFoodInventory().getAmount() + " / " + this.controller.getCapacity());
        FontMetrics metrics = g2.getFontMetrics();
        int offset = (gp.titleSize - metrics.stringWidth(str)) / 2;
        int offsetY = 0;
        if (this.controller.getFch().getIsFocused()) {
            offsetY -= 30;
        }
        g2.drawString(
                str,
                (int) this.controller.getPos().getScreenX() + offset,
                (int) this.controller.getPos().getScreenY() - 12 + offsetY
        );

        // fly up
        if (this.flyUpNumber != null) {
            this.flyUpNumber.draw(g2);
        }
    }
}
