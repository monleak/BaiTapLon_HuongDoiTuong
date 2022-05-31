package model.Activities;

public class SleepActivity extends Activity {
    @Override
    public ActivityType getActivityType() {
        return ActivityType.sleep;
    }

    @Override
    public int getDeltaHP() {
        return 5;
    }

    @Override
    public int getDeltaWater() {
        return -1;
    }

    @Override
    public int getDeltaCalo() {
        return -1;
    }

    @Override
    public int getDeltaSleep() {
        return +12;
    }

}
