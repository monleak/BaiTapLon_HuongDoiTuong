package model;

import model.Activities.*;
import model.Animals.Animal;
import model.Foods.Food;

import java.util.ArrayList;
import java.util.List;

/**
 * Class chính sử dụng các class trong package model để mô phỏng nông trại ( con vật, lịch trình, thức ăn ... )
 * <ul>
 * <li>
 *     Danh sách con vật
 * </li>
 * <li>
 *     Hoạt động
 * </li>
 * <li>
 *     Lịch trình
 * </li>
 * <li>
 *     Quản lý thức ăn
 * </li>
 * <li>
 *     TTin ng chơi (nếu có)
 * </li>
 * </ul>
 * <b>
 * NOTE:
 * </b>
 * <ul>
 * <li>
 * Chỉ xử lý các thuộc tính, logic của con vật.
 * </li>
 * <li>
 * Các vấn đề khác được xử lý ở phần giao diện.
 * </li>
 * </ul>
 */
public class ModelState {
    private int simulationSpeed;
    private List<Animal> animalList;
    private List<Schedule> defaultScheduleList;
    protected FoodManager foodManager;

//    , List<Schedule> defaultSchedule, List<Food> foodList
    public ModelState(int simulationSpeed) {
        this.simulationSpeed = simulationSpeed;
        this.animalList = new ArrayList<Animal>(10);
        this.defaultScheduleList = new ArrayList<>(10);


        Activity sleepActivity  = new SleepActivity();
        Activity eatActivity    = new EatActivity();
        Activity playActivity   = new PlayActivity();
        Activity drinkActivity  = new DrinkActivity();

        Schedule catSchedule = new Schedule();

        defaultScheduleList.add(catSchedule);

        this.foodManager = new FoodManager();
    }

    public List<Schedule> getDefaultScheduleList() {
        return defaultScheduleList;
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

    public Food[] getFoodList() {
        return foodManager.getFoodList();
    }

    public void run (int day, int hour, int minute) {
        // Cập nhật trạng thái của các con vật theo thời gian.
        for (Animal a : animalList) {
            a.life(day, hour, minute);
        }
    }
}
