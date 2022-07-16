package view.graphics;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Tạo các animation di chuyển cho nhân vật.
 *
 * <ul>
 * <li>
 * Dựa trên file sprite sheet, cho hiển thị từng sprite chuyển tiếp nhau 1 cách liên tục sẽ tạo ra cảm giác đối tượng như đang chuyển động.
 * </li>
 * <li>
 * Khi thao tác với sprite animation thường gặp thêm khái niệm Tile, 1 sprite sheet lưu 1 bức ảnh là 1 chuỗi các Tile, mỗi Tile hiển thị 1 khung hình của chuỗi chuyển động, như vậy Tile cũng giống với Sprite/Frame.
 * </li>
 * <li>
 * Không chỉ cần sprite sheet để hiển thị ảnh lên màn hình mà còn cần 1 file đi kèm để lưu thông tin x, y, width, height của các tile trong sprite sheet.
 * </li>
 * </ul>
 */
public class SpriteAnimation<T> {

    private List<Sprite> frames;
//    private final List<Integer> states;
    private int currentFrame;
    private int numFrames;

    private int count;
    private int delay;

    private int timesPlayed;

    public SpriteAnimation(List<Sprite> frames) {
        setFrames(frames);
        timesPlayed = 0;
//        states = new ArrayList<>();
    }

    public SpriteAnimation() {
        timesPlayed = 0;
//        states = new ArrayList<>();
        this.frames = new ArrayList<>();
    }

    public void setAnimation(List<Sprite> frames, int delay) {
        this.setFrames(frames);
        this.setDelay(delay);
    }

    public void setFrames(List<Sprite> frames) {
        if (frames == null) {
            throw new NullPointerException("SpriteAnimation#setFrames: List<Sprites> frames is null!");
        }
        this.frames = frames;
        currentFrame = 0;
        count = 0;
        timesPlayed = 0;
        delay = 2;
        this.numFrames = frames.size();
    }

    public void setDelay(int i) { delay = i; }
    public void setFrame(int i) { currentFrame = i; }
//    public void setNumFrames(int i, int state) { states[state] = i; }

    /**
     * gọi trong update của lớp chứa nó.
     */
    public void update() {
        if(delay == -1) return;

        count++;

        if(count == delay) {
            currentFrame++;
            count = 0;
        }
        if(currentFrame == numFrames || frames.contains(currentFrame)) {
            currentFrame = 0;
            timesPlayed++;
        }
    }

    public int getDelay() { return delay; }
    public int getFrame() { return currentFrame; }
    public int getNumFrames () {return numFrames;}
    public int getCount() { return count; }

    /**
     * Trả về ảnh hiện tại.
     * Ảnh sẽ thay đổi sau vài frame để tạo thành animation.
     */
    public Sprite getImage() {
//        System.out.println(this);
        if (frames.size() == 0)
            throw new ArrayStoreException("Sprite#frame is length = 0");
        return frames.get(currentFrame);
    }
    public boolean hasPlayedOnce() { return timesPlayed > 0; }
    public boolean hasPlayed(int i) { return timesPlayed == i; }

    @Override
    public String toString() {
        return "SpriteAnimation{" +
                "frames=" + frames +
//                ", states=" + states +
                ", currentFrame=" + currentFrame +
                ", numFrames=" + numFrames +
                ", count=" + count +
                ", delay=" + delay +
                ", timesPlayed=" + timesPlayed +
                '}';
    }
}
