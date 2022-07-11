package model.Animals;

import model.Activities.*;
import model.Food;
import model.FoodInventory;

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
    protected FoodInventory neededFood;

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
        } else {
            this.water = 0;
            this.setHP(this.getHP() - 4);
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
        } else {
            this.calo = 0;
            this.setHP(this.getHP() - 4);
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
        } else {
            this.sleep = 0;
            this.setHP(this.getHP() - 4);
        }
    }

    public boolean isDead() {
        return isDead;
    }
    public Schedule getSchedule(){
        return schedule;
    }
    public void setDead(boolean dead) {
        isDead = dead;
    }

    public FoodInventory getNeededFood () {
        return neededFood;
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

    // handle specific activity
    public void drink (int ml) {
        this.setSleep(this.getSleep() + ml);
    }
    public int eat (FoodInventory foodInventory) {
        if(foodInventory.getFood().equals(neededFood.getFood())) {
            int maxEatAmount = (int) (Math.sqrt(this.age + 1));
            int neededEatAmount = (int) Math.ceil( 1.0 * (this.maxCalo - this.calo) / this.neededFood.getFood().getCalo());
            int inventoryHave = foodInventory.getAmount();
            if (neededEatAmount < maxEatAmount) {
                if (inventoryHave < neededEatAmount) {
                    this.setCalo(this.calo + inventoryHave * foodInventory.getFood().getCalo());
                    foodInventory.setAmount(0);
                } else {
                    this.setCalo(this.maxCalo);
                    foodInventory.setAmount(foodInventory.getAmount() - neededEatAmount);
                }
            } else {
                if (inventoryHave < maxEatAmount) {
                    this.setCalo(this.calo + inventoryHave * foodInventory.getFood().getCalo());
                    foodInventory.setAmount(0);
                } else {
                    this.setCalo(this.maxCalo);
                    foodInventory.setAmount(foodInventory.getAmount() - maxEatAmount);
                }
            }
        }
        return 0;
    }

    public Activity getActivity() {
        return activity;
    }

    /**
     * Method: updateState
     * @brief Cập nhật trạng thái của con vật khi thực hiện hành động.
     * NOTE: Có thể override, và goi super.updateState()
     */
    protected void updateState () {
        if (this.getHP() < this.getMaxHP()) {
            this.setHP(this.getHP() + 1);
        }
        if (this.activity != null) {
            // update animal state
            this.setHP(this.getHP() + this.activity.getDeltaHP(this.maxHP));
            this.setCalo (this.getCalo() + this.activity.getDeltaCalo(this.neededFood));
            this.setWater(this.getWater() + this.activity.getDeltaWater(this.maxWater));;
            this.setSleep(this.getSleep() + this.activity.getDeltaSleep(this.maxSleep));
            System.out.println(
                    "Hp: " + this.activity.getDeltaHP(this.maxHP) +
                    "Calo: " + this.activity.getDeltaCalo(this.neededFood) +
                    "Water: " + this.activity.getDeltaWater(this.maxWater) +
                    "Sleep: " + this.activity.getDeltaSleep(this.maxSleep)
            );

            // if specific activity
            if(this.activity instanceof EatActivity) {
                if (((EatActivity) this.activity).isReady())
                    this.eat(this.neededFood);      // ăn sau khi đi đến chỗ thớc ăn.
            } else if (this.activity instanceof DrinkActivity) {
                this.drink(10);
            }

        }
    }

    /**
     * Method: nextActivity
     * @param hours:  hiện tại [0, 23]
     * @brief Thực hiện hành động tiếp thep.
     * NOTE: Có thể override, và  super.updateState()
     */
    protected void nextActivity(int hours) {
        try {
            // FIXME: SET ACTIVITY
            if (this.activity instanceof IPrepareActivity) {
                ((IPrepareActivity) this.activity).onCancel();  // reset act when change to new activity
            }
            this.activity = this.schedule.getRandomActivity(this);
            System.out.println( "[ hour: "  + hours + "]" +"Set next activity: ");
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
            if(minutes == 0 || this.activity == null)
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
                ", neededFood=" + neededFood +
                '}';
    }
}
