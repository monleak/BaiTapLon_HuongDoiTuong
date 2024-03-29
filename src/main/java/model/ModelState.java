package model;

import model.Activities.*;
import model.Animals.Animal;
import model.Animals.Cat;

import java.util.ArrayList;
import java.util.List;

/**
 * Class chính sử dụng các class trong package model để mô phỏng nông trại ( con vật, lịch trình,
 * thức ăn ... )
 *
 * <ul>
 *   <li>Danh sách con vật
 *   <li>Hoạt động
 *   <li>Lịch trình
 *   <li>Quản lý thức ăn
 *   <li>TTin ng chơi (nếu có)
 * </ul>
 *
 * <b> NOTE: </b>
 *
 * <ul>
 *   <li>Chỉ xử lý các thuộc tính, logic của con vật.
 *   <li>Các vấn đề khác được xử lý ở phần giao diện.
 * </ul>
 */
public class ModelState {
    private int simulationSpeed;
    private List<Animal> animalList;
    private List<Schedule> defaultScheduleList;
    private final List<Food> foodList;
    private final List<FoodInventory> foodInventoryList;
    private FoodManager foodManager;
    private TimeManager timeManager;

    public ModelState(int simulationSpeed) {
        // init arr
        this.simulationSpeed = simulationSpeed;
        this.animalList = new ArrayList<>(10);
        this.defaultScheduleList = new ArrayList<>(10);
        this.foodList = new ArrayList<>(10);
        this.foodInventoryList = new ArrayList<>(10);
        timeManager = new TimeManager(0, 6, 0);

        // them thuc an
        foodList.add(new Food("egg"));
        foodList.add(new Food("meat"));
        foodList.add(new Food("rice"));

        // them luong thuc an
        foodList.forEach(food -> {
            this.foodInventoryList.add(new FoodInventory(food, 10));
        });

        Schedule catSchedule = new Schedule();

        defaultScheduleList.add(catSchedule);

        this.foodManager = new FoodManager();
    }

    public List<Schedule> getDefaultScheduleList() {
        return defaultScheduleList;
    }

    public void setSimulationSpeed(int simulationSpeed) {
        if (simulationSpeed > 0 && simulationSpeed <= 12)
            this.simulationSpeed = simulationSpeed;
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

    public List<Food> getFoodList() {
        return foodList;
    }

    public TimeManager getTimeManager() {
        return timeManager;
    }

    public List<FoodInventory> getFoodInventoryList() {
        return foodInventoryList;
    }

    public void run() {
        timeManager.update();
        int day = timeManager.getDays();
        int hour = timeManager.getHours();
        int minute = timeManager.getMinutes();

        // Tính lượng thức ăn khi bắt đầu ngày mới.
        if (minute == 0 && hour == 0) {
            foodManager.calcFoodDay(animalList);
        }

        // Cập nhật trạng thái của các con vật theo thời gian.
        for (Animal a : animalList) {
            a.life(day, hour, minute);
        }

    }
}
