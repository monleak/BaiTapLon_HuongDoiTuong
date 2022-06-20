package model.Activities;

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
        return -4;
    }

    @Override
    public int getDeltaCalo() {
        return -4;
    }

    @Override
    public int getDeltaSleep() {
        return -2;
    }
}
