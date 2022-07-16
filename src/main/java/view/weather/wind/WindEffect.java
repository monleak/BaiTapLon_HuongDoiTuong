package view.weather.wind;

import view.effect.IDrawable;
import view.graphics.Sprite;
import view.main.Camera;
import view.math.Vector2f;
import view.utils.ImageSplitter;
import view.weather.rain.RainDrop;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static states.GameStateManager.gp;

public class WindEffect implements IDrawable {
    private Camera camera;
    private final List<LeafFly> leafFlyList;
    private final List<Sprite> leafSprites;

    public WindEffect(Camera camera) {
        this.camera = camera;

        this.leafSprites = new ArrayList<>();
        this.leafFlyList = new ArrayList<>();

        this.setImage();

        Random r = new Random();
        for (int i = 0; i < 1000; i++) {
            int length = r.nextInt(100);
            int startX = r.nextInt(gp.worldWidth);
            int startY = r.nextInt(gp.worldHeight);

            this.leafFlyList.add(
                    new LeafFly(
                            leafSprites,
                            new Vector2f(startX, startY),
                            120
                    )
            );
        }
    }

    public void setImage() {
        String path = "/ninja_adventure/FX/Particle/Leaf.png";
        System.out.println("Load image: " + path);
        ImageSplitter is = new ImageSplitter(
                gp, path,
                12, 7,
                0, 0, 0, 0,
                24, 14

        );
        System.out.println("\t>> col: " + is.getColumns() + ", rows: " + is.getRows());
        for (int i = 0; i < is.getColumns(); i++) {
            Sprite s = new Sprite(is.getSubImage(0, i));
            leafSprites.add(s);
        }
    }

    public void draw(Graphics2D g2) {
        Random rand = new Random();
        for (LeafFly r : leafFlyList) {
            if (rand.nextInt(1000) < 1 && !r.isShown()) {
                r.start();
            }
            r.update();
            if (camera.getBounds().inside( (int) r.getPos().getX(), (int) r.getPos().getY())) {
                r.draw(g2);
            }
        }
    }
}