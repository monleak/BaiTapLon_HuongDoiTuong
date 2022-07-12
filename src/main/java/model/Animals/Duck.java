package model.Animals;

import model.*;
import model.Activities.Schedule;

public class Duck extends Animal {

    // NOTE: FOR TESTING
    public Duck () {
        this.maxCalo    = 100;
        this.maxHP      = 100;
        this.maxSleep   = 150;
        this.maxWater   = 150;

        this.setWater(70);
        this.setCalo(80);
        this.setHP(95);
        this.setSleep(10);
        this.setAge(0);

        this.neededFood = new FoodInventory(new Food("fish", 20), 25);
        this.activity = null;
        this.schedule = new Schedule();
    }

    public Duck (Schedule s, Food f, int foodAmount) {
        this.maxCalo    = 100;
        this.maxHP      = 100;
        this.maxSleep   = 100;
        this.maxWater   = 100;

        this.setWater(100);
        this.setCalo(100);
        this.setHP(100);
        this.setSleep(100);
        this.setAge(0);

        this.neededFood = new FoodInventory(f, foodAmount);
        this.activity = null;
        this.schedule = s;
    }

    @Override
    public void growUp() {
        getNeededFood().setAmount(getNeededFood().getAmount() + 15);

        this.maxCalo    += 25;
        this.maxHP      += 25;
        this.maxSleep   += 10;
        this.maxWater   += 15;

        this.setAge(this.getAge() + 1);
    }
}
