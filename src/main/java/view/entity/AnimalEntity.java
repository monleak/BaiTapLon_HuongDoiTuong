package view.entity;

import model.Animals.Animal;
import states.PlayState;
import view.effect.FocusableHandler;
import view.effect.IFocusable;
import view.graphics.SpriteSheet;
import view.main.GamePanel;
import view.title.TileCollision;

import java.awt.*;
import java.awt.image.BufferedImage;

enum Posture {
    STAND, SIT, EAT, LEAP
}

public abstract class AnimalEntity extends Entity implements IFocusable {

    protected Animal animal;
    protected TileCollision tc;
    public FocusableHandler fch;
    public Posture posture;
    public AnimalEntity(GamePanel gp, PlayState ps) {
        super(gp, ps);

        tc = new TileCollision(this);
        fch = new FocusableHandler();

//        setImage();
    }

    public Animal getAnimal() {
        return animal;
    }

    @Override
    public void setHovered(boolean hovered) {
        this.fch.setHovered(hovered);
    }
    @Override
    public void setFocused(boolean focused) {
        this.fch.setFocused(focused);
    }
    @Override
    public boolean getIsHovered() {
        return this.fch.getIsHovered();
    }
    @Override
    public boolean getIsFocused() {
        return this.fch.getIsFocused();
    }

    public abstract String[] getAnimalStatus ();

    /**
     * {@inheritDoc}
     *
     * AnimalEntity.update:
     * <ul>
     *     <li>
     *         Set action
     *     </li>
     *     <li>
     *         Check collision tiles
     *     </li>
     * </ul>
     */
    @Override
    public void update() {
        {
            super.update();
            setAction();

            collisionOn = false;
            ps.cChecker.checkTile(this);

//            // action
//            spriteCounter++;
//            if(spriteCounter > 12) {
//                if(spriteNum == 1) {
//                    spriteNum = 2;
//                } else if (spriteNum == 2) {
//                    spriteNum = 1;
//                }
//                spriteCounter = 0;
//            }
        }
    }

//    public void drawCenteredString(Graphics g, int worldX, int worldY, String text, Rectangle rect, Font font) {
//        // Get the FontMetrics
//        FontMetrics metrics = g.getFontMetrics(font);
//        // Determine the X coordinate for the text
//        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
//        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
//        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
//        // Set the font
//        g.setFont(font);
//        // Draw the String
//        g.drawString(text, worldX + x, worldY + y);
//    }

    /**
     * {@inheritDoc}
     *
     * AnimalEntity.draw:
     * <ul>
     *     <li>
     *         Vẽ tam giác màu đỏ trên đầu con vật khi focus.
     *     </li>
     * </ul>
     */
    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);

        if(fch.getIsHovered()) {
            g2.drawRect((int) this.pos.getWorldVar().x, (int) this.pos.getWorldVar().y, gp.titleSize, gp.titleSize);
        }

        int screenX = (int) this.pos.getWorldVar().x;
        int screenY = (int) this.pos.getWorldVar().y;
        if(this.fch.getIsFocused() || this.fch.getIsHovered()) {
//                g2.setColor(Color.WHITE);
//                drawCenteredString(g2, screenX , screenY - 24,name, new Rectangle(gp.titleSize, 14), new Font("Monaco", Font.PLAIN, 12));
        }
        if(this.fch.getIsFocused()) {
            g2.setColor(Color.BLACK);
            g2.fillPolygon(new int[] {screenX + 14, screenX+ gp.titleSize/2, screenX + gp.titleSize - 14}, new int[] {screenY - 30, screenY - 22, screenY - 30}, 3);
            g2.setColor(Color.RED);
            g2.fillPolygon(new int[] {screenX + 16, screenX+ gp.titleSize/2, screenX + gp.titleSize - 16}, new int[] {screenY - 28, screenY - 24, screenY - 28}, 3);
            ps.ui.showMessage("Focus: " + this);
        }
    }
}
