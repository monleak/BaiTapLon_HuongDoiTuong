package view.main;

import states.PlayState;
import view.entity.AnimalEntity;
import view.entity.ChickenEntity;
import view.object.Buffalo;
import view.object.Fox;
import view.object.OBJ_Fence;
import view.object.OBJ_Key;
import view.utils.Direction;

/**
 * Dùng để tạo các con vật, vật thể trên bản đồ.
 * phương thức: setObject
 *
 * TODO: Khi kết hợp với model phải tạo class khác.
 */
public class AssetSetter {
    GamePanel gp;
    PlayState ps;

    public AssetSetter(GamePanel gp, PlayState ps) {
        this.gp = gp;
        this.ps = ps;
    }

    public void setObject() {
        ps.obj[0] = new OBJ_Key(gp, ps);
        ps.obj[0].getBounds().getPos().x = (23 * gp.titleSize);
        ps.obj[0].getBounds().getPos().y = (7 * gp.titleSize);

        ps.obj[1] = new Fox(gp, ps);
        ps.obj[1].getBounds().getPos().x = ((int)(38f * gp.titleSize));
        ps.obj[1].getBounds().getPos().y = ((int)(14f * gp.titleSize));
        ps.obj[1].collision = false;
        Fox f = (Fox) ps.obj[1];
//        f.follow(ps.player);

//
//        ps.obj[2] = new ChickenEntity(gp, ps);
//        ps.obj[2].getBounds().getPos().x = ((int)(38f * gp.titleSize));
//        ps.obj[2].getBounds().getPos().y = ((int)(10f * gp.titleSize));
//        ps.obj[2].collision = false;
//
//        ps.obj[3] = new ChickenEntity(gp, ps);
//        ps.obj[3].getBounds().getPos().x = ((int)(39f * gp.titleSize));
//        ps.obj[3].getBounds().getPos().y = ((int)(10f * gp.titleSize));
//        ps.obj[3].collision = false;
//
//        ps.obj[4] = new ChickenEntity(gp, ps);
//        ps.obj[4].getBounds().getPos().x = ((int)(38f * gp.titleSize));
//        ps.obj[4].getBounds().getPos().y = ((int)(9f * gp.titleSize));
//        ps.obj[4].collision = false;
//
//        ps.obj[5] = new ChickenEntity(gp, ps);
//        ps.obj[5].getBounds().getPos().x = ((int)(38f * gp.titleSize));
//        ps.obj[5].getBounds().getPos().y = ((int)(11f * gp.titleSize));
//        ps.obj[5].collision = false;
//
//        for (int i = 6; i < 20; i++) {
//            ps.obj[i] = new ChickenEntity(gp, ps);
//            ps.obj[i].getBounds().getPos().x = ((int)((33f + i / 2) * gp.titleSize));
//            ps.obj[i].getBounds().getPos().y = ((int)((8f + i % 2) * gp.titleSize));
//            ps.obj[i].collision = false;
//        }
//
//        for (int i = 21; i < 30; i++) {
//            ps.obj[i] = new ChickenEntity(gp, ps);
//            ps.obj[i].getBounds().getPos().x = ((int)((33f + i / 2) * gp.titleSize));
//            ps.obj[i].getBounds().getPos().y = ((int)((12f + i % 2) * gp.titleSize));
//            ps.obj[i].collision = false;
//        }

//        ps.obj[1] = new OBJ_Key(gp, ps);
//        ps.obj[1].setWorldX(23 * gp.titleSize);
//        ps.obj[1].setWorldY(40 * gp.titleSize);
//
//        ps.obj[2] = new OBJ_Key(gp, ps);
//        ps.obj[2].setWorldX(36 * gp.titleSize);
//        ps.obj[2].setWorldY(40 * gp.titleSize);
//
//        ps.obj[3] = new Buffalo(gp, ps);
//        ps.obj[3].setWorldX(37 * gp.titleSize);
//        ps.obj[3].setWorldY(41 * gp.titleSize);
//
//        ps.obj[4] = new Buffalo(gp, ps);
//        ps.obj[4].setWorldX(37 * gp.titleSize);
//        ps.obj[4].setWorldY(10 * gp.titleSize);
//        ps.obj[4].collision = false;
//
//        ps.obj[5] = new Buffalo(gp, ps);
//        ps.obj[5].setWorldX((int)(37.4f * gp.titleSize));
//        ps.obj[5].setWorldY((int)(10.4f * gp.titleSize));
//        ps.obj[5].collision = false;
////

//
//        ps.obj[7] = new ChickenEntity(gp, ps);
//        ps.obj[7].setWorldX(30* gp.titleSize);
//        ps.obj[7].setWorldY(12* gp.titleSize);
//        //        gp.obj[7].collision = true;
//
//        ps.obj[8] = new ChickenEntity(gp, ps);
//        ps.obj[8].setWorldX(30* gp.titleSize);
//        ps.obj[8].setWorldY(11* gp.titleSize);
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
