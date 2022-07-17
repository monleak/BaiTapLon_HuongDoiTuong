package controller;

import behavior.AnimalBehavior;
import model.Activities.DrinkActivity;
import model.Activities.EatActivity;
import model.Activities.PlayActivity;
import model.Activities.SleepActivity;
import model.Animals.Animal;
import states.PlayState;
import view.main.KeyHandler;
import view.main.MouseHandler;
import view.math.Vector2f;


import static states.GameStateManager.gp;

public abstract class AnimalController extends EntityController {

    private final Animal model;
    private AnimalBehavior prevBeh;
    int counter;
    int rand;
    public PlayState ps;

    public AnimalController(Vector2f pos, PlayState ps, Animal model) {
        super(pos, ps);

        this.ps = ps;
        this.model = model;
        this.name = "Animal";
        this.getMovement().setSpeed(1);
    }

    public Animal getModel() {
        return model;
    }

    public AnimalBehavior getPrevBeh() {
        return prevBeh;
    }

    public void setPrevBeh(AnimalBehavior prevBeh) {
        this.prevBeh = prevBeh;
    }

    public AnimalBehavior getBehavior () {
//        return getRandomBehavior();
        if (this.getModel().getActivity() instanceof EatActivity) {
            if (this.getPrevBeh() != AnimalBehavior.EAT) {
                return AnimalBehavior.GO_TO_EAT;
            } else {
                return AnimalBehavior.EAT;
            }
        } else if (this.getModel().getActivity() instanceof DrinkActivity) {
            if (this.getPrevBeh() != AnimalBehavior.DRINK) {
                return AnimalBehavior.GO_TO_DRINK;
            } else {
                return AnimalBehavior.DRINK;
            }
        } else if (this.getModel().getActivity() instanceof PlayActivity) {
            return AnimalBehavior.PLAY;
        } else if (this.getModel().getActivity() instanceof SleepActivity) {
            return AnimalBehavior.SIT;
        } else return AnimalBehavior.SIT;
    }

    public abstract void onBehaviorChange (AnimalBehavior behavior);

    @Override
    public void input(KeyHandler keyHandler, MouseHandler mouseHandler) {
        super.input(keyHandler, mouseHandler);

        AnimalBehavior behavior = getBehavior();
//        System.out.println(behavior);
//        System.out.println(this.model.getActivity());
        if (prevBeh != behavior) {
            System.out.println(behavior);
            this.prevBeh = behavior;
            onBehaviorChange(behavior);
        }
    }
}
