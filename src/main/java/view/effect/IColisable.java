package view.effect;

import java.awt.*;

public interface IColisable {
    Rectangle getSolidArea();
    int getSolidAreaDefaultX();
    int getSolidAreaDefaultY();
    int getColision();
}
