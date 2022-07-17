package view.entity;

import controller.AnimalController;
import static states.GameStateManager.gp;

import model.Animals.Animal;
import model.Animals.Cat;
import states.PlayState;
import view.renderer.AnimalRenderer;
import view.math.Vector2f;

public abstract class AnimalEntity extends Entity {


    public AnimalEntity(PlayState ps) {
        super(ps);
    }

}
