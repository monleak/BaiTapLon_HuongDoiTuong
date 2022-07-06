package view.main;

import states.PlayState;
import view.entity.ChickenEntity;
import view.entity.FoxEntity;
import view.object.OBJ_Key;

/**
 * Dùng để tạo các con vật, vật thể trên bản đồ.
 * phương thức: setObject
 *
 * TODO: Khi kết hợp với model phải tạo class khác.
 */
public class AssetSetter
{
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

        ps.obj[1] = new FoxEntity(gp, ps);
        ps.obj[1].getBounds().getPos().x = ((int)(38f * gp.titleSize));
        ps.obj[1].getBounds().getPos().y = ((int)(14f * gp.titleSize));
        ps.obj[1].collision = false;
        FoxEntity f = (FoxEntity) ps.obj[1];
        f.follow(ps.player);

        ps.obj[2] = new ChickenEntity(gp, ps);
        ps.obj[2].getBounds().getPos().x = ((int)(38f * gp.titleSize));
        ps.obj[2].getBounds().getPos().y = ((int)(10f * gp.titleSize));
        ps.obj[2].collision = false;

        ps.obj[3] = new ChickenEntity(gp, ps);
        ps.obj[3].getBounds().getPos().x = ((int)(38f * gp.titleSize));
        ps.obj[3].getBounds().getPos().y = ((int)(10f * gp.titleSize));
        ps.obj[3].collision = false;

    }
}
