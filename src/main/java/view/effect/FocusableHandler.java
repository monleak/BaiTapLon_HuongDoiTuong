package view.effect;

import model.Animals.Animal;
import states.PlayState;
import view.entity.AnimalEntity;
import view.entity.GameObject;
import view.math.AABB;
import view.math.Vector2f;

import java.awt.*;

public class FocusableHandler implements IFocusable {
    private boolean isHovered;
    private boolean isFocused;
    private Font font;
    private PlayState ps;
    private GameObject go;

    public FocusableHandler (PlayState ps, GameObject go) {
        this.ps = ps;
        this.go = go;
        this.font = new Font("serif", Font.BOLD, 16);
    }

    public boolean getIsHovered() {
        return isHovered;
    }

    public void setHovered(boolean hovered) {
        isHovered = hovered;
    }

    public boolean getIsFocused() {
        return isFocused;
    }

    public void setFocused(boolean focused) {
        // show msg
        if (focused) {
            if (go instanceof AnimalEntity)
                ps.ui.getAnimalStatusUI().showAnimalStatus( ((AnimalEntity) go).getAnimal() );

            ps.ui.getMessageUI().showMessage("Focused: " + go);
        } else {
            ps.ui.getAnimalStatusUI().hideAnimalStatus();
            ps.ui.getMessageUI().showMessage("Unfocused: " + go);
        }
        isFocused = focused;
    }

    /**
     * Draw a String centered in the middle of a Rectangle.
     *
     * @param g The Graphics instance.
     * @param text The String to draw.
     */
    private void drawCenteredString(Graphics2D g, AABB bounds, String text, Font font) {
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        if (metrics != null) {
            // Determine the X coordinate for the text
            int x = (int) bounds.getPos().getWorldVar().x + ( (int) bounds.getWidth() - metrics.stringWidth(text)) / 2;
            // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
            int y = (int) bounds.getPos().getWorldVar().y - metrics.getHeight() + metrics.getAscent() - 9;
            // Set the font
            g.setFont(font);
            // Draw the String
            g.drawString(text, x, y);
        }
    }

    /**
     * FocusableHandler.draw:
     * Vẽ hình tam giác màu đỏ và tên object khi focus.
     */
    public void draw(Graphics2D g2, AABB bounds, String name) {

        int screenX = (int) bounds.getPos().getWorldVar().x;
        int screenY = (int) bounds.getPos().getWorldVar().y - 9;

        if(this.isHovered) {
            g2.drawRect( screenX, screenY, (int) bounds.getWidth(), (int) bounds.getWidth());
        }

        if(isFocused) {
            g2.setColor(Color.BLACK);
            g2.fillPolygon(new int[] {screenX + 14, screenX + (int) bounds.getWidth()/2, screenX + (int) bounds.getWidth() - 14}, new int[] {screenY - 30, screenY - 22, screenY - 30}, 3);
            g2.setColor(Color.RED);
            g2.fillPolygon(new int[] {screenX + 16, screenX+ (int) bounds.getWidth()/2, screenX + (int) bounds.getWidth() - 16}, new int[] {screenY - 28, screenY - 24, screenY - 28}, 3);

            drawCenteredString(g2, bounds, name, font);
        }
    }

    public void draw(Graphics2D g2, AnimalEntity animalEntity) {

        this.draw(g2, animalEntity.getBounds(), animalEntity.name);

        // draw thanh HP, food, water tren dau con vat!
        Vector2f pos = animalEntity.getBounds().getPos();
        AABB bounds = animalEntity.getBounds();
        int barScreenX = (int) pos.getWorldVar().x;
        int barWidth = (int) bounds.getWidth();
        int barHeight = 9;
        int barScreenY = (int) pos.getWorldVar().y - barHeight;
        g2.setColor(Color.WHITE);
        g2.fillRect(
                barScreenX - 1,
                barScreenY - 1,
                barWidth + 2,
                barHeight + 2
        );

        System.out.println(animalEntity.getAnimal().getHP() / animalEntity.getAnimal().getMaxHP());
        if (animalEntity.getAnimal() != null) {
            g2.setColor(Color.RED);
            // remove calculate
            g2.fillRect(barScreenX, barScreenY,
                    barWidth * (animalEntity.getAnimal().getHP() / animalEntity.getAnimal().getMaxHP()),
                    barHeight / 3
            );
            g2.setColor(Color.GREEN);
            g2.fillRect(barScreenX,
                    barScreenY + barHeight / 3,
                    barWidth * (animalEntity.getAnimal().getCalo() / animalEntity.getAnimal().getMaxCalo()),
                    barHeight / 3
            );
            g2.setColor(Color.BLUE);
            g2.fillRect(barScreenX,
                    barScreenY + 2 * barHeight / 3,
                    barWidth * (animalEntity.getAnimal().getCalo() / animalEntity.getAnimal().getMaxCalo()),
                    barHeight / 3
            );
        }
        g2.setColor(Color.WHITE);
    }

}
