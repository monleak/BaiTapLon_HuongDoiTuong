package view.main;

import states.PlayState;
import view.entity.AnimalEntity;
import view.entity.ChickenEntity;
import view.object.Buffalo;
import view.object.Fox;
import view.object.OBJ_Fence;
import view.object.OBJ_Key;
import view.utils.Direction;

public class AssetSetter {
    GamePanel gp;
    PlayState ps;

    public AssetSetter(GamePanel gp, PlayState ps) {
        this.gp = gp;
        this.ps = ps;
    }

    public void setObject() {
        ps.obj[0] = new OBJ_Key(gp, ps);
        ps.obj[0].setWorldX(23 * gp.titleSize);
        ps.obj[0].setWorldY(7 * gp.titleSize);

        ps.obj[1] = new OBJ_Key(gp, ps);
        ps.obj[1].setWorldX(23 * gp.titleSize);
        ps.obj[1].setWorldY(40 * gp.titleSize);

        ps.obj[2] = new OBJ_Key(gp, ps);
        ps.obj[2].setWorldX(36 * gp.titleSize);
        ps.obj[2].setWorldY(40 * gp.titleSize);

        ps.obj[3] = new Buffalo(gp, ps);
        ps.obj[3].setWorldX(37 * gp.titleSize);
        ps.obj[3].setWorldY(41 * gp.titleSize);

        ps.obj[4] = new Buffalo(gp, ps);
        ps.obj[4].setWorldX(37 * gp.titleSize);
        ps.obj[4].setWorldY(10 * gp.titleSize);
        ps.obj[4].collision = false;

        ps.obj[5] = new Buffalo(gp, ps);
        ps.obj[5].setWorldX((int)(37.4f * gp.titleSize));
        ps.obj[5].setWorldY((int)(10.4f * gp.titleSize));
        ps.obj[5].collision = false;
//
        ps.obj[6] = new Fox(gp, ps);
        ps.obj[6].setWorldX((int)(37.2f * gp.titleSize));
        ps.obj[6].setWorldY((int)(14f * gp.titleSize));
        ps.obj[6].collision = false;

        ps.obj[7] = new ChickenEntity(gp, ps);
        ps.obj[7].setWorldX(30* gp.titleSize);
        ps.obj[7].setWorldY(12* gp.titleSize);
        //        gp.obj[7].collision = true;

        ps.obj[8] = new ChickenEntity(gp, ps);
        ps.obj[8].setWorldX(30* gp.titleSize);
        ps.obj[8].setWorldY(11* gp.titleSize);
//
//        ps.obj[9] = new ChickenEntity(gp, ps);
//        ps.obj[9].setWorldX(30* gp.titleSize);
//        ps.obj[9].setWorldY(10* gp.titleSize);
//
//        ps.obj[10] = new ChickenEntity(gp, ps);
//        ps.obj[10].setWorldX(31* gp.titleSize);
//        ps.obj[10].setWorldY(12* gp.titleSize);
//
//        ps.obj[11] = new ChickenEntity(gp, ps);
//        ps.obj[11].setWorldX(31* gp.titleSize);
//        ps.obj[11].setWorldY(13* gp.titleSize);

//        gp.obj[12] = new OBJ_Fence(gp, Direction.UP, 8);
//        gp.obj[12].setWorldX(21* gp.titleSize);
//        gp.obj[12].setWorldY((15)* gp.titleSize);
//
//        gp.obj[13] = new OBJ_Fence(gp, Direction.LEFT, 8);
//        gp.obj[13].setWorldX(25* gp.titleSize);
//        gp.obj[13].setWorldY((12)* gp.titleSize);
    }
}
