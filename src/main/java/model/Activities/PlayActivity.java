package model.Activities;

import model.FoodInventory;

public class PlayActivity extends Activity {

    @Override
    public int getDeltaWater(int maxWater) {
        return -12;
    }
    @Override
    public int getDeltaCalo(FoodInventory foodInventory) {
        return -15;
    }
    @Override
    public int getDeltaSleep(int maxSleep) {
        return -10;
    }
}
