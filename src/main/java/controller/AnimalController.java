package controller;

import behavior.AnimalBehavior;
import model.Activities.Activity;
import model.Activities.DrinkActivity;
import model.Activities.EatActivity;
import model.Animals.Animal;
import states.PlayState;
import view.effect.ILocator;
import view.main.KeyHandler;
import view.main.MouseHandler;
import view.math.Vector2f;
import view.title.TileCollision;

import javax.swing.plaf.IconUIResource;
import java.util.Random;

public class AnimalController extends EntityController implements ILocator {

    private Animal model;

    private Activity prevAct;
    int rand;
    int counter;

    public AnimalController(Vector2f pos, PlayState ps, Animal model) {
        super(pos, ps);

        this.model = model;
        this.name = "Animal";
    }

    public AnimalBehavior getBehavior () {
//        if (this.model.getActivity() instanceof EatActivity)
//            return AnimalBehavior.EAT;
//        if (this.model.getActivity() instanceof DrinkActivity)
//            return AnimalBehavior.SIT;
//        else
//            return AnimalBehavior.PLAY;
        Random random = new Random();
        counter++;
        if (counter == 300) {
            counter = 0;
            rand = random.nextInt();
        }
        switch (rand % 3) {
            case 0:
                return AnimalBehavior.EAT;
            case 1:
                return AnimalBehavior.PLAY;
            case 2:
                return AnimalBehavior.SIT;
        }
        return AnimalBehavior.EAT;
    }

    @Override
    public void input(KeyHandler keyHandler, MouseHandler mouseHandler) {

    }

}
