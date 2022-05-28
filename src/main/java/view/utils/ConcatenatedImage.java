package view.utils;

import view.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ConcatenatedImage {
    private BufferedImage image;
    private BufferedImage[][] subImageList;
    int rows, columns;
    GamePanel gp;

    private void constructor (GamePanel gp, String path, int subImageWidth, int subImageHeight, int pt, int pb, int pl, int pr) {
        try {
            this.gp = gp;
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        rows = image.getHeight() / subImageHeight;
        columns = image.getWidth() / subImageWidth;

        Tool tool = new Tool();

        subImageList = new BufferedImage[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                BufferedImage subImage = image.getSubimage(
                        j * subImageWidth + pl,
                        i * subImageHeight + pt,
                        subImageWidth - pr,
                        subImageHeight - pb);
                subImageList[i][j] = tool.scaleImage(
                        subImage
                        ,gp.titleSize , gp.titleSize
//                        ,Color.BLACK
//                        new Color(0, 0, 0, 1)
                );
            }
        }
    }

    public ConcatenatedImage  (GamePanel gp, String path, int subImageWidth, int subImageHeight, int pt, int pb, int pl, int pr) {
        constructor( gp, path, subImageWidth, subImageHeight, pt, pb, pl, pr);
    }

    public ConcatenatedImage (GamePanel gp, String path, int subImageWidth, int subImageHeight, int padding) {
        constructor( gp, path, subImageWidth, subImageHeight, padding/2, padding, padding/2, padding);
    }

    public BufferedImage scaleImage(BufferedImage img, int width, int height,
                                    Color background) {
        int imgWidth = img.getWidth();
        int imgHeight = img.getHeight();
        if (imgWidth*height < imgHeight*width) {
            width = imgWidth*height/imgHeight;
        } else {
            height = imgHeight*width/imgWidth;
        }
        BufferedImage newImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = newImage.createGraphics();
        try {
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g.setBackground(background);
            g.clearRect(0, 0, width, height);
            g.drawImage(img, 0, 0, width, height, null);
        } finally {
            g.dispose();
        }
        return newImage;
    }


    public BufferedImage getImage() {
        return image;
    }

    public BufferedImage[][] getSubImageList() {
        return subImageList;
    }

    public BufferedImage getSubImage (int row, int column) {
        return subImageList[row][column];
    }
    public BufferedImage getSubImage (int row, int column, int width, int height) {
        BufferedImage image = subImageList[row][column];
//        gp.setOpaque(true);
//        gp.setBackground(new Color(0,0,0,0,))
        this.scaleImage(image, width, height, new Color(0, 0, 0, 0));
        return image;
    }

    public BufferedImage getFlipSubImage (int row, int column) {
        BufferedImage image = subImageList[row][column];

        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-image.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        image = op.filter(image, null);

        return image;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
}
