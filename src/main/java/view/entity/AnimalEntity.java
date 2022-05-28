package view.entity;

import model.Animal;
import states.PlayState;
import view.effect.FocusableHandler;
import view.effect.IFocusable;
import view.main.GamePanel;
import view.utils.ConcatenatedImage;
import view.utils.Direction;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

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
}
