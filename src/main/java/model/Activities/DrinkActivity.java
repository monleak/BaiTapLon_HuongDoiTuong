package model.Activities;

import model.FoodInventory;

public class DrinkActivity extends Activity {

    @Override
    public int getDeltaWater(int maxWater) { return 16; }
    @Override
    public int getDeltaCalo(FoodInventory foodInventory) { return -6; }
    @Override
    public int getDeltaSleep(int maxSleep) {
        return -4;
    }

}
