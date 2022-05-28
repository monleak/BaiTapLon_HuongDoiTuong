package view.entity;

import states.PlayState;
import view.effect.IMoveable;
import view.main.GamePanel;
import view.utils.Direction;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity extends GameObject implements IMoveable {
    // locatable
    // runnable
    private int speed;
    protected BufferedImage[] up, down, right, left;
    public Direction direction;
    // run effect
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public boolean collisionOn = false;

    public Entity (GamePanel gp, PlayState ps) {
        super(gp, ps);
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

    public abstract void getImage();
    public void setAction() {

    }
    public void update() {
        setAction();

        collisionOn = false;
        ps.cChecker.checkTile(this);

        // if collision is false can move
        if (!collisionOn) {
            if (direction == Direction.UP)          setWorldY(getWorldY() - getSpeed());
            else if (direction == Direction.DOWN)   setWorldY(getWorldY() + getSpeed());
            else if (direction == Direction.RIGHT)  setWorldX(getWorldX() + getSpeed());
            else if (direction == Direction.LEFT)   setWorldX(getWorldX() - getSpeed());
        }

        // action
        spriteCounter++;
        if(spriteCounter > 12) {
            if(spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

    }
    public abstract void draw (Graphics2D g2);
}
