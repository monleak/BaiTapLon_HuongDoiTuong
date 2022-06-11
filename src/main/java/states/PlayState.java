package states;

import view.entity.Entity;
import view.entity.GameObject;
import view.entity.Player;
import view.main.*;
import view.title.TileManager;

import java.awt.*;

/**
 * PlayState
 * @brief trạng thái của màn hình chơi game.
 *
 *
 */
public class PlayState extends GameState {

    public TileManager tileM;               // draw ban do
    public UI ui;                           // hien thi text tren mh
    public CollisionChecker cChecker;       // check va cham
    public AssetSetter assetSetter;         // them cac con vat...
    Sound music = new Sound();              // nhac nen
    Sound se = new Sound();                 // am thanh khi cham vao con vat
    public GameObject[] obj = new GameObject[100];    // Danh sach

    public Player player;
    private int timeCount;

    public PlayState (GameStateManager gsm) {
        // init
        super(gsm);

        tileM       = new TileManager(GameStateManager.gp, this);
        ui          = new UI(GameStateManager.gp);
        cChecker    = new CollisionChecker(GameStateManager.gp, this);
        assetSetter = new AssetSetter(GameStateManager.gp, this);

        player      = new Player(GameStateManager.gp, this);
        this.timeCount = 0;
//        now = LocalDate.now();
    }

    @Override
    public void setup () {
        assetSetter.setObject();
        playMusic(0);
    }

    @Override
    public void update(double time) {

        if(!gsm.isStateActive(GameStateManager.PAUSE)) {
            player.update();

            for (int i = 0; i < obj.length; i++) {
                if(obj[i] instanceof Entity ) {
                    ((Entity) obj[i]).update();
                }
            }
        }

    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key) {
        if(!gsm.isStateActive(GameStateManager.PAUSE)){
            player.input(mouse, key);

            if (key.pPressed) {
                gsm.addAndpop(GameStateManager.PAUSE);
            }
        }
    }

    @Override
    public void draw(Graphics2D g2) {

        tileM.draw(g2);

        for (GameObject superObject : obj) {
            if (superObject != null) {
                superObject.draw(g2);
            } else {
                break;
            }
        }

        player.draw(g2);
        ui.draw(g2);
//        g2.setColor(new Color (163, 67, 67));
//        g2.drawRect(10, 20, 30, 400);

    }

    /**
     * Sound manager
     * @param i
     */
    public void playMusic (int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic () {
        music.stop();
    }
    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }
}
