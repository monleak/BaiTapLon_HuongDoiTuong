package view.renderer;

import behavior.AnimalBehavior;
import behavior.DirectionBehaviorKey;
import controller.AnimalController;
import view.utils.Direction;
import view.utils.ImageSplitter;

import java.awt.*;

import static states.GameStateManager.gp;

public class CatRenderer extends AnimalRenderer {

    public CatRenderer(AnimalController controller) {
        super(controller);

        this.setImage();
    }

    @Override
    protected void setImage() {
        String image = "/cat/cat.png";
        System.out.println("Load Image: " + image);
        ImageSplitter ci = new ImageSplitter(
                gp, image,
                32 , 40,
                0, 0, 0, 0,
                96, 120
        );
        System.out.println( "\t>> col: " + ci.getColumns() + ", rows: " + ci.getRows());

        AnimalBehavior behavior = AnimalBehavior.PLAY;
        genSpriteSheet(ci, 4, 8, behavior);

        genSpriteSheet(ci, 7, 5, AnimalBehavior.GO_TO_EAT);
        genSpriteSheet(ci, 5, 8, AnimalBehavior.GO_TO_DRINK);
        genSpriteSheet(ci, 6, 4, AnimalBehavior.SIT);
        genSpriteSheet(ci, 2, 4, AnimalBehavior.EAT);
        genSpriteSheet(ci, 3, 4, AnimalBehavior.DRINK);

        this.getAni().setAnimation(getSpriteSheet().getSpriteArray(
                new DirectionBehaviorKey(this.controller.getDirection(), AnimalBehavior.PLAY)
        ), 10);   // DEFAULT ANIMATION
    }

    public void genSpriteSheet (ImageSplitter ci, int row, int toCol, AnimalBehavior behavior) {
        DirectionBehaviorKey
                _UP = new DirectionBehaviorKey(Direction.UP, behavior),
                _DOWN = new DirectionBehaviorKey(Direction.DOWN, behavior),
                _RIGHT = new DirectionBehaviorKey(Direction.RIGHT,behavior),
                _LEFT = new DirectionBehaviorKey(Direction.LEFT, behavior);
        for (int i = 0; i < toCol; i++) {
            getSpriteSheet()
                    .addSprite(_UP, ci.getSubImage(row, i));
            getSpriteSheet()
                    .addSprite(_DOWN, ci.getFlipSubImage(row, i));
        }
        getSpriteSheet().spriteArrayLike(_UP, _RIGHT);
        getSpriteSheet().spriteArrayLike(_DOWN, _LEFT);
    }

    @Override
    public void draw(Graphics2D g2) {
//        super.draw(g2);

        this.drawWithoutImage(g2);

        g2.drawImage(
                getImage(),
                (int)  this.controller.getPos().getScreenX() - 32,
                (int)  this.controller.getPos().getScreenY() - 60,
                null
        );
    }
}