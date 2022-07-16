package view.weather.rain;

import view.effect.IDrawable;
import view.graphics.Sprite;
import view.main.Camera;
import view.math.Vector2f;
import view.utils.ImageSplitter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static states.GameStateManager.gp;

// TODO: INHERIT
public class RainEffect implements IDrawable {
    private Camera camera;
    private final List<RainDrop> rainDropList;
    private final List<Sprite> rainSprites;

    private List<RainGround> rainGroundList;
    private List<Sprite> rainGroundSprites;

    public RainEffect(Camera camera) {
        this.camera = camera;

        this.rainDropList = new ArrayList<>();
        this.rainSprites = new ArrayList<>();
        this.rainGroundSprites = new ArrayList<>();
        this.rainGroundList = new ArrayList<>();

        this.setImage();

        Random r = new Random();
        for (int i = 0; i < 1000; i++) {
            int length = r.nextInt(100);
            int startX = r.nextInt(gp.worldWidth);
            int startY = r.nextInt(gp.worldHeight);
            this.rainDropList.add(
                    new RainDrop(
                            rainSprites,
                            new Vector2f(startX, startY),
                            new Vector2f(startX + length, startY + length),
                            60
                    )
            );
            if (length < 30) {
                this.rainGroundList.add (
                        new RainGround(
                                rainGroundSprites,
                                new Vector2f(startX - 120, startY + 300),
                                240
                        )
                );
            }
        }
    }

    public void setImage () {
        //
        String path = "/ninja_adventure/FX/Particle/Rain.png";
        System.out.println("Load image: " + path);
        ImageSplitter is = new ImageSplitter(
                gp, path,
                8, 8,
                0, 0, 0, 0,
                24, 24

        );
        System.out.println( "\t>> col: " + is.getColumns() + ", rows: " + is.getRows());
        for (int i = 0; i < is.getColumns(); i++) {
            Sprite s = new Sprite(is.getSubImage(0, i));
            rainSprites.add(s);
//            rainDropList.add(new RainDrop(
//                    rainSprites,
//                    new Vector2f(0, 0),
//                    new Vector2f(200, 200),
//                    200
//                    ));
        }

        //
        String path2 = "/ninja_adventure/FX/Particle/RainOnFloor.png";
        System.out.println("Load image: " + path2);
        ImageSplitter is2 = new ImageSplitter(
                gp, path2,
                8, 8,
                0, 0, 0, 0,
                24, 24

        );
        System.out.println( "\t>> col: " + is2.getColumns() + ", rows: " + is2.getRows());
        for (int i = 0; i < is2.getColumns(); i++) {
            Sprite s = new Sprite(is2.getSubImage(0, i));
            rainGroundSprites.add(s);
//            rainGroundList.add(new RainGround(
//                    rainSprites,
//                    new Vector2f(0, 0),
//                    200
//            ));
        }
    }

    public void update() {

    }

    public void draw(Graphics2D g2) {

        Random rand = new Random();
        for (RainDrop r : rainDropList) {
            if (rand.nextInt(1000) < 1 && !r.isShown()) {
                r.start();
            }
            r.update();
            if (camera.getBounds().inside( (int) r.getPos().getX(), (int) r.getPos().getY())) {
                r.draw(g2);
            }
        }

        for (RainGround r : rainGroundList) {
            if (camera.getBounds().inside( (int) r.getPos().getX(), (int) r.getPos().getY())) {
                if (rand.nextInt(1000) < 1 && !r.isShown()) {
                    r.start();
                }
                r.update();
                r.draw(g2);
            }
        }


    }

}