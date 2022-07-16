package view.entity;

import controller.EntityController;
import states.PlayState;
import view.renderer.Renderer;
import view.main.KeyHandler;
import view.main.MouseHandler;

import java.awt.*;

public abstract class Entity {

    protected EntityController controller;
    protected Renderer renderer;
    private final PlayState ps;


    public Entity(PlayState ps) {
        this.ps = ps;
        create();
    }

    public PlayState getPs() {
        return ps;
    }

    public abstract void create ();

    public EntityController getController () {
        return this.controller;
    }
    public Renderer getRenderer () {
        return this.renderer;
    }

    public void input(KeyHandler keyH, MouseHandler mouseH) {
        this.getController().input(keyH, mouseH);
    }
    public void update() {
        this.getRenderer().update();
    }
    public void draw (Graphics2D g2) {
        this.getRenderer().draw(g2);
    }

}
