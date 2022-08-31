package view.entity;

import controller.CatController;
import controller.ChickenController;
import model.Animals.Animal;
import states.PlayState;
import view.math.Vector2f;
import view.renderer.CatRenderer;
import view.renderer.ChickenRenderer;

import java.util.Random;

import static states.GameStateManager.gp;

public class CatEntity extends AnimalEntity{
    Animal model;

    public CatEntity(PlayState ps, Animal model) {
        super(ps);
        this.model = model;
        Random r = new Random();
        int x = 39 + r.nextInt(3);
        int y = 39 + r.nextInt(3);
        this.create2(new Vector2f( x * gp.titleSize, y * gp.titleSize));
    }

    public void create2 (Vector2f pos) {
        CatController controller = new CatController(
                pos,
                getPs(),
                model
        );
        this.controller = controller;
        this.renderer = new CatRenderer(controller);
    }

    @Override
    public void create() {

    }
}
