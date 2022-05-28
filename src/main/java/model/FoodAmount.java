package model;

public class FoodAmount {
    private final Food food;
    private int amount;

    public FoodAmount(Food food, int amount) {
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
    public int getCalo () {
        return this.food.getCalo() * this.amount;
    }
}

