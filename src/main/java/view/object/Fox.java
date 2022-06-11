package view.object;

import states.PlayState;
import view.Graphics.SpriteSheet;
import view.entity.AnimalEntity;
import view.main.GamePanel;
import view.utils.ConcatenatedImage;
import view.utils.Direction;

import java.awt.*;

public class Fox extends AnimalEntity {
    private int counter;

    public Fox (GamePanel gp, PlayState ps) {
        super(gp, ps);
//        this.gp = gp;
        this.name = "Fox";

        this.collision = false;
        this.direction = Direction.DOWN;

        // todo: move to AnimalEntity class
        setImage();
    }

    @Override
    public void setAction() {
        super.setAction();
    }

    @Override
    public void animate(boolean isRunning) {
//        setAnimation(0, sprite.getSpriteArray(0), 100);
    }

//    @Override
//    public void update() {
//        super.update();
//    }

    @Override
    public void setImage() {
        ConcatenatedImage ci = new ConcatenatedImage(gp,
                "/fox-sprite-sheet.png",
                32, 32, 12, 12, 6, 12
        );

        sprite = new SpriteSheet(7, 14);
        int[] framePerAction =  {5, 14, 8, 11, 5, 6, 7};
        int i = 0;
        for (int len : framePerAction) {
            for (int j = 0; j < len; j++) {
//                System.out.println("i = " + i + "j =  " + j);
                sprite.addSprite(i, ci.getSubImage(i, j));
            }
            i++;
        }
        setAnimation(4, sprite.getSpriteArray(4), 10);
    }

    @Override
    public String[] getAnimalStatus() {
        return new String[0];
    }

    @Override
    public void update () {
//        animate(true);
        super.update();
    }

    @Override
    public void draw(Graphics2D g2) {
//        int r = 1;
//        int C_MAX = 13;
//
//        this.counter++;
//        if(counter == C_MAX * 10){
//            counter = 0;
//        }
//        int c = counter / 10;
//        image = ci.getSubImage(r, c);
//        System.out.println(ani.getFrame());
        g2.drawImage(ani.getImage().image, getScreenX(), getScreenY(), gp.titleSize, gp.titleSize, null);
//        System.out.println(ani.getImage());
    }
}
