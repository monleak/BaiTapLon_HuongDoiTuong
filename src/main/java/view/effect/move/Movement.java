package view.effect.move;

import view.math.AABB;
import view.math.Vector2f;
import view.title.TileCollision;
import view.utils.Direction;
import java.util.Objects;


public abstract class Movement {
    protected final Vector2f pos;
    protected final AABB bounds;
    private int speed;

    public Movement(AABB bounds, TileCollision tileCollision, int speed) {
        this.bounds = Objects.requireNonNull(bounds, "bounds must not be null");
        this.pos = bounds.getPos();
        this.speed = speed;
    }

    public Movement(Vector2f pos, TileCollision tileCollision, int speed) {
        this.pos = Objects.requireNonNull(pos);
        this.bounds = null;
        this.speed = speed;
    }
    protected abstract boolean isNotMoveCollision(int dx, int dy);

    public abstract void move(Direction direction, int speed);

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public boolean isRunning () {
        return speed != 0;
    }
}
