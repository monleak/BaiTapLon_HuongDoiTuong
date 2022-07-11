package view.component;

import java.awt.*;

public abstract class MyButton {
    protected String text;
    protected boolean isActive;
    protected int x;
    protected int y;
    protected Font font;
    protected int paddingTop, paddingBottom, paddingLeft, paddingRight;

    public void onPressed() {
        isActive = true;
    }
    public void onReleased() {
        isActive = false;
        exec();
    }

    public abstract void exec ();

    protected void drawActiveButton (Graphics2D g2, FontMetrics metrics) {
        g2.setColor(Color.WHITE);
        g2.fillRect(
                x - paddingLeft ,
                y  - paddingTop + metrics.getHeight() / 8,
                metrics.stringWidth(text) + paddingRight * 2,
                metrics.getHeight() + paddingBottom * 2
        );
        g2.setColor(Color.BLACK);
        g2.drawString(text, x - 1, y - 1 + metrics.getHeight());
    }

    protected void drawInactiveButton (Graphics2D g2, FontMetrics metrics) {
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y + metrics.getHeight());
        g2.drawRect(
                x - paddingLeft,
                y - paddingTop + metrics.getHeight() / 8,
                metrics.stringWidth(text) + paddingRight + paddingLeft,
                metrics.getHeight() + paddingBottom + paddingTop
        );
    }

    public void draw (Graphics2D g2) {
        if (this.font != null)
            g2.setFont(this.font);
        FontMetrics metrics = g2.getFontMetrics();
        if (isActive) {
            drawActiveButton(g2, metrics);
        } else {
            drawInactiveButton(g2, metrics);
        }
    }

}
