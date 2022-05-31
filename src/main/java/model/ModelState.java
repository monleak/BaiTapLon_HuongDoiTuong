package model;

import model.Activities.DrinkActivity;
import model.Activities.EatActivity;
import model.Activities.PlayActivity;
import model.Activities.SleepActivity;
import model.Animals.Cat;

import java.util.ArrayList;
import java.util.List;

public class ModelState {
    private int simulationSpeed;
    private List<Animal> animalList;
    private List<Schedule> defaultScheduleList;
    private Player player;
    private final List<Food> foodList;
    protected FoodManager foodManager;

//    , List<Schedule> defaultSchedule, List<Food> foodList
    public ModelState(int simulationSpeed) {
        this.simulationSpeed = simulationSpeed;
        this.animalList = new ArrayList<Animal>(10);
        this.defaultScheduleList = new ArrayList<>(10);
        this.player = new Player();
        this.foodList = new ArrayList<Food>(10);

        foodList.add(new Food("egg"));
        foodList.add(new Food("meat"));
        foodList.add(new Food("cookie"));

        Activity sleepActivity  = new SleepActivity();
        Activity eatActivity    = new EatActivity();
        Activity playActivity   = new PlayActivity();
        Activity drinkActivity  = new DrinkActivity();

        Schedule catSchedule = new Schedule();
        catSchedule.setActivity(0, sleepActivity)
                .setActivity(1, sleepActivity)
                .setActivity(2, sleepActivity)
                .setActivity(3, sleepActivity)
                .setActivity(4, sleepActivity)
                .setActivity(5, sleepActivity)
                .setActivity(6, sleepActivity)
                .setActivity(7, playActivity)
                .setActivity(8, playActivity)
                .setActivity(9, playActivity)
                .setActivity(10, eatActivity)
                .setActivity(11, eatActivity)
                .setActivity(12, drinkActivity)
                .setActivity(13, drinkActivity)
                .setActivity(14, playActivity)
                .setActivity(15, playActivity)
                .setActivity(16, playActivity)
                .setActivity(17, playActivity)
                .setActivity(18, playActivity)
                .setActivity(19, playActivity)
                .setActivity(20, playActivity)
                .setActivity(21, sleepActivity)
                .setActivity(22, sleepActivity)
                .setActivity(23, sleepActivity);

        defaultScheduleList.add(catSchedule);

        this.animalList.add(new Cat(catSchedule, foodList.get(0), 10));
        this.foodManager = new FoodManager();

    }

    public int getSimulationSpeed() {
        return simulationSpeed;
    }

    public List<Animal> getAnimalList() {
        return animalList;
    }

    public List<Schedule> getDefaultSchedule() {
        return defaultScheduleList;
    }

    public Player getPlayer() {
        return player;
    }

}

class ModelStateMain {
    public static void main (String[] args) {
        // test foodManager
        ModelState gameState = new ModelState(10);

        System.out.println(gameState.foodManager.calcFoodDay(gameState.getAnimalList()));


//        for (int i = 0; i < 2; i++) {
//            for (int j = 0; j < 15; j++ ) {
//                for (int k = 0; k < 24; k++) {
//                    for (int l = 0; l < 60; l++) {
//                        gameState.getAnimalList().get(0).life(j, k, l);
////                        System.out.println("j: " + j + ", k: " + k + ", l: " + l);
//                    }
//                }
//            }
//        }
//        gameState.getAnimalList().get(0).life(0, 0, 0);
        System.out.println(gameState.getAnimalList().get(0));

    }
}