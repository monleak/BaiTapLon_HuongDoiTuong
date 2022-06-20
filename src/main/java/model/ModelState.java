package model;

import model.Activities.*;
import model.Animals.Animal;
import model.Animals.Cat;

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
        // FIXME:
//        catSchedule.setActivity(0, sleepActivity)
//                .setActivity(1, sleepActivity)
//                .setActivity(2, sleepActivity)
//                .setActivity(3, sleepActivity)
//                .setActivity(4, sleepActivity)
//                .setActivity(5, sleepActivity)
//                .setActivity(6, sleepActivity)
//                .setActivity(7, playActivity)
//                .setActivity(8, playActivity)
//                .setActivity(9, playActivity)
//                .setActivity(10, eatActivity)
//                .setActivity(11, eatActivity)
//                .setActivity(12, drinkActivity)
//                .setActivity(13, drinkActivity)
//                .setActivity(14, playActivity)
//                .setActivity(15, playActivity)
//                .setActivity(16, playActivity)
//                .setActivity(17, playActivity)
//                .setActivity(18, playActivity)
//                .setActivity(19, playActivity)
//                .setActivity(20, playActivity)
//                .setActivity(21, sleepActivity)
//                .setActivity(22, sleepActivity)
//                .setActivity(23, sleepActivity);

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

    public void run (int day, int hour, int minute) {

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
