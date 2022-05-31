package model.Activities;

public class EatActivity extends Activity {
    @Override
    public ActivityType getActivityType() {
        return ActivityType.eat;
    }
    @Override
    public int getDeltaWater() {
        return 0;
    }
}
