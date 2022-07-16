package view.entity.obj;

import controller.gameObject.FoodTrayController;
import model.Food;
import model.FoodInventory;
import states.PlayState;
import view.main.KeyHandler;
import view.main.MouseHandler;
import view.math.Vector2f;
import view.renderer.objectRenderer.FoodTrayRenderer;

import java.awt.*;

import static states.GameStateManager.gp;

public class FoodTray {

    private FoodInventory inventory;
    private FoodTrayController controller;
    private FoodTrayRenderer renderer;

    // test constructor
    public FoodTray(PlayState ps) {
        this.inventory = new FoodInventory(
                new Food("fish", 10),
                10
        );
        this.controller = new FoodTrayController(
                ps,
                new Vector2f(30 * gp.titleSize, 10 * gp.titleSize),
                this.inventory
        );
        this.renderer = new FoodTrayRenderer(this.controller);
    }

    public FoodTray(PlayState ps, FoodInventory inventory, Vector2f pos) {
        this.inventory = inventory;
        this.controller = new FoodTrayController(
                ps,
                pos,
                this.inventory
        );
        this.renderer = new FoodTrayRenderer(this.controller);
    }

    public FoodInventory getInventory() {
        return inventory;
    }

    public FoodTrayController getController() {
        return controller;
    }

    public FoodTrayRenderer getRenderer() {
        return renderer;
    }

    public void input(KeyHandler keyH, MouseHandler mouseH) {
        this.controller.input(keyH, mouseH);
    }

    public void update()  {
        this.renderer.update();
    }

    public void draw (Graphics2D g2) {
        this.renderer.draw(g2);
    }

}
