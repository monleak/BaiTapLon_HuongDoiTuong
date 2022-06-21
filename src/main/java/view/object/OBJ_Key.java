package view.object;

import states.PlayState;
import view.effect.FocusableHandler;
import view.effect.IFocusable;
import view.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Key extends SuperObject implements IFocusable {

    public FocusableHandler fch = new FocusableHandler();

    // IFocusManager
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

    public OBJ_Key (GamePanel gp, PlayState ps) {
        super (gp, ps);

        name = "Key";
        try {
            this.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/view/object/key.png")));
            this.image = this.tool.scaleImage(image, gp.titleSize, gp.titleSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }

    /**
     * Draw a String centered in the middle of a Rectangle.
     *
     * @param g The Graphics instance.
     * @param text The String to draw.
     * @param rect The Rectangle to center the text in.
     */
    public void drawCenteredString(Graphics g, int worldX, int worldY, String text, Rectangle rect, Font font) {
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        // Set the font
        g.setFont(font);
        // Draw the String
        g.drawString(text, worldX + x, worldY + y);
    }

    @Override
    public void draw(Graphics2D g2, boolean isFloatingEnabled) {
        super.draw(g2, isFloatingEnabled);
    }

    @Override
    public void draw(Graphics2D g2) {
        draw(g2, true);
    }
}
