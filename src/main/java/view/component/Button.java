package view.component;

import states.GameStateManager;

import java.awt.*;

/**
 * Observer Pattern
 */
public abstract class Button {
    private String text;
    private boolean isActive;
    private int keyCode;
    private int x;
    private int y;
    private Font font;
    private int padding;

    public Button(int x, int y, String text, int keyCode) {
        this.text = text;
        this.keyCode = keyCode;
        this.x = x;
        this.y = y;
        this.padding = 0;

        GameStateManager.gp.getKeyH().addObserver(this);
    }

    public Button(int x, int y, String text, int keyCode, int padding) {
        this(x, y, text, keyCode);
        this.padding = padding;
    }

    public Button(int x, int y, String text, int keyCode, Font font) {
        this(x, y, text, keyCode);
        this.font = font;
    }

    public Button(int x, int y, String text, int keyCode, Font font, int padding) {
        this(x, y, text, keyCode, font);
        this.padding = padding;
    }

    public abstract void exec ();

    public void onPressed() {
        isActive = true;
    }
    public void onReleased() {
        isActive = false;
        exec();
    }

    @Override
    protected void finalize() throws Throwable {
        GameStateManager.gp.getKeyH().removeObserver(this);
        super.finalize();
    }

    public void handlePressed (int key) {
        if (key == keyCode) {
            this.onPressed();
        }
    }

    public void handleReleased (int key) {
        if (key == keyCode) {
            this.onReleased();
        }
    }

    public void draw (Graphics2D g2) {
        if (this.font != null)
            g2.setFont(this.font);
        FontMetrics metrics = g2.getFontMetrics();
        if (isActive) {
            g2.setColor(Color.WHITE);
            g2.fillRect(x - padding , y  - padding + metrics.getHeight() / 8, metrics.stringWidth(text) + padding * 2, metrics.getHeight() + padding * 2);
            g2.setColor(Color.BLACK);
            g2.drawString(text, x - 1, y - 1 + metrics.getHeight());
        } else {
            g2.setColor(Color.WHITE);
            g2.drawString(text, x, y + metrics.getHeight());
            g2.drawRect(x - padding, y - padding + metrics.getHeight() / 8, metrics.stringWidth(text) + padding * 2, metrics.getHeight() + padding * 2) ;
        }
    }
}
