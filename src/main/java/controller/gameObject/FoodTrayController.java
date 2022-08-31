package controller.gameObject;

import controller.GameObjController;
import model.FoodInventory;
import states.PlayState;
import view.main.KeyHandler;
import view.main.MouseHandler;
import view.math.AABB;
import view.math.Vector2f;

public class FoodTrayController extends GameObjController {

    private FoodInventory foodInventory;
    private int capacity;

    public FoodTrayController(PlayState ps, Vector2f pos, FoodInventory foodInventory) {
        super(pos, ps);

        this.foodInventory = foodInventory;
        this.capacity = foodInventory.getAmount();
        this.name = this.getFoodInventory().getFood().getName();
    }

    public FoodInventory getFoodInventory() {
        return foodInventory;
    }

    public FoodTrayController(PlayState ps, AABB bounds, FoodInventory foodInventory) {
        super(bounds, ps);

        this.foodInventory = foodInventory;
        this.capacity = foodInventory.getAmount();
        this.name = "FoodTray ???";
    }

    public int getCapacity () {
        return capacity;
    }

    public int getContain () {
        return this.foodInventory.getAmount();
    }

    @Override
    public void input(KeyHandler keyHandler, MouseHandler mouseHandler) {
        // focus handler
    }

}
