package view.entity;

import states.PlayState;
import view.graphics.Sprite;
import view.graphics.SpriteAnimation;
import view.graphics.SpriteSheet;
import view.effect.IMoveable;
import view.main.GamePanel;
import view.math.Vector2f;
import view.utils.Direction;

import java.awt.*;

public abstract class Entity extends GameObject implements IMoveable {
    // locatable
    // runnable
    private int speed;
    public Direction direction;
    // run effect
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public boolean collisionOn = false;
    protected int currentAnimation;
    protected SpriteSheet sprite;
    protected SpriteAnimation ani;

    public Entity (GamePanel gp, PlayState ps) {
        super(gp, ps);
        ani = new SpriteAnimation();
//        setImage();
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public int getSpriteNum() {
        return spriteNum;
    }

    public abstract void setImage();
    public void setAction() {}

    public Vector2f getPos() {
        return pos;
    }

    // entity
    public void setAnimation(int i, Sprite[] frames, int delay) {
        currentAnimation = i;
        ani.setFrames(i, frames);
        ani.setDelay(delay);
    }
    public abstract void animate(boolean isRunning);

//    public abstract void input(MouseHandler mouseH, KeyHandler keyH);
    public void update() {
        ani.update();
    };

    public abstract void draw (Graphics2D g2);
}
