package model.Animals;

import model.*;

public class Chicken extends Animal {

    // NOTE: This constructor only use for testing
    public Chicken () {
        this.maxCalo    = 100;
        this.maxHP      = 100;
        this.maxSleep   = 100;
        this.maxWater   = 100;

        this.setWater(100);
        this.setCalo(100);
        this.setHP(100);
        this.setSleep(100);
        this.setAge(0);

        this.neededFood = new FoodAmount(new Food("Fish", 10), 100);
        this.activity = null;
//        this.schedule = new Schedule();
    }

    public Chicken (Schedule s, Food f, int foodAmount) {
        this.maxCalo    = 100;
        this.maxHP      = 100;
        this.maxSleep   = 100;
        this.maxWater   = 100;

        this.setWater(100);
        this.setCalo(100);
        this.setHP(100);
        this.setSleep(100);
        this.setAge(0);

        this.neededFood = new FoodAmount(f, foodAmount);
        this.activity = null;
        this.schedule = s;
    }

    @Override
    public void growUp() {
        getNeededFood().setAmount(getNeededFood().getAmount() + 10);

        this.maxCalo    += 20;
        this.maxHP      += 20;
        this.maxSleep   += 20;
        this.maxWater   += 20;

        this.setAge(this.getAge() + 1);
    }
}
