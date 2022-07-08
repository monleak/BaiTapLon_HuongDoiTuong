package model.Activities;

import model.FoodInventory;

public class SleepActivity extends Activity {

    @Override
    public int getDeltaWater(int maxWater) {
        return -2;
    }
    @Override
    public int getDeltaCalo(int maxCalo) {
        return -2;
    }
    @Override
    public int getDeltaSleep(int maxSleep) {
        return 16;
    }
}
