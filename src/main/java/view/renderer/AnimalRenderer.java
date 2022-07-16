package view.renderer;

import behavior.AnimalBehavior;
import behavior.DirectionBehaviorKey;
import controller.AnimalController;
import view.graphics.Sprite;
import view.graphics.SpriteAnimation;
import view.graphics.SpriteSheet;
import view.utils.Direction;
import view.utils.ImageSplitter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

import static states.GameStateManager.gp;

public class AnimalRenderer extends EntityRenderer {

    AnimalController controller;
    private final SpriteSheet<DirectionBehaviorKey> spriteSheet;
    private final SpriteAnimation<Integer> ani;
    private BufferedImage image;
    private AnimalBehavior behavior;

    public AnimalRenderer(AnimalController controller) {
        super(controller);
        this.controller = controller;
        this.spriteSheet = new SpriteSheet<>();
        this.ani = new SpriteAnimation<>();

        this.setImage();
    }

    private void setImage () {
        String image = "/chicken/chicken-sprite-sheet.png";
        System.out.println("Load image: " + image);
        ImageSplitter ci = new ImageSplitter(gp, image, 32, 32, 0);
        System.out.println( "\t>> col: " + ci.getColumns() + ", rows: " + ci.getRows());

        DirectionBehaviorKey
                EAT_UP = new DirectionBehaviorKey(Direction.UP, AnimalBehavior.EAT),
                EAT_DOWN = new DirectionBehaviorKey(Direction.DOWN, AnimalBehavior.EAT),
                EAT_RIGHT = new DirectionBehaviorKey(Direction.RIGHT,AnimalBehavior.EAT),
                EAT_LEFT = new DirectionBehaviorKey(Direction.LEFT, AnimalBehavior.EAT),
                SIT_UP = new DirectionBehaviorKey(Direction.UP, AnimalBehavior.SIT),
                SIT_DOWN = new DirectionBehaviorKey(Direction.DOWN, AnimalBehavior.SIT),
                SIT_RIGHT = new DirectionBehaviorKey(Direction.RIGHT,AnimalBehavior.SIT),
                SIT_LEFT = new DirectionBehaviorKey(Direction.LEFT, AnimalBehavior.SIT),
                PLAY_UP = new DirectionBehaviorKey(Direction.UP, AnimalBehavior.PLAY),
                PLAY_DOWN = new DirectionBehaviorKey(Direction.DOWN, AnimalBehavior.PLAY),
                PLAY_RIGHT = new DirectionBehaviorKey(Direction.RIGHT,AnimalBehavior.PLAY),
                PLAY_LEFT = new DirectionBehaviorKey(Direction.LEFT, AnimalBehavior.PLAY);

        int[] eatLeftCols   = {0, 0, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 0, 0, 0};
        int[] row1          = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        genSpriteSheet(eatLeftCols, row1, ci, AnimalBehavior.EAT);

        int[] playLeftCols  = {0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0};
        int[] row0          = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        genSpriteSheet(playLeftCols, row0, ci, AnimalBehavior.PLAY);


        int[] sitLeftCols   = {0, 1, 0, 1, 0, 1, 2, 2, 2, 3, 2, 3, 3, 3, 3, 3, 3, 3, 3, 2, 3, 2, 1, 0, 0};
        int[] row2          = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
        genSpriteSheet(sitLeftCols, row2, ci, AnimalBehavior.SIT);


        this.ani.setAnimation(spriteSheet.getSpriteArray(SIT_UP), 10);   // DEFAULT ANIMATION

    }

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

    public void updateBehavior(AnimalBehavior behavior) {
        if (behavior != this.behavior) {
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
        }
    }

    @Override
    public void update() {
        ani.update();
        image = ani.getImage().image;

        this.updateBehavior(this.controller.getBehavior());
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
