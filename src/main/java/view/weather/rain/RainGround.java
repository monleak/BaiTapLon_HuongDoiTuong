package view.weather.rain;

import view.graphics.Sprite;
import view.math.Vector2f;
import view.weather.WeatherAnimation;

import java.util.List;

public class RainGround extends WeatherAnimation {
    private int circle;
    private int cCircle;    // counter

    public RainGround(List<Sprite> spriteList, Vector2f pos, int duration) {
        super(spriteList, pos, duration /( spriteList.size() + 10));
        this.circle = duration / spriteList.size();
    }

    public void start () {
        this.cCircle = 0;
        this.isShown = true;
    }

    public void update () {
        if (isShown) {
            if (cCircle < circle) {
                super.update();
                cCircle++;
            } else {
                this.isShown = false;
            }
        }
    }

}
