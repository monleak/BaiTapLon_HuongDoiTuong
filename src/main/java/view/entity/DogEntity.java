package view.entity;

import controller.CatController;
import controller.DogController;
import model.Animals.Animal;
import states.PlayState;
import view.math.Vector2f;
import view.renderer.AnimalRenderer;
import view.renderer.CatRenderer;
import view.renderer.DogRenderer;

import java.util.Random;

import static states.GameStateManager.gp;

public class DogEntity extends AnimalEntity {
    Animal model;
    public DogEntity(PlayState ps, Animal model) {
        super(ps);
        this.model = model;
        Random r = new Random();
        int x = 10 + r.nextInt(3);
        int y = 39 + r.nextInt(3);
        this.create2(new Vector2f( x * gp.titleSize, y * gp.titleSize));
    }

    public void create2 (Vector2f pos) {
        DogController controller = new DogController(
                pos,
                getPs(),
                model
        );
        this.controller = controller;
        this.renderer = new DogRenderer(controller);
    }

    @Override
    public void create() {

    }
}
