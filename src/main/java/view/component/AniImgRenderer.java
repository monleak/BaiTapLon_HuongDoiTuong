package view.component;

import view.graphics.Sprite;
import view.graphics.SpriteAnimation;
import view.math.AABB;
import view.math.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class AniImgRenderer {
    protected final AABB bounds;
    protected final List<Sprite> spriteList;
    protected final SpriteAnimation<Integer> ani;
    protected BufferedImage image;

    public AniImgRenderer() {
        this.bounds = new AABB(new Vector2f(0, 0), 48, 48);
        this.spriteList = new ArrayList<>();
        this.ani = new SpriteAnimation<>();
    }

    public AABB getBounds() {
        return bounds;
    }

    public void update() {
        ani.update();
        image = ani.getImage().image;
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(image,
                (int) bounds.getPos().getScreenX(),
                (int) bounds.getPos().getScreenY(),
                null
        );
    }
}
