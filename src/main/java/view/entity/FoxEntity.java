package view.entity;

import model.Animals.Animal;
import states.PlayState;
import view.graphics.SpriteSheet;
import view.ai.Node;
import view.main.GamePanel;
import view.math.AABB;
import view.utils.ImageSplitter;
import view.utils.Direction;

import java.awt.*;

public class FoxEntity extends AnimalEntity {
    protected int currentRow;
    protected int currentColumn;
    protected int entityRow;
    protected int entityColumn;
    protected Entity entity;

    protected AABB followRange;
    protected AABB unFollowRange;

    public FoxEntity(GamePanel gp, PlayState ps) {
        super(gp, ps);
        this.name = "Fox";

        this.collision = false;
        this.direction = Direction.DOWN;

        this.currentRow = 0;
        this.currentColumn = 0;
        this.setSpeed(2);

        this.followRange = new AABB(this.pos, 5*gp.titleSize);
        this.followRange.setXOffset( (int) -2 * gp.titleSize);
        this.followRange.setYOffset((int) -2 * gp.titleSize);
        this.unFollowRange = new AABB(this.pos, 11*gp.titleSize);
        this.unFollowRange.setXOffset( (int) -5 * gp.titleSize);
        this.unFollowRange.setYOffset((int) -5 * gp.titleSize);

        setImage();
    }

    public FoxEntity (GamePanel gp, PlayState ps, Animal animal) {
        this(gp, ps);
        this.animal = animal;
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

    /**
     * Follow:
     * - Đi theo, đến vị trí của 1 entity ( 1 con vật khác, người chơi, vật thể khác ) trên bản đò.
     */
    public void follow (Entity entity) {
        if (currentAnimation != 4)
            setAnimation(4, sprite.getSpriteArray(4), 10);
        this.entity = entity;
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

        // follow player if in range
        if (entity == null && this.followRange.colCircleBox(ps.player.getBounds()) ) {
            follow(ps.player);
        } else if (!this.unFollowRange.colCircleBox(ps.player.getBounds())) {
            unfollow();
        } else if (entity != null) {
            follow(ps.player);
        }

        int currentRow = (int) this.getBounds().getPos().x / gp.titleSize;
        int currentColumn = (int) this.getBounds().getPos().y / gp.titleSize;
        if (entity != null) {
            int entityRow = (int) entity.getBounds().getPos().x / gp.titleSize;
            int entityCol = (int) entity.getBounds().getPos().y / gp.titleSize;

            if ((currentColumn != this.currentColumn || currentRow != this.currentRow) ||
                    (this.entityRow != entityRow || this.entityColumn != entityCol) ) {
                this.currentColumn = currentColumn;
                this.currentRow = currentRow;
                this.entityColumn = entityCol;
                this.entityRow = entityRow;
                follow(ps.player);
            }
            pathFinder.search();
        }

        // run to goal
        if(pathFinder.getPathList().size() > 0) {
            Node next = pathFinder.getPathList().get(0);
            if (this.getPos().x > next.column * gp.titleSize) {
                this.getPos().x -= getSpeed();
                direction = Direction.LEFT;
            } else
            if (this.getPos().x < next.column * gp.titleSize) {
                this.getPos().x += getSpeed();
                direction = Direction.RIGHT;
            } else
            if (this.getPos().y > next.row * gp.titleSize) {
                this.getPos().y -= getSpeed();
                direction = Direction.UP;
            } else
            if (this.getPos().y < next.row * gp.titleSize) {
                this.getPos().y += getSpeed();
                direction = Direction.DOWN;
            }
        }
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
        super.draw(g2);

        // Vẽ vòng tròn follow range và unfollow range.
        if (entity == null) {
            g2.setColor(Color.RED);
            g2.drawOval(
                    (int) this.followRange.getPos().getWorldVar().x + (int) this.followRange.getXOffset(),
                    (int) this.followRange.getPos().getWorldVar().y + (int)  this.followRange.getXOffset(),
                    (int) this.followRange.getRadius(),
                    (int) this.followRange.getRadius()
                    );
            g2.setColor(Color.WHITE);
        } else {
            g2.drawOval(
                    (int) this.unFollowRange.getPos().getWorldVar().x + (int) this.unFollowRange.getXOffset(),
                    (int) this.unFollowRange.getPos().getWorldVar().y + (int)  this.unFollowRange.getXOffset(),
                    (int) this.unFollowRange.getRadius(),
                    (int) this.unFollowRange.getRadius()
            );
        }
    }
}
