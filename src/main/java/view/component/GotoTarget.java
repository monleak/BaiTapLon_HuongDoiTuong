package view.component;

import view.graphics.Sprite;
import view.main.KeyHandler;
import view.main.MouseHandler;
import view.math.Vector2f;
import view.utils.Tool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

import static states.GameStateManager.gp;

public class GotoTarget extends AniImgRenderer{
    private boolean isShown;

    public GotoTarget() {
        this.setImage ();
    }

    private void setImage () {
        String path1 = "/kenney_crosshair/crosshair011.png";
        String path2 = "/kenney_crosshair/crosshair009.png";
        String path3 = "/kenney_crosshair/crosshair007.png";
        String path4 = "/kenney_crosshair/crosshair012.png";
        System.out.println("Load image: " + path1);
        System.out.println("Load image: " + path2);
        System.out.println("Load image: " + path3);
        System.out.println("Load image: " + path4);

        try {
            Tool tool = new Tool();
            BufferedImage img1 = ImageIO.read(
                    Objects.requireNonNull(getClass().getResource(path1))
            );
            BufferedImage img2 = ImageIO.read(
                    Objects.requireNonNull(getClass().getResource(path2))
            );
            BufferedImage img3 = ImageIO.read(
                    Objects.requireNonNull(getClass().getResource(path3))
            );
            BufferedImage img4 = ImageIO.read(
                    Objects.requireNonNull(getClass().getResource(path4))
            );
            img1 = tool.scaleImage(img1, 48, 48);
            img2 = tool.scaleImage(img2, 48, 48);
            img3 = tool.scaleImage(img3, 48, 48);
            img4 = tool.scaleImage(img4, 48, 48);
            System.out.println(img1.getTransparency());
            spriteList.add(new Sprite(img1));
            spriteList.add(new Sprite(img2));
            spriteList.add(new Sprite(img3));
            spriteList.add(new Sprite(img4));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.ani.setAnimation(spriteList, 20);
    }

    public void input (KeyHandler keyHandler, MouseHandler mouseHandler) {
//        bounds.getPos().setX(
//                ((int) ((Vector2f.getWorldX() + mouseHandler.getX()) / gp.titleSize ) * gp.titleSize)
//        );
//        bounds.getPos().setY(
//                ((int) ((Vector2f.getWorldY() + mouseHandler.getY()) / gp.titleSize ) * gp.titleSize)
//        );
//
//        isShown = mouseHandler.isOnScreen();
    }

    public boolean isShown() {
        return isShown;
    }

    public void setShown(boolean shown) {
        isShown = shown;
    }

    public void draw(Graphics2D g2) {
//        AlphaComposite alcom = AlphaComposite.getInstance(
//                AlphaComposite.SRC_OVER, 0.5f);
//        g2.setComposite(alcom);
        if (isShown)
            super.draw(g2);
    }
}
