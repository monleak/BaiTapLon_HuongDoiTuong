package model.Foods;

import model.Animals.*;

public class Meat extends Food{
    public int getCalo() {
        return calo;
    }

    public void eatBy (Chicken animal) {
        eatable(animal);
    }
    public void eatBy (Cat animal) {}
    public void eatBy (Dog animal) {
        eatable(animal);
    }
    public void eatBy (Duck animal) {}
    public void eatBy (Pig animal) {
        eatable(animal);
    }
}
