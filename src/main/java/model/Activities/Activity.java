package model.Activities;

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
    public abstract ActivityType getActivityType();
    public int getDeltaHP() {
        return 0;
    }
    public int getDeltaWater() {
        return 0;
    }
    public int getDeltaCalo() {
        return 0;
    }
    public int getDeltaSleep() {
        return 0;
    }

    @Override
    public String toString() {
        return "Activity{ type=" + getActivityType() + " }";
    }
}
