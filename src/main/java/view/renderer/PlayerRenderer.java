package view.renderer;

import behavior.PlayerBehavior;
import controller.PlayerController;
import view.graphics.Sprite;
import view.graphics.SpriteAnimation;
import view.graphics.SpriteSheet;
import view.utils.ImageSplitter;

import java.awt.*;
import java.awt.image.BufferedImage;
import static states.GameStateManager.gp;

import java.util.ArrayList;
import java.util.List;

public class PlayerRenderer extends EntityRenderer {

    PlayerController controller;
    private final SpriteSheet<PlayerBehavior> spriteSheet;
    private final SpriteAnimation<Integer> ani;
    private BufferedImage image;
    private List<Sprite> attackEffectList;
    private SpriteAnimation<Integer> attackAni;
    private BufferedImage atkImg;

    public PlayerRenderer(PlayerController controller) {
        super(controller);

        // controller
        this.controller = controller;
        // ani
        this.spriteSheet = new SpriteSheet<>();
        this.ani = new SpriteAnimation<>();
        // setup bound
        this.controller.getBounds().setHeight(16);
        this.controller.getBounds().setWidth(24);
        this.controller.getBounds().setXOffset(12);
        this.controller.getBounds().setYOffset(48 - 16);
        // attack
        this.attackAni = new SpriteAnimation<>();
        this.attackEffectList = new ArrayList<>();
        // set image
        this.setImage();
    }

    private void setImage () {
        // ani
        String image = "/sprout-lands-sprites/characters/basic-charakter-spritesheet.png";
        System.out.println("Load image: " + image);
        ImageSplitter ci = new ImageSplitter(gp, image, 48, 48, 32);
        System.out.println( "\t>> col: " + ci.getColumns() + ", rows: " + ci.getRows());

        for(int i = 0; i < 2; i++) {
            spriteSheet.addSprite(PlayerBehavior.UP, ci.getSubImage(1, i));
                    spriteSheet.addSprite(PlayerBehavior.DOWN, ci.getSubImage(0, i));
                    spriteSheet.addSprite(PlayerBehavior.LEFT, ci.getSubImage(2, i));
                    spriteSheet.addSprite(PlayerBehavior.RIGHT, ci.getSubImage(3, i));
        }
        for(int i = 2; i < 4; i++) {
            spriteSheet.addSprite(PlayerBehavior.R_UP, ci.getSubImage(1, i));
            spriteSheet.addSprite(PlayerBehavior.R_DOWN, ci.getSubImage(0, i));
            spriteSheet.addSprite(PlayerBehavior.R_LEFT, ci.getSubImage(2, i));
            spriteSheet.addSprite(PlayerBehavior.R_RIGHT, ci.getSubImage(3, i));
        }

        String image2 = "/sprout-lands-sprites/characters/basic-charakter-actions.png";
        System.out.println("Load image: " + image2);
        ImageSplitter ci2 = new ImageSplitter(gp, image2, 48, 48, 0);
        System.out.println( "\t>> col: " + ci2.getColumns() + ", rows: " + ci2.getRows());

        for (int i = 0; i < ci2.getColumns(); i++) {
            spriteSheet.addSprite(PlayerBehavior.A_DOWN,    ci2.getSubImage(0, i, 144, 144));
            spriteSheet.addSprite(PlayerBehavior.A_UP,      ci2.getSubImage(1, i, 144, 144));
            spriteSheet.addSprite(PlayerBehavior.A_LEFT,    ci2.getSubImage(2, i, 144, 144));
            spriteSheet.addSprite(PlayerBehavior.A_RIGHT,   ci2.getSubImage(3, i, 144, 144));
            spriteSheet.addSprite(PlayerBehavior.B_DOWN,    ci2.getSubImage(4, i, 144, 144));
            spriteSheet.addSprite(PlayerBehavior.B_UP,      ci2.getSubImage(5, i, 144, 144));
            spriteSheet.addSprite(PlayerBehavior.B_LEFT,    ci2.getSubImage(6, i, 144, 144));
            spriteSheet.addSprite(PlayerBehavior.B_RIGHT,   ci2.getSubImage(7, i, 144, 144));
            spriteSheet.addSprite(PlayerBehavior.C_DOWN,    ci2.getSubImage(8, i, 144, 144));
            spriteSheet.addSprite(PlayerBehavior.C_UP,      ci2.getSubImage(9, i, 144, 144));
            spriteSheet.addSprite(PlayerBehavior.C_LEFT,    ci2.getSubImage(10, i, 144, 144));
            spriteSheet.addSprite(PlayerBehavior.C_RIGHT,   ci2.getSubImage(11, i, 144, 144));
        }

        this.ani.setAnimation(spriteSheet.getSpriteArray(PlayerBehavior.DOWN), 10);   // DEFAULT ANIMATION

        // attack ani
        String atkPath = "/ninja_adventure/FX/Elemental/Ice/SpriteSheet.png";
        System.out.println("Load image: " + atkPath);
        ImageSplitter is = new ImageSplitter(gp, atkPath, 32, 32, 0);
        System.out.println( "\t>> col: " + is.getColumns() + ", rows: " + is.getRows());
        for (int i = 0; i < is.getColumns(); i++) {
            attackEffectList.add(new Sprite(is.getSubImage(0, i)));
        }
        attackAni.setAnimation(attackEffectList, 2);
    }

    public void animate (PlayerBehavior behavior) {
            List<Sprite> spriteList = spriteSheet.getSpriteArray(behavior);
            if( spriteList != null )
                this.ani.setAnimation(spriteList, 20);
    }

    public void onBehaviorChange () {
        if (this.controller.behaviorMapper() ) {
            animate(this.controller.getBehavior());
        }
    }

    @Override
    public void update() {
        ani.update();
        image = ani.getImage().image;

        attackAni.update();
        atkImg = attackAni.getImage().image;


        this.onBehaviorChange();
    }

    @Override
    public void draw(Graphics2D g2) {
//        super.draw(g2);

        int size = (image.getHeight() - gp.titleSize) / 2;
        g2.drawImage(
                image,
                (int) this.controller.getPos().getScreenX() - size,
                (int) this.controller.getPos().getScreenY() - size,
                null
        );

        g2.drawRect(
                (int) (this.controller.getPos().getScreenX() + this.controller.getBounds().getXOffset()),
                (int) (this.controller.getPos().getScreenY() + this.controller.getBounds().getYOffset()),
                (int) this.controller.getBounds().getWidth(),
                (int) this.controller.getBounds().getHeight()
        );

        if (this.controller.isAttacking()) {
            if (ani.getFrame() == ani.getNumFrames() - 1) {
//                g2.setColor(Color.RED);
                // attack effect
                g2.drawImage(
                        atkImg,
                        (int) (this.controller.getAttackBounds().getPos().getScreenX() + this.controller.getAttackBounds().getXOffset() * 2),
                        (int) (this.controller.getAttackBounds().getPos().getScreenY() + this.controller.getAttackBounds().getYOffset() * 2),
                        null
                        );
            }
            // draw rect
//            g2.drawRect(
//                    (int) (this.controller.getAttackBounds().getPos().getScreenX() + this.controller.getAttackBounds().getXOffset()),
//                    (int) (this.controller.getAttackBounds().getPos().getScreenY() + this.controller.getAttackBounds().getYOffset()),
//                    (int) (this.controller.getAttackBounds().getWidth()),
//                    (int) (this.controller.getAttackBounds().getHeight())
//            );
            g2.setColor(Color.WHITE);
        }

        this.controller.getPathFinder().draw(g2);

    }
}
