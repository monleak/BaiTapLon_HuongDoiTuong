package states;

import view.main.Camera;
import view.main.GamePanel;
import view.main.KeyHandler;
import view.main.MouseHandler;
import view.math.AABB;
import view.math.Vector2f;
import view.utils.Fontf;

import java.awt.*;

/**
 * Quản lý trạng thái của các màn hình.
 *
 */
public class GameStateManager {
    private final GameState[] states;

    /**
     * index của các màn hình
     *
     * NOTE: màn hình có index cao hơn sẽ render chồng lên màn hình trước.
     */
    public static final int MENU = 5;
    public static final int PLAY = 1;
    public static final int PAUSE = 2;
    public static final int GAME_OVER = 3;
    public static final int EDIT = 4;

    public static Font font;
    public static Fontf fontf;

    public static Graphics2D g2;
    public static GamePanel gp;
    public static Camera camera;
    public static Vector2f map;

    public GameStateManager (Graphics2D g2,GamePanel gp) {
        GameStateManager.g2 = g2;
        GameStateManager.gp = gp;
        GameStateManager.map = new Vector2f(100, 0);
        Vector2f.setWorldVar(100, 100);
        AABB gameAABB = new AABB(
                new Vector2f(-gp.titleSize, -gp.titleSize),
                gp.screenWidth + gp.titleSize * 2,
                gp.screenHeight + gp.titleSize * 2
        );
//        gameAABB.setXOffset(-gp.titleSize * 10);
//        gameAABB.setYOffset(gp.titleSize * 100);
        GameStateManager.camera = new Camera(
                gameAABB,
                gp.titleSize
        );

//        System.out.println(map.getWorldVar());

        font = new Font("font/font.png", 10, 10);
        fontf = new Fontf();
        fontf.loadFont("font/Stackedpixel.ttf", "MeatMadness");
        fontf.loadFont("font/GravityBold8.ttf", "GravityBold8");

        states = new GameState[6];
        // states[PLAY] = new PlayState(this);
        states[MENU] = new MenuState(this, camera);
    }

    /**
     * Setup game_object
     */
    public void setup() {
        if (isStateActive(PLAY))
            for (GameState state : states) {
                if (state != null) {
                    state.setup();
                }
            }
    }

    /**
     * Check xem có đang render state này ko
     */
    public boolean isStateActive(int state) {
        return states[state] != null;
    }

    public GameState getState(int state) {
        return states[state];
    }

    /**
     * Bỏ state
     */
    public void pop(int state) {
        states[state] = null;
    }

    /**
     * Thêm state
     */
    public void add(int state) {
        if (states[state] != null)
            return;

        if (state == PLAY) {
//            cam = new Camera(new AABB(new Vector2f(0, 0), GamePanel.width + 64, GamePanel.height + 64));
            states[PLAY] = new PlayState(this, camera);
            this.setup();
        }
        else if (state == MENU) {
            states[MENU] = new MenuState(this, camera);
        }
        else if (state == PAUSE) {
            states[PAUSE] = new PauseState(this, camera);
        }
        else if (state == GAME_OVER) {
//            states[GAMEOVER] = new GameOverState(this);
        }
        else if (state == EDIT) {
            if(states[PLAY] != null) {
//                states[EDIT] = new EditState(this, cam);
            }
        }
    }

    public void addAndpop(int state) {
        addAndpop(state, 0);
    }

    public void addAndpop(int state, int remove) {
        pop(remove);
        add(state);
    }

    /**
     * Gọi trong GamePane.update()
     */
    public void update(double time) {
        camera.update();
        for (int i = 0; i < states.length; i++) {
            if (states[i] != null) {
                states[i].update(time);
            }
        }
    }
    /**
     * Gọi trong GamePane.input(...)
     */
    public void input(MouseHandler mouse, KeyHandler key) {
        camera.input(mouse, key);
        for (int i = 0; i < states.length; i++) {
            if (states[i] != null) {
//                System.out.println(states[i]);
                states[i].input(mouse, key);
            }
        }
    }
    /**
     * Gọi trong GamePane.draw()
     */
    public void render(Graphics2D g2) {
//        g.setFont(GameStateManager.fontf.getFont("MeatMadness"));
        for (int i = 0; i < states.length; i++) {
            if (states[i] != null) {
                states[i].draw(g2);
            }
        }
//        states[PLAY].draw(g2);
    }

}
