package view.title.blocks;

import java.awt.Graphics2D;

import view.Graphics.Sprite;
import view.math.AABB;
import view.math.Vector2f;

public abstract class Block {
    protected int w;
    protected int h;

    public Sprite img;
    public Vector2f pos;

    public Block(Sprite img, Vector2f pos, int w, int h) {
        this.img = img;
        this.pos = pos;
        this.w = w;
        this.h = h;
//        System.out.println(pos);
    }

    public int getWidth() { return w; }
    public int getHeight() { return h; }

    public abstract boolean update(AABB p);
    public abstract boolean isInside(AABB p);

    public abstract Sprite getImage();
    public Vector2f getPos() { return pos; }

    @Override
    public String toString() {
        return "Block{" +
                "w=" + w +
                ", h=" + h +
                ", img=" + img +
                ", pos=" + pos +
                '}';
    }

    public void render(Graphics2D g2) {
        g2.drawImage(img.image, (int) pos.getWorldVar().x, (int) pos.getWorldVar().y, w, h, null);
//        g2.drawImage(img.image, 1, 1, 100, 199, null);
//        System.out.println("DrawImg" + (int) pos.getWorldVar().x + " " + (int) pos.getWorldVar().y + " " + w + " " + h);
//        System.out.println(pos.getWorldVar());
    }
}
