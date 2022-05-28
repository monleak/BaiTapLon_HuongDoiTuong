package model;

public abstract class Activity {
    public abstract ActivityType getActivityType();
    public abstract int getDeltaHP();
    public abstract int getDeltaWater();
    public abstract int getDeltaCalo();
    public abstract int getDeltaSleep();

    @Override
    public String toString() {
        return "Activity{ type=" + getActivityType() + " }";
    }
}
