package model.Animals;

import model.*;
import model.Activities.Schedule;

public class Dog extends Animal {

    // NOTE: FOR TESTING
    public Dog () {
        this.maxCalo    = 300;
        this.maxHP      = 500;
        this.maxSleep   = 100;
        this.maxWater   = 200;

        this.setWater(100);
        this.setCalo(300);
        this.setHP(500);
        this.setSleep(100);
        this.setAge(0);

        this.neededFood = new FoodInventory(new Food("rice", 40), 20);
        this.activity = null;
        this.schedule = new Schedule();
    }

    public Dog (Schedule s, FoodInventory foodInventory) {
        this.maxCalo    = 300;
        this.maxHP      = 500;
        this.maxSleep   = 100;
        this.maxWater   = 100;

        this.setWater(100);
        this.setCalo(300);
        this.setHP(500);
        this.setSleep(100);
        this.setAge(0);

        this.neededFood = foodInventory;
        this.activity = null;
        this.schedule = s;
    }

    @Override
    public void growUp() {
//        getNeededFood().setAmount(getNeededFood().getAmount() + 20);

        this.maxCalo    += 20;
        this.maxHP      += 20;
        this.maxSleep   += 20;
        this.maxWater   += 20;

        this.setAge(this.getAge() + 1);
    }
}
