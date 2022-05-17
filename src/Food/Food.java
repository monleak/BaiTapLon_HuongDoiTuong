package Food;


public class Food {
    private double calo;
    private String name;
    Food(String name, double calo){
        this.name = name;
        this.calo = calo;
    }

    public double getCalo() {
        return calo;
    }

    public void setCalo(double calo) {
        this.calo = calo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
