package view.utils;

//import view.entity.ChickenEntity;
import view.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * <code>ImageSplitter</code>Dùng để cắt ảnh lớn thành mảng 2 chiều các ảnh nhỏ
 *
 * <p>
 * Example: {@@link ChickenEntity#setImage()}
 * </p>
 */
public class ImageSplitter {
    private BufferedImage image;
    private BufferedImage[][] subImageList;
    int rows, columns;
    GamePanel gp;
    Tool tool = new Tool();


    public ImageSplitter (
            GamePanel gp, String path,
            int subImageWidth, int subImageHeight,
            int pt, int pb, int pl, int pr,
            int scaleX, int scaleY
    ) {
        try {
            this.gp = gp;
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));
        } catch (NullPointerException | IOException e) {
            System.err.println("[ERR] Cannot load image: " + (path));
            e.printStackTrace();
        }

        rows = image.getHeight() / subImageHeight;
        columns = image.getWidth() / subImageWidth;

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
                        ,scaleX , scaleY
                );
            }
        }
    }

    public ImageSplitter(GamePanel gp, String path, int subImageWidth, int subImageHeight, int pt, int pb, int pl, int pr) {
        this( gp, path, subImageWidth, subImageHeight, pt, pb, pl, pr, gp.titleSize, gp.titleSize);
    }

    public ImageSplitter(GamePanel gp, String path, int subImageWidth, int subImageHeight, int padding) {
        this( gp, path, subImageWidth, subImageHeight, padding/2, padding, padding/2, padding, gp.titleSize, gp.titleSize);
    }

    // no scale
    public ImageSplitter (
        GamePanel gp, String path,
        int subImageWidth, int subImageHeight
    ) {
        try {
            this.gp = gp;
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));
        } catch (NullPointerException | IOException e) {
            System.err.println("[ERR] Cannot load image: " + (path));
            e.printStackTrace();
        }

        rows = image.getHeight() / subImageHeight;
        columns = image.getWidth() / subImageWidth;

        subImageList = new BufferedImage[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                BufferedImage subImage = image.getSubimage(
                        j * subImageWidth,
                        i * subImageHeight,
                        subImageWidth,
                        subImageHeight);
                subImageList[i][j] = subImage;
            }
        }
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
    public BufferedImage getSubImageByIndex (int idx) {
        try {
            int r = idx / columns;
            int c = idx % columns;

            if (r >= rows)
                throw new Error("r < rows :: " + r + " < " + rows +
                                "\n" + "idx=" + idx +
                                "\ncolumns=" + columns
                );
            if (c >= columns)
                throw new Error("r < rows" + r + " < " + rows);

            return subImageList[r][c];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<BufferedImage> getArrayList () {
        ArrayList<BufferedImage> imgs = new ArrayList<>();
        for (BufferedImage[] arr: subImageList) {
            for (BufferedImage img: arr) {
                imgs.add(img);
            }
        }
        return imgs;
    }

    public BufferedImage getSubImage (int row, int column, int width, int height) {
        BufferedImage image = subImageList[row][column];
        return tool.scaleImage(image, width, height);
//        this.scaleImage(image, width, height, new Color(0, 0, 0, 0));
//        return image;
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
