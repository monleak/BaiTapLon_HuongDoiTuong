package view.title.blocks;

import java.awt.Graphics2D;

import view.graphics.Sprite;
import view.math.AABB;
import view.math.Vector2f;

/**
 * Block: 1 ô 48x48 trên bản đồ
 *
 */
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
        g2.drawImage(img.image, (int) pos.getScreenX(), (int) pos.getScreenY(), w, h, null);
    }
}
