package model.Foods;

import model.Animals.*;

public abstract class Food {

    public final int calo = 1;

    public int getCalo() {
        return calo;
    }

    public void eatable(Animal animal) {
        animal.setCalo(this.getCalo());
    }

    public void cantEatBy (Animal animal) {}

    public abstract void eatBy (Chicken animal);
    public abstract void eatBy (Cat animal);
    public abstract void eatBy (Dog animal);
    public abstract void eatBy (Duck animal);
    public abstract void eatBy (Pig animal);
}
