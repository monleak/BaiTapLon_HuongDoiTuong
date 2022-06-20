package view.effect;

import view.utils.Direction;

public interface IMoveable {

//    void setScreenX(int num);
//    void setScreenY(int num);
    void setSpeed(int speed);

    int getSpeed();
    Direction getDirection();
}
