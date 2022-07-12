package model.Activities;

import model.FoodInventory;

public class EatActivity extends Activity implements IPrepareActivity {

    private boolean isReady = false;
    @Override
    public int getDeltaWater(int maxWater) {
        return 5;
    }
    @Override
    public int getDeltaCalo(FoodInventory foodInventory) {
//        return foodInventory.getTotalCalo() / 16;
        return 0;
    }
    @Override
    public int getDeltaSleep(int maxSleep) {
        return -5;
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
