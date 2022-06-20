package view.entity;

import states.PlayState;
import view.graphics.SpriteSheet;
import view.ai.Node;
import view.ai.PathFinder;
import view.main.GamePanel;
import view.math.AABB;
import view.utils.ImageSplitter;
import view.utils.Direction;

import java.awt.*;

public class TODOEntity extends AnimalEntity {
    protected PathFinder pathFinder;
    protected int currentRow;
    protected int currentColumn;
    protected int entityRow;
    protected int entityColumn;
    protected Entity entity;

    protected AABB followRange;
    protected AABB unFollowRange;

    public FoxEntity(GamePanel gp, PlayState ps) {
        super(gp, ps);

        setImage();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAction() {
        super.setAction();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void animate(boolean isRunning) {
//        setAnimation(0, sprite.getSpriteArray(0), 100);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setImage() {
        System.out.println("Set image: /fox-sprite-sheet.png" + sprite);
        ImageSplitter ci = new ImageSplitter(gp,
                "/fox-sprite-sheet.png",
                32, 32, 12, 12, 6, 12
        );
        System.out.println( "col: " + ci.getColumns() + "rows: " + ci.getRows());

        sprite = new SpriteSheet(7, 14);
        int[] framePerAction =  {5, 14, 8, 11, 5, 6, 7};
        int i = 0;
        for (int len : framePerAction) {
            for (int j = 0; j < len; j++) {
                sprite.addSprite(i, ci.getSubImage(i, j));
            }
            i++;
        }
        setAnimation(3, sprite.getSpriteArray(3), 10);
    }

    public void follow (Entity entity) {
        if (currentAnimation != 4)
            setAnimation(4, sprite.getSpriteArray(4), 10);
        this.entity = entity;
        int entityRow = (int) entity.getBounds().getPos().x;
        int entityCol = (int) entity.getBounds().getPos().y / gp.titleSize;
        pathFinder.setNodes(
                (int) this.pos.x,
                (int) this.pos.y,
                (int) entity.getBounds().getPos().x,
                (int) entity.getBounds().getPos().y,
                null
        );
    }

    public void unfollow () {
        entity = null;
        if (sprite != null) // if setImage not error
            setAnimation(3, sprite.getSpriteArray(3), 10);
    }

    @Override
    public String[] getAnimalStatus() {
        return new String[0];
    }

    /**
     * {@inheritDoc}
     *
     * FoxEntity.update:
     * <ul>
     *     <li>
     *         Update image
     *     </li>
     *     <li>
     *         Update follow and unfollow range collision
     *     </li>
     *     <li>
     *         If follow: find path and go to
     *     </li>
     * </ul>
     */
    @Override
    public void update () {
        super.update();
        image = ani.getImage().image;
    }

    /**
     * {@inheritDoc}
     *
     * FoxEntity.draw:
     * <ul>
     *     <li>
     *         Vẽ vòng tròn follow range và unfollow range (debug).
     *     </li>
     * </ul>
     */
    @Override
    public void draw(Graphics2D g2) {
    }
}

