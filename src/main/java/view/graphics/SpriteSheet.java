package view.graphics;

import org.jetbrains.annotations.NotNull;
import states.GameStateManager;
import view.utils.ImageSplitter;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class SpriteSheet<T> {

    private final Map<T, List<Sprite>> spritesArray;
    private final Map<T, Integer> spritesIndexCounter;

    public SpriteSheet () {
        spritesArray = new HashMap<>();
        spritesIndexCounter = new HashMap<>();
    }

    static @NotNull BufferedImage deepCopy(@NotNull BufferedImage bi ) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    public SpriteSheet addSprite( T key, BufferedImage image ) {
        try {
            List<Sprite> sprites;
            if (spritesArray.containsKey(key)) {
                sprites = spritesArray.get(key);
            } else {
                sprites = new ArrayList<>();
                spritesArray.put(key, sprites);
            }
            sprites.add(new Sprite(image));
            spritesIndexCounter.put(key, sprites.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public void spriteArrayLike (T from, T to) {
        this.setSpriteArray(to, this.getSpriteArray(from));

    }

    public void setSpriteArray (T key, List<Sprite> sprites) {
        this.spritesArray.put(key, sprites);
    }

    public List<Sprite> getSpriteArray (T key) {
        return spritesArray.getOrDefault(key, null);
    }

    @Override
    public String toString() {
        return "SpriteSheet{" +
                "spritesArray=" + spritesArray +
                ", spritesIndexCounter=" + spritesIndexCounter +
                '}';
    }
}