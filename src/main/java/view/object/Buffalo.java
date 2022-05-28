package view.object;

import states.PlayState;
import view.main.GamePanel;
import view.utils.ConcatenatedImage;

import java.awt.*;

public class Buffalo extends OBJ_Key {
    ConcatenatedImage ci;
    private int counter;

    public Buffalo (GamePanel gp, PlayState ps) {
        super(gp, ps);
//        this.gp = gp;
        this.solidAreaDefaultX = 6;
        this.solidAreaDefaultY = 18;
        this.solidArea = new Rectangle(solidAreaDefaultX, solidAreaDefaultY, 36, 28);

        name = "Buffalo";
        ci = new ConcatenatedImage(gp,
                "/sprout-lands-sprites/characters/free-cow-sprites.png",
                32, 32, 0);
        this.image = ci.getSubImage(0, 0);
        collision = true;
    }

    @Override
    public void draw(Graphics2D g2) {
        int c = 0;
        int r = 0;
        int INTERVAL = 12;
        counter++;
        if(counter > INTERVAL * 5) {
            counter = 0;
        } else {
            if (counter < INTERVAL) {
                c = 0;
            }
            else if (counter < INTERVAL*2) {
                c = 1;
            }
            else if(counter < INTERVAL * 3) {
                c = 2;
            }
//            else if (counter < INTERVAL*4) {
//                r = 1; c = 0;
//            } else if (counter < INTERVAL*5) {
//                r = 1; c = 1;
//            }
        }
        this.image = ci.getSubImage(r, c);
        int screenX = getScreenX();
//                getWorldX() - gp.player.getWorldX() + gp.player.screenX + this.solidAreaDefaultX;
        int screenY = getScreenY();
//                getWorldY() - gp.player.getWorldY() + gp.player.screenY + this.solidAreaDefaultY;
        super.draw(g2, false);
//        Rectangle s = (Rectangle) this.solidArea.clone();
//        s.setLocation(screenX, screenY);
//        g2.draw(s);
    }
}
