package model.Activities;

import model.Animals.Animal;
import model.FoodInventory;

public interface IPrepareActivity {

    String EAT = "EAT";
    String DRINK = "DRINK";

    boolean isReady ();

    void onPrepareDone(Activity activity);

    void onCancel();

    void run (Animal animal, FoodInventory foodInventory);

}
