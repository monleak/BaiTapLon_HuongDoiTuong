package model.Activities;

import model.Animals.Animal;

import java.util.ArrayList;
import java.util.Random;

/**
 * Lịch trình
 */
public class Schedule implements Cloneable {
    public ArrayList<Activity> activityList;

    public Schedule () {
        this.activityList = new ArrayList<Activity>();

        this.activityList.add(new DrinkActivity());
        this.activityList.add(new EatActivity());
        this.activityList.add(new PlayActivity());
        this.activityList.add(new SleepActivity());
        // todo: add set list method and remove above lines
    }

//    public void addActivity (Activity activity) {
//        for (int i=0;i<activityList.size();i++){
//            if(activityList.get(i).getActivityType() == activity.getActivityType()){
//                return;
//            }
//        }
//        this.activityList.add(activity);
//    }

//    public FoodAmount getFoodConsumeInOneDay (Animal a) {
//        int amount = 0;
//        for (Activity act : activityList) {
//            if(act.getActivityType() != ActivityType.eat)
//                amount += act.getDeltaCalo();
//        }
//        return new FoodAmount(a.getNeededFood().getFood(), amount);
//    }

    public ArrayList<Activity> getActivityList() {
        return activityList;
    }

    public Activity getRandomActivity(Animal a){
        if(a.isHungry()){
            return this.activityList.get(1);
        }
        if(a.isThirsty()){
            return this.activityList.get(0);
        }
        if(a.getSleep() < 0.1*a.getMaxSleep()){
            //Buồn ngủ
            return this.activityList.get(3);
        }
        int scoreEat=0,scoreDrink=0,scoreSleep=0,scorePlay=0;
        scoreEat += 10*(1-a.getCalo()/a.getMaxCalo())/0.02;
        scoreDrink += 10*(1-a.getWater()/a.getMaxWater())/0.03;
        scoreSleep += 10*(1-a.getSleep()/a.getMaxSleep())/0.07;
        scorePlay += 10*((a.getSleep()-0.3*a.getMaxSleep())/a.getMaxSleep()
                            + (a.getCalo()-0.3*a.getMaxCalo())/a.getMaxCalo()
                            + (a.getWater()-0.3*a.getMaxWater())/a.getMaxWater())/0.01;
        if(a.isSick()) {
            scoreDrink /= 3;
            scoreEat /= 3;
            scorePlay = 0;
        }
        double sumScore = scoreDrink+scoreEat+scorePlay+scoreSleep;
        double[] P = new double[4];
        P[0] = scoreDrink/sumScore;
        P[1] = P[0] + scoreEat/sumScore;
        P[2] = P[1] + scorePlay/sumScore;
        P[3] = P[2] + scoreSleep/sumScore;
        double r = (new Random()).nextDouble();
        for (int i=0;i<4;i++){
            if(i==0){
                if(r<P[i]) return this.activityList.get(i);
            }else if(r>=P[i-1] && r< P[i]) return this.activityList.get(i);
        }
        return this.activityList.get((new Random()).nextInt(P.length));
    }
}
