package view.effect.move;

import view.math.AABB;
import view.math.Vector2f;
import view.title.TileCollision;
import view.utils.Direction;

public  class ObjectMovement extends Movement{

    private final TileCollision tileCollision;

    public ObjectMovement(AABB bounds, TileCollision tileCollision) {
        super(bounds, tileCollision, 4);
        this.tileCollision = tileCollision;
    }

    public ObjectMovement(Vector2f pos, TileCollision tileCollision) {
        super(pos, tileCollision, 4);
        this.tileCollision = tileCollision;
    }

    @Override
    protected boolean isNotMoveCollision( int dx, int dy) {
        if (tileCollision == null)
            return true;
        else
            return !tileCollision.collisionTile(dx, dy);
    }

    public void move (Direction direction, int speed) {
        if (speed > 0)
            if (direction == Direction.UP) {
                if (isNotMoveCollision( 0, -speed)) {
                    pos.addY(-speed);
                }
            }
            else if (direction == Direction.DOWN) {
                if (isNotMoveCollision( 0, speed)) {
                    pos.addY(speed);
                }
            }
            else if (direction == Direction.RIGHT) {
                if (isNotMoveCollision( speed, 0)) {
                    pos.addX(speed);
                }
            }
            else if (direction == Direction.LEFT) {
                if (isNotMoveCollision( -speed, 0)) {
                    pos.addX(-speed);
                }
            }
    }
}
