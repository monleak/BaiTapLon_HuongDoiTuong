package view.weather;

import view.graphics.Sprite;
import view.graphics.SpriteAnimation;
import view.math.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public abstract class WeatherAnimation {
    protected Vector2f pos;
    protected SpriteAnimation<Integer> ani;
    protected boolean isShown;
    protected List<Sprite> spriteList;
    protected BufferedImage image;

    public WeatherAnimation(List<Sprite> spriteList, Vector2f pos, int aniDelay ) {
        this.ani = new SpriteAnimation();
        this.spriteList = spriteList;
        this.pos = pos;
        this.isShown = false;

        ani.setAnimation(spriteList, aniDelay);
    }

    public Vector2f getPos() {
        return pos;
    }
    public boolean isShown() {
        return isShown;
    }

    public abstract void start ( );
    public void update () {
        ani.update();
        image = ani.getImage().image;
    }

    public void draw (Graphics2D g2) {
        if (isShown)
            g2.drawImage(
                    image,
                    (int) this.pos.getScreenX(),
                    (int) this.pos.getScreenY(),
                    null
            );
    }
}
