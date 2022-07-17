package view.renderer;

import behavior.AnimalBehavior;
import behavior.DirectionBehaviorKey;
import controller.AnimalController;
import view.component.FlyUpNumberPool;
import view.graphics.Sprite;
import view.graphics.SpriteAnimation;
import view.graphics.SpriteSheet;
import view.utils.Direction;
import view.utils.ImageSplitter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

import static states.GameStateManager.gp;

public abstract class AnimalRenderer extends EntityRenderer {

    AnimalController controller;
    private final SpriteSheet<DirectionBehaviorKey> spriteSheet;
    private final SpriteAnimation<Integer> ani;
    private BufferedImage image;
    private AnimalBehavior behavior;
    private Direction direction;

    public AnimalRenderer(AnimalController controller) {
        super(controller);
        this.controller = controller;
        this.spriteSheet = new SpriteSheet<>();
        this.ani = new SpriteAnimation<>();

        // TODO: IN SUB CLASS
//        this.setImage();
    }

    protected abstract void setImage ();

    public void genSpriteSheet (int[] cols, int[] rows, ImageSplitter ci, AnimalBehavior behavior) {
        DirectionBehaviorKey
                _UP = new DirectionBehaviorKey(Direction.UP, behavior),
                _DOWN = new DirectionBehaviorKey(Direction.DOWN, behavior),
                _RIGHT = new DirectionBehaviorKey(Direction.RIGHT,behavior),
                _LEFT = new DirectionBehaviorKey(Direction.LEFT, behavior);
        for (int i = 0; i < Math.min(cols.length, rows.length); i++) {
            spriteSheet.addSprite(_UP, ci.getSubImage(rows[i], cols[i]));
            spriteSheet.addSprite(_DOWN, ci.getFlipSubImage(rows[i], cols[i]));
        }
        spriteSheet.spriteArrayLike(_UP, _LEFT);
        spriteSheet.spriteArrayLike(_DOWN, _RIGHT);
    }

    public AnimalBehavior getBehavior() {
        return behavior;
    }

    public AnimalController getController() {
        return controller;
    }

    public SpriteSheet<DirectionBehaviorKey> getSpriteSheet() {
        return spriteSheet;
    }

    public SpriteAnimation<Integer> getAni() {
        return ani;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void updateBehaviorOrDirection(AnimalBehavior behavior, Direction direction) {
        if (behavior != this.behavior || this.direction != direction) {
            List<Sprite> spriteList = spriteSheet.getSpriteArray(new DirectionBehaviorKey(
                    this.controller.getDirection(), behavior
            ));
            System.out.println(this.controller.getBehavior());
            if (spriteList != null)
                this.ani.setAnimation(
                        spriteList
                        , 10
                );
            this.behavior = behavior;
            this.direction = direction;
        }

    }

    @Override
    public void update() {
        ani.update();
        image = ani.getImage().image;

        this.updateBehaviorOrDirection(this.controller.getBehavior(), this.controller.getDirection());
    }

    public void drawWithoutImage (Graphics2D g2) {
        super.drawWithoutImage(g2);

        g2.drawString(String.valueOf(this.controller.getBehavior()),
                (int) this.controller.getPos().getScreenX(),
                (int) this.controller.getPos().getScreenY() + gp.titleSize + 10
        );

        this.controller.getPathFinder().draw(g2);


        // bar
        int x = (int) this.controller.getPos().getScreenX();
        int y = (int) this.controller.getPos().getScreenY();
        int BAR_HEIGHT = 12;
        float HP = 1.0f * this.controller.getModel().getHP() / this.controller.getModel().getMaxHP();
        float sleep = 1.0f * this.controller.getModel().getSleep() / this.controller.getModel().getMaxSleep();
        float water = 1.0f * this.controller.getModel().getWater() / this.controller.getModel().getMaxWater();
        float calo = 1.0f * this.controller.getModel().getCalo() / this.controller.getModel().getMaxCalo();
        g2.setColor(Color.WHITE);
        g2.fillRect(
                x, y - BAR_HEIGHT,
                gp.titleSize, BAR_HEIGHT
        );
        g2.setColor(Color.RED);
        g2.fillRect(
                x + 1, y - BAR_HEIGHT + 1,
                (int) ((gp.titleSize - 2) * HP)
                , 4
        );
        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(
                x + 1, y - BAR_HEIGHT + 5,
                (int) ((gp.titleSize - 2) * calo)
                , 2
        );
        g2.setColor(Color.YELLOW);
        g2.fillRect(
                x + 1, y - BAR_HEIGHT + 7,
                (int) ((gp.titleSize - 2) * calo)
                , 2
        );
        g2.setColor(Color.BLACK);
        g2.fillRect(
                x + 1, y - BAR_HEIGHT + 9,
                (int) ((gp.titleSize - 2) * calo)
                , 2
        );
        g2.setColor(Color.WHITE);
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);

        drawWithoutImage(g2);

        g2.drawImage(
                image,
                (int) this.controller.getPos().getScreenX(),
                (int) this.controller.getPos().getScreenY(),
                null
        );

    }
}
