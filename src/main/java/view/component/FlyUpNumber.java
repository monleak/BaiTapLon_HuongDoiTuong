package view.component;

import view.math.AABB;
import view.math.Vector2f;
import view.utils.Animation;

import java.awt.*;

public class FlyUpNumber {
    private String value;
    private Color color;
    private AABB bound;
    private Animation animation;
    private boolean isShown;
    private Font font;

    public FlyUpNumber(int value, Color color, AABB bound) {
        this.value = String.valueOf(value);
        this.color = color;
        this.bound = bound;
        this.font = new Font("MeatMadness", Font.PLAIN, 24);

        this.isShown = false;

        this.animation = new Animation()
                .setDelay(100)
                .setFrom(0)
                .setTo(50)
                .setNumFrames(30)
                .addActionListener((actionEvent -> {
                    this.animation.resetDefault();
                    this.isShown = false;
                }));
    }

    public FlyUpNumber setValue (int value) {
        this.value = String.valueOf(value);
        return this;
    }

    public FlyUpNumber setValue (String value) {
        this.value = value;
        return this;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void dispatch () {
        this.isShown = true;
        this.animation.resetDefault();
        this.animation.start();
    }

    public void update () {
        this.animation.update();
    }

    public void draw (Graphics2D g2) {
        if (this.isShown) {

            g2.setFont(this.font);
            FontMetrics fontMetrics = g2.getFontMetrics();

            g2.setColor(color);
            g2.drawString(
                    this.value,
                    (int) Vector2f.getStaticWorldX(bound.getCenterX()) - fontMetrics.stringWidth(this.value) / 2,
                    (int) Vector2f.getStaticWorldY(bound.getCenterY()) - this.animation.getValue()
            );
        }

    }
}
