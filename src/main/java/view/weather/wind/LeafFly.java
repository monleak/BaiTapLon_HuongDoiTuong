package view.weather.wind;

import view.graphics.Sprite;
import view.math.Vector2f;
import view.weather.WeatherAnimation;

import java.awt.*;
import java.util.List;

public class LeafFly extends WeatherAnimation {

    private Vector2f start;
    private int circle, cCounter;

    public LeafFly(List<Sprite> spriteList, Vector2f pos, int aniDelay) {
        super(spriteList, pos, aniDelay);
        this.setup(pos, aniDelay );
        this.ani.setAnimation(spriteList, 20);
    }

    public void setup (Vector2f start, int circle) {
        this.start = start;
        this.pos = new Vector2f(start.getX(), start.getY());
        this.circle = circle;   // chu ky
        this.cCounter = 0;
        this.isShown = false;
    }

    public void start () {
        this.setup(this.start, this.circle);
        this.isShown = true;
    }

    public void update () {
        if (isShown)
            if (cCounter < circle) {
                super.update();
                this.pos.addX(2);
                this.pos.addY(1);
                cCounter++;
            } else {
                isShown = false;
            }
    }

    @Override
    public void draw(Graphics2D g2) {
        if (1f * cCounter / circle > 0.8) {
            // NOTE: transparent image
            float alpha = 1f / 0.8f * (circle - cCounter) / circle ;
            AlphaComposite alcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, alpha);
            g2.setComposite(alcom);
        } else if (1f * cCounter / circle < 0.2) {
            // NOTE: transparent image
            float alpha = 1f / 0.2f * (cCounter) / circle ;
            AlphaComposite alcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, alpha);
            g2.setComposite(alcom);
        }
        super.draw(g2);

        AlphaComposite alcom = AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, 1);
        g2.setComposite(alcom);
    }
}
