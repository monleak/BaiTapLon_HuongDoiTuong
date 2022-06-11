package view.entity;

import model.Food;
import model.ModelState;
import states.PlayState;
import view.Graphics.SpriteSheet;
import view.effect.FocusableHandler;
import view.main.GamePanel;
import view.utils.ConcatenatedImage;
import view.utils.Direction;

import java.awt.*;
import java.util.Random;

public class ChickenEntity extends AnimalEntity{
    private int counter;
    private int lifeCounter;
    private int actionLockCounter;
    public int UP = 3;
    public int DOWN = 2;
    public int LEFT = 1;
    public int RIGHT = 0;

    public ChickenEntity (GamePanel gp, PlayState ps) {
        super(gp, ps);
        // TODO: HARD CODE
        ModelState gs = new ModelState(100);

        this.animal = gs.getAnimalList().get(0);

        this.setSpeed(1);
        this.direction = Direction.DOWN;
        posture = Posture.STAND;

        setImage();
//        this.collision = true;
    }

    public void setImage() {
        ConcatenatedImage ci = new ConcatenatedImage(gp, "/chicken/chicken-sprite-sheet.png", 32, 32, 0);

        sprite = new SpriteSheet(4, 16);
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                sprite.addSprite(UP, ci.getSubImage(i, j)) ;
                sprite.addSprite(LEFT, ci.getSubImage(i, j)) ;
                sprite.addSprite(DOWN, ci.getFlipSubImage(i, j)) ;
                sprite.addSprite(RIGHT, ci.getFlipSubImage(i, j)) ;
            }
        }
    }

    // test
    public String[] getAnimalStatus () {
        String name = "Chicken";
        int HP = animal.getHP();
        int age = animal.getAge();
        int calo = animal.getCalo();
        int sleep = animal.getSleep();
        int water =  animal.getWater();
        Food food = animal.getNeededFood().getFood();

        return (new String[] {
                "Name: " + name,
                "HP: " + HP,
                "Age: " + age,
                "Calo: " + calo,
                "Sleep: " + sleep,
                "Water: " + water,
                "Food: " + food
        });
    }

    @Override
    public void setAction () {
        // TODO: GENERIC
        if(counter == 0) {
            if (lifeCounter == 15 * 24 * 60) {
                lifeCounter = 0;
            }
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
//        System.out.println(direction);
    }

    @Override
    public void animate(boolean isRunning) {
        // todo: move animate logic here
    }

    public void update (double time) {
        counter++;
        int circle = 10;
        int nState = 12;
        int c = counter / (circle);
        if(counter >= (circle * nState)) {
            counter = 0;

            Random random = new Random();
            int r = random.nextInt(4);
            if (r == 0) posture = Posture.EAT;
            else if (r == 1) posture = Posture.LEAP;
            else if (r == 2) posture = Posture.SIT;
            else posture = Posture.STAND;

            if(posture == Posture.EAT || posture == Posture.SIT) {
                setSpeed(0);
            } else {
                setSpeed(1);
            }
        } else {

            int[] stand = {1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4};
            int[] eat = {5, 6, 7, 8, 5, 6, 7, 8, 5, 6, 7, 8};
            // lay
            int[] sit = {9, 10, 11, 12, 12, 12, 12, 12, 12, 11, 10, 9 };
            int[] leap = {13, 13, 14, 14, 15, 15, 16, 16, 15, 14, 13, 12, 13};

            int i = 0;
            switch (posture) {
                case SIT:
                    i = sit[c] - 1;
                    break;
                case EAT:
                    i = eat[c] - 1;
                    break;
                case STAND:
                    i = stand[c] - 1;
                    break;
                case LEAP:
                    i = leap[c] - 1;
                    break;
            }
            switch (direction) {
                case UP:
//                    image = sprite.getSprite(UP, i).image;
//                    this.pos.y -= this.getSpeed();
//                    break;
                case LEFT:
                    image = sprite.getSprite(UP, i).image;
//                    this.pos.x -= this.getSpeed();
                    break;
                case DOWN:
//                    image = sprite.getSprite(DOWN, i).image;
//                    this.pos.y += this.getSpeed();
//                    break;
                case RIGHT:
                    image = sprite.getSprite(DOWN, i).image;
//                    this.pos.x += this.getSpeed();
                    break;
            }

            if (direction == Direction.UP) {
                if (!tc.collisionTile(0, - getSpeed())) {
                    pos.addY(-getSpeed());
                    collisionOn = false;
                }
                else collisionOn = true;
            }
            else if (direction == Direction.DOWN) {
                if (!tc.collisionTile(0, getSpeed())) {
                    pos.addY(getSpeed());
                    collisionOn = false;
                }
                else collisionOn = true;
            }
            else if (direction == Direction.RIGHT) {
                if (!tc.collisionTile(getSpeed(), 0)) {
                    pos.addX(getSpeed());
                    collisionOn = false;
                }
                else collisionOn = true;
            }
            else if (direction == Direction.LEFT) {
                if (!tc.collisionTile(-getSpeed(), 0)) {
                    pos.addX(-getSpeed());
                    collisionOn = false;
                }
                else collisionOn = true;
            }

        }
    }

    public void draw (Graphics2D g2) {
        // todo
        update (0);
        super.draw(g2);
        g2.drawImage(image, (int) this.pos.getWorldVar().x, (int) this.pos.getWorldVar().y, null );
    }
}
