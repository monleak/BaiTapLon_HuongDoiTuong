package view.entity;

import model.Animals.Animal;
import states.PlayState;
import view.graphics.SpriteSheet;
import view.main.GamePanel;
import view.utils.ImageSplitter;
import view.utils.Direction;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ChickenEntity extends AnimalEntity {
    private int counter;
    private int lifeCounter;
    private int actionLockCounter;
    // Có flip ảnh hay không
    public static final int NOFLIP = 0;
    public static final int FLIP = 4;
    // Tư thế
    public static final int STAND = 0;
    public static final int EAT = 1;
    public static final int SIT = 2;
    public static final int LEAP = 3;

    public Direction prevDirection;
    public int posture;


    /**
     * DOWN + STAND
     */

    public ChickenEntity (GamePanel gp, PlayState ps) {
        super(gp, ps);

        this.setSpeed(1);
        this.direction = Direction.DOWN;
        posture = STAND;

        setImage();
        setAnimation(
                FLIP,
                sprite.getSpriteArray(FLIP + posture),
                12
        );
    }

    public ChickenEntity (GamePanel gp, PlayState ps, Animal animal) {
        this(gp, ps);
        this.animal = animal;
        this.name = "Chicken";
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setImage() {
        System.out.println("Set Image: /chicken/chicken-sprite-sheet.png: " + sprite);
        ImageSplitter ci = new ImageSplitter(gp, "/chicken/chicken-sprite-sheet.png", 32, 32, 0);
        System.out.println( "col: " + ci.getColumns() + "rows: " + ci.getRows());

        BufferedImage[] imgs = new BufferedImage[16];
        BufferedImage[] flipImgs = new BufferedImage[16];


        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                imgs[i*4+j] = ci.getSubImage(i, j);
                flipImgs[i*4+j] = ci.getFlipSubImage(i, j);
            }
        }

        // Mảng gồm index của các ảnh trong 1 động tác.
        int[][] actIds = {
                {1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4},   // STAND
                {5, 6, 7, 8, 5, 6, 7, 8, 5, 6, 7, 8},   // EAT
                {9, 10, 11, 12, 12, 12, 12, 12, 12, 11, 10, 9 },    // SIT
                {13, 13, 14, 14, 15, 15, 16, 16, 15, 14, 13, 12, 13}    // LEAP
        };

        // spritesheet
        this.sprite = new SpriteSheet(8, 16);
        for (int i = 0; i < actIds.length; i++) {
            int[] ids = actIds[i];
            for (int j : ids) {
                this.sprite.addSprite(
                        NOFLIP + i,
                        imgs[j-1]
                );
                this.sprite.addSprite(
                        FLIP + i,
                        imgs[j-1]
                );
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAction () {
        // TODO: GENERIC
        if(counter == 0) {
            if (lifeCounter == 15 * 24 * 60) {
                lifeCounter = 0;
            }
            if (this.animal != null)
                this.animal.life(
                    lifeCounter / (24 * 60),
                    lifeCounter / (60) % 24,
                    lifeCounter % 60
                );
            // NOTE: De counter xuat phat tu 0
            lifeCounter++;
        }

        actionLockCounter++;
        if(actionLockCounter > 120) {
            Random random = new Random();
            int i = random.nextInt(4);
            switch (i) {
                case 1:
                    direction = Direction.UP;
                    break;
                case 2:
                    direction = Direction.DOWN;
                    break;
                case 3:
                    direction = Direction.RIGHT;
                    break;
                case 0:
                    direction = Direction.LEFT;
                    break;
            }
            actionLockCounter = 0;
        }

        // random tu the
        counter++;
        int circle = 10;
        int nState = 12;
        if(counter >= (circle * nState)) {
            counter = 0;
            Random random = new Random();
            int r = random.nextInt(4);
            if (r == 0) posture = EAT;
            else if (r == 1) posture = LEAP;
            else if (r == 2) posture = SIT;
            else posture = STAND;

            if(posture == EAT || posture == SIT) {
                setSpeed(0);
            } else {
                setSpeed(1);
            }
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void animate(boolean isRunning) {

        if (direction == prevDirection)
            return;
        prevDirection = direction;

        switch (direction) {
            case UP:
            case LEFT:
                setAnimation(
                        FLIP,
                        sprite.getSpriteArray(FLIP + posture),
                        12
                );
                break;
            case DOWN:
            case RIGHT:
                setAnimation(
                        NOFLIP,
                        sprite.getSpriteArray(NOFLIP + posture),
                        12
                );
                break;
        }
    }

    public void update (double time) {
        checkCollisionAndMove(this.direction, this.getSpeed());
        animate(true);
        image = ani.getImage().image;
    }

    /**
     * {@inheritDoc}
     */
    public void draw (Graphics2D g2) {
        update (0);
        super.draw(g2);
    }
}
