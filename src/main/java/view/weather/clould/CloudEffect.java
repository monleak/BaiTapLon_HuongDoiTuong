package view.weather.clould;

import view.effect.IDrawable;
import view.graphics.Sprite;
import view.main.Camera;
import view.math.Vector2f;
import view.utils.ImageSplitter;
import view.weather.WeatherAnimation;
import view.weather.rain.RainDrop;
import view.weather.rain.RainGround;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static states.GameStateManager.gp;

public class CloudEffect implements IDrawable {
    private Camera camera;
    private final List<WeatherAnimation> weatherAniList;
    private final List<Sprite> spriteList;

    public CloudEffect(Camera camera) {
        this.camera = camera;

        this.weatherAniList = new ArrayList<>();
        this.spriteList = new ArrayList<>();

        this.setImage();

        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            int length = r.nextInt(100);
            int startX = r.nextInt(gp.worldWidth);
            int startY = r.nextInt(gp.worldHeight);
            this.weatherAniList.add(
                    new Cloud(
                            spriteList,
                            new Vector2f(startX, startY),
                            600
                    )
            );
        }
    }

    private void setImage () {
        String path = "/ninja_adventure/FX/Particle/Clouds.png";
        System.out.println("Load image: " + path);
        ImageSplitter is = new ImageSplitter(
                gp, path,
                80, 36,
                0, 0, 0, 0,
                240, 108
        );
        System.out.println( "\t>> col: " + is.getColumns() + ", rows: " + is.getRows());

        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
        ColorConvertOp op = new ColorConvertOp(cs, null);
        BufferedImage image = is.getSubImage(0, 0);
        image = op.filter(image, null);
        Sprite s = new Sprite(image);
        spriteList.add(s);
    }

    @Override
    public void draw(Graphics2D g2) {
        Random rand = new Random();
        for (WeatherAnimation r : weatherAniList) {
            if (rand.nextInt(100) < 1 && !r.isShown()) {
                r.start();
            }
            r.update();
            if (camera.getBounds().inside( (int) r.getPos().getX(), (int) r.getPos().getY())) {
                r.draw(g2);
            }
        }
    }
}
