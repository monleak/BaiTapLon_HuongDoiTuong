package model.Activities;

import model.FoodInventory;

public class PlayActivity extends Activity {

    @Override
    public int getDeltaWater(int maxWater) {
        return -8;
    }
    @Override
    public int getDeltaCalo(int maxCalo) {
        return -8;
    }
    @Override
    public int getDeltaSleep(int maxSleep) {
        return -10;
    }
}
