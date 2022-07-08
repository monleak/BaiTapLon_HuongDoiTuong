package model.Activities;

import model.Animals.Animal;
import model.IInteractable;

public class EatActivity extends Activity {

    @Override
    public int getDeltaWater(int maxWater) {
        return 4;
    }
    @Override
    public int getDeltaCalo(int maxCalo) {
        return 0;
    }
    @Override
    public int getDeltaSleep(int maxSleep) {
        return -2;
    }
}
