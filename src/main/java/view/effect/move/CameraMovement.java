package view.effect.move;

import states.GameStateManager;
import view.math.AABB;
import view.math.Vector2f;
import view.title.TileCollision;
import view.utils.Direction;

public class CameraMovement extends Movement{

    private final AABB mapBounds;

    public CameraMovement(AABB bounds, TileCollision tileCollision) {
        super(bounds, tileCollision, 4);

        this.mapBounds = new AABB(
                new Vector2f(),
                GameStateManager.gp.worldWidth,
                GameStateManager.gp.worldHeight
        );
    }

    public CameraMovement(Vector2f pos, TileCollision tileCollision) {
        super(pos, tileCollision, 4);

        this.mapBounds = new AABB(
                new Vector2f(),
                GameStateManager.gp.worldWidth,
                GameStateManager.gp.worldHeight
        );
    }

    protected boolean isNotMoveCollision (int dx, int dy) {
        return (
                mapBounds.inside(
                        (int) pos.getX() + dx,
                        (int) pos.getY() + dy
                )
                && mapBounds.inside(
                        (int) (pos.getX() + bounds.getWidth() + dx),
                        (int) (pos.getY() + bounds.getHeight() + dy)
                )
        );
    }

    public void move (Direction direction, int speed) {
        if (speed > 0)
            if (direction == Direction.UP) {
                if (isNotMoveCollision(0, -speed)) {
                    pos.addY(-speed);
                    Vector2f.setWorldVar(pos.getX(), pos.getY());
                }
            }
            else if (direction == Direction.DOWN) {
                if (isNotMoveCollision(0, speed)) {
                    pos.addY(speed);
                    Vector2f.setWorldVar(pos.getX(), pos.getY());
                }
            }
            else if (direction == Direction.RIGHT) {
                if (isNotMoveCollision(speed, 0)) {
                    pos.addX(speed);
                    Vector2f.setWorldVar(pos.getX(), pos.getY());
                }
            }
            else if (direction == Direction.LEFT) {
                if (isNotMoveCollision( -speed, 0)) {
                    pos.addX(-speed);
                    Vector2f.setWorldVar(pos.getX(), pos.getY());
                }
            }
    }
}
