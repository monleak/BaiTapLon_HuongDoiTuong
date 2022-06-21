package view.object;

import states.PlayState;
import view.main.GamePanel;
import view.utils.Direction;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class OBJ_Fence extends SuperObject{
    Direction direction;
    int num;
    public OBJ_Fence (GamePanel gp, PlayState ps, Direction direction, int num) {
        super(gp, ps);

        name = "Fence";
        try {
            this.direction = direction;
            this.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprout-lands-sprites/tilesets/fences.png")));
            this.image = this.tool.scaleImage(image, 192, 192);
            if(direction == Direction.LEFT) {
                this.image = this.image.getSubimage(12, 2, 24, 48);
                this.solidArea.setSize(24, num * 48);
            }
            else if (direction == Direction.UP) {
                this.image = this.image.getSubimage(81, 2, 48, 36);
                this.solidArea.setSize(num * 48, 36);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
        this.num = num;
    }

    public static BufferedImage rotate(BufferedImage image, double angle) {
        double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
        int w = image.getWidth(), h = image.getHeight();
        int neww = (int)Math.floor(w*cos+h*sin), newh = (int) Math.floor(h * cos + w * sin);
        GraphicsConfiguration gc = getDefaultConfiguration();
        BufferedImage result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
        Graphics2D g = result.createGraphics();
        g.translate((neww - w) / 2, (newh - h) / 2);
        g.rotate(angle, w / 2, h / 2);
        g.drawRenderedImage(image, null);
        g.dispose();
        return result;
    }
    private static GraphicsConfiguration getDefaultConfiguration() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        return gd.getDefaultConfiguration();
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
    }
}
