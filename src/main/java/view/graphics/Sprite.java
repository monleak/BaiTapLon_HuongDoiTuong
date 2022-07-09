package view.graphics;

import java.awt.image.BufferedImage;

/**
 * Hình của nhân vật (ng chơi, động vật).
 *
 * <p>
 * Các Sprite thường được kết hợp với nhau tạo thành 1 phần khung cảnh trong game,
 * là 1 đối tượng đồ họa 2D được vẽ lên màn hình,
 * có thể dịch chuyển nó thông qua cách đặt tọa độ trong hàm vẽ lên màn hình.
 * </p>
 *
 * <p>
 * Sprite là cách phổ biến để tạo ra các cảnh lớn và phức tạp và có thể thao tác trên mỗi Sprite riêng biệt.
 * Nhưng thực tế trong 1 game, thường có từ 10 đến hàng trăm Sprite,
 * việc tải riêng từng ảnh sẽ tiêu tốn năng suất tổ chức và chiếm nhiều bộ nhớ và mất nhiều thời gian để xử lý.
 * Để quản lý các Sprite và tránh sử dụng quá nhiều hình ảnh người ta tạo ra sprite sheet.
 * </p>
 */
public class Sprite {

    public BufferedImage image;

    private int w;
    private int h;

    public Sprite(BufferedImage image) {
        this.image = image;

        this.w = image.getWidth();
        this.h = image.getHeight();
    }

    public int getWidth() { return w; }
    public int getHeight() { return h; }

}


