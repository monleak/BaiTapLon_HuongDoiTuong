package model.Activities;

import model.FoodInventory;

public class DrinkActivity extends Activity implements IPrepareActivity {
    private boolean isReady = false;

    @Override
    public int getDeltaWater(int maxWater) { return 16; }
    @Override
    public int getDeltaCalo(FoodInventory foodInventory) { return -6; }
    @Override
    public int getDeltaSleep(int maxSleep) {
        return -4;
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
}
