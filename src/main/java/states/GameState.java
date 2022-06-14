package states;

import view.main.Camera;
import view.main.KeyHandler;
import view.main.MouseHandler;

import java.awt.*;


/**
 * GameState
 * @brief Quản lý trạng thái của 1 màn hình trong game
 *
 */
public abstract class GameState {
    protected GameStateManager gsm;
    protected Camera camera;

    public GameState(GameStateManager gsm, Camera camera) {
        this.gsm = gsm;
        this.camera = camera;
    }

    public abstract void update(double time);
    public abstract void input(MouseHandler mouse, KeyHandler key);
    public abstract void draw(Graphics2D g);

    public void setup() {}

    @Override
    public String toString() {
        return "GameState{" +
                this.getClass().getName() +
                '}';
    }
}
