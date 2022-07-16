package view.entity;

import controller.AnimalController;
import static states.GameStateManager.gp;

import model.Animals.Animal;
import model.Animals.Cat;
import states.PlayState;
import view.renderer.AnimalRenderer;
import view.math.Vector2f;

public class AnimalEntity extends Entity {

    Animal model;

    public AnimalEntity(PlayState ps) {
        super(ps);
    }

    @Override
    public void create () {
        this.model =new Cat();
        AnimalController animalController = new AnimalController(
                new Vector2f( 15 * gp.titleSize, 15 * gp.titleSize),
                getPs(),
                model
                );
        this.controller = animalController;
        this.renderer = new AnimalRenderer(animalController);
    }

}
