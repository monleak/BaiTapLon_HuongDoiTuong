package action;

import java.util.List;

/**
 * class Schedule
 */
public class Schedule implements Runnable {
    private final List<Action> actions;
    private final Thread t;
    public Schedule(List<Action> actions) {
        this.actions = actions;
        t = new Thread(this);
    }

    /**
     * Method: run
     * @brief Create thread run and actions.
     *
     * TODO: update animal state
     */
    @Override
    public void run() {
//        System.out.println("Started...");
        actions.forEach(a -> {
            try {
                    Thread.sleep(a.getHours()* 1000L);
                    a.perform(/* animal list */);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    public void start(/* animal list */) {
        t.start();
    }
}
