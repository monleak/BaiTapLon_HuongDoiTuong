package NPC;

public class Ga extends DongVat{
    @Override
    public void updatePerYear(){
        this.setHP(this.getTuoi()*5+100);
        this.setMaxFood(this.getTuoi()*50+1000);
        this.setMaxWater(this.getTuoi()*30+500);
    }
}
