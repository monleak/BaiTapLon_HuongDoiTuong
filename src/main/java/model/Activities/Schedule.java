package model.Activities;

import model.Animals.Animal;

import java.util.ArrayList;
import java.util.Random;

/**
 * Lịch trình
 */
public class Schedule implements Cloneable {
    public ArrayList<Activity> activityList;

    public static final int DRINK   = 0,
                            EAT     = 1,
                            PLAY    = 2,
                            SLEEP   = 3;

    public Schedule () {
        this.activityList = new ArrayList<Activity>();

        this.activityList.add(new DrinkActivity());
        this.activityList.add(new EatActivity());
        this.activityList.add(new PlayActivity());
        this.activityList.add(new SleepActivity());
        // todo: add set list method and remove above lines
    }

    public ArrayList<Activity> getActivityList() {
        return activityList;
    }

    public Activity getRandomActivity(Animal a){
        if(a.isHungry()){
            return this.activityList.get(EAT);
        }
        if(a.isThirsty()){
            return this.activityList.get(DRINK);
        }
        if(a.getSleep() < 0.1*a.getMaxSleep()){
            //Buồn ngủ
            return this.activityList.get(SLEEP);
        }
        int scoreEat,scoreDrink,scoreSleep,scorePlay;
        scoreEat = 5;
        scoreDrink = 5;
        scoreSleep = 5;
        scorePlay = 85;
        if(a.isSick()) {
            scoreDrink /= 3;
            scoreEat /= 3;
            scorePlay = 0;
        }
        double sumScore = scoreDrink+scoreEat+scorePlay+scoreSleep;
        double[] P = new double[4];
        P[DRINK] = scoreDrink/sumScore;
        P[EAT] = P[DRINK] + scoreEat/sumScore;
        P[PLAY] = P[EAT] + scorePlay/sumScore;
        P[SLEEP] = P[SLEEP] + scoreSleep/sumScore;
        double r = (new Random()).nextDouble();
        for (int i=0;i<4;i++){
            if(i==0){
                if(r<P[i]) return this.activityList.get(i);
            }else if(r>=P[i-1] && r< P[i]) return this.activityList.get(i);
        }
        return this.activityList.get(PLAY);
    }
}
