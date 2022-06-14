package view.utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Giá trị chạy từ để tạo animation.
 * - Bắt đầu: from
 * - Kết thúc: to
 * - Số khung hình: frames
 * - Trễ: delay
 *
 */
public class Animation {
    private float value;
    private int from;
    private int to;
    private int delay;
    private int delayCounter;
    private float updateStep;
    private int frames;
    private int frameStep;
    private int frameCounter;
    private boolean isActive;
    private boolean isDone;
    private boolean isLoop;
    private boolean isLoopReverse;
    private boolean isReverse;
    private int timesPlayed;
    private ActionListener actionListener;

    public Animation () {
        this.from = 0;
        this.value = this.from;
        this.to = 100;
        this.delay = 0;
        this.frames = 100;
        this.isActive = false;
        this.isDone = false;
        this.isLoop = false;
        this.delayCounter = 0;
        this.frameStep = 1;
        this.frameCounter = 0;
        this.updateStep = (to - from) * 1.0f / frames;
        this.isLoopReverse = false;
        this.isReverse = false;
        this.timesPlayed = 0;
    }

    // builder
    public Animation setForm(int from) {
        this.from = from;
        this.value = from;
        this.updateStep = (to - from) * 1.0f / frames;
        return this;
    }

    public Animation setTo (int to) {
        this.to = to;
        this.updateStep = (to - from) * 1.0f / frames;
        return this;
    }

    public Animation setDelay (int delayFrames) {
        this.delay = delayFrames;
        return this;
    }

    public Animation setNumFrames (int frames) {
        this.frames = frames;
        this.updateStep = (to - from) * 1.0f / frames;
        return this;
    }

    // getValue
    public int getValue() {
        return (int) value;
    }

    private void reset (boolean isStart, boolean isEnd) {
        if (isLoopReverse) {
            if(isStart)
                isReverse = false;
            if (isEnd)
                isReverse = true;
        } else {
            this.value = this.from;
//            this.isBack = !this.isBack;
        }
    }

    public void start() {
        if (!isDone) {
            isActive = true;
        }
    }

    public void loop () {
        isActive = true;
        isDone = false;
        isLoop = true;
        this.value = this.from;
    }

    public void loopReverse () {
        this.isLoopReverse = true;
        this.loop();
    }

    public Animation addActionListener (ActionListener actionListener) {
        this.actionListener = actionListener;
        return this;
    }

    private void afterAnim () {
        if (actionListener != null)
            actionListener.actionPerformed(null);
    }

    private void done() {
        isDone = true;
        isActive = false;
        afterAnim();
    }

    public void pause () {
        isActive = false;
    }

    private void updateValue () {
        if (!isActive)
            return;
        if (delayCounter != delay) {
            delayCounter++;
            return;
        }

        float newValue;
        int vfrom = from;
        int vto = to;
        if (this.from > this.to) {
            vto = from;
            vfrom = to;
        }
        if (isReverse) {
            newValue = Math.max(this.value - this.updateStep, vfrom);
        } else {
            newValue = Math.min(this.value + this.updateStep, vto);
        }
        this.value = newValue;

        boolean isStart = Math.round(this.value) == this.from;
        boolean isEnd = Math.round(this.value) == this.to;
        if (isStart || isEnd) {
            if (isLoop) {
                reset(isStart, isEnd);
            } else {
                done();
            }
        }
        if(isEnd) {
            timesPlayed++;
        }
    }

    public void update() {
        if (this.frameStep == this.frameCounter) {
            this.frameCounter = 1;
            updateValue();
        } else {
            this.frameCounter ++;
        }
    }

    @Override
    public String toString() {
        return "Animation{" +
                "value=" + value +
                ", from=" + from +
                ", to=" + to +
                ", delay=" + delay +
                ", updateStep=" + updateStep +
                ", frames=" + frames +
                ", frameStep=" + frameStep +
                ", frameCounter=" + frameCounter +
                ", isActive=" + isActive +
                ", isDone=" + isDone +
                ", isLoop=" + isLoop +
                ", isLoopReverse=" + isLoopReverse +
                ", isReverse=" + isReverse +
                ", timesPlayed=" + timesPlayed +
                ", actionListener=" + actionListener +
                '}';
    }

    // example
    public static void main (String[] args) {
        Animation a = new Animation()
                .setForm(255)
                .setTo(0)
                .addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Done!!!!!");
            }
        });
        a.start();
        for (int i = 0; i < 150; i++) {
            a.update();
            System.out.println(a);
        }
    }
}
