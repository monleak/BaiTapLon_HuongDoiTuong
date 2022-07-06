package view.entity;

import states.PlayState;
import view.ai.PathFinder;
import view.graphics.Sprite;
import view.graphics.SpriteAnimation;
import view.graphics.SpriteSheet;
import view.main.GamePanel;
import view.title.TileCollision;
import view.utils.Direction;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity extends GameObject{
    private int speed;
    BufferedImage image;

    public Direction direction;
    protected int currentAnimation;
    protected SpriteSheet sprite;
    protected SpriteAnimation ani;
    protected PathFinder pathFinder;
    protected TileCollision tc;

    /**
     * WARNING: DO NOT USE THIS
     * setImage();
     * call an overridable (public or protected) method in constructor can cause error.
     *
     * There are a few more restrictions that a class must obey to allow inheritance. Constructors must not invoke overridable methods, directly or indirectly. If you violate this rule, program failure will result. The superclass constructor runs before the subclass constructor, so the overriding method in the subclass will be invoked before the subclass constructor has run. If the overriding method depends on any initialization performed by the subclass constructor, the method will not behave as expected.
     * @see <a href="https://stackoverflow.com/questions/3404301/whats-wrong-with-overridable-method-calls-in-constructors">Stackoverflow</a>
     */
    public Entity (GamePanel gp, PlayState ps) {
        super(gp, ps);
        ani = new SpriteAnimation();
        pathFinder = new PathFinder(gp, ps);
        tc = new TileCollision(this);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Direction getDirection() {
        return direction;
    }

    /**
     * Entity.setImage:
     * <ul>
     *     <li>
     *         Override để set sprite sheet image.
     *     </li>
     *     <li>
     *         Gọi setImage ở constructor của lớp con. (KO GỌI Ở LỚP CHA (AnimalEntity) !!!)
     *     </li>
     * </ul>
     */
    public abstract void setImage();

    /**
     * Entity.setAction:
     * <ul><li>
     * random hươngs di chuyển và tư thế cho con vật
     * </li></ul>
     */
    public void setAction() {}


    // entity
    public void setAnimation(int i, Sprite[] frames, int delay) {
        currentAnimation = i;
        ani.setFrames(i, frames);
        ani.setDelay(delay);
    }

    /**
     * Entity.anmage:
     * <ul>
     *     <li>
     *         gọi setAnimation khi thay đổi hướng chạy, tư thế (đứng, nằm)...
     *     </li>
     * </ul>
     * Ex:
     * <pre>
     * if (direction == Direction.DOWN) {
     *     setAnimation(RUN_DOWN, sprite.getSpriteArray(RUN_DOWN), 10);
     * }
     * </pre>
     * @see view.entity.Player#animate(boolean)
     */
    public abstract void animate(boolean isRunning);

    /**
     * AnimalEntity.checkCollisionAndMove:
     * <p>
     * Check va chạm, nếu ko va vòa tường thì di chuyển theo thược tính dierection.
     * </p>
     */
    public void checkCollisionAndMove(Direction direction, int speed) {
        if (direction == Direction.UP) {
            if (!tc.collisionTile(0, - speed)) {
                pos.addY(-speed);
            }
        }
        else if (direction == Direction.DOWN) {
            if (!tc.collisionTile(0, speed)) {
                pos.addY(speed);
            }
        }
        else if (direction == Direction.RIGHT) {
            if (!tc.collisionTile(speed, 0)) {
                pos.addX(speed);
            }
        }
        else if (direction == Direction.LEFT) {
            if (!tc.collisionTile(-speed, 0)) {
                pos.addX(-speed);
            }
        }
    }
//    public abstract void input(MouseHandler mouseH, KeyHandler keyH);

    /**
     * Entity.update:
     * <ul>
     *     <li>
     *         update animation.
     *     </li>
     * </ul>
     */
    public void update() {
        ani.update();
    };
    /**
     * Entity.draw:
     * <ul>
     *   <li>
     *     Vẽ hình con vật ( set thís.image )
     *   </li>
     *   <li>
     *       Khi override cần gọi super.draw(g2) để không mất hình con vật.
     *   </li>
     * </ul>
     */
    public void draw (Graphics2D g2) {
        // draw this.image
        g2.drawImage(image, (int) this.pos.getWorldVar().x, (int) this.pos.getWorldVar().y, gp.titleSize, gp.titleSize, null);
    }
}
