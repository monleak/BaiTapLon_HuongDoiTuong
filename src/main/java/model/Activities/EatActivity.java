package model.Activities;

import model.Food;
import model.FoodInventory;

public class EatActivity extends Activity {

    @Override
    public int getDeltaWater(int maxWater) {
        return 5;
    }
    @Override
    public int getDeltaCalo(FoodInventory foodInventory) {
        return foodInventory.getTotalCalo() / 16;
    }
    @Override
    public int getDeltaSleep(int maxSleep) {
        return -5;
    }
}
