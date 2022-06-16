package view.graphics;

import states.GameStateManager;
import view.utils.ConcatenatedImage;

import java.awt.image.BufferedImage;

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
    private int spritesIndexCounter[];
    private int arrLen;
//    private int w;
//    private int h;
    private String file;

    public SpriteSheet (int sttNum, int len) {
        spritesArray = new Sprite[sttNum][len];
        spritesIndexCounter = new int[sttNum];
        arrLen = len;
    }

    public SpriteSheet (String fileName, int w, int h) {
        ConcatenatedImage ci = new ConcatenatedImage(GameStateManager.gp, fileName, w, h, 0);

        spritesArray = new Sprite[ci.getRows()][ci.getColumns()];

        for (int i = 0; i < ci.getRows(); i++) {
            for (int j = 0; j < ci.getColumns(); j++) {
                spritesArray[i][j] = new Sprite(ci.getSubImage(i, j));
            }
        }
    }

//    public BufferedImage loadSprite (String file) {
//        BufferedImage sprite = null;
//        try {
//            sprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(file)));
//        } catch (Exception e) {
//            System.out.println("ERROR: could not load file: " + file);
//        }
//        return sprite;
//    }
//
//    private void loadSpriteArray () {
//
//    }

    public SpriteSheet addSprite(int sttNum, BufferedImage image) {
        try {
            int currentIdx = spritesIndexCounter[sttNum];
            spritesArray[sttNum][currentIdx] = new Sprite(image);
            spritesIndexCounter[sttNum] += 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public Sprite[] getSpriteArray (int sttIdx) {
        return spritesArray[sttIdx];
    }
    public Sprite getSprite (int sttIdx, int idx) {
        return spritesArray[sttIdx][idx];
    }
}