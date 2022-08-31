package view.renderer;

import behavior.AnimalBehavior;
import behavior.DirectionBehaviorKey;
import controller.AnimalController;
import view.utils.Direction;
import view.utils.ImageSplitter;

import static states.GameStateManager.gp;

public class DogRenderer extends AnimalRenderer{

    public DogRenderer(AnimalController controller) {
        super(controller);

        this.setImage();
    }

    @Override
    protected void setImage() {
        String image = "/dog/Dog.png";
        System.out.println("Load Image: " + image);
        ImageSplitter ci = new ImageSplitter(gp, image,
                90, 58,
                0
        );
        System.out.println( "\t>> col: " + ci.getColumns() + ", rows: " + ci.getRows());

        genSpriteSheet(ci, 4, 3, AnimalBehavior.SIT);
        genSpriteSheet(ci, 1, 6, AnimalBehavior.DANG_DI_CANH_NHA);
        genSpriteSheet(ci, 0, 4, AnimalBehavior.EAT);
        genSpriteSheet(ci, 0, 4, AnimalBehavior.DRINK);
        genSpriteSheet(ci, 1, 6, AnimalBehavior.GO_TO_EAT);
        genSpriteSheet(ci, 1, 6, AnimalBehavior.PLAY);
        genSpriteSheet(ci, 2, 5, AnimalBehavior.GO_TO_EAT);

        this.getAni()
                .setAnimation(getSpriteSheet().getSpriteArray(
                        new DirectionBehaviorKey(Direction.DOWN, AnimalBehavior.PLAY)
                ), 10);
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
        getSpriteSheet().spriteArrayLike(_UP, _LEFT);
        getSpriteSheet().spriteArrayLike(_DOWN, _RIGHT);
    }


}
