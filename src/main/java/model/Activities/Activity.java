package model.Activities;

import model.FoodInventory;

/**
 * Hoạt động:
 *
 * - Đã implement trong Animal:
 *      + Các động vật thực hiện các hoạt động sẽ đươc cập nhật trạng thái:
 *          attr += getDeltaAttr()
 *      + Nếu các trạng thái khác = 0 mà ko trừ được nữa thí sẽ giảm HP.
 *
 * - todo:
 *      + kế thừa và override các method getDelta...()
 *      + vd: play.getDeltaCalo() return -4
 */
public abstract class Activity {

    public int getDeltaHP(int maxHP) {
        return 0;
    }

    public abstract int getDeltaWater(int maxWater);
    public abstract int getDeltaCalo(FoodInventory foodInventory);
    public abstract int getDeltaSleep(int maxSleep);

    @Override
    public String toString() {
        return "Activity{ type=" + this.getClass().getName() + " }";
    }
}
