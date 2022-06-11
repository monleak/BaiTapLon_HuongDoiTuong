package view.title.blocks;

import view.Graphics.Sprite;
import view.math.AABB;
import view.math.Vector2f;

import java.awt.*;

public class CollisionBlock extends Block{

    private AABB collision;

    public CollisionBlock (Sprite img, Vector2f pos, int w, int h) {
        super(img, pos, w, h);
        this.collision = new AABB(pos, w, h);
    }

    public boolean update(AABB p) {
        return false;
    }

    public Sprite getImage() { return img; }

    public boolean isInside(AABB p) {
        return false;
    }

    public void render(Graphics2D g){
        super.render(g);
    }
}
