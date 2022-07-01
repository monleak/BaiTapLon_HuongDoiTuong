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
    public float x;
    public float y;

    // Tọa đô của camera (static)
    public static float worldX;
    public static float worldY;

    public Vector2f() {
        x = 0;
        y = 0;
    }

    public Vector2f(Vector2f vec) {
        new Vector2f(vec.x, vec.y);
    }

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void addX(float f) { x += f; }
    public void addY(float f) { y += f; }

    public void setX(float f) { x = f; }
    public void setY(float f) { y = f; }

    public void setVector(Vector2f vec) {
        this.x = vec.x;
        this.y = vec.y;
    }

    public void setVector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public static void setWorldVar(float x, float y) {
        worldX = x;
        worldY = y;
    }

    public static float getWorldVarX(float x) {
        return x - worldX;
    }

    public static float getWorldVarY(float y) {
        return y - worldY;
    }

    /**
     * Trả về vị trí của điểm trên màn hình.
     *
     * @return
     */
    public Vector2f getWorldVar() {
        return new Vector2f(x - worldX, y - worldY);
    }

    @Override
    public String toString() {
        return x + ", " + y;
    }

}
