package model.Activities;

import model.Animals.Animal;
import model.FoodInventory;

/**
 * Hành đọng cần chuẩn bị:
 * VD: Khi ăn cần đi đến chỗ khay thức ăn xong mới ăn.
 */
public interface IPrepareActivity {

    boolean isReady ();

    void onPrepareDone(Activity activity);

    void onCancel();

}
