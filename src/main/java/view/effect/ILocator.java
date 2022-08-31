package view.effect;

import view.math.AABB;
import view.math.Vector2f;
import view.title.TileCollision;
import view.utils.Direction;

public interface ILocator {
    Direction getDirection ();
//    int getSpeed ();
//    TileCollision getTileCollision();

    void setDirection (Direction direction);
//    void setSpeed(int speed);

    AABB getBounds ();
    Vector2f getPos ();
}
