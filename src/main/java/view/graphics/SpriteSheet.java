package view.graphics;

import states.GameStateManager;
import view.utils.ImageSplitter;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

/**
 * 1 sheet các sprite
 * <p>
 * Cắt ảnh của nhân vật để tạo ra mảng 2 chiều
 * - Chiều thứ 1: chỉ hành động (VD: các ảnh nhân vật chạy tạo thành 1 mảng).
 * - Chiều thứ 2: các ảnh trong 1 hành động.
 * </p>
 *
 * <p>
 * Sprite sheet là gì?
 * Là tập hợp nhiều Sprite đơn lẻ thành 1 tập tin duy nhất,
 * giúp tăng tốc độ xử lý cho việc hiển thị hình ảnh lên màn hình.
 * Do chỉ hiển thị 1 phần của bức ảnh (load 1 lần lên bộ nhớ) sẽ tốt hơn nhiều với việc lấy nhiều bức ảnh và hiển thị chúng.
 * </p>
 */
public class SpriteSheet {

    private Sprite spritesArray[][];
    private Sprite spritesGrayArray[][];
    private int spritesIndexCounter[];
    private int arrLen;
    private String file;

    public SpriteSheet (int sttNum, int len) {
        spritesArray = new Sprite[sttNum][len];
        spritesGrayArray = new Sprite[sttNum][len];

        spritesIndexCounter = new int[sttNum];
        arrLen = len;

    }

    public SpriteSheet (String fileName, int w, int h) {
        ImageSplitter ci = new ImageSplitter(GameStateManager.gp, fileName, w, h, 0);

        spritesArray = new Sprite[ci.getRows()][ci.getColumns()];

        for (int i = 0; i < ci.getRows(); i++) {
            for (int j = 0; j < ci.getColumns(); j++) {
                spritesArray[i][j] = new Sprite(ci.getSubImage(i, j));
            }
        }
    }

    static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    public SpriteSheet addSprite(int sttNum, BufferedImage image) {
        try {
            int currentIdx = spritesIndexCounter[sttNum];
            spritesArray[sttNum][currentIdx] = new Sprite(image);
            BufferedImage image1 = deepCopy(image);
            spritesGrayArray[sttNum][currentIdx] = new Sprite(image1, true); // gray
            spritesIndexCounter[sttNum] += 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public Sprite[] getSpriteArray (int sttIdx) {
        return spritesArray[sttIdx];
    }
    public Sprite[] getGraySpriteArray (int sttIdx) {
        return spritesGrayArray[sttIdx];
    }
    public Sprite getSprite (int sttIdx, int idx) {
        return spritesArray[sttIdx][idx];
    }


}