package view.entity;

import controller.ChickenController;
import model.Animals.Animal;
import model.Animals.Cat;
import states.PlayState;
import view.math.Vector2f;
import view.renderer.ChickenRenderer;

import java.util.Random;

import static states.GameStateManager.gp;

public class ChickenEntity extends AnimalEntity{
    Animal model;

    public ChickenEntity(PlayState ps) {
        super(ps);
    }

    public ChickenEntity(PlayState ps, Animal model) {
        super(ps);
        this.model = model;
        Random r = new Random();
        int x = 10 + r.nextInt(3);
        int y = 10 + r.nextInt(3);
        this.create2(new Vector2f( x * gp.titleSize, y * gp.titleSize));
    }

        public void create2 (Vector2f pos) {
        if (this.model == null) {
//            this.model = new Cat();
        }
//        System.out.println( " >model " + this.model);

        ChickenController controller = new ChickenController(
                pos,
                getPs(),
                model
        );
        this.controller = controller;
        this.renderer = new ChickenRenderer(controller);
    }

    @Override
    public void create() {

    }
}
