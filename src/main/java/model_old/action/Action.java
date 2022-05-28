package model_old.action;

public class Action {
    private final ActionType actionType;
    private final int hours;

    public Action(ActionType actionType, int hours) {
        this.actionType = actionType;
        this.hours = hours;
    }

    public Action(ActionType actionType) {
        this.actionType = actionType;
        hours = 1;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public int getHours() {
        return hours;
    }

    public void perform (/* Animal a */) {
        System.out.println("Perform: " + this.toString());
        // TODO: update animal state
        // NOTE: Heavy calculate
        double a = 0;
        for (int i = 0; i < 100; i++) {
            a += Math.exp(i);
            for (int j = 0; j < 10000; j++) {
                a -= j;
                for (int k = 1; k < 100; k++) {
                    a -= k;
                }
                // test
                Test2.commonNumber++;
            }
        }
        System.out.println(a);
    }

    @Override
    public String toString() {
        return "Action{" +
                "actionType=" + actionType +
                ", hours=" + hours +
                '}';
    }
}
