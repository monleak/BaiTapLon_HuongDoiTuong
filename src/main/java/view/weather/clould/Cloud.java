package view.weather.clould;

import view.graphics.Sprite;
import view.math.Vector2f;
import view.weather.WeatherAnimation;

import java.awt.*;
import java.util.List;

public class Cloud extends WeatherAnimation {

    private int circle;
    private int cCounter;
    private int updateCounter;

    public Cloud(List<Sprite> spriteList, Vector2f pos, int aniDelay) {
        super(spriteList, pos, aniDelay);
        this.setup( (int) pos.getX(), (int) pos.getY(), aniDelay);
    }

    public void setup (int x, int y, int circle) {
        this.pos.setX(x);
        this.pos.setY(y);

        this.setup(circle);
    }

    public void setup (int circle) {
        this.circle = circle;   // chu ky
        this.cCounter = 0;
        this.isShown = false;
    }

    @Override
    public void start () {
        this.setup(this.circle);
        this.isShown = true;
    }

    public void update () {
        if (isShown) {
            if (updateCounter > 10) {
                if (cCounter < circle) {
                    super.update();
                    this.pos.addX(-1);
                    this.pos.addY(1);
                    cCounter++;
                } else {
                    isShown = false;
                }
                updateCounter = 0;
            } else {
                updateCounter++;
            }
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
