package model.Animals;

import model.Activities.*;
import model.Foods.Food;

import java.util.Random;

/**
 * Con vật:
 *
 * - Mỗi con vật có các thuộc tính HP, water, calo, age, sleep
 *                              và maxHP, max...
 * - Khi HP = 0 thì chết.
 * - Thức ăn: neededFood
 * - Lịch trình: schedule
 * - isSick, isHungry, isThirsty: để kiểm tra đk ốm, đói, khát.
 * - drink, eat được gọi khi hành động là ăn, uống.
 * - nextActivity: h.động tiếp theo
 * - growUp: sau 1 khoảng thời gian con vật sẽ lớn lên.
 *
 * - NOTE: life: được gọi trong animalEntity.update()
 * - TODO: tính toán thức ăn khi hoạt động là random ???
 */
public abstract class Animal {
    private int HP;
    protected int maxHP;
    private int water;
    protected int maxWater;
    private int calo;
    protected int maxCalo;
    private int age;
    private int maxAge;
    private int sleep;
    protected int maxSleep;
    private boolean isDead = false;
    protected Activity activity;
    protected Schedule schedule;

    // Getter and protected setter
    public int getHP() {
        return HP;
    }
    protected void setHP(int HP) {
        int min = Math.min(HP, maxHP);
        if (min > 0)
            this.HP = min;
        else {
            this.HP = 0;
            isDead = true;
        }
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getWater() {
        return water;
    }
    public int getMaxWater() {
        return maxWater;
    }
    protected void setWater(int water) {
        int min = Math.min(water, maxWater);
        if(min > 0) {
            this.water = min;
        } else if (min < 0) {
            this.setHP(this.getMaxHP() - 2);
        } else {
            this.water = 0;
        }
    }
    public int getCalo() {
        return calo;
    }
    public int getMaxCalo() {
        return maxCalo;
    }
    public void setCalo(int calo) {
        int min = Math.min(calo, maxCalo);
        if (min > 0) {
            this.calo = min;
        } else if (min < 0) {
            this.setHP(this.getMaxHP() - 2);
        }else {
            this.calo = 0;
        }
    }
    public int getAge() {
        return age;
    }
    public int getMaxAge() {
        return maxAge;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getMaxSleep() {
        return maxSleep;
    }
    public int getSleep() {
        return sleep;
    }
    public void setSleep(int sleep) {
        int min = Math.min(sleep, maxSleep);
        if ( min > 0 ) {
            this.sleep = min;
        } else if (min < 0) {
            this.setHP(this.getMaxHP() - 2);
        } else {
            this.sleep = 0;
            this.activity = new SleepActivity();
        }
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    // method to check animal status
    public boolean isThirsty () {
        return water < 0.1*maxWater;
    }
    public boolean isHungry () {
        return calo < 0.1*maxCalo;
    }
    public boolean isSick () {
        return HP < 0.2*maxHP;
    }

    public Activity getActivity() {
        return activity;
    }

    public abstract void eat (Food food);

    /**
     * Method: updateState
     * @brief Cập nhật trạng thái của con vật khi thực hiện hành động.
     * NOTE: Có thể override, và goi super.updateState()
     */
    protected void updateState () {
        System.out.println(this.activity);
        if (this.activity != null) {
            // update animal state
            this.setHP(this.getHP() + this.activity.getDeltaHP(this.maxHP));
            this.setCalo(this.getCalo() + this.activity.getDeltaCalo(this.maxCalo));
            this.setWater(this.getWater() + this.activity.getDeltaWater(this.maxWater));;
            this.setSleep(this.getSleep() + this.activity.getDeltaSleep(this.maxSleep));
            System.out.println(
                    "Hp: " + this.activity.getDeltaHP(this.maxHP) +
                    "Calo: " + this.activity.getDeltaCalo(this.maxCalo) +
                    "Water: " + this.activity.getDeltaWater(this.maxWater) +
                    "Sleep: " + this.activity.getDeltaSleep(this.maxSleep)
            );
        }
    }

    /**
     * Method: nextActivity
     * @param hours:  hiện tại [0, 23]
     * @brief Thực hiện hành động tiếp thep.
     * NOTE: Có thể override, và  super.updateState()
     */
    protected void nextActivity(int hours) {
        // next activity
        try {
            Random r = new Random(100);
            // FIXME: SET ACTIVITY
            this.activity = this.schedule.getRandomActivity(this);
            System.out.println( "[ hour: "  + hours + "]" +"Set next activity: ");
            if (isHungry() && r.nextInt() < 2) {
                this.activity = new SleepActivity();
            }
            if (isThirsty() && r.nextInt() < 3) {
                this.activity = new SleepActivity();
            }
            if (isSick() && r.nextInt() < 4) {
                this.activity = new SleepActivity();
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method: growUp
     * @brief Cập nhật trạng thái của con vật khi lớn lên 1 tuổi.
     * TODO: OVERRIDE THIS
     */
    public abstract void growUp();

    /**
     * Method: life
     * @param day: [0, 30]
     * @param hours [0, 23]
     * @param minutes [0, 60]
     * NOTE: Không override.
     */
    public final void life(int day ,int hours, int minutes) {
        if(!isDead) {
            if(minutes == 0)
                nextActivity(hours);    // Thay đổi hành động mỗi
            if (minutes % 15 == 0)
                updateState();          // thực hiện hành động gây thay đổi trạng thái mỗi 15' 1 lần.
            if(day == 30 && hours == 0 && minutes == 0)
                growUp();               // Lớn lên mỗi 15 ngày.
            System.out.println("life: [" + day + "|" + hours + ":" + minutes + "] " + this);
        } else {
            System.out.println("dead");
        }
    }

    @Override
    public String toString() {
        return "Animal{" +
                "HP=" + HP +
                ", maxHP=" + maxHP +
                ", water=" + water +
                ", maxWater=" + maxWater +
                ", calo=" + calo +
                ", maxCalo=" + maxCalo +
                ", age=" + age +
                ", sleep=" + sleep +
                ", maxSleep=" + maxSleep +
                ", activity=" + activity +
                ", schedule=" + schedule +
                '}';
    }
}
