package model.Activities;

import model.FoodInventory;

public class SleepActivity extends Activity {

    @Override
    public int getDeltaWater(int maxWater) {
        return -8;
    }
    @Override
    public int getDeltaCalo(FoodInventory foodInventory) {
        return -4;
    }
    @Override
    public int getDeltaSleep(int maxSleep) {
        return 16;
    }
}
