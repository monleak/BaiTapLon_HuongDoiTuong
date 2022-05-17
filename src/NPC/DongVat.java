package NPC;

import Food.Food;
import Main.Param;

import java.util.ArrayList;

enum TrangThai{
    doi,khat,khoeManh,chet,doiVaKhat
}
public abstract class DongVat {
    private int Tuoi;
    private int HP;
    private double canNang;
    private double currentFood;
    private double currentWater;
    private double maxFood;
    private double maxWater;
    private TrangThai trangThai;
    private ArrayList<Food> Foods;

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public double getCanNang() {
        return canNang;
    }

    public void setCanNang(double canNang) {
        this.canNang = canNang;
    }

    public double getCurrentFood() {
        return currentFood;
    }

    public void setCurrentFood(double currentFood) {
        this.currentFood = currentFood;
    }

    public double getCurrentWater() {
        return currentWater;
    }

    public void setCurrentWater(double currentWater) {
        this.currentWater = currentWater;
    }
    public int getTuoi() {
        return Tuoi;
    }

    public void setTuoi(int tuoi) {
        Tuoi = tuoi;
    }

    public void addFood(Food food){
        this.Foods.add(food);
    }

    public boolean checkFood(Food food){
        for(int i=0;i<Foods.size();i++){
            if(food.getName().equals(Foods.get(i).getName())){
                return true;
            }
        }
        return false;
    }
    public abstract void updatePerYear();
    public void updatePerSecond(){
        if(this.trangThai != TrangThai.chet){
            //Giảm thức ăn và nước theo thời gian
            this.currentFood = this.currentFood - Param.SUB_FoodWater_PER_SEC;
            this.currentWater = this.currentWater - Param.SUB_FoodWater_PER_SEC;
            //Giảm máu khi bị đói hoặc khát
            if(this.currentFood == 0 || this.currentWater == 0){
                this.HP = this.HP - Param.SUB_HP_PER_SEC;
            }
            //Set trạng thái dựa trên thuộc tính
            if(this.currentFood < this.maxFood*0.1 && this.currentWater >= this.maxWater*0.1){
                this.trangThai = TrangThai.doi;
            }else if(this.currentWater < this.maxWater*0.1&&this.currentFood >= this.maxFood*0.1){
                this.trangThai = TrangThai.khat;
            }else if(this.currentWater < this.maxWater*0.1&&this.currentFood < this.maxFood*0.1){
                this.trangThai = TrangThai.doiVaKhat;
            }else this.trangThai = TrangThai.khoeManh;
            if(this.getHP() <= 0){
                this.trangThai = TrangThai.chet;
            }
        }
    }
    public double getMaxFood() {
        return maxFood;
    }

    public void setMaxFood(double maxFood) {
        this.maxFood = maxFood;
    }

    public double getMaxWater() {
        return maxWater;
    }

    public void setMaxWater(double maxWater) {
        this.maxWater = maxWater;
    }

    public boolean An(Food food){
        if(checkFood(food)){
            this.setCurrentFood(this.getCurrentFood()+food.getCalo());
            if(this.getCurrentFood() > this.getMaxFood()){
                this.setCurrentFood(this.getMaxFood());
            }
            return true;
        }else return false;
    }
    public void Uong(double Water){
        this.setCurrentWater(this.getCurrentWater()+Water);
    }
    public TrangThai getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(TrangThai trangThai) {
        this.trangThai = trangThai;
    }
}
