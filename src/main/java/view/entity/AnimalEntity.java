package view.entity;

import model.Animals.Animal;
import states.PlayState;
import view.effect.FocusableHandler;
import view.effect.IFocusable;
import view.main.GamePanel;
import view.utils.Direction;

import java.awt.image.BufferedImage;

enum Posture {
    STAND, SIT, EAT, LEAP
}

public abstract class AnimalEntity extends Entity implements IFocusable {

    protected Animal animal;

    BufferedImage image;
    public FocusableHandler fch;
    public Posture posture;
    public AnimalEntity(GamePanel gp, PlayState ps) {
        super(gp, ps);
    }

    public Animal getAnimal() {
        return animal;
    }

    @Override
    public void setHovered(boolean hovered) {
        this.fch.setHovered(hovered);
    }
    @Override
    public void setFocused(boolean focused) {
        this.fch.setFocused(focused);
    }
    @Override
    public boolean getIsHovered() {
        return this.fch.getIsHovered();
    }
    @Override
    public boolean getIsFocused() {
        return this.fch.getIsFocused();
    }

    public abstract String[] getAnimalStatus ();

    @Override
    public void update() {
        {
            super.update();
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
    }
}
