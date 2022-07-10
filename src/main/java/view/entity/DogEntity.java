package view.entity;
import model.Activities.*;
import model.Animals.Animal;
import model.Food;
import model.ModelState;
import states.PauseState;
import states.PlayState;
import view.ai.Node;
import view.ai.PathFinder;
import view.graphics.SpriteSheet;
import view.main.GamePanel;
import view.utils.ImageSplitter;
import view.utils.Direction;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class DogEntity extends AnimalEntity{
    private int counter;
    private int lifeCounter;
    private int actionLockCounter;
    private int directionLockCounter;
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

    private static int activity;

    private PathFinder pathFinder;

    /**
     * DOWN + STAND
     */

    public DogEntity (GamePanel gp, PlayState ps) {
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

    public DogEntity (GamePanel gp, PlayState ps, Animal animal) {
        this(gp, ps);
        this.animal = animal;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setImage() {
        String image = "/dog/Dog.png";
        System.out.println("Load Image: " + image);
        ImageSplitter ci = new ImageSplitter(gp, image, 90, 58, 0);
        System.out.println( "\t>> col: " + ci.getColumns() + ", rows: " + ci.getRows());

        BufferedImage[] imgs = new BufferedImage[36];
        BufferedImage[] flipImgs = new BufferedImage[36];


        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                imgs[i*4+j] = ci.getSubImage(i, j);
                flipImgs[i*4+j] = ci.getFlipSubImage(i, j);
            }
        }

        // Mảng gồm index của các ảnh trong 1 động tác.
        int[][] actIds = {
//                {5,6,7,8,9,10,5,6,7,8,9,10},   // STAND
//                {1,2,3,4,1,2,3,4,1,2,3,4},   // EAT
//                {20,21,22,16,17,18,19},    // SIT
//                {11,12,13,14,15,11,12,13,14,15}    // LEAP
                {4, 1},
                {2, 3}
        };

        sprite = new SpriteSheet(6, 6);
        // spritesheet
        for (int i = 0; i < actIds.length; i++) {
            int[] ids = actIds[i];
            for (int j : ids) {
//                this.sprite.addSprite(
//                        NOFLIP + i,
//                        imgs[j-1]
//                );
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
//            if (lifeCounter == 15 * 24 * 60) {
//                lifeCounter = 0;
//            }
//            if (this.animal != null)
//                this.animal.life(
//                        lifeCounter / (24 * 60),
//                        lifeCounter / (60) % 24,
//                        lifeCounter % 60
//                );
//            // NOTE: De counter xuat phat tu 0
//            lifeCounter++;
        }
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
                // FIXME:
//                setAnimation(
//                        FLIP,
//                        sprite.getSpriteArray(FLIP + posture),
//                        12
//                );
                break;
            case DOWN:
            case RIGHT:
                // FIXME:
//                setAnimation(
//                        NOFLIP,
//                        sprite.getSpriteArray(NOFLIP + posture),
//                        12
//                );
                break;
        }
    }

    public void update () {
//        setAction();
        super.update();

        if(activity != EAT) {
            checkCollisionAndMove(this.direction, this.getSpeed());
        }
        animate(true);

        // FIXME:
         image = ani.getImage().image;
        /**
         * Exception in thread "Thread-1" java.lang.NullPointerException at
         * view.entity.DogEntity.update(DogEntity.java:231) at
         * states.PlayState.update(PlayState.java:115) at
         * states.GameStateManager.update(GameStateManager.java:143) at
         * view.main.GamePanel.update(GamePanel.java:96) at view.main.GamePanel.run(GamePanel.java:79)
         * at java.lang.Thread.run(Thread.java:748)
         */

        pathFinder.search();
        if(pathFinder.getPathList().size() > 0 && activity == EAT) {
            Node next = pathFinder.getPathList().get(0);
            if (this.getPos().x > next.column * gp.titleSize) {
                this.getPos().x -= getSpeed();
            } else
            if (this.getPos().x < next.column * gp.titleSize) {
                this.getPos().x += getSpeed();
            } else
            if (this.getPos().y > next.row * gp.titleSize) {
                this.getPos().y -= getSpeed();
            } else
            if (this.getPos().y < next.row * gp.titleSize) {
                this.getPos().y += getSpeed();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void draw (Graphics2D g2) {
        super.draw(g2);

        pathFinder.draw(g2);
    }
}
