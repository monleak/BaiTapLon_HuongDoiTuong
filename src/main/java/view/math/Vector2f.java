package view.math;

/**
 * Vector2f: Vector, 2dim, float.
 * Xác định vị trí của 1 điẻm trên map và trên màn hình.
 *
 * Vị trí trên map: thuộc tính x, y
 * Vị trí trên màn hình: getWorldVar(): Vector2f
 *
 */
public class Vector2f {

    // Tọa độ trên map
    private Vector2fBase base;

    // Tọa đô của camera (static)
    public static float worldX;
    public static float worldY;

    public Vector2f() {
        base = new Vector2fBase(0, 0);
    }

    public Vector2f(Vector2f vec) {
        new Vector2f(vec.getX(), vec.getY());
    }

    public Vector2f(float x, float y) {
        this.base = new Vector2fBase(x, y);
    }

    // NOTE: same base
    public Vector2f(Vector2fBase base) {
        this.base = base;
    }

    public float getX() {
        return this.base.getX();
    }

    public float getY() {
        return this.base.getY();
    }

    public void addX(float f) { this.base.addX(f); }
    public void addY(float f) { this.base.addY(f); }

    public void setX(float f) { this.base.setX(f); }
    public void setY(float f) { this.base.setY(f); }

    public void setVector(Vector2f vec) {
        this.base.setX(vec.getX());
        this.base.setY(vec.getY());
    }

    public void setVector(float x, float y) {
        this.base.setX(x);
        this.base.setY(y);
    }

    public static void setWorldVar(float x, float y) {
        worldX = x;
        worldY = y;
    }

    public static float getStaticWorldX(float x) {
        return x - worldX;
    }

    public static float getStaticWorldY(float y) {
        return y - worldY;
    }
    public static float getStaticScreenX(float x) {
        return x + worldX;
    }

    public static float getStaticScreenY(float y) {
        return y + worldY;
    }

    /**
     * Trả về vị trí của điểm trên màn hình.
     *
     * @deprecated Create too much instance
     *
     * @return
     */
    public Vector2f getWorldVar() {
        return new Vector2f(getX() - worldX, getY() - worldY);
    }

    public static float getWorldX() {
        return worldX;
    }

    public static float getWorldY() {
        return worldY;
    }

    public float getScreenX () {
        return getX() - worldX;
    }

    public float getScreenY () {
        return getY() - worldY;
    }

    @Override
    public String toString() {
        return getX() + ", " + getY();
    }

}
