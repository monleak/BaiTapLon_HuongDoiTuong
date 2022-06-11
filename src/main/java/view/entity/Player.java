package view.entity;

import states.PlayState;
import view.Graphics.SpriteAnimation;
import view.Graphics.SpriteSheet;
import view.effect.FocusManager;
import view.main.*;
import view.utils.ConcatenatedImage;
import view.utils.Direction;

import java.awt.*;

public class Player extends Entity {

    public final int screenX, screenY;
    private final FocusManager focusManager;
//    private BufferedImage image = null;

    // instead of BufferedImage

    private Direction prevDirection;
    private boolean isRunning;

    public int UP = 3;
    public int DOWN = 2;
    public int LEFT = 1;
    public int RIGHT = 0;

    public int RUN_UP = 7;
    public int RUN_DOWN = 6;
    public int RUN_LEFT = 5;
    public int RUN_RIGHT = 4;

    public Player(GamePanel gp, PlayState ps) {
        super(gp, ps);

        this.focusManager   = new FocusManager(gp, ps);

        screenX = (gp.screenWidth - gp.titleSize)/2 ;
        screenY = (gp.screenHeight - gp.titleSize)/2;

        // collision
        solidArea   = new Rectangle();
        solidArea.x = 10;
        solidArea.y = 24;
        solidArea.height    = 24;
        solidArea.width     = 28;

        this.ps = ps;
        // object collision
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValue();
        setImage();
    }

    public void setDefaultValue () {
        setWorldX(gp.worldWidth / 2 - 2 * gp.titleSize);
        setWorldY(gp.worldHeight / 2 - 4 * gp.titleSize);
        setSpeed(4);
        direction = Direction.DOWN;
    }

    @Override
    public void setImage() {
        ConcatenatedImage ci = new ConcatenatedImage(gp,"/sprout-lands-sprites/characters/basic-charakter-spritesheet.png", 48, 48, 32);

        sprite = new SpriteSheet(8, 2);

        for(int i = 0; i < 2; i++) {
            sprite.addSprite(UP, ci.getSubImage(1, i))
                    .addSprite(DOWN, ci.getSubImage(0, i))
                    .addSprite(LEFT, ci.getSubImage(2, i))
                    .addSprite(RIGHT, ci.getSubImage(3, i));
        }
        for(int i = 2; i < 4; i++) {
            sprite.addSprite(RUN_UP, ci.getSubImage(1, i))
                    .addSprite(RUN_DOWN, ci.getSubImage(0, i))
                    .addSprite(RUN_LEFT, ci.getSubImage(2, i))
                    .addSprite(RUN_RIGHT, ci.getSubImage(3, i));
        }

        setAnimation(RIGHT, sprite.getSpriteArray(DOWN), 10);
    }

