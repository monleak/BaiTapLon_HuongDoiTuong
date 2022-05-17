package NPC;

import Food.Food;

public class Cho extends DongVat{
    @Override
    public void updatePerYear(){
        this.setHP(this.getTuoi()*5+200);
        this.setMaxFood(this.getTuoi()*50+2000);
        this.setMaxWater(this.getTuoi()*30+1500);
    }
}
