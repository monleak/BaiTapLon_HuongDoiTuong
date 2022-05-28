package model;

public class Schedule implements Cloneable {
    protected Activity[] activityList;

    public Schedule () {
        this.activityList = new Activity[24];
    }

    public Schedule setActivity (int id, Activity activity) {
        try {
            activityList[id] = activity;
        } catch ( IndexOutOfBoundsException e ) {
            e.printStackTrace();
        }
        return this;
    }

    public FoodAmount getFoodConsumeInOneDay (Animal a) {
        int amount = 0;
        for (Activity act : activityList) {
            if(act.getActivityType() != ActivityType.eat)
                amount += act.getDeltaCalo();
        }
        return new FoodAmount(a.getNeededFood().getFood(), amount);
    }

    public Activity[] getActivityList() {
        return activityList;
    }
}
