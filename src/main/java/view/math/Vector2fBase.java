package view.math;

public class Vector2fBase {
    private float x;
    private float y;

    public Vector2fBase(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void addX(float x) {
        this.x += x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void addY(float y) {
        this.y += y;
    }
}
