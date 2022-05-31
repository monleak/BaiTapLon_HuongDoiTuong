package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
//
//Khi chạy chương trình sẽ chạy class ModelState đầu tiên.
//        Trong constructor khởi tạo animalList, activityList, defaultScheduleList, foodList, foodManager
//FoodManager:
//        constructor: public FoodManager( Animal[] animalList );
//        Các method calc... trả về danh sách lượng đồ ăn cần thiết trong ngày, tuần, tháng.
//        ( có thể trả về dữ liệu: FoodAmount[], Map<Food, Integer>, List<FoodAmount> )

public class FoodManager {

    public Map<Food, Integer> calcFoodDay(List<Animal> animalList) {
        Map<Food, Integer> foodIntegerMap = new HashMap<>();
        for (Animal a : animalList) {
            foodIntegerMap.put(a.getNeededFood().getFood(), a.schedule.getFoodConsumeInOneDay(a).getAmount());
        }
        return foodIntegerMap;
    }
}