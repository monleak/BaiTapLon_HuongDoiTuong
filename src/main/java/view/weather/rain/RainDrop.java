package view.weather.rain;

import view.graphics.Sprite;
import view.math.Vector2f;
import view.weather.WeatherAnimation;

import java.util.List;

public class RainDrop extends WeatherAnimation {
    private Vector2f start;
    private Vector2f end;
    private int dx, dy, circle, cCounter;

    public RainDrop(List<Sprite> spriteList, Vector2f start, Vector2f end, int circle) {
        super(spriteList, new Vector2f(start.getX(), start.getY()), circle / 3);
        this.setup(start, end, circle);
        ani.setAnimation(spriteList, circle / 3);
    }

    public void setup (Vector2f start, Vector2f end, int circle) {
        this.start = start;
        this.end = end;
        this.pos = new Vector2f(start.getX(), start.getY());
        this.circle = circle;   // chu ky
//        this.dx = (int) ((end.getScreenX() - start.getScreenX()) / circle);
//        this.dy = (int) ((end.getScreenY() - start.getScreenY()) / circle);
        this.cCounter = 0;
        this.isShown = false;
    }

    @Override
    public void start () {
        this.setup(this.start, this.end, this.circle);
        this.isShown = true;
    }

    public void update () {
        if (isShown)
            if (cCounter < circle) {
                super.update();
                this.pos.addX(-2);
                this.pos.addY(5);
                cCounter++;
            } else {
                isShown = false;
            }
    }

}
