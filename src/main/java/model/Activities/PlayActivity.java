package model.Activities;

import model.Activity;
import model.ActivityType;

public class PlayActivity extends Activity {
    @Override
    public ActivityType getActivityType() {
        return ActivityType.play;
    }

    @Override
    public int getDeltaHP() {
        return 0;
    }

    @Override
    public int getDeltaWater() {
        return 0;
    }

    @Override
    public int getDeltaCalo() {
        return 0;
    }

    @Override
    public int getDeltaSleep() {
        return 0;
    }
}
