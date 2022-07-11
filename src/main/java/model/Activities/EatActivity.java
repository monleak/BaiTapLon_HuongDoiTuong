package model.Activities;

import model.Animals.Animal;
import model.Food;
import model.FoodInventory;

import java.util.Objects;

public class EatActivity extends Activity implements IPrepareActivity {

    private boolean isReady = false;
    @Override
    public int getDeltaWater(int maxWater) {
        return 4;
    }
    @Override
    public int getDeltaCalo(FoodInventory foodInventory) {
//        return foodInventory.getTotalCalo() / 16;
        return 0;
    }
    @Override
    public int getDeltaSleep(int maxSleep) {
        return -2;
    }

    @Override
    public boolean isReady() {
        return isReady;
    }

    @Override
    public void onPrepareDone(Activity activity) {
        if (activity.equals(this)) {
            isReady = true;

        }
    }

    @Override
    public void onCancel () {
        isReady = false;
    }

    @Override
    public void run(Animal animal, FoodInventory foodInventory) {
        animal.eat(foodInventory);
    }
}
