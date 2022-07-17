package view.weather.night;

import view.effect.IDrawable;
import view.main.Camera;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

import static states.GameStateManager.gp;

public class LightingEffect implements IDrawable {
    private Camera camera;
    BufferedImage darknessFilter;
    BufferedImage originalFilter;
    Color[] colors;
    float[] fraction;
    Area screenArea;
    Graphics2D darkG2;
    RadialGradientPaint gPaint;
    int circleSize;
    Area lightArea;
    Shape circle;
    public LightingEffect(Camera camera) {
        this.camera = camera;

        colors = new Color[5];
        fraction = new float[5];

        colors[0] = new Color(0, 0, 0, 0.1f);
        colors[1] = new Color(0, 0, 0, 0.2f);
        colors[2] = new Color(0, 0, 0, 0.4f);
        colors[3] = new Color(0, 0, 0, 0.6f);
        colors[4] = new Color(0, 0, 0, 0.8f);

        fraction[0] = 0f;
        fraction[1] = 0.25f;
        fraction[2] = 0.5f;
        fraction[3] = 0.75f;
        fraction[4] = 0.9f;

        int centerX = (int) camera.getFocusedEntity().getPos().getScreenX() + gp.titleSize / 2;
        int centerY = (int) camera.getFocusedEntity().getPos().getScreenY() + gp.titleSize / 2;

        this.originalFilter = new BufferedImage(
                gp.screenWidth, gp.screenHeight,
                BufferedImage.TYPE_INT_ARGB
        );
        this.darknessFilter = deepCopy(this.originalFilter);

        darkG2 = (Graphics2D) darknessFilter.getGraphics();

        screenArea = new Area(
                new Rectangle2D.Double(
                        0, 0, gp.screenWidth, gp.screenHeight
                )
        );

        this.circleSize = 500;

        gPaint = new RadialGradientPaint(
                centerX, centerY, circleSize / 2, fraction, colors
        );

        int x = centerX - circleSize / 2;
        int y = centerY - circleSize / 2;
        circle = new Ellipse2D.Double(
                x, y, circleSize, circleSize
        );

        lightArea = new Area(circle);


        createHole(centerX, centerY);

    }

    static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    public void createHole( int centerX, int centerY) {
//        screenArea.subtract(lightArea);
//
//        darkG2.setPaint(gPaint);
//
//        darkG2.fill(lightArea);
//
//        darkG2.fill(screenArea);
//        darkG2.dispose();
    }

    public void updateHole (int centerX, int centerY) {
        this.darknessFilter = deepCopy(originalFilter);
        darkG2 = (Graphics2D) darknessFilter.getGraphics();

//        screenArea.add(lightArea);

        int x = centerX - circleSize / 2;
        int y = centerY - circleSize / 2;
        circle.getBounds().x = x;
        circle.getBounds().y = y;
//        lightArea = new Area(circle);
        screenArea.subtract(lightArea);

//        gPaint.getCenterPoint().setLocation(centerX, centerY);
//        gPaint.getFocusPoint().setLocation(centerX, centerY);
        gPaint = new RadialGradientPaint(
                centerX, centerY, circleSize / 2, fraction, colors
        );
        darkG2.setPaint(gPaint);
//        darkG2.fill(lightArea);
        darkG2.fill(screenArea);
        darkG2.dispose();
    }

    @Override
    public void draw(Graphics2D g2) {
        int centerX = (int) camera.getFocusedEntity().getPos().getScreenX() + gp.titleSize / 2;
        int centerY = (int) camera.getFocusedEntity().getPos().getScreenY() + gp.titleSize / 2;

        updateHole(centerX, centerY);

        g2.drawImage(darknessFilter, 0, 0, null);
    }
}
