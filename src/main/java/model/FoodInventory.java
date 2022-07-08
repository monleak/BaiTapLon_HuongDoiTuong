package model;

import model.Animals.Animal;
import model.Foods.Food;

public class FoodInventory {
    private final Food food;
    private int amount;

    public FoodInventory(Food food, int amount) {
        this.food = food;
        this.amount = amount;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) { this.amount = amount; }

    public Food getFood() {
        return food;
    }
    public int getTotalCalo() {
        return this.food.getCalo() * this.amount;
    }

}

