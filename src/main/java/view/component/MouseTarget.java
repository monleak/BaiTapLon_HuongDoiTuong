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

public class MouseTarget extends AniImgRenderer{
    private boolean isShown;

    public MouseTarget() {
        this.setImage ();
    }

    private void setImage () {
        String path1 = "/kenney_crosshair/crosshair025.png";
        String path2 = "/kenney_crosshair/crosshair026.png";
        System.out.println("Load image: " + path1);
        System.out.println("Load image: " + path2);

        try {
            Tool tool = new Tool();
            BufferedImage img1 = ImageIO.read(
                    Objects.requireNonNull(getClass().getResource(path1))
            );
            BufferedImage img2 = ImageIO.read(
                    Objects.requireNonNull(getClass().getResource(path2))
            );
            img1 = tool.scaleImage(img1, 48, 48);
            img2 = tool.scaleImage(img2, 48, 48);
            spriteList.add(new Sprite(img1));
            spriteList.add(new Sprite(img2));
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.ani.setAnimation(spriteList, 20);
    }

    public void input (KeyHandler keyHandler, MouseHandler mouseHandler) {
//        x = (int) Vector2f.getStaticWorldX(( (int) (Vector2f.getWorldX() + mouseHandler.getX()) / gp.titleSize ) * gp.titleSize );
//        y = (int) Vector2f.getStaticWorldY(( (int) (Vector2f.getWorldY() + mouseHandler.getY()) / gp.titleSize ) * gp.titleSize );

        bounds.getPos().setX(
                ((int) ((Vector2f.getWorldX() + mouseHandler.getX()) / gp.titleSize ) * gp.titleSize)
        );
        bounds.getPos().setY(
                ((int) ((Vector2f.getWorldY() + mouseHandler.getY()) / gp.titleSize ) * gp.titleSize)
        );

        isShown = mouseHandler.isOnScreen();

    }

    public void draw(Graphics2D g2) {
        if (isShown)
            super.draw(g2);
    }

}
