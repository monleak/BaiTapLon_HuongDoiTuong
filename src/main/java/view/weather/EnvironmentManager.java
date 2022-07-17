package view.weather;

import model.TimeManager;
import states.GameStateManager;
import view.effect.IDrawable;
import view.main.Camera;
import view.weather.clould.CloudEffect;
import view.weather.rain.RainEffect;
import view.weather.wind.WindEffect;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnvironmentManager implements IDrawable {
    private TimeManager timeManager;
    private List<IDrawable> effectList;
    private final int MAX_DARK_OPACITY = 150;
    private IDrawable activatedEffect;
    private boolean mark = true;


    public EnvironmentManager(TimeManager timeManager, Camera camera) {
        this.timeManager = timeManager;
        this.effectList = new ArrayList<>();
        this.effectList.add(
                new WindEffect(camera)
        );
        this.effectList.add(
                new RainEffect(camera)
        );
        this.effectList.add(
                new CloudEffect(camera)
        );

        this.selectRandomEffect();
    }

    public void selectRandomEffect () {
        Random random = new Random();
        int r = random.nextInt(this.effectList.size() + 1);
        if (r != this.effectList.size()) {
            this.activatedEffect = effectList.get(r);
        }
//        this.activatedEffect = effectList.get(1);

    }

    @Override
    public void draw(Graphics2D g2) {
        if (this.timeManager.getHours() % 3 == 0) {
            if (mark) {
                selectRandomEffect();
                mark = false;
            }
        } else {
            mark = true;
        }

        if (this.activatedEffect != null)
            this.activatedEffect.draw(g2);

        // night effect
        int screenHeight = GameStateManager.gp.screenHeight;
        int screenWidth = GameStateManager.gp.screenWidth;
        int opacity = 0;
        if (timeManager.getHours() >= 21 || timeManager.getHours() < 5) {
            opacity = MAX_DARK_OPACITY;
        }

        if (timeManager.getHours() >= 5 && timeManager.getHours() < 8) {
            int offset = (timeManager.getHours() - 5 ) * 60 + timeManager.getMinutes();
            int max = 3 * 60;
            opacity = (int) ((1 - 1.0f * offset / max) * MAX_DARK_OPACITY) ;
        } else if (timeManager.getHours() >= 18 && timeManager.getHours() < 21) {
            int offset = (timeManager.getHours() - 18 ) * 60 + timeManager.getMinutes();
            int max = 3 * 60;
            opacity = (int) (1.0f * offset / max * MAX_DARK_OPACITY) ;
        }

        // mau toi khi troi dem
        g2.setColor(new Color(3, 3, 3, opacity));
        g2.fillRect(0, 0, screenWidth, screenHeight);
        g2.setColor(Color.WHITE);

    }
}
