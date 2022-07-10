package model;

/**
 * Sync time giữa model và view
 * <br/>
 * timeManager.update() được gọi trong ModelState#update()
 */
public class TimeManager {
    private int days;
    private int hours;
    private int minutes;
    public static final int MAX_DAY = 30;
    public static final int MAX_HOUR = 24;
    public static final int MAX_MINUTE = 60;

    public TimeManager () {}

    public TimeManager(int days, int hours, int minutes) {
        this.days = days;
        this.hours = hours;
        this.minutes = minutes;
    }

    public void update() {
        this.minutes++;
        if (this.minutes == MAX_MINUTE) {
            this.minutes = 0;
            this.hours++;
            if (this.hours == MAX_HOUR) {
                this.hours = 0;
                this.days++;
                if (this.days == MAX_DAY) {
                    this.days = 0;
                }
            }
        }
    }

    public int getDays() {
        return days;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    @Override
    public String toString() {
        return " | " + days +
                " / " + hours +
                ":" + minutes +
                ' ';
    }
}