    public void animate(boolean isRunning) {
        if (prevDirection == direction && this.isRunning == isRunning) {
            return;
        } else {
            prevDirection = direction;
            this.isRunning = isRunning;
        }

        if (!isRunning)
        {
            if (direction == Direction.DOWN) {
                setAnimation(DOWN, sprite.getSpriteArray(DOWN), 30);
            }
            else if (direction == Direction.UP) {
                setAnimation(UP, sprite.getSpriteArray(UP), 30);
            }
            else if (direction == Direction.LEFT) {
                setAnimation(LEFT, sprite.getSpriteArray(LEFT), 30);
            }
            else if (direction == Direction.RIGHT) {
                setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 30);
            }
        } else {
            if (direction == Direction.DOWN) {
                setAnimation(RUN_DOWN, sprite.getSpriteArray(RUN_DOWN), 10);
            }
            else if (direction == Direction.UP) {
                setAnimation(RUN_UP, sprite.getSpriteArray(RUN_UP), 10);
            }
            else if (direction == Direction.LEFT) {
                setAnimation(RUN_LEFT, sprite.getSpriteArray(RUN_LEFT), 10);
            }
            else if (direction == Direction.RIGHT) {
                setAnimation(RUN_RIGHT, sprite.getSpriteArray(RUN_RIGHT), 10);
            }
        }

    }

    public void input(MouseHandler mouseH, KeyHandler keyH) {
        animate((keyH.rightPressed || keyH.upPressed || keyH.downPressed || keyH.leftPressed));

        if(keyH.upPressed) {
            direction = Direction.UP;
        }
        else if(keyH.downPressed) {
            direction = Direction.DOWN;
        }
        else if(keyH.leftPressed) {
            direction = Direction.LEFT;
        }
        else if(keyH.rightPressed) {
            direction = Direction.RIGHT;
        }

        // Check tile collision
        collisionOn = false;
        ps.cChecker.checkTile(this);
        // Check object collision
        int objIndex = ps.cChecker.checkObject(this, true);
        targetNewObject(objIndex);
        this.focusManager.checkAndHoverObject(objIndex);

        // if collision is false can move
        if (!collisionOn) {
            /**
             * TODO: handle multi direction ( press multi key )
             */
            if (keyH.upPressed)          setWorldY(getWorldY() - getSpeed());
            else if (keyH.downPressed)   setWorldY(getWorldY() + getSpeed());
            else if (keyH.rightPressed)  setWorldX(getWorldX() + getSpeed());
            else if (keyH.leftPressed)   setWorldX(getWorldX() - getSpeed());
        }

        // Handle focus
        if (keyH.enterPressed) {
            this.focusManager.checkAndFocusObject();
        }

        /**
         * NOTE: Player run animation
         */
//        if(keyH.rightPressed || keyH.upPressed || keyH.downPressed || keyH.leftPressed) {
//            spriteCounter++;
//            if(spriteCounter > 12) {
//                if(spriteNum == 1) {
//                    spriteNum = 2;
//                } else if (spriteNum == 2) {
//                    spriteNum = 1;
//                }
//                spriteCounter = 0;
//            }
//        } else {
//            spriteCounter++;
//            if(spriteCounter > 36) {
//                if(spriteNum == 1) {
//                    spriteNum = 2;
//                } else if (spriteNum == 2) {
//                    spriteNum = 1;
//                }
//                spriteCounter = 0;
//            }
//        }
    }

    /**
     * Handle when player collision with object
     * @param index: index of object
     */
    public void targetNewObject(int index) {
        if( index != 999) {
            String objName = ps.obj[index].name;
            // remove item
            // gp.obj[index] = null;
            if(this.focusManager.getHoveredObjId() == index && this.focusManager.isNewHovered()) {
                ps.playSE(2);
                ps.ui.showMessage("Hover: " + this.focusManager.getHoveredObjId());
                this.focusManager.setNewHovered(false);
            }
        }
    }

    public void draw (Graphics2D g2) {

        int x = screenX;
        int y = screenY;

        /**
         * NOTE: Stop moving camera at the edge
         */
        if (screenX > getWorldX()) {
            x = getWorldX();
        }
        if (screenY > getWorldY() ) {
            y = getWorldY();
        }
        int rightOffset = gp.screenWidth - ps.player.screenX;
        if (rightOffset > gp.worldWidth - ps.player.getWorldX()) {
            x = gp.screenWidth - (gp.worldWidth - getWorldX());
        }
        int bottomOffset = gp.screenHeight - ps.player.screenY;
        if (bottomOffset > gp.worldHeight - ps.player.getWorldY()) {
            y = gp.screenHeight - (gp.worldHeight - getWorldY());
        }

//        g2.drawImage(image, x, y, gp.titleSize, gp.titleSize, null);
        g2.drawImage(ani.getImage().image, x, y, gp.titleSize, gp.titleSize, null);

        if (this.focusManager.getFocusedObjId() != 999) {
            GameObject selectedAnimal = ps.obj[this.focusManager.getFocusedObjId()];
            if ( selectedAnimal instanceof  AnimalEntity) {
                ps.ui.showMessageList(
                        ((AnimalEntity) selectedAnimal).getAnimalStatus()
                );
            }
        }
        // TEST: draw character image frame
        // g2.drawRect(screenX + soliArea.x, screenY + soliArea.y, soliArea.width, soliArea.height);
    }
}
