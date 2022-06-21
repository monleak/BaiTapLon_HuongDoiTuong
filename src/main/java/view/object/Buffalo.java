package view.object;

import states.PlayState;
import view.main.GamePanel;
import view.utils.ImageSplitter;

import java.awt.*;

/**
 * @deprecated
 */
public class Buffalo extends OBJ_Key {
    ImageSplitter ci;
    private int counter;

    public Buffalo (GamePanel gp, PlayState ps) {
        super(gp, ps);
//        this.gp = gp;
        this.solidAreaDefaultX = 6;
        this.solidAreaDefaultY = 18;
        this.solidArea = new Rectangle(solidAreaDefaultX, solidAreaDefaultY, 36, 28);

        name = "Buffalo";
        ci = new ImageSplitter(gp,
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
        }
        this.image = ci.getSubImage(r, c);

    }
}
