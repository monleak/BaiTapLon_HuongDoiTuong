package view.object;

import states.GameState;
import states.PlayState;
import view.Graphics.SpriteSheet;
import view.ai.Node;
import view.ai.PathFinder;
import view.entity.AnimalEntity;
import view.entity.Entity;
import view.main.GamePanel;
import view.math.AABB;
import view.math.Vector2f;
import view.utils.ConcatenatedImage;
import view.utils.Direction;

import java.awt.*;
import java.util.ArrayList;

public class Fox extends AnimalEntity {
    protected PathFinder pathFinder;
    protected int currentRow;
    protected int currentColumn;
    protected int entityRow;
    protected int entityColumn;
    protected Entity entity;

    protected AABB followRange;
    protected AABB unFollowRange;

    public Fox (GamePanel gp, PlayState ps) {
        super(gp, ps);
        this.name = "Fox";

        this.collision = false;
        this.direction = Direction.DOWN;

        // todo: move to AnimalEntity class
        setImage();

        this.currentRow = 0;
        this.currentColumn = 0;
        pathFinder = new PathFinder(gp, ps);
        this.setSpeed(2);

        this.followRange = new AABB(this.pos, 5*gp.titleSize);
        this.followRange.setXOffset( (int) -2 * gp.titleSize);
        this.followRange.setYOffset((int) -2 * gp.titleSize);
        this.unFollowRange = new AABB(this.pos, 11*gp.titleSize);
        this.unFollowRange.setXOffset( (int) -5 * gp.titleSize);
        this.unFollowRange.setYOffset((int) -5 * gp.titleSize);
    }

    @Override
    public void setAction() {
        super.setAction();
    }

    @Override
    public void animate(boolean isRunning) {
//        setAnimation(0, sprite.getSpriteArray(0), 100);
    }

//    @Override
//    public void update() {
//        super.update();
//    }

    @Override
    public void setImage() {
        ConcatenatedImage ci = new ConcatenatedImage(gp,
                "/fox-sprite-sheet.png",
                32, 32, 12, 12, 6, 12
        );

        sprite = new SpriteSheet(7, 14);
        int[] framePerAction =  {5, 14, 8, 11, 5, 6, 7};
        int i = 0;
        for (int len : framePerAction) {
            for (int j = 0; j < len; j++) {
//                System.out.println("i = " + i + "j =  " + j);
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
//        if () {
            pathFinder.setNodes(
                    (int) this.pos.x,
                    (int) this.pos.y,
                    (int) entity.getBounds().getPos().x,
                    (int) entity.getBounds().getPos().y,
                    null
            );
//            this.currentColumn = currentColumn;
//            this.currentRow = currentRow;
//        }
    }

    public void unfollow () {
        entity = null;
        setAnimation(3, sprite.getSpriteArray(3), 10);
    }

    @Override
    public String[] getAnimalStatus() {
        return new String[0];
    }

    @Override
    public void update () {
//        animate(true);
        super.update();

        if (entity == null && this.followRange.colCircleBox(ps.player.getBounds()) ) {
//            System.out.println(this.followRange.distance(ps.player.getPos()));
//            System.out.println(this.followRange.collides(ps.player.getBounds()));
//            System.out.println("followowww");
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
//            pathFinder.setNodes(
//                    currentRow,
//                    currentColumn,
//                    30,
//                    30,
//                    null
//            );
                this.currentColumn = currentColumn;
                this.currentRow = currentRow;
                this.entityColumn = entityCol;
                this.entityRow = entityRow;
                follow(ps.player);
//                pathFinder.setNodes(
//                        (int) this.getBounds().getPos().x,
//                        (int) this.getBounds().getPos().y,
//                        (int) entity.getBounds().getPos().x,
//                        (int) entity.getBounds().getPos().y,
//                        null
//                );
            }
            pathFinder.search();
        }

//        System.out.println(this.getBounds().getPos().x / gp.titleSize);

//        System.out.println(pathFinder.getPathList());
        // run to goal
        if(pathFinder.getPathList().size() > 0) {
//            System.out.println(getSpeed());
            Node next = pathFinder.getPathList().get(0);
            if (this.getPos().x > next.column * gp.titleSize) {
                this.getPos().x -= getSpeed();
            } else
            if (this.getPos().x < next.column * gp.titleSize) {
                this.getPos().x += getSpeed();
            } else
            if (this.getPos().y > next.row * gp.titleSize) {
                this.getPos().y -= getSpeed();
            } else
            if (this.getPos().y < next.row * gp.titleSize) {
                this.getPos().y += getSpeed();
            }
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
//        int r = 1;
//        int C_MAX = 13;
//
//        this.counter++;
//        if(counter == C_MAX * 10){
//            counter = 0;
//        }
//        int c = counter / 10;
//        image = ci.getSubImage(r, c);
//        System.out.println(ani.getFrame());
        g2.drawImage(ani.getImage().image, (int) this.pos.getWorldVar().x, (int) this.pos.getWorldVar().y, gp.titleSize, gp.titleSize, null);
//        System.out.println(ani.getImage());
//        ArrayList<Node> pathList = pathFinder.getPathList();
//        for (Node node : pathList) {
//            Vector2f pos = new Vector2f(node.column * gp.titleSize, node.row * gp.titleSize);
//            g2.setColor(Color.DARK_GRAY);
//            g2.drawRect(
////                    ani.getImage().image,
//                    (int) pos.getWorldVar().x,
//                    (int) pos.getWorldVar().y,
//                    gp.titleSize,
//                    gp.titleSize
////                    null
//            );
//            System.out.println(node.row * gp.titleSize);
//            System.out.println((int) this.pos.getWorldVar().x);
//        }

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
