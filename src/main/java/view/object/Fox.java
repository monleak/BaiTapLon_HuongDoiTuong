package view.object;

import states.PlayState;
import view.main.GamePanel;
import view.utils.ConcatenatedImage;

import java.awt.*;

public class Fox extends  OBJ_Key{
    ConcatenatedImage ci;
    private int counter;

    public Fox (GamePanel gp, PlayState ps) {
        super(gp, ps);
//        this.gp = gp;
        this.name = "Fox";
        this.ci = new ConcatenatedImage(gp,
                "/fox-sprite-sheet.png",
                32, 32, 12, 12, 6, 12
        );
        this.collision = false;
    }

    @Override
    public void draw(Graphics2D g2) {
        int r = 1;
        int C_MAX = 13;

        this.counter++;
        if(counter == C_MAX * 10){
            counter = 0;
        }
        int c = counter / 10;

        image = ci.getSubImage(r, c);

        super.draw(g2, false);
    }
}
