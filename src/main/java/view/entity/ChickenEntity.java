package view.entity;

import model.Activities.*;
import model.Animals.Animal;
import org.jetbrains.annotations.NotNull;
import states.PlayState;
import view.ai.PathFinder;
import view.graphics.SpriteSheet;
import view.main.GamePanel;
import view.utils.Direction;
import view.utils.ImageSplitter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ChickenEntity extends AnimalEntity {
    private int counter;
    private int lifeCounter;
    private int actionLockCounter;
    private int directionLockCounter;
    // Có flip ảnh hay không
    public static final int NOFLIP = 0;
    public static final int FLIP = 4;
    // TODO: 2. tăng flip lên 1 ( 4 + 1 = 5)

    // Tư thế
    public static final int STAND = 0;
    public static final int EAT = 1;
    public static final int SIT = 2;
    public static final int LEAP = 3;
    // TODO: 1. THÊM 1 TƯ THẾ Ở ĐÂY, CHO = 4

    public Direction prevDirection;
    public int posture;

    private static int activity;

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
        pathFinder = new PathFinder(gp, ps);
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
        this.sprite = new SpriteSheet(8, 16);
        String image = "/chicken/chicken-sprite-sheet.png";
        System.out.println("Load Image: " + image);
        ImageSplitter ci = new ImageSplitter(gp, image, 32, 32, 0);
        System.out.println( "\t>> col: " + ci.getColumns() + ", rows: " + ci.getRows());

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
                {1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4},   // STAND = 0
                {5, 6, 7, 8, 5, 6, 7, 8, 5, 6, 7, 8},   // EAT
                {9, 10, 11, 12, 12, 12, 12, 12, 12, 11, 10, 9 },    // SIT
                {13, 13, 14, 14, 15, 15, 16, 16, 15, 14, 13, 12, 13}    // LEAP
                // TODO: 3. Thêm index ảnh cho posture tương ứng ở đây
        };

        // spritesheet
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
        actionLockCounter++;
        if(actionLockCounter > 60*60*15 && !animal.isHungry() && !animal.isThirsty() && !animal.isSick()){
            Activity randomAct = animal.getActivity();
            if (randomAct instanceof EatActivity)
                activity = EAT;
            else if (randomAct instanceof DrinkActivity)
                activity = EAT;
            else if (randomAct instanceof PlayActivity)
                activity = STAND;
            else if (randomAct instanceof SleepActivity)
                activity = SIT;
            else activity = SIT;
        }
        directionLockCounter++;
        if(directionLockCounter > 120) {
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
            directionLockCounter = 0;
        }

        // random tu the
        counter++;
        int circle = 10;
        int nState = 12;
        if(counter >= (circle * nState)) {
            counter = 0;
            Random random = new Random();

            if (activity == EAT) posture = EAT;
            else if (activity == SIT) posture = SIT;
            else if (activity == STAND){
                //hoạt động play
                if(random.nextDouble()<0.5){
                    posture = STAND;
                }else {
                    posture = LEAP;
                }
            }

            if(posture == SIT) {
//                setSpeed(0);
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

        if (this.animal != null)
            if (this.animal.isDead()) {
                setAnimation(
                        FLIP,
                        sprite.getGraySpriteArray(
                                FLIP + posture
                        ),
                        12
                );
            }


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

    @Override
    public void update () {

        // NOTE: Map Animal.activity -> AnimalEntity.Activity
        if (animal != null)
            if (animal.getActivity() instanceof EatActivity
                    || animal.getActivity() instanceof DrinkActivity) {
                activity = EAT;
            } else if (animal.getActivity() instanceof PlayActivity) {
                activity = STAND;
            } else {
                activity = SIT;
            }

        // NOTE: Handle for each AnimalEntity.Activity
        if(activity != EAT){
            checkCollisionAndMove(this.direction, this.getSpeed());
            unfollow();
            if (activity == SIT) {
                goTo(ps.obj[0]); // go to object key

            }
        } else {
            goToFoodTray();
        }
        animate(true);
        image = ani.getImage().image;
        super.update();
    }

    /**
     * {@inheritDoc}
     */
    public void draw (@NotNull Graphics2D g2) {
        super.draw(g2);

        pathFinder.draw(g2);
    }
}
