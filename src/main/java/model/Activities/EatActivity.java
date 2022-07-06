package model.Activities;

import model.Food;
import model.FoodInventory;

public class EatActivity extends Activity {

    @Override
    public int getDeltaWater(int maxWater) {
        return 8;
    }
    @Override
    public int getDeltaCalo(FoodInventory foodInventory) {
        return foodInventory.getTotalCalo();
    }
    @Override
    public int getDeltaSleep(int maxSleep) {
        return -2;
    }
}
