package model.Activities;

import model.FoodInventory;

public class PlayActivity extends Activity {

    @Override
    public int getDeltaWater(int maxWater) {
        return -4;
    }
    @Override
    public int getDeltaCalo(FoodInventory foodInventory) {
        return -4;
    }
    @Override
    public int getDeltaSleep(int maxSleep) {
        return -10;
    }
}
