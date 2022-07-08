package model;

import model.Animals.Animal;
import model.Foods.Food;
import model.Foods.Meat;
import model.Foods.Rice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodManager {
    private final Food[] foodList;

    public static final int RICE = 0,
                            MEAT = 1;

    public FoodManager () {
        this.foodList = new Food[2];
        this.foodList[RICE] = new Rice();
        this.foodList[MEAT] = new Meat();
    }

    public Food[] getFoodList() {
        return foodList;
    }

}
