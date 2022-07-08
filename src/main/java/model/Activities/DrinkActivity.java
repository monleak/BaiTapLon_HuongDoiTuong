package model.Activities;

import model.Animals.Animal;
import model.FoodInventory;

public class DrinkActivity extends Activity {

    @Override
    public int getDeltaWater(int maxWater) {
        return 16;
    }
    @Override
    public int getDeltaCalo(int maxCalo) {
        return -2;
    }
    @Override
    public int getDeltaSleep(int maxSleep) {
        return -2;
    }

}
