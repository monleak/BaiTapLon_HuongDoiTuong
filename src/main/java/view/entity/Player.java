package view.entity;

import states.PlayState;
import view.effect.FocusManager;
import view.main.*;
import view.utils.ConcatenatedImage;
import view.utils.Direction;
import view.utils.Tool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {

    public final int screenX, screenY;
    private final FocusManager focusManager;
    private BufferedImage image = null;

    public Player(GamePanel gp, PlayState ps) {
        super(gp, ps);

        this.focusManager   = new FocusManager(gp, ps);

        up      = new BufferedImage[4];
        down    = new BufferedImage[4];
        right   = new BufferedImage[4];
        left    = new BufferedImage[4];

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
        getImage();
    }

    public void setDefaultValue () {
        setWorldX(gp.worldWidth / 2 - 2 * gp.titleSize);
        setWorldY(gp.worldHeight / 2 - 4 * gp.titleSize);
        setSpeed(4);
        direction = Direction.DOWN;
    }

    @Override
    public void getImage() {
        ConcatenatedImage ci = new ConcatenatedImage(gp,"/sprout-lands-sprites/characters/basic-charakter-spritesheet.png", 48, 48, 32);
        for(int i = 0; i < 4; i++) {
            up[i]       = ci.getSubImage(1, i);
            down[i]     = ci.getSubImage(0, i);
            left[i]     = ci.getSubImage(2, i);
            right[i]    = ci.getSubImage(3, i);
        }
    }

    public BufferedImage setup(String imageName) {
        BufferedImage image = null;
        Tool tool = new Tool();
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/walking/" + imageName +  ".png")));
            image = tool.scaleImage(image, gp.titleSize, gp.titleSize);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void input(MouseHandler mouseH, KeyHandler keyH) {
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
        if(keyH.rightPressed || keyH.upPressed || keyH.downPressed || keyH.leftPressed) {
            spriteCounter++;
            if(spriteCounter > 12) {
                if(spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        } else {
            spriteCounter++;
            if(spriteCounter > 36) {
                if(spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

        switch (direction) {
            case UP:
                directionClick(keyH, up);
                break;
            case DOWN:
                directionClick(keyH, down);
                break;
            case LEFT:
                directionClick(keyH, left);
                break;
            case RIGHT:
                directionClick(keyH, right);
                break;
        }
    }

    public void update(double time) {

    }

    private void directionClick(KeyHandler keyH, BufferedImage[] imgs) {
        if(!(keyH.rightPressed || keyH.upPressed || keyH.downPressed || keyH.leftPressed)) {
            if(spriteNum == 1 ) {
                image = imgs[0];
            } else {
                image = imgs[1];
            }
        } else {
            if(spriteNum == 1) {
                image = imgs[2];
            }
            if(spriteNum == 2) {
                image = imgs[3];
            }
        }
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

        g2.drawImage(image, x, y, gp.titleSize, gp.titleSize, null);

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
