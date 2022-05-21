package action;

import java.util.ArrayList;
import java.util.Collections;

// Singleton
public class ScheduleManager {
    static ScheduleManager instance;
    public Schedule dogActions;
    public Schedule catActions;

    private ScheduleManager() {
        dogActions = new Schedule( Collections.unmodifiableList(
                new ArrayList<Action>() {{
                    add(new Action(ActionType.sleep,    6));
                    add(new Action(ActionType.eat,      1));
                    add(new Action(ActionType.play,     5));
                    add(new Action(ActionType.eat,      1));
                    add(new Action(ActionType.nap,      2));
                    add(new Action(ActionType.play,     5));
                    add(new Action(ActionType.sleep,    4));
                }}));
        catActions = new Schedule(Collections.unmodifiableList(
                new ArrayList<Action>() {{
                    add(new Action(ActionType.sleep,    6));
                    add(new Action(ActionType.eat,      1));
                    add(new Action(ActionType.play,     5));
                    add(new Action(ActionType.eat,      1));
                    add(new Action(ActionType.nap,      2));
                    add(new Action(ActionType.play,     5));
                    add(new Action(ActionType.sleep,    4));
                }}));
    }

    public static ScheduleManager getInstance () {
        if (instance == null) {
            instance = new ScheduleManager();
        }
        return instance;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "dogActions=" + dogActions +
                ", catActions=" + catActions +
                '}';
    }
}
