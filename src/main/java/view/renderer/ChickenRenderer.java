package view.renderer;

import behavior.AnimalBehavior;
import behavior.DirectionBehaviorKey;
import controller.AnimalController;
import view.utils.Direction;
import view.utils.ImageSplitter;

import static states.GameStateManager.gp;

public class ChickenRenderer extends AnimalRenderer{
    public ChickenRenderer(AnimalController controller) {
        super(controller);

        this.setImage();
    }

    @Override
    protected void setImage () {
        String image = "/chicken/chicken-sprite-sheet.png";
        System.out.println("Load image: " + image);
        ImageSplitter ci = new ImageSplitter(gp, image, 32, 32, 0);
        System.out.println( "\t>> col: " + ci.getColumns() + ", rows: " + ci.getRows());

        DirectionBehaviorKey
                SIT_UP = new DirectionBehaviorKey(Direction.UP, AnimalBehavior.SIT);

        int[] eatLeftCols   = {0, 0, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 0, 0, 0};
        int[] row1          = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        genSpriteSheet(eatLeftCols, row1, ci, AnimalBehavior.EAT);
        genSpriteSheet(eatLeftCols, row1, ci, AnimalBehavior.DRINK);

        int[] playLeftCols  = {0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 0};
        int[] row0          = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        genSpriteSheet(playLeftCols, row0, ci, AnimalBehavior.PLAY);
        genSpriteSheet(playLeftCols, row0, ci, AnimalBehavior.GO_TO_DRINK);
        genSpriteSheet(playLeftCols, row0, ci, AnimalBehavior.GO_TO_EAT);

        int[] sitLeftCols   = {0, 1, 0, 1, 0, 1, 2, 2, 2, 3, 2, 3, 3, 3, 3, 3, 3, 3, 3, 2, 3, 2, 1, 0, 0};
        int[] row2          = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
        genSpriteSheet(sitLeftCols, row2, ci, AnimalBehavior.SIT);

        // set ani
        this.getAni().setAnimation(getSpriteSheet().getSpriteArray(SIT_UP), 10);   // DEFAULT ANIMATION

    }

}
