package view.title.blocks;

import view.graphics.Sprite;
import view.math.AABB;
import view.math.Vector2f;

import java.awt.Graphics2D;




public class ObjBlock extends Block {
    
    public ObjBlock(Sprite img, Vector2f pos, int w, int h) {
        super(img, pos, w, h);
    }

    public boolean update(AABB p) {
        return true;
    }

    public Sprite getImage() {
        return img;
    }
    
    public boolean isInside(AABB p) {
        return false;
    }

    public void render(Graphics2D g2){
        super.render(g2);
//        g2.drawRect((int) this.pos.getWorldVar().x, (int) this.pos.getWorldVar().y, 100, 100);
    }

}
