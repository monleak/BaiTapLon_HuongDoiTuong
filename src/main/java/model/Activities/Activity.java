package model.Activities;

public abstract class Activity {
    public abstract ActivityType getActivityType();
    public int getDeltaHP() {
        return 0;
    }
    public int getDeltaWater() {
        return 0;
    }
    public int getDeltaCalo() {
        return 0;
    }
    public int getDeltaSleep() {
        return 0;
    }

    @Override
    public String toString() {
        return "Activity{ type=" + getActivityType() + " }";
    }
}
