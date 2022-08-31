package model.Animals;

import model.*;
import model.Activities.Schedule;

public class Cat extends Animal {

    // NOTE: This constructor only use for testing
    public Cat () {
        this.maxCalo    = 200;
        this.maxHP      = 400;
        this.maxSleep   = 100;
        this.maxWater   = 100;

        this.setWater(100);
        this.setCalo(200);
        this.setHP(400);
        this.setSleep(100);
        this.setAge(0);

        this.neededFood = new FoodInventory(new Food("Fish", 10), 100);
        this.activity = null;
        this.schedule = new Schedule();
    }

    public Cat (Schedule s, FoodInventory foodInventory) {
        this.maxCalo    = 200;
        this.maxHP      = 400;
        this.maxSleep   = 100;
        this.maxWater   = 100;

        this.setWater(100);
        this.setCalo(200);
        this.setHP(400);
        this.setSleep(100);
        this.setAge(0);

        this.neededFood = foodInventory;
        this.activity = null;
        this.schedule = s;
    }

    @Override
    public void growUp() {
//        getNeededFood().setAmount(getNeededFood().getAmount() + 10);

        this.maxCalo    += 20;
        this.maxHP      += 20;
        this.maxSleep   += 20;
        this.maxWater   += 20;

        this.setAge(this.getAge() + 1);
    }
}
