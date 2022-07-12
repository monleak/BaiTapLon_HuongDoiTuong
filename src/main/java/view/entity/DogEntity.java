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

import static basic.Params.*;

public class DogEntity extends AnimalEntity{
    /*
    Chó có hành động canh nhà, đi chơi
    Khi đói nó sẽ đi ăn
    Khi buồn ngủ nó sẽ đi ngủ
     */
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
    //Hành động
    public static final int CanhNha = 0;
    public static final int DiChoi = 1;
    public static final int AnUong = 2;
    public static final int Ngu = 3;

    public Direction prevDirection;
    public int prevPosture;
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
        activity = DiChoi;

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
//<<<<<<< HEAD
//                {5,6,7,8,9,10,5,6,7,8,9,10},   // STAND
//                {1,2,3,4,1,2,3,4,1,2,3,4},   // EAT
//                {20,21,22,16,17,18,19},    // SIT
//                {11,12,13,14,15,11,12,13,14,15},    // LEAP
//                {4, 1},
//                {2, 3}
//=======
                {5,6,7,8},   // STAND
                {1,2,3,4},   // EAT
                {13,14,15,16},    // SIT
                {9,10,11,12}    // LEAP
        };

        sprite = new SpriteSheet(9, 9);
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
//        if(counter == 0) {
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
//        }
        actionLockCounter++;
        if(actionLockCounter > 60*60*15 && !animal.isHungry() && !animal.isThirsty() && !animal.isSick()){
            Activity randomAct = animal.getActivity();

            if (randomAct instanceof EatActivity)
            {
                activity = AnUong;
            }
            else if (randomAct instanceof DrinkActivity)
            {
                activity = AnUong;
            }
            else if (randomAct instanceof PlayActivity){
                if(rand.nextDouble()< 0.7){
                    activity = CanhNha;
                }else activity = DiChoi;
            }
            else if (randomAct instanceof SleepActivity)
            {
                activity = Ngu;
            }
            actionLockCounter=0;
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
        if(counter>=120) {
            counter = 0;
//            Random random = new Random();
//
//            if (activity == EAT) posture = STAND;
//            else if (activity == SIT) posture = SIT;
//            else if (activity == STAND){
//                //hoạt động play
//                if(random.nextDouble()<0.5){
//                    posture = STAND;
//                }else {
//                    posture = LEAP;
//                }
//            }
//
//            if(posture == SIT) {
//                setSpeed(0);
//            } else if(posture == LEAP) {
//                setSpeed(2);
//            }else setSpeed(1);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void animate(boolean isRunning) {

        if (direction == prevDirection && prevPosture == posture)
            return;
        prevDirection = direction;
        prevPosture = posture;

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
        super.update();

        setAction();
        if(activity == CanhNha) {
//                Canh cửa nhà
            pathFinder.setNodes(
                    (int) this.getBounds().getCenterX(),
                    (int) this.getBounds().getCenterY(),
                    HOME[0],
                    HOME[1]
            );
            pathFinder.search();
            if (pathFinder.getPathList().size() > 0) {
                posture = LEAP;
                setSpeed(2);
                Node next = pathFinder.getPathList().get(0);
                if (this.getPos().x > next.column * gp.titleSize) {
                    this.getPos().x -= getSpeed();
                } else if (this.getPos().x < next.column * gp.titleSize) {
                    this.getPos().x += getSpeed();
                } else if (this.getPos().y > next.row * gp.titleSize) {
                    this.getPos().y -= getSpeed();
                } else if (this.getPos().y < next.row * gp.titleSize) {
                    this.getPos().y += getSpeed();
                }else {
                    // remove node
                    pathFinder.getPathList().remove(0);
                }
            } else {
                posture = SIT;
                setSpeed(1);
            }
        }else if(activity == DiChoi){
            posture = STAND;
            setSpeed(1);
            checkCollisionAndMove(this.direction, this.getSpeed());
        }else if(activity == AnUong){
            //đi ăn
            pathFinder.setNodes(
                    (int) this.getPos().x,
                    (int) this.getPos().y,
                    HOME[0],
                    HOME[1]
            );
            pathFinder.search();
            if(pathFinder.getPathList().size() > 0) {
                posture = STAND;
                setSpeed(1);
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
                }else {
                    // remove node
                    pathFinder.getPathList().remove(0);
                }
            }else {
                posture = EAT;
                setSpeed(0);
            }
        }else if(activity == Ngu){
            posture = SIT;
            setSpeed(0);
        }else{
            posture = STAND;
            setSpeed(1);
            checkCollisionAndMove(this.direction, this.getSpeed());
        }
        animate(true);
        image = ani.getImage().image;
//>>>>>>> aa70c1823a720d27824f2b176f8656410207ff7f
    }

    /**
     * {@inheritDoc}
     */
    public void draw (Graphics2D g2) {
        super.draw(g2);

        pathFinder.draw(g2);
    }
}
